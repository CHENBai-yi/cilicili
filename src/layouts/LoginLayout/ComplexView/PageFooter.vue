<template>
  <footer>
    <q-img :src="bannerImage" fit="cover" style="width: 100%; height: 5vh;">
      <div class="row items-center justify-center no-wrap" style="width: 100%; height: 100%;">
        {{ CiliFrontend.subTitle }}
        is powered by&nbsp;
        <a href="https://gitee.com/cby-cby/cilicili" style="color: white; text-decoration: none;"
           target="_blank">
          Gin-Quasar-Admin
          {{ CiliVersion }}
        </a>
      </div>
    </q-img>
  </footer>
</template>

<script setup>
import {computed} from 'vue';
import {useStorageStore} from 'src/stores/storage'
import config from '../../../../package.json'

const CiliVersion = config.version
const storageStore = useStorageStore()
const CiliFrontend = computed(() => storageStore.GetCiliFrontend())
const bannerImage = computed(() => {
  if (CiliFrontend.value.bannerImage && CiliFrontend.value.bannerImage.substring(0, 12) === 'cili-upload:') {
    return process.env.API + CiliFrontend.value.bannerImage.substring(12)
  }
  return "images/planet.png"
})

</script>

<style lang="scss" scoped>
footer {
  position: relative;
  z-index: 0;
  overflow: hidden;
  color: #ffffff;
}
</style>
