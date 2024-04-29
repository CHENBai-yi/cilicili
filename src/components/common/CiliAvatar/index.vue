<template>
  <q-avatar :size="size">
    <img :src="avatarSrc"/>
  </q-avatar>
</template>

<script setup>
import {computed, toRefs} from 'vue';
import {useQuasar} from 'quasar';
import {RealUrl} from "src/utils/convert"

const $q = useQuasar();
const props = defineProps({
  loginUser: {
    type: Boolean,
    required: false,
    default: false,
  },
  src: {
    type: String,
    required: false,
    default: '',
  },
  size: {
    type: String,
    required: false,
    default: 'md', // 'xs', 'sm', 'md', 'lg', 'xl'， '**px'
  },
})
const {loginUser, src, size} = toRefs(props)

const avatarSrc = computed(() => {
  const cookieAvatar = $q.cookies.get('cili-avatar')
  if (loginUser.value) {
    // Login user's avatar
    return RealUrl(cookieAvatar)
  } else {
    return RealUrl(src.value)
  }
})
</script>
