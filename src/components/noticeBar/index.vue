<template>
  <div class="flex row no-wrap content-center items-center q-pl-sm">
    <q-icon color="negative" name="campaign" size="25px"/>
    <u-notice-bar v-if="!!noticeList && noticeList.length>0"
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
  </div>
</template>
<script setup>
import {UNoticeBar} from 'undraw-ui'
import {onMounted, ref} from 'vue'
import {useStorageStore} from 'src/stores/storage'
import useTheme from "src/composables/useTheme"

const {darkTheme} = useTheme()
const storageStore = useStorageStore();
const noticeList = ref('')
/*'🔥有一条深海鱼在往深处游，游着游着它就哭了，因为它觉得压力好大。',
  '请手机自我检讨一下，为什么总拉着我一起熬夜？',
  '历史老师：你为什么交白卷？我：我怕我会篡改历史',
  '今天天气很好，在房间里宅久了，准备去客厅散散心。'*/
onMounted(() => {
  //@ts-ignore
  noticeList.value = storageStore.GetCiliAdvertisingText()

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
