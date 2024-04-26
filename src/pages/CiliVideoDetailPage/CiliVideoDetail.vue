<template>
  <q-layout view="lHh lpr lFf" @scroll="scrollTo">
    <transition appear
                enter-active-class="animated animate__fadeInDown"
                leave-active-class="animated animate__fadeOutUp">
      <q-header :class="darkTheme">
        <cili-tool-bar :isJump="true" class="shadow-6 "/>
      </q-header>
    </transition>
    <q-page-container>
      <q-page :class="$q.screen.lt.sm?'q-pa-xs':'q-pa-sm q-px-xl'" style="min-height: 263px;">
        <div>
          <n-space size="large" style="justify-content: center;flex-flow:nowrap">
            <n-layout :style="$q.screen.lt.sm?{height:'300px'}:{height:'550px',width:'1500px'}" embedded has-sider
                      sider-placement="right">
              <n-layout-content :class="darkTheme"
                                :content-style="$q.screen.lt.sm?{paddingRight:'20px',overflow:'hidden'}:{overflow:'hidden'}"
                                embedded>
                <cili-video-player
                  ref="dp"
                  :danmaku="dplayerObj.danmaku"
                  :highlight="dplayerObj.highlight"
                  :loop="false"
                  :showShadow="false"
                  :video="dplayerObj.video"
                />
                <div v-if="dplayerObj.buy!==undefined&&!dplayerObj.buy" class="el-loading-mask cili_mask">
                  <img onclick="javascript:window.location='#/pay/pay.html'" src="/pay/pc-fhy.gif" style="width:100%">
                </div>
              </n-layout-content>

              <n-layout-sider
                :collapsed-width="0"
                :default-collapsed="$q.screen.lt.sm"
                :inverted="$q.dark.isActive"
                :show-collapsed-content="false"
                :show-trigger="$q.screen.lt.sm?'bar':false"
                :width="364"
                bordered
                collapse-mode="width"
                content-style="padding-left:15px;overflow:hidden"
              >

                <div :class="$q.dark.isActive?'left_background bg-white q-mb-sm rounded-borders ':''">
                  <div class="left q-mb-sm rounded-borders "></div>
                </div>
                <n-scrollbar>
                  <div class="q-gutter-sm">
                    <CiliLink v-for="(item ,index) in videoList" :key="index" :weight=600 color=""
                              @click="switchVideo(item.content)">
                      <div class="flex no-wrap items-center justify-evenly">
                        <q-icon class="q-pr-sm" name="play_circle_filled" size="20px"/>
                        {{ String(index + 1).padStart(2, '0') }}.
                        <n-ellipsis style="max-width: 300px">
                          {{ item.section }}-{{ item.title }}
                        </n-ellipsis>
                      </div>
                    </CiliLink>
                    <p v-if="!videoList||videoList.length===0" class="text-weight-bolder text-blue-grey">
                      没有更多小节信息</p>
                  </div>
                </n-scrollbar>

              </n-layout-sider>
            </n-layout>
          </n-space>
          <div>

            <n-h2 :class="darkTheme" class="no-margin flex items-center q-gutter-x-sm ">
              <q-icon name="fas fa-graduation-cap"/>
              <n-ellipsis style="max-width: 300px">{{ videoInfo.title }}</n-ellipsis>
            </n-h2>
            <div class="no-margin flex items-center q-gutter-x-xs">
              <div class="q-pr-lg text-weight-medium">{{ $t('Lecturer') }}:<span class="q-pl-sm ">{{
                  videoInfo.author
                }}</span></div>
              <div class="q-pr-lg text-weight-medium">{{ $t('Category') }}:<span class="q-pl-sm">{{
                  videoInfo.tag
                }}</span></div>
              <div class="q-pr-lg text-weight-medium">
                <q-icon name="visibility"/>
                <em class="q-pl-xs">{{ videoInfo.view }}</em></div>
              <CiliLink class="q-pr-lg " color="">
                <q-icon name="share"/>
                <em class="q-pl-xs text-weight-medium">{{ $t('Share') }}</em></CiliLink>
            </div>
          </div>
        </div>
        <div :class="$q.dark.isActive?'cili_dark':''" class="card q-mt-lg">
          <TabMenu :active-index="$route.meta.index" :model="items">
            <template #item="{ item, props }">
              <router-link v-if="item.route" v-slot="{ href, navigate }"
                           :to="'/video/'+$route.params.name+'/'+$route.params.id+'/'+item.route" append>
                <a v-ripple :href="href" v-bind="props.action" @click="navigate">
                  <span v-bind="props.icon"/>
                  <span v-bind="props.label">{{ item.label }}</span>
                </a>
              </router-link>
              <a v-else v-ripple :href="item.url" :target="item.target" v-bind="props.action">
                <span v-bind="props.icon"/>
                <span v-bind="props.label">{{ item.label }}</span>
              </a>
            </template>
          </TabMenu>
        </div>
      </q-page>
      <div ref="videoArea">
        <router-view v-slot="{ Component }">
          <transition :duration="50"
                      appear enter-active-class="animated animate__fadeIn">
            <keep-alive>
              <component :is="Component"/>
            </keep-alive>
          </transition>
        </router-view>
      </div>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import useTheme from "src/composables/useTheme"
import {inject, onBeforeUpdate, onMounted, reactive, ref} from 'vue'
import Hls from 'hls.js';
import CiliVideoPlayer from 'src/components/ciliVideoPlayer/CiliVideoPlayer.vue'
import {scroll, useQuasar} from 'quasar'
import {useCommonStore} from 'src/stores/common'
import {useRoute, useRouter} from 'vue-router'
import {Base64} from "js-base64";

const $q = useQuasar()
const $router = useRoute()
const $Router = useRouter()
const commonStore = useCommonStore()
const {getScrollTarget, setVerticalScrollPosition} = scroll
const videoArea = ref(null)
const scrollTo = (e) => {
  commonStore.SetPos(e.position)
}

// 采用元素对象(element object)
function scrollToElement(el, pos) {
  let target = getScrollTarget(el)
  // let offset = el.offsetTop - el.scrollHeight
  let offset = pos
  let duration = 50
  setVerticalScrollPosition(target, offset, duration)
}

const {darkTheme} = useTheme()
const items = ref([
  {label: '课程信息', icon: 'pi pi-info-circle', route: 'info'},
  {
    label: '目录',
    icon: 'pi pi-list',
    route: 'catalogue'
  },
  // {label: '服务', icon: 'fab fa-servicestack', url: 'https://vuejs.org/'},
  {label: '评价', icon: 'fas fa-comment-alt', route: 'comment'},
]);

const dplayerObj = reactive({
  buy: undefined,
  video: {
    pic: "VCG41N1403887001.jpg",
    url: 'https://api.dogecloud.com/player/get.m3u8?vcode=5ac682e6f8231991&userId=17&ext=.m3u8', //视频地址
    type: 'customHls',
    customType: {
      customHls: function (video, player) {
        const hls = new Hls(); //实例化Hls  用于解析m3u8
        hls.loadSource(video.src);
        hls.attachMedia(video);
      }
    },
    quality: [
      {
        name: '高清',
        url: 'https://api.dogecloud.com/player/get.m3u8?vcode=5ac682e6f8231991&userId=17&ext=.m3u8',
        type: 'customHls',
      },
      {
        name: '标清',
        url: 'http://vjs.zencdn.net/v/oceans.mp4',
        type: 'normal',
      },
    ],
    defaultQuality: 1,
  },
  danmaku: {
    id: '9E2E3368B56CDBB4',
    api: 'https://api.prprpr.me/dplayer/',
    token: 'eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDczMTAwNjQsImlhdCI6MTcwNzA1MDgwNCwicGx0IjotMX0.7HlHWIzzKZFtR3DmoabYz1wDT4iWIfug2Zc9cFBccAc',
    maximum: 1000,
    addition: ['https://api.prprpr.me/dplayer/v3/bilibili?aid=4157142'],
    user: 'DIYgod',
    bottom: '15%',
    unlimited: false,
    speedRate: 0.5,
  },
  highlight: [
    {
      time: 20,
      text: '这是第 20 秒',
    },
    {
      time: 120,
      text: '这是 2 分钟',
    },
  ],
})
onBeforeUpdate(() => {
  if ($q.screen.gt.sm) {
    scrollToElement(videoArea.value, commonStore.GetPos())
  }
})
const videoList = ref([])
const videoInfo = ref({})
const urls = reactive({
  info: 'courses/get-course-video-info-by-id'
})


const bus = inject('bus')
onMounted(async () => {
  if (!!$router.query.t) {
    window.$message.success(Base64.decode($router.query.t), {render: window.$render})
    dplayerObj.buy = true
  } else {
    commonStore.SetCurrentVideoAddress($router.path)
  }
  bus.on('handleSearch', (query) => {
    const routeData = $Router.resolve(`/course?query=${query}`);
    window.open(routeData.href, '_blank');
  })
  const data = {
    name: $router.params.name,
    id: $router.params.id
  }
  const res = await commonStore.GetVideoDetail(urls.info, data)
  dplayerObj.video.pic = res.video.pic
  dplayerObj.buy = res.video.buy
  dplayerObj.video.quality[0].url = res.video.url
  dplayerObj.video.quality[1].url = res.video.url
  videoList.value = res.videoList
  videoInfo.value = res.video
  dp.value.render()
})
const dp = ref(null)
const switchVideo = (e) => {
  console.log(e)
  dp.value.dp.switchVideo(e.video, e.danmu);
  dp.value.dp.video.autoplay = true
}

</script>
<style lang="scss" scoped>
.left {
  width: 117px;
  height: 28px;
  background-image: url('/images/catalogue-title.png');
  background-size: cover;
  background-position: center;
}

.left_background {
  width: 120px;
  height: 33px;
}

.cili_dark {
  :deep(.p-tabmenu .p-tabmenu-nav .p-menuitem-link) {
    background: #1D1D1D;
    border-color: #1D1D1D;
    color: #FFFFFF;
  }

  :deep(.p-tabmenu .p-tabmenu-nav) {
    background: #1D1D1D;
    border-width: 0 0 1px 0;
  }
}

.cili_mask {
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 12
}

</style>
