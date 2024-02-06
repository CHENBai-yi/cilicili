<template>
  <n-carousel autoplay class="rounded-borders" show-arrow>
    <img v-for="(url,index) in urls"
         :src=url
         class="carousel-img"
    >
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
import {reactive} from 'vue'

const urls = reactive([
  "https://naive-ui.oss-cn-beijing.aliyuncs.com/carousel-img/carousel1.jpeg",
  "https://naive-ui.oss-cn-beijing.aliyuncs.com/carousel-img/carousel2.jpeg",
  "https://naive-ui.oss-cn-beijing.aliyuncs.com/carousel-img/carousel3.jpeg",
  "https://naive-ui.oss-cn-beijing.aliyuncs.com/carousel-img/carousel4.jpeg"
])
</script>

<style scoped>

</style>
