<template>
  <div class="col-6 ">
    <q-layout class="q-ma-sm rounded-borders border " container view="lHh lpr lFf">
      <q-header :class="darkTheme" class="rounded-borders border ">
        <el-card :body-style={padding:0} :class="darkTheme" class=" rounded-borders border q-pa-sm" shadow="always">
          我的消息
        </el-card>
      </q-header>

      <q-page-container>
        <q-page class="q-gutter-md q-pt-md">
          <el-card v-for="(i,index) in noticeList" :class="darkTheme" class=" rounded-borders border"
                   shadow="always">
            <div class="top">
                  <span class="title"
                  >{{ i.notice_title }}</span><span
              class="time">{{ i.created_at }}</span></div>
            <div class="bottom">
                  <span class="text">
                    <span v-html="i.notice_content">
                    </span>
                    <!--                    错过拜年纪直播？没关系，拜年纪正片+单品已全部上线！小伙伴们快来看看吧-->
                    <!--                    <CiliLink class="q-ml-sm" color="#2faee3" href="https://www.bilibili.com/video/BV1iU421d7aG"-->
                    <!--                              target="_blank">-->
                    <!--                    <q-icon name="ion-ios-link"/> 网页链接-->
                    <!--                   </CiliLink>-->
                  </span>
            </div>
          </el-card>
        </q-page>
      </q-page-container>
    </q-layout>

  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import {postAction} from 'src/api/manage'
import {useUserStore} from 'src/stores/user'
import XEUtils from 'xe-utils'
import useTheme from "src/composables/useTheme"
import {CiliFrontendDefault} from 'src/config/default'

const {darkTheme} = useTheme()

const noticeList = ref([
  {
    notice_title: '2024拜年纪全程回顾上线！',
    created_at: '2024年2月10日 11:00',
    notice_content: ''
  }
])
const userStore = useUserStore()
const css = ref(`url(${CiliFrontendDefault.imageList.noticeBackgroundImg}) top/cover no-repeat fixed`)
const getNoticeUrl = ref('notice/get-notice-list')
const getNoticeTypeSystem = async () => {
  const res = await postAction(getNoticeUrl.value, {
    notice_type: 'noticeType_system',
    notice_to_user: userStore.GetUsername(),
    page: 1,
    page_size: 9999
  })
  if (res && res.code === 1) {
    noticeList.value = res.data.records.map(item => {
      item.created_at = XEUtils.toDateString(item.created_at, 'yyyy年MM月dd日 HH:mm')
      return item
    })
  }
}
onMounted(() => {
  getNoticeTypeSystem()
})
</script>

<style scoped>
.side-bar {
  font-size: 14px;
  font-weight: 700;
  color: inherit;
}

.space-left {
  background-color: rgba(255, 255, 255, 0.8);
}

.container_d {
  height: calc(100vh - 64px);
  background: v-bind(css);
}

.border {
  border-radius: 8px;
}

.bottom {
  color: inherit;
  padding-left: 8px;
}

.title {
  color: inherit;
  font-weight: 700;
}

.time {
  color: inherit;
  font-size: 12px;
  line-height: 22px;
  margin: 0 10px;
}

.text {
  word-break: break-word;
  line-height: 19px;
}

p, span {
  margin: 0;
  padding: 0;
}
</style>
