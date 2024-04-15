<template>
  <q-page>
    <transition appear
                enter-active-class="animated animate__fadeInDown"
                leave-active-class="animated animate__fadeOutUp">
      <n-result v-if="status!==undefined" :description="status?'将为您跳转到课程页面':''"
                :status="status?'success':'error'"
                :title="status?'支付成功':'哦 豁'"
                class="q-pt-xl q-mt-xl">
        <template #footer>
          <router-link #="{ navigate, href }" custom to="/">
            <n-a :href="href" @click="navigate">
              若无响应点此回到主页
            </n-a>
          </router-link>
        </template>
      </n-result>
    </transition>
  </q-page>
</template>

<script setup>
import {onBeforeUnmount, onMounted, ref} from 'vue'
import {useCommonStore} from 'src/stores/common'
import {useRouter} from 'vue-router'
import XEUtils from 'xe-utils'
import {getAction} from 'src/api/manage'
import {Base64} from "js-base64";


const status = ref(undefined)
const $Router = useRouter()
const commonStore = useCommonStore()
onMounted(async () => {
  if (!!commonStore.getCurrentVideoAddress()) {
    const res = await getAction('memberShip/id')
    if (res.code === 1) {
      status.value = true
      setTimeout(() => $Router.push({
        path: commonStore.getCurrentVideoAddress(),
        query: {timestamp: XEUtils.timestamp(), t: Base64.encode(`解锁成功！恭喜您成为第${res.data}位会员`)}
      }), 3000)
    } else {
      status.value = false
    }
  }
})
onBeforeUnmount(() => commonStore.delCurrentVideoAddress())
</script>

<style scoped>

</style>
