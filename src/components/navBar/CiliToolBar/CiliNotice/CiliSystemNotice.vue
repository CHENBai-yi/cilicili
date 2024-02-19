<template>
  <q-page class="overflow-hidden ">
    <cili-tool-bar class="shadow-24"/>
    <!--    <div class="row justify-center   q-mt-xs q-gutter-md ">-->
    <div class="row justify-center   q-mt-xs q-gutter-md container_d">
      <div class=" q-pa-md flex  column q-gutter-y-md items-center rounded-borders space-left  ">
        <div class="side-bar">
          <q-icon left name="ion-md-mail-unread"/>
          消息中心
        </div>
        <CiliLink class="side-bar">
          <q-icon left name="circle_notifications"/>
          系统通知
        </CiliLink>

        <CiliLink class="side-bar">
          <q-icon left name="fas fa-comment"/>
          我的消息
        </CiliLink>
      </div>
      <div class="col-6 ">
        <q-layout class="q-ma-sm rounded-borders border " container view="lHh lpr lFf">
          <q-header :class="darkTheme" class="rounded-borders border ">
            <el-card :body-style={padding:0} :class="darkTheme" class=" rounded-borders border q-pa-sm" shadow="always">
              系统通知
            </el-card>
          </q-header>

          <q-page-container>
            <q-page class="q-gutter-md q-pt-md">
              <el-card v-for="(i,index) in 100" :class="darkTheme" class=" rounded-borders border" shadow="always">
                <div class="top">
                  <span class="title"
                  >2024拜年纪全程回顾上线！</span><span
                  class="time">2024年2月10日 11:00</span></div>
                <div class="bottom">
                  <span class="text"

                  ><span
                  >错过拜年纪直播？没关系，拜年纪正片+单品已全部上线！小伙伴们快来看看吧！
              <CiliLink color="#2faee3" href="https://www.bilibili.com/video/BV1iU421d7aG" target="_blank">
                <q-icon name="ion-ios-link"/> 网页链接
              </CiliLink>
            </span></span></div>
              </el-card>
            </q-page>
          </q-page-container>
        </q-layout>

      </div>
    </div>
  </q-page>
</template>

<script setup>

const {darkTheme} = useTheme()
import useTheme from "src/composables/useTheme"</script>

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
  background: url(https://s1.hdslb.com/bfs/static/blive/blfe-message-web/static/img/infocenterbg.a1a0d152.jpg) top/cover no-repeat fixed;
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
