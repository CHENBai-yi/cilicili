<template>
  <u-notice-bar v-if="!!noticeList"
                :data="noticeList"
                color="#409eff"
                height="14"
                size="14"
                suffix-icon="comment"

  >
  </u-notice-bar>
</template>
<script setup>
import {UNoticeBar} from 'undraw-ui'
import {ref, onMounted} from 'vue'
import {useStorageStore} from 'src/stores/storage'

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
