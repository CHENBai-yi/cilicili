<template>
  <q-layout style="overflow: hidden">
    <q-page-container>
      <q-page class="bg-image row q-pa-sm">
        <div class="col-12">
          <DarkTheme class="float-right"/>
        </div>
        <div class="col-12 flex flex-center">
          <q-card v-bind:style="$q.screen.lt.sm?{'width': '80%'}:{'width':'30%'}">
            <q-card-section>
              <q-avatar class="absolute-center shadow-10" size="103px">
                <img src="profile.svg">
              </q-avatar>
            </q-card-section>
            <q-card-section>
              <div class="text-center q-pt-lg">
                <div class="col text-h6 ellipsis">
                  {{ randomWelcome() }}
                </div>
              </div>
            </q-card-section>
            <q-card-section>
              <q-form
                class="q-gutter-md"
                @submit="onSubmit"
              >
                <q-input
                  v-model.trim="form.username"
                  :label="$t('Username')"
                  :disable="loading"
                  lazy-rules
                  :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]" dense no-error-icon
                />
                <q-input
                  v-model.trim="form.password"
                  :label="$t('Password')"
                  :disable="loading"
                  lazy-rules
                  :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]" :type="isPwd ? 'password' : 'text'"
                  dense no-error-icon
                >
                  <template v-slot:append>
                    <q-icon :name="isPwd ? 'visibility_off' : 'visibility'" class="cursor-pointer"
                            @click="isPwd = !isPwd"/>
                  </template>
                </q-input>
                <div class="row q-gutter-y-md q-mt-none">
                  <q-checkbox v-model="rememberMe" :disable="loading" :label="$t('RememberMe')" dense
                              @update:model-value="changeRememberMe"/>
                </div>
                <div>
                  <q-btn :label="$t('Login')" :loading="loading" color="primary" type="submit"/>
                </div>
              </q-form>
              <q-inner-loading :showing="loading" style="background-color: rgba(0, 0, 0, 0);">
                <q-spinner-hourglass color="primary" size="3em"/>
              </q-inner-loading>
            </q-card-section>
          </q-card>
          <DbInit v-if="dbNeedInit" @initDbSuccess="checkDb"/>
        </div>
        <div class="col-12 self-end ">
          <GqaLanguage class="power-show " style="width: 90px"/>
          <div class="power-show">
            {{ gqaFrontend.subTitle }}
            is powered by&nbsp;
            <a :style="{ color: $q.dark.isActive ? '#fff' : '#000' }"
               href="https://github.com/Junvary/gin-quasar-admin"
               style="text-decoration: none"
               target="_blank">
              Gin-Quasar-Admin
              {{ gqaVersion }}
            </a>
          </div>
        </div>
      </q-page>

    </q-page-container>

  </q-layout>
</template>

<script setup>
import GqaLanguage from 'src/components/GqaLanguage/index.vue'
import {computed, onBeforeMount, ref} from 'vue'
import DarkTheme from 'src/components/GqaTheme/DarkTheme.vue';
import {randomWelcome} from 'src/utils/welcome'
import DbInit from './myInitDbView/index.vue'
import {useStorageStore} from 'src/stores/storage'
import {useQuasar} from 'quasar'
import {postAction} from "src/api/manage";
import useCommon from "src/composables/useCommon";
import {useUserStore} from 'src/stores/user'

import {usePermissionStore} from 'src/stores/permission';
import {useRouter} from 'vue-router';

const {gqaFrontend} = useCommon()
const $q = useQuasar()
const isPwd = ref(true)
const form = ref({
  username: 'admin',
  password: 'admin123',
  captcha: '',
  captcha_id: '',
})
const router = useRouter()
const userStore = useUserStore()
const rememberMe = ref(true)
const changeRememberMe = (value) => {
  userStore.ChangeRememberMe(value)
}
const loading = ref(false)
const dbNeedInit = ref(false)
const storageStore = useStorageStore()
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
  if (gqaFrontend.value.bannerImage && gqaFrontend.value.bannerImage.substring(0, 11) === 'gqa-upload:') {
    return process.env.API + gqaFrontend.value.bannerImage.substring(11)
  }
  return ''
})
const permissionStore = usePermissionStore()
onBeforeMount(() => {
  checkDb()
})
const captchaImage = ref('')
const getCaptcha = () => {
  postAction('public/get-captcha').then((res) => {
    captchaImage.value = res.data.captcha_image
    form.value.captcha_id = res.data.captcha_id
  })
}
const checkDb = async () => {
  const res = await postAction('public/check-db')

  if (res.code === 1) {
    storageStore.SetGqaGoVersion(res.data.go_version)
    storageStore.SetGqaGinVersion(res.data.gin_version)
    storageStore.SetGqaPluginList(res.data.plugin_list)
    if (res.data.need_init === false) {
      dbNeedInit.value = false
      await storageStore.SetGqaDict()
      await storageStore.SetGqaFrontend()
      await storageStore.SetGqaBackend()
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

const onSubmit = async () => {
  loading.value = true
  const res = await userStore.HandleLogin({
    username: form.value.username,
    password: form.value.password,
    captcha: form.value.captcha,
    captcha_id: form.value.captcha_id
  })
  if (res) {
    // The Get User menu is added here to trigger the get user default page.
    // After successful access here, the boot/permission.js file determines that permissionStore.userMenu.length exists.
    // Go to next()
    const userMenu = await permissionStore.GetUserMenu()
    if (userMenu && userMenu.length) {
      userMenu.forEach(item => {
        router.addRoute(item)
      })
      router.push({name: permissionStore.defaultPage[0]})
    } else {
      form.value.captcha = ''
      getCaptcha()
      loading.value = false
      $q.notify({
        type: 'negative',
        message: userMenu,
      })
    }
  } else {
    form.value.captcha = ''
    loading.value = false
    getCaptcha()
  }
}
</script>

<style lang="scss" scoped>

.bg-image {
  background-image: linear-gradient(135deg, #7028e4 0%, #e5b2ca 100%);
}

.power-show {
  position: absolute;
  width: 100%;
  text-align: center;
  bottom: 0;
  font-weight: bold;
  font-size: 10px;
}
</style>
