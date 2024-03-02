<template>
  <q-page class="overflow-hidden ">
    <div class="row justify-center   q-mt-xs q-gutter-md container_d">
      <div :class="darkTheme" class=" q-pa-md flex  column q-gutter-y-md items-center rounded-borders space-left  ">
        <div class="side-bar column items-center justify-center">
          <q-avatar size="60px">
            <img :src="avatar">
          </q-avatar>
          <span style="margin-top:10px"> {{ username }}</span>
        </div>
        <q-separator class="full-width" inset/>
        <CiliLink :style="$route.path==='/account/setting'?{color:'#409eff'}:{}" class="side-bar "
                  href="#/account/setting" target="_self">
          <q-icon left name="manage_accounts" size="xs"/>
          账号信息
        </CiliLink>

        <CiliLink :style="$route.path==='/account/order'?{color:'#409eff'}:{}" class="side-bar" href="#/account/order"
                  target="_self">
          <q-icon left name="shopping_cart" size="xs"/>
          我的订单
        </CiliLink>
      </div>
      <div class="col-6 ">
        <q-layout class="q-ma-sm rounded-borders border " container view="lHh lpr lFf">
          <q-page-container>
            <router-view/>
          </q-page-container>
        </q-layout>

      </div>
    </div>
  </q-page>
</template>

<script setup>
import {computed, ref} from 'vue'
import {useUserStore} from 'src/stores/user'
import useTheme from "src/composables/useTheme"
import {CiliFrontendDefault} from 'src/config/default'

const userStore = useUserStore()
const {darkTheme} = useTheme()
const avatar = computed(() => userStore.GetAvatar())
const username = computed(() => userStore.GetNickname() || userStore.GetUsername())
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
