<template>
  <q-layout :class="darkTheme" style="overflow-x: hidden;"
            view="hhh Lpr lFr">
    <q-pull-to-refresh
      bg-color="black"
      color="orange-2"
      icon="autorenew"
      @refresh="refresh"
    >
      <q-page-container style="overflow: hidden;">
        <router-view v-slot="{ Component }">
          <component :is="Component"/>
          <q-inner-loading :showing="componentLoading">
            <q-spinner-bars class="fixed" size="3em" style="top:50%;left:50%"/>
          </q-inner-loading>

        </router-view>
      </q-page-container>
      <CiliBackToTop/>
    </q-pull-to-refresh>
  </q-layout>
</template>
<script setup>
import useTheme from "src/composables/useTheme"

import {inject, nextTick, onMounted, ref} from "vue";

const componentLoading = ref(true)
const bus = inject('bus')
onMounted(() => {
  bus.on('changeRoute', (val) => {
    componentLoading.value = val
  })
})
const {darkTheme} = useTheme()
const refresh = (done) => {
  setTimeout(() => {
    done()
  }, 1000)
}

nextTick(() => {
  setTimeout(() => {
    componentLoading.value = false
  }, 500)
})
</script>

