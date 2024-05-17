<template>
  <q-page class="overflow-hidden ">
    <cili-tool-bar/>
    <!--    <div class="row justify-center   q-mt-xs q-gutter-md ">-->
    <div class="row justify-center   q-mt-xs q-gutter-md container_d">
      <div class=" q-pa-md flex  column q-gutter-y-md items-center rounded-borders space-left  ">
        <div class="side-bar">
          <q-icon left name="ion-md-mail-unread"/>
          消息中心
        </div>
        <CiliLink :style="$route.path==='/notice/system'?{color:'#409eff'}:{}" class="side-bar" href="#/notice/system"
                  target="_self">
          <q-icon left name="circle_notifications"/>
          <span>系统通知</span>
        </CiliLink>
        <CiliLink :style="$route.path==='/notice/me'?{color:'#409eff'}:{}" class="side-bar" href="#/notice/me"
                  target="_self">
          <q-icon left name="fas fa-comment"/>
          <span>我的消息</span>
        </CiliLink>
      </div>
      <router-view/>
    </div>
  </q-page>
</template>

<script setup>
import {ref} from 'vue'
import useTheme from "src/composables/useTheme"
import {CiliFrontendDefault} from 'src/config/default'

const {darkTheme} = useTheme()
const css = ref(`url(${CiliFrontendDefault.imageList.noticeBackgroundImg}) top/cover no-repeat fixed`)

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


p, span {
  margin: 0;
  padding: 0;
}
</style>
