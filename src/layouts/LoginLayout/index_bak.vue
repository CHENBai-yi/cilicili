<template>
  <q-layout style="overflow: hidden">
    <q-page-container>
      <ComplexView v-if="loginLayout === ComplexView && !dbNeedInit" :pluginComponent="pluginComponent"
                   :pluginCurrent="pluginCurrent"/>
      <div v-else
           :class="changeContainerImg === '' ? 'Cili-login-layout-img-container' : 'Cili-login-layout-img-container-with-img'"
           :style="changeContainerImg === '' ? { background: $q.dark.isActive ? 'black' : '#e3f4fa' } : changeContainerImg">
        <div class="main-title-logo row justify-center items-center">
          <Cili-avatar :src="CiliFrontend.logo" class="gin-quasar-admin-logo" size="45px"/>
          <span class="text-weight-bold text-h4" style="margin-left:8px">
                        {{ CiliFrontend.mainTitle }}
                    </span>
        </div>
        <figure class="primary-ball"/>
        <figure class="positive-ball"/>
        <figure class="warning-ball"/>
        <figure class="negative-ball"/>
        <q-card :class="darkThemeLoginCard" bordered class="init-login-card shadow-15" style="border-radius: 20px;">
          <InitDbView v-if="dbNeedInit" @initDbSuccess="checkDb"/>
          <SimpleView v-else/>
        </q-card>
        <audio ref="loginBgm" loop src="http://music.163.com/song/media/outer/url?id=1479760027.mp3"></audio>
        <div class="power-show">
          {{ CiliFrontend.subTitle }}
          is powered by&nbsp;
          <a :style="{ color: $q.dark.isActive ? '#fff' : '#000' }" href="https://gitee.com/cby-cby/cilicili"
             style="text-decoration: none"
             target="_blank">
            Gin-Quasar-Admin
            {{ CiliVersion }}
          </a>
        </div>
        <div class="version-git-show">
          <q-btn flat>
            {{ $t('Version') }}{{ $t('Info') }}
            <Cili-version-menu/>
          </q-btn>
          <q-btn flat label="Github" @click="openLink('https://gitee.com/cby-cby/cilicili')"/>
          <q-btn flat label="Gitee" @click="openLink('https://gitee.com/cby-cby/cilicili')"/>
        </div>
        <div class="language-show">
          <CiliLanguage style="width: 100%;"/>
        </div>
        <div class="dark-theme-show">
          <q-btn :icon="playFlag ? 'music_off' : 'music_note'" flat round @click="playBgm"></q-btn>
          <DarkTheme/>
        </div>
      </div>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import useCommon from 'src/composables/useCommon'
import {computed, onBeforeMount, ref} from 'vue'
import SimpleView from './SimpleView/index.vue'

import ComplexView from './ComplexView/index.vue'
import InitDbView from './InitDbView/index.vue'
import {useStorageStore} from 'src/stores/storage'
import {useQuasar} from 'quasar'
import {useI18n} from 'vue-i18n'
import CiliLanguage from 'src/components/CiliLanguage/index.vue'
import config from '../../../package.json'
import DarkTheme from 'src/components/CiliTheme/DarkTheme.vue';
import useTheme from 'src/composables/useTheme';
import {CiliConsoleLogo} from "src/config/config"
import {CheckComponent} from 'src/utils/check'

const {darkThemeLoginCard} = useTheme()
const CiliVersion = config.version
const $q = useQuasar()
const {t} = useI18n()
const {CiliFrontend} = useCommon()
const storageStore = useStorageStore()
const pluginCurrent = ref(null)
const pluginComponent = ref(null)
const dbNeedInit = ref(false)

const loginLayout = computed(() => {
  if (CiliFrontend.value.loginLayoutStyle && CiliFrontend.value.loginLayoutStyle === 'loginLayoutStyle_login') {
    return Cli
  } else if (CiliFrontend.value.loginLayoutStyle && CiliFrontend.value.loginLayoutStyle === 'loginLayoutStyle_portal') {
    return Cli
  } else {
    return Cli
  }
})

onBeforeMount(() => {
  checkDb()
  CiliConsoleLogo()
})

const pluginsFile = import.meta.glob('../../plugins/**/LoginLayout/index.vue')

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
      pluginCurrent.value = res.data.plugin_login_layout
      if (pluginCurrent.value) {
        const cc = CheckComponent(pluginCurrent.value, pluginsFile, 3)
        if (cc[2]) {
          $q.notify({
            type: 'negative',
            message: t('LoginLayoutNotSupport'),
          })
        } else {
          pluginComponent.value = cc[1]
        }
      }
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
const openLink = (url) => {
  window.open(url)
}
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

const loginBgm = ref(null)
const playFlag = ref(false)
const playBgm = () => {
  if (playFlag.value) {
    loginBgm.value.pause()
    playFlag.value = false
  } else {
    loginBgm.value.play()
    playFlag.value = true
  }

}
</script>
