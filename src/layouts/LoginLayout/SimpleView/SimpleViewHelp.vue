<template>
  <transition appear enter-active-class="animated slideInRight" leave-active-class="animated slideOutRight">
    <div class="col column justify-center items-center" style="margin-top: -120px">
      <CiliLottie :src="robot"/>
      <div class="col column q-gutter-y-xs" style="margin-left: 90px;">
                <span class="text-weight-bold text-h5" style="margin-top: -40px">
                    {{ $t('WelcomeTo') }}
                    {{ CiliFrontend.subTitle }}
                </span>
        <div class="row justify-start">
          <q-chip v-for="item in pluginList" class="glossy" color="primary" style="cursor: pointer;"
                  text-color="white">
            {{ item.plugin_name }}
            <q-tooltip>
              <q-badge>
                {{ item.plugin_version }}
              </q-badge>
              {{ item.plugin_memo }}
            </q-tooltip>
          </q-chip>
        </div>
        <span class="text-subtitle1">
                    {{ CiliFrontend.webDescribe }}
                </span>
      </div>
    </div>
  </transition>
</template>

<script setup>
import {computed} from 'vue';
import CiliLottie from 'src/components/CiliLottie/index.vue'
import robot from 'src/assets/robot.json'
import useCommon from 'src/composables/useCommon'
import {useStorageStore} from 'src/stores/storage'

const {CiliFrontend} = useCommon()
const storageStore = useStorageStore()

const pluginList = computed(() => {
  return storageStore.GetCiliPluginList()
})
</script>
