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
                <img src="images/profile.svg">
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
        </div>
        <div class="col-12 self-end ">
          <CiliLanguage class="power-show " style="width: 90px"/>
          <PageFooter/>
        </div>
      </q-page>

    </q-page-container>

  </q-layout>
</template>

<script setup>
import CiliLanguage from 'src/components/CiliLanguage/index.vue'
import {ref} from 'vue'
import DarkTheme from 'src/components/CiliTheme/DarkTheme.vue';
import {randomWelcome} from 'src/utils/welcome'
import PageFooter from 'src/layouts/MainLayout/PageFooter.vue'
import {useQuasar} from 'quasar'
import {postAction} from "src/api/manage";
import useCommon from "src/composables/useCommon";
import {useUserStore} from 'stores/user'

import {usePermissionStore} from 'stores/permission';
import {useRouter} from 'vue-router';

const {CiliFrontend} = useCommon()
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
const permissionStore = usePermissionStore()
const captchaImage = ref('')
const getCaptcha = () => {
  postAction('public/get-captcha').then((res) => {
    captchaImage.value = res.data.captcha_image
    form.value.captcha_id = res.data.captcha_id
  })
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
        message: userMenu.message,
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
