<template>
  <n-carousel autoplay class="rounded-borders" show-arrow style="height:525px;min-height:525px">
    <a v-for="(url,index) in urls" :href="url.link">
      <img
        :src=url.img
        class="carousel-img"
      >
    </a>
    <template #arrow="{ prev, next }">
      <div class="custom-arrow">
        <button class="custom-arrow--left" type="button" @click="prev">
          <q-icon name="arrow_back_ios"></q-icon>
        </button>
        <button class="custom-arrow--right" type="button" @click="next">
          <q-icon name="arrow_forward_ios"></q-icon>
        </button>
      </div>
    </template>
    <template #dots="{ total, currentIndex, to }">
      <ul class="custom-dots">
        <li
          v-for="index of total"
          :key="index"
          :class="{ ['is-active']: currentIndex === index - 1 }"
          @click="to(index - 1)"
        />
      </ul>
    </template>
  </n-carousel>
</template>

<script setup>
import {computed} from 'vue'
import {useStorageStore} from 'src/stores/storage'

const storageStore = useStorageStore();
const urls = computed(() => {
  if (storageStore.GetCiliCarousel()) {
    return storageStore.GetCiliCarousel()
  }
  return storageStore.CiliCarousel
})
</script>

<style scoped>

</style>
