<template>

  <el-link :disabled="disabled" :href="href" :target="target" :underline="false">
    <slot></slot>
  </el-link>
</template>

<script setup>
import useTheme from "src/composables/useTheme"

const {darkTheme} = useTheme()
defineProps({
  size: {
    type: String,
    default: '14px'
  },
  color: {
    type: String,
    default: '#606266'
  },
  href: {
    type: String
  },
  weight: {
    type: Number,
    default: 500
  },
  target: {
    type: String,
    default: '_blank'
  },
  disabled: {
    type: Boolean,
    default: false
  }
})
</script>

<style lang="scss" scoped>
a {
  font-size: v-bind(size);
  font-family: PingFang SC, HarmonyOS_Medium, Helvetica Neue, Microsoft YaHei, sans-serif;
  font-weight: v-bind(weight);
  color: v-bind(color);
}

a:hover,
a:active {
  color: var(--el-color-primary);
}
</style>
