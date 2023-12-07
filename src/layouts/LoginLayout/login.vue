<template>
  <q-layout>
    <q-page-container>
      <q-page class="bg-image column q-pa-xs">
        <!--        flex flex-center-->
        <div class="col-2">
          <DarkTheme class=" float-right"/>
        </div>
        <div class="col flex flex-center">
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
              >
                <q-input
                  v-model="username"
                  :label="$t('Username')"
                  filled
                  lazy-rules
                />
                <q-input
                  v-model="password"
                  :label="$t('Password')"
                  filled
                  lazy-rules
                  type="password"
                />
                <div>
                  <q-btn :label="$t('Login')" color="primary" to="/" type="button"/>
                </div>
              </q-form>
            </q-card-section>
          </q-card>
          <DbInit v-if="dbNeedInit" @initDbSuccess="checkDb"/>
        </div>
        <div class="col-2">
          <GqaLanguage style="width: 110px"/>
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

const $q = useQuasar()


const dbNeedInit = ref(false)
const storageStore = useStorageStore()
const username = ref('Mr Chen')
const password = ref('12345')
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

onBeforeMount(() => {
  checkDb()
})
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
</script>

<style lang="scss" scoped>

.bg-image {
  background-image: linear-gradient(135deg, #7028e4 0%, #e5b2ca 100%);
}

</style>
