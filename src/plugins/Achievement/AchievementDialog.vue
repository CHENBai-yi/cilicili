<template>
  <q-dialog v-model="visible" persistent position="bottom" transition-hide="rotate" transition-show="rotate">
    <CiliCard endColor="#f68057" startColor="#fcbc25" style="width: 500px; box-shadow: 0 0 50px 10px #fcbc25;">
      <q-card-section>
                <span class="row justify-center text-h6 text-bold text-white">
                    新成就解锁
                </span>
        <q-separator dark spaced/>
        <div class="row items-center">
          <q-chip class="glossy col-2" color="orange-8" icon="emoji_events" text-color="white">
            {{ category }}
          </q-chip>
          <div class="col text-dark row justify-center">
            {{ name }}
          </div>
          <q-chip class="glossy col-2" color="orange-8" icon="star" text-color="white">
            80
          </q-chip>
        </div>
      </q-card-section>
    </CiliCard>
  </q-dialog>
</template>

<script setup>
import CiliCard from 'src/components/CiliCard/index.vue'
import {onBeforeUnmount, ref} from 'vue';

const visible = ref(false)

const category = ref("")
const name = ref("")
const timer = ref(null)
const show = (detail) => {
  category.value = detail?.category
  name.value = detail?.name
  visible.value = true
  timer.value = setTimeout(() => {
    visible.value = false
  }, 4000)
}

onBeforeUnmount(() => {
  clearTimeout(timer.value)
})

defineExpose({
  show
})
</script>
