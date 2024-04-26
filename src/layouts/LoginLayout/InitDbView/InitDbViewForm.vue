<template>
  <transition appear enter-active-class="animated slideInRight" leave-active-class="animated slideOutRight">
    <div class="col row column justify-center items-center">
      <q-card flat style="background: rgba(0, 0, 0, 0);">
        <q-toolbar>
          <q-avatar class="gin-quasar-admin-logo" font-size="52px" size="100px">
            <img src="images/logo.png"/>
          </q-avatar>
          <q-toolbar-title class="row items-center">
                        <span class="text-weight-bold">
                            {{ $t('Init') }}
                            {{ $t('Database') }}
                        </span>
          </q-toolbar-title>
        </q-toolbar>
        <q-card-section>
          <q-form class="text-center Cili-form" @submit="onInitDb">
            <div class="q-gutter-y-md column">
              <div class="row q-gutter-md">
                <q-input v-model.trim="form.db_type" :label="$t('Database') + $t('Type')"
                         :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]" bottom-slots
                         class="col" disable no-error-icon
                         outlined>
                </q-input>
                <q-input v-model.trim="form.db_host" :label="$t('Database') + $t('Address')"
                         :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]"
                         class="col" no-error-icon
                         outlined>
                </q-input>
                <q-input v-model.trim="form.db_port" :label="$t('Database') + $t('Port')"
                         :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]"
                         class="col" no-error-icon
                         outlined>
                </q-input>
              </div>
              <div class="row q-gutter-md">
                <q-input v-model.trim="form.db_schema" :label="$t('Database') + $t('Name')"
                         :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]"
                         class="col" no-error-icon
                         outlined>
                </q-input>
                <q-input v-model.trim="form.db_user" :label="$t('Database') + $t('Username')"
                         :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]"
                         class="col" no-error-icon
                         outlined>
                </q-input>
                <q-input v-model.trim="form.db_password" :label="$t('Database') + $t('Password')"
                         :rules="[(val) => (val && val.length > 0) || $t('NeedInput')]" autofocus
                         class="col" no-error-icon outlined
                         type="password">
                </q-input>
              </div>
            </div>
            <q-btn :label="$t('Start') + $t('Init')" :loading="initLoading" color="primary" type="submit"/>
          </q-form>
        </q-card-section>
      </q-card>
    </div>
  </transition>
</template>

<script setup>
import {ref} from 'vue'
import {useI18n} from 'vue-i18n'
import {useQuasar} from 'quasar'
import {postAction} from 'src/api/manage'
import {useStorageStore} from 'src/stores/storage'

const {t} = useI18n()
const $q = useQuasar()
const storageStore = useStorageStore()
const initLoading = ref(false)
const form = ref({
  db_type: 'Mysql',
  db_host: '127.0.0.1',
  db_port: '3306',
  db_schema: 'cili-quasar-admin',
  db_user: 'root',
  db_password: '74521CBy',
})

const emit = defineEmits(['initDbSuccess'])

const onInitDb = () => {
  initLoading.value = true
  postAction('public/init-db', form.value)
    .then((res) => {
      res = {...res, ...res.data}
      console.log(res.code)
      if (res.code === 1) {
        $q.notify({
          type: 'positive',
          message: t('Database') + t('Init') + t('Success'),
        })
        storageStore.SetCiliDict()
        storageStore.SetCiliFrontend()
        storageStore.SetCiliBackend()
        emit('initDbSuccess')
      } else {
        $q.notify({
          type: 'negative',
          message: res.message,
        })
      }

    })
    .finally(() => {
      initLoading.value = false
    })
}

</script>
