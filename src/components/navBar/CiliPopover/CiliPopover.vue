<template>
  <el-popover
    :disabled="$q.screen.lt.lg"
    :effect="$q.dark.isActive?'dark':'light'"
    :show-arrow="false"
    :teleported="false"
    :width="200"
    placement="top"
    trigger="hover"
    @before-enter="beforeEnter"
    @after-enter="afterEnter"
  >
    <template #reference>
      <q-btn :class="clClass" :ripple="false" :style="clStyle" fab-mini flat rounded stack unelevated
             @click="clickEvent">
        <slot name="badge">

        </slot>
        <q-icon v-if="!!name" :class="{ 'example-fab-animate--hover': opened !== true }" :name=name>

        </q-icon>
        {{ label }}
      </q-btn>

    </template>
    <template #default>
      <slot></slot>
    </template>
  </el-popover>
</template>

<script setup>
import {ref} from 'vue'

defineProps({
  name: String,
  label: String,
  style: String,
  clClass: String,
  clStyle: String,
  to: String,
  clickEvent: Function

})
const opened = ref(true)
const beforeEnter = () => {
  opened.value = true

}
const afterEnter = () => {
  opened.value = false

}

</script>

<style lang="sass" scoped>
.example-fab-animate,
.q-btn:hover .example-fab-animate--hover
  animation: example-fab-animate 0.82s cubic-bezier(.36, .07, .19, .97) both
  transform: translate3d(0, 0, 0)
  backface-visibility: hidden
  perspective: 1000px
  transition-duration: 200ms
  transition-delay: 0ms


@keyframes example-fab-animate
  50%
    transform: translate3d(0, -4px, 0)

  100%
    transform: translate3d(0, 1px, 0)

</style>
