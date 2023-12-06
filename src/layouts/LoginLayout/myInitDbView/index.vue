<template>
  <div class="q-pa-md q-gutter-sm">
    <q-dialog
      v-model="dialog"
      :maximized="maximizedToggle"
      persistent
      transition-hide="slide-down"
      transition-show="slide-up"
    >
      <q-card :class="darkTheme">
        <q-bar>
          <q-space/>

          <q-btn :disable="!maximizedToggle" dense flat icon="minimize" @click="maximizedToggle = false">
            <q-tooltip v-if="maximizedToggle" class="bg-white text-primary">Minimize</q-tooltip>
          </q-btn>
          <q-btn :disable="maximizedToggle" dense flat icon="crop_square" @click="maximizedToggle = true">
            <q-tooltip v-if="!maximizedToggle" class="bg-white text-primary">Maximize</q-tooltip>
          </q-btn>
          <q-btn v-close-popup dense flat icon="close">
            <q-tooltip class="bg-white text-primary">Close</q-tooltip>
          </q-btn>
        </q-bar>

        <q-card-section>
          <div class="text-h6" style="">{{ $t('InitDbHelp1') }}</div>
        </q-card-section>

        <div class="q-pa-md">
          <div class="row justify-center items-center content-center">
            <div class="col-10 col-md-auto">
              <q-card-section>
                <InitDbViewForm @initDbSuccess="initDbSuccess"/>
              </q-card-section>
            </div>

            <div class="col-2 col-md-auto">
              <div class="text-h6" style="">{{ $t('InitDbHelp2') }}</div>
            </div>
          </div>
        </div>

      </q-card>

    </q-dialog>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import InitDbViewForm from '../InitDbView/InitDbViewForm.vue'
import {useRouter} from "vue-router";
import {postAction} from "src/api/manage";
import useTheme from 'src/composables/useTheme';

const {darkTheme} = useTheme()


const maximizedToggle = ref(true)
const dialog = ref(false)
const router = useRouter()

onMounted(() => {
  checkDb()
})
const checkDb = async () => {
  const res = await postAction('public/check-db')
  console.log(res, "check-db")
  console.log(res.code, "ada")
  if (res.code === 1) {
    if (res.data.need_init === false) {
      await router.push({path: '/'})
    } else {
      dialog.value = !dialog.value
    }
  }
}

const emit = defineEmits(['initDbSuccess'])
const initDbSuccess = () => {
  emit('initDbSuccess')
}
</script>
