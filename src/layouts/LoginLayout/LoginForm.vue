<template>
  <q-card-section>
    <q-card-section>
      <div class="text-center">
        <Cili-avatar :src="CiliFrontend.logo" size="xl"/>
      </div>
      <div class="text-h4 text-center text-bold">
        {{ CiliFrontend.subTitle }}
      </div>
      <div class="text-h6 text-center q-mt-md q-mb-xs">
        {{ $t('WelcomeBack') }}
      </div>
      <q-form class="q-mt-lg Cili-form" @submit="onSubmit">
        <q-input v-model.trim="form.username" :disable="loading" :placeholder="$t('Username')"
                 :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]" color="black" dense no-error-icon
                 outlined rounded/>
        <q-input v-model.trim="form.password" :disable="loading" :placeholder="$t('Password')"
                 :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]" :type="isPwd ? 'password' : 'text'"
                 color="black" dense no-error-icon outlined
                 rounded>
          <template v-slot:append>
            <q-icon :name="isPwd ? 'visibility_off' : 'visibility'" class="cursor-pointer"
                    @click="isPwd = !isPwd"/>
          </template>
        </q-input>
        <!--        <q-input v-model.trim="form.captcha" :disable="loading" :placeholder="$t('Captcha')"-->
        <!--                 :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]" color="black" dense no-error-icon-->
        <!--                 outlined rounded>-->
        <!--          <template v-slot:after>-->
        <!--            <q-btn padding="none" style="width: 120px; height: 100%" @click="getCaptcha">-->
        <!--              <q-img :src="captchaImage"/>-->
        <!--            </q-btn>-->
        <!--          </template>-->
        <!--        </q-input>-->
        <div class="column q-gutter-y-md q-mt-none">
          <q-checkbox v-model="rememberMe" :disable="loading" :label="$t('RememberMe')" dense
                      @update:model-value="changeRememberMe"/>
        </div>
        <div class="items-center justify-around q-mt-md row">
          <q-btn :label="$t('Login')" :loading="loading" color="primary" glossy rounded style="width: 100%"
                 type="submit"/>
        </div>
      </q-form>
      <q-inner-loading :showing="loading" style="background-color: rgba(0, 0, 0, 0);">
        <q-spinner-hourglass color="primary" size="3em"/>
      </q-inner-loading>
    </q-card-section>
  </q-card-section>
</template>

<script setup>
import useCommon from 'src/composables/useCommon'
import {onMounted, ref} from 'vue';
import {postAction} from 'src/api/manage'
import {useUserStore} from 'src/stores/user'
import {usePermissionStore} from 'src/stores/permission';
import {useRoute, useRouter} from 'vue-router';
import {useQuasar} from 'quasar';

const {CiliFrontend} = useCommon()

const isPwd = ref(true)
const form = ref({
  username: '',
  password: '',
  captcha: '',
  captcha_id: '',
})
const router = useRouter()
const route = useRoute()
const $q = useQuasar()
const userStore = useUserStore()
const permissionStore = usePermissionStore()
const rememberMe = ref(true)
const captchaImage = ref('')
const loading = ref(false)
const getCaptcha = () => {
  postAction('public/get-captcha').then((res) => {
    captchaImage.value = res.data.captcha_image
    form.value.captcha_id = res.data.captcha_id
  })
}
const changeRememberMe = (value) => {
  userStore.ChangeRememberMe(value)
}

onMounted(() => {
  getCaptcha()
})

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
