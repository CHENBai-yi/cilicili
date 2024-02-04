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
        <router-view></router-view>
        <CiliBackToTop/>
      </q-page-container>
    </q-pull-to-refresh>
  </q-layout>
</template>
<script setup>
import useTheme from "src/composables/useTheme"

const {darkTheme} = useTheme()
const refresh = (done) => {
  setTimeout(() => {

    done()
  }, 1000)
}
</script>

