<template>
  <q-layout :class="darkTheme" class="shadow-2 rounded-borders" style="overflow-x: hidden;"
            view="lHh lpr lFf"> <!-- Be sure to play with the Layout demo on docs -->
    <q-header :class="darkTheme">
      <cili-tool-bar class="shadow-24"/>
    </q-header>
    <q-page-container>
      <router-view v-slot="{ Component }">
        <component :is="Component"/>
        <q-inner-loading :showing="componentLoading">
          <q-spinner-bars
            :class="darkTheme" size="3em"
          />
        </q-inner-loading>
      </router-view>
    </q-page-container>
    <CiliBackToTop/>
  </q-layout>
</template>

<script setup>
import useTheme from "src/composables/useTheme"
import {inject, nextTick, onMounted, ref} from "vue";

const {darkTheme} = useTheme()
const componentLoading = ref(true)
const bus = inject('bus')
onMounted(() => {
  bus.on('changeRoute', (val) => {
    componentLoading.value = val
  })
})

nextTick(() => {
  setTimeout(() => {
    componentLoading.value = false
  }, 500)
})
</script>
