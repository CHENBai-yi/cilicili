<template>
  <div ref="CiliLottie" :style="{ height: height, width: width }"/>
</template>

<script setup>
import {ref, toRefs, watch} from 'vue';
import lottie from 'lottie-web'

const props = defineProps({
  src: {
    type: Object,
    default: () => {
    },
  },
  path: {
    type: String,
    default: '',
  },
  width: {
    type: String,
    default: '450px'
  },
  height: {
    type: String,
    default: '450px'
  },
})
const {src, path, height} = toRefs(props)
const CiliLottie = ref(null)
watch(CiliLottie, () => {
  show()
})
const show = () => {
  lottie.loadAnimation({
    container: CiliLottie.value,
    renderer: 'svg',
    loop: true,
    autoplay: true,
    animationData: src.value,
    path: path.value,
  })
}
</script>
