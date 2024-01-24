<template>
  <div
    :class="changeContainerImg === '' ? 'Cili-login-layout-img-container' : 'Cili-login-layout-img-container-with-img'"
    :style="changeContainerImg === '' ? { background: $q.dark.isActive ? 'black' : '#e3f4fa' } : changeContainerImg">
    <component :is="loginLayout"/>
    <DbInit v-if="dbNeedInit" @initDbSuccess="checkDb"/>
  </div>


</template>

<script setup>

import Cli from './CiliciliView/login.vue'
import ComplexView from './ComplexView/index.vue'
import {computed, onMounted, ref,} from "vue";
import useCommon from "src/composables/useCommon";
import {postAction} from "src/api/manage";
import DbInit from './myInitDbView/index.vue'
import {useStorageStore} from 'stores/storage'
import {useQuasar} from 'quasar'

const $q = useQuasar()

const dbNeedInit = ref(false)
const {CiliFrontend} = useCommon()
const storageStore = useStorageStore()
const loginLayout = computed(() => {
  if (CiliFrontend.value.loginLayoutStyle && CiliFrontend.value.loginLayoutStyle === 'loginLayoutStyle_login') {
    return Cli
  } else if (CiliFrontend.value.loginLayoutStyle && CiliFrontend.value.loginLayoutStyle === 'loginLayoutStyle_portal') {
    return ComplexView
  } else {
    return Cli
  }
})

const checkDb = async () => {
  const res = await postAction('public/check-db')

  if (res.code === 1) {
    storageStore.SetCiliGoVersion(res.data.go_version)
    storageStore.SetCiliGinVersion(res.data.gin_version)
    storageStore.SetCiliPluginList(res.data.plugin_list)
    if (res.data.need_init === false) {
      dbNeedInit.value = false
      await storageStore.SetCiliDict()
      await storageStore.SetCiliFrontend()
      await storageStore.SetCiliBackend()
    }
    if (res.data.need_init === true) {
      dbNeedInit.value = true
      $q.notify({
        type: 'warning',
        message: res.message,
      })
    }
  }
}
onMounted(() => {
  checkDb()
})


const changeContainerImg = computed(() => {
  if (bannerImage.value === '') {
    return ''
  } else {
    return {
      background: 'url(' + bannerImage.value + ')',
      backgroundRepeat: "no",
      backgroundPosition: 'center',
      backgroundSize: "cover",
      overflow: 'hidden',
      height: '100vh',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
    }
  }
})
const bannerImage = computed(() => {
  if (CiliFrontend.value.bannerImage && CiliFrontend.value.bannerImage.substring(0, 12) === 'cili-upload:') {
    return process.env.API + CiliFrontend.value.bannerImage.substring(12)
  }
  return ''
})
</script>

<style scoped>

</style>
