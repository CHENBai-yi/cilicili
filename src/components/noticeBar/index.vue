<template>
  <transition appear
              leave-active-class="animated animate__fadeOut">
    <div v-if="!!noticeList && noticeList.length>0" class="flex row no-wrap content-center items-center q-pl-sm">

      <q-icon color="negative" name="campaign" size="25px"/>
      <transition appear
                  enter-active-class="animated animate__fadeInRight"
                  leave-active-class="animated animate__fadeOut">
        <u-notice-bar
          :class="darkTheme"
          :data="noticeList"
          :delay="3000"
          :height="28"
          :size="18"
          :spped="100"
          background="#ecf5ff"
          class="full-width"
          color="#409eff"
          style="padding-left:2px"
        >
        </u-notice-bar>
      </transition>

    </div>
  </transition>
</template>
<script setup>
import {UNoticeBar} from 'undraw-ui'
import {computed} from 'vue'
import {useStorageStore} from 'src/stores/storage'
import useTheme from "src/composables/useTheme"

const {darkTheme} = useTheme()
const storageStore = useStorageStore();
const noticeList = computed(() => {
  if (!!storageStore.CiliAdvertisingText) {
    setTimeout(() => {
      storageStore.DelCiliAdvertisingText()
    }, 30000)
  }
  return storageStore.CiliAdvertisingText
})
</script>
<style lang="scss" scoped>
:deep(.el-carousel__item) {
  align-items: flex-start;
}

:deep(.u-notice-bar-wrap .text-box .text) {
  white-space: nowrap;
  position: relative;
  left: 0;
}

</style>
