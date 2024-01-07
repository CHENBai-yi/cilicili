<template>
  <div :style="{ color: $q.dark.isActive ? '#fff' : '#000' }" class="row justify-center items-end gqa-footer">
    {{ gqaFrontend.subTitle }}
    is powered by&nbsp;
    <a :style="{ color: $q.dark.isActive ? '#fff' : '#000' }" href="https://gitee.com/cby-cby/cilicili"
       style="text-decoration: none"
       target="_blank">
      Gin-Quasar-Admin
      {{ gqaVersion }}
    </a>
  </div>
</template>

<script setup>
import {computed, ref} from 'vue';
import {useStorageStore} from 'src/stores/storage'
import useTheme from 'src/composables/useTheme';
import config from '/package.json'

const {darkTheme} = useTheme()
const storageStore = useStorageStore()
const gqaFrontend = computed(() => storageStore.GetGqaFrontend())
const gqaVersion = ref(config.version)
</script>

<style lang="scss" scoped>
.gqa-footer {
  height: 20px;
  font-size: 10px;
  background: rgba($color: #000000, $alpha: 0) !important;
}
</style>
