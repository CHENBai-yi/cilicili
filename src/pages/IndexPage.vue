<template xmlns="http://www.w3.org/1999/html">
  <q-page style="overflow: hidden">
    <NavBar/>
    <cili-video-layout/>
    <CiliAdvertisingBar/>
  </q-page>
</template>
<script setup>
import CiliVideoLayout from 'src/components/CiliVideoLayout/CiliVideoLayout.vue'
import CiliAdvertisingBar from 'src/components/CiliAdvertisingBar/index.vue'</script>
<style lang="scss" scoped>
:deep(video) {
  object-fit: cover;
}
</style>


