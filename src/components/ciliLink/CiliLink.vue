<template>
  <el-link :href="href" :target="target" :underline="false">
    <slot></slot>
  </el-link>
</template>

<script setup>
defineProps({
  size: {
    type: String,
    default: '14px'
  },
  font: {
    type: String
  },
  color: {
    type: String,
    default: '#606266'
  },
  href: {
    type: String
  },
  weight: {
    type: Number
  },
  target: {
    type: String,
    default: '_blank'
  }
})
</script>

<style lang="scss" scoped>
* {
  --el-link-text-color: v-bind(color);
  --el-link-font-size: v-bind(font);
  font-family: PingFang SC, HarmonyOS_Medium, Helvetica Neue, Microsoft YaHei, sans-serif;
}
</style>
