<template>
  <n-popconfirm :positive-text="null" :negative-text="null" >
    <template #trigger>
      <n-button text type="warning" >未通过原因</n-button>
    </template>
    {{reason}}
  </n-popconfirm>
</template>

<script setup>
import {ref} from 'vue'
const show=ref(false)
defineProps({
  reason:String
})
</script>

<style scoped>

</style>
