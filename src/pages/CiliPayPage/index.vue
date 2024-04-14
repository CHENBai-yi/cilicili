<template>
  <q-page class="overflow-hidden ">
    <cili-tool-bar class="shadow-24"/>
    <!--    <div class="row justify-center   q-mt-xs q-gutter-md ">-->
    <div class="row justify-center   q-mt-xs q-gutter-md container_d">
      <PayInfoComponent/>
    </div>
  </q-page>

</template>

<script setup>
import {onMounted} from 'vue'
import {useCommonStore} from 'src/stores/common'
import PayInfoComponent from 'src/components/ciliPayComponent/PayInfoComponent.vue'

const commonStore = useCommonStore()

onMounted(() => {
  if (!!commonStore.getCurrentVideoAddress()) {
    window.location.href = commonStore.getCurrentVideoAddress()
    console.log(commonStore.getCurrentVideoAddress())
  }
})
</script>

<style scoped>

</style>
