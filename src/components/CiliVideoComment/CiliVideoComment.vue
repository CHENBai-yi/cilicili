<template>

  <div class="inline-block">
    <transition appear
                enter-active-class="animated animate__fadeInDown"
    >
      <div v-if="!showReplayForm" class="flex no-wrap items-center justify-center">
        <n-alert :bordered="false" :class="$q.dark.isActive?darkTheme:''"
                 :show-icon="false" class="flex no-padding  no-wrap justify-center items-center "
                 style="height:51px;width:898px" type="warning">
          <div :class="$q.dark.isActive?darkTheme:''" class="q-gutter-x-md login-tip-font">
            <span>请先</span>
            <q-btn :ripple="{ color: 'yellow' }" color="primary" label="登录" no-caps size="12px"
                   @click.stop="toLogin"/>
            <span>后发表评论 (・ω・)</span></div>
        </n-alert>
      </div>
    </transition>

    <u-comment-scroll :disable="disable" @more="more">
      <u-comment ref="commentRef" :class="darkTheme" :config="config" class="no-padding no-margin" relative-time
                 upload @like="like" @operate="operate" @submit="submit" @show-info="showInfo"
                 @reply-page="replyPage">
        <!-- <div>导航栏卡槽</div> -->
        <!--         <template #header>头部卡槽</template>-->
        <!--         <template #info>用户信息卡槽</template>-->
        <template #card="scope">
          <div class="user-card">
            <div class="user-avatar">
              <q-avatar
              >
                <img :src="scope.avatar" @error="setDefaultImage">
              </q-avatar>

            </div>
            <div class="user-content">
              <div class="user-info">
                <a class="username" href="" target="_blank">
                  <span class="name" style="max-width: 10em">{{ scope.username }}</span>
                  <span blank="true" class="rank">
                <u-icon size="24" v-html="useLevel(scope.level)"></u-icon>
              </span>
                </a>
              </div>
              <div class="social-info">
                <a class="attention" href="">
                  <span>{{ scope.attention }}</span>
                  <span>关注</span>
                </a>
                <a class="follower" href="">
                  <span>{{ scope.follower }}</span>
                  <span>粉丝</span>
                </a>
                <a class="like" href="">
                  <span>{{ scope.like }}</span>
                  <span>获赞</span>
                </a>
              </div>
              <div class="card-btn">
                <el-button type="primary">关注</el-button>
                <el-button>发消息</el-button>
              </div>
            </div>
          </div>
        </template>
        <template #operate="scope">
          <Operate v-if="config.user?.id==scope.uid" :comment="scope" @remove="remove"/>
        </template>
        <!-- <template #func>功能区域卡槽</template> -->
      </u-comment>
    </u-comment-scroll>
  </div>
</template>

<script lang="ts" setup>
// 下载表情包资源emoji.zip https://gitee.com/undraw/undraw-ui/releases/tag/v0.0.0
// static文件放在public下,引入emoji.ts文件可以移动assets下引入,也可以自定义到指定位置
import emoji from './emoji'
import useTheme from "src/composables/useTheme"
import {computed, inject, onMounted, reactive, ref, watchEffect} from 'vue'
import Operate from './CiliOperate/Operate.vue'
import {RealUrl} from 'src/utils/convert'
import {
  CommentApi,
  CommentInstance,
  ConfigApi,
  createObjectURL,
  dayjs,
  ReplyApi,
  ReplyPageParamApi,
  SubmitParamApi,
  throttle,
  useLevel,
  usePage,
  UToast
} from 'undraw-ui'
import {getComment, postAction, postMultipartFileAction, urls} from './comment'
import defaultImage from 'src/assets/quasar-logo-vertical.svg'
import {useRoute} from 'vue-router'
import {useI18n} from "vue-i18n";
import {useUserStore} from "src/stores/user";

const {darkTheme} = useTheme()
const {t} = useI18n()
const $router = useRoute()
const commentRef = ref<CommentInstance>()
const userStore = useUserStore()
// 用户信息是否加载
const loading = ref(false)
// 请求获取用户详细信息
const showInfo = (uid: string, finish: Function) => {
  loading.value = true
  postAction(urls.userInfo, {id: uid})
    .then(res => {
      if (res.code === 1) {
        //@ts-ignore
        res.data.avatar = RealUrl(res.data.avatar)
        finish(res.data)
      }
    }).finally(() => loading.value = false)
}
const showReplayForm = computed(() => userStore.token)
const config = reactive<ConfigApi>({
  user: {
    id: 1,
    username: 'jack',
    avatar: 'https://static.juzicon.com/avatars/avatar-200602130320-HMR2.jpeg?x-oss-process=image/resize,w_100',
    // 评论id数组 建议:存储方式用户uid和评论id组成关系,根据用户uid来获取对应点赞评论id,然后加入到数组中返回
    likeIds: []
  },
  emoji: emoji,
  comments: [],
  showForm: !!showReplayForm.value,

})
watchEffect(() => {
  console.log(showReplayForm.value)
  config.showForm = !!showReplayForm.value
  if (config.showForm) {
    loadUserInfo()
  }
})
// 当前页数
let pageNum = 2
// 页大小
let pageSize = 1
// 评论总数量
let total = 1

function loadUserInfo() {
  loading.value = true
  if (!!userStore.GetToken()) {
    postAction(urls.userInfo, {id: null})
      .then(res => {
        if (res.code === 1) {
          //@ts-ignore
          config.user = {...config.user, ...res.data}
          //@ts-ignore
          config.user.avatar = RealUrl(config.user.avatar)
        }
      }).finally(() => loading.value = false)
  }
}

// 初始化评论列表
onMounted(async () => {
  try {
    loadUserInfo();
    const res = await getComment(pageNum - 1, pageSize, $router.params.id)
    res.data = res.data?.map((item: any) => {
      let user = item.user
      user.avatar = RealUrl(user.avatar)
      item.user = user
      return item;
    })
    config.comments.push(...res.data)
    total = res.total
  } catch (e) {
    console.debug(e)
  }
})
// 是否禁用滚动加载评论
const disable = ref(false)
// 加载更多评论
const more = async () => {
  console.log(disable.value)
  if (pageNum <= Math.ceil(total / pageSize)) {
    const res = await getComment(pageNum, pageSize, $router.params.id)
    setTimeout(() => {

      config.comments.push(...res.data)
      pageNum++
    }, 200)
  } else {
    disable.value = true
  }
}

let temp_id = 100

// 提交评论事件

const submit = async ({content, parentId, files, finish}: SubmitParamApi) => {
  console.log('提交评论: ' + content, parentId, files)

  /**
   * 上传文件后端返回图片访问地址，格式以'||'为分割; 如:  '/static/img/program.gif||/static/img/normal.webp'
   */
  let contentImg = files?.map(e => createObjectURL(e)).join('||')
  let href: any = void 0
  if (!!files && files.length > 0) {
    const formData = new FormData();
    formData.append('file', files[0]);
    const res1 = await postMultipartFileAction<object>(urls.upload, formData)
    if (res1.code === 1) {
      //@ts-ignore
      href = process.env.API + res1.data.records
    } else {
      href = contentImg
    }
  }
  temp_id += 1
  const comment: CommentApi = {
    id: String(temp_id),
    parentId: parentId,
    uid: config.user.id,
    // address: '来自江苏',
    content: content,
    likes: 0,
    createTime: dayjs().format('YYYY-MM-DD HH:mm:ss'),
    contentImg: href,
    user: {
      username: config.user.username,
      avatar: config.user.avatar,
      level: 6,
      homeLink: `/${config.user.id}`
    },
    reply: null
  }
  const res = await postAction(`${urls.add + $router.params.id}`, comment)
  if (res.code === 1) {
    console.log(res.data)
    finish(<CommentApi>res.data)
    UToast({message: res.message, type: 'info'})
  }

}
// 点赞按钮事件 将评论id返回后端判断是否点赞，然后在处理点赞状态
const like = async (id: string, finish: () => void) => {
  const res = await postAction(`${urls.like + id}`)
  if (res.code === 1) {
    finish()
  }
}

const setDefaultImage = (e: { target: { src: string; }; }) => {
  e.target.src = defaultImage
}
//回复分页
const reply = {
  total: 6,
  list: [
    {
      id: '34',
      parentId: '3',
      uid: '9',
      address: '来自西安',
      likes: 34,
      content: '不要由于别人不能成为我们所希望的人而愤怒，因为我们自己也难以成为自己所希望的人。',
      createTime: '1天前',
      user: {
        username: 'poli301',
        avatar:
          'https://static.juzicon.com/avatars/avatar-190919180043-XPLP.jpg?x-oss-process=image/resize,m_fill,w_100,h_100',
        level: 4,
        homeLink: '/34'
      }
    },
    {
      id: '35',
      parentId: '3',
      uid: '10',
      username: 'fish_eno',
      avatar:
        'https://static.juzicon.com/avatars/avatar-190919180320-NAQJ.jpg?x-oss-process=image/resize,m_fill,w_100,h_100',
      level: 6,
      link: '/35',
      likes: 32,
      address: '来自武汉',
      content: '世上莫名其妙走霉运的人多的是，都是一边为命运生气，一边化愤怒为力量地活着。',
      createTime: '11小时前',
      user: {
        username: 'poli301',
        avatar:
          'https://static.juzicon.com/avatars/avatar-190919180043-XPLP.jpg?x-oss-process=image/resize,m_fill,w_100,h_100',
        level: 4,
        homeLink: '/34'
      }
    }
  ]
} as ReplyApi
const replyPage = ({parentId, pageNum, pageSize, finish}: ReplyPageParamApi) => {
  console.log("回复分页", parentId, pageNum, pageSize)
  let tmp = {
    total: reply.total,
    list: usePage(pageNum, pageSize, reply.list)
  }

  setTimeout(() => {
    finish(tmp)
  }, 200)
}


// 删除评论
const remove = (comment: CommentApi) => {
  setTimeout(() => {
    commentRef.value?.remove(comment)
  }, 200)
}

const _throttle = throttle((type: string, comment: CommentApi, finish: Function) => {
  switch (type) {
    case '删除':
      alert(`删除成功: ${comment.id}`)
      finish()
      break
    case '举报':
      alert(`举报成功: ${comment.id}`)
      break
  }
})

const operate = (type: string, comment: CommentApi, finish: Function) => {
  _throttle(type, comment, finish)
}
const bus = inject('bus')
const toLogin = () => {
  //@ts-ignore
  bus.emit('showLoginFrom')
}
</script>
<style lang="scss" scoped>
.user-card {
  display: flex;

  .user-content {
    flex: 1;
    margin-left: 16px;

    .user-info {
      .username {
        display: flex;
        align-items: center;
        text-decoration: none;

        .name {
          max-width: 10rem;
          font-weight: 500;
          font-size: 15px;
          color: #252933;
          line-height: 32px;
          margin-right: 4px;
        }
      }
    }

    .social-info {
      font-size: 12px;
      margin-bottom: 10px;

      a {
        text-decoration: none;
      }

      a:not(:first-child) {
        margin-left: 18px;
      }

      a span:last-child {
        margin-left: 3px;
        color: #9499a0;
      }
    }
  }
}

:deep(.comment-list-wrapper) {
  padding-top: 0 !important;
}

:deep(.content) {
  margin-bottom: 20px !important;
}

.login-tip-font {
  font-family: PingFang SC, HarmonyOS_Regular, Helvetica Neue, Microsoft YaHei, sans-serif;
  font-weight: 600;
  font-size: 14px;
  box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
}
</style>
