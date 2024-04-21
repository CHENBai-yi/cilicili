<template>
  <u-notice-bar
    :data="noticeList"
    background="#ecf5ff"
    color="#409eff"
    prefix-icon="dianzan"
    suffix-icon="comment"
  ></u-notice-bar>
</template>
<script lang="ts" setup>
import {UNoticeBar} from 'undraw-ui'
import {ref} from 'vue'

const noticeList = ref([
  '🔥有一条深海鱼在往深处游，游着游着它就哭了，因为它觉得压力好大。',
  '请手机自我检讨一下，为什么总拉着我一起熬夜？',
  '历史老师：你为什么交白卷？我：我怕我会篡改历史',
  '今天天气很好，在房间里宅久了，准备去客厅散散心。'
])
</script>
