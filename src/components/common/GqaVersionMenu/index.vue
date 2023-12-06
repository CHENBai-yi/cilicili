<template>
  <q-menu v-model="showVersion" anchor="center middle" cover>
    <q-list bordered dense separator>
      <q-item v-close-popup v-ripple clickable>
        <q-item-section>
          <q-chip icon="img:icons/gqa32.png">
            GQA: {{ gqaVersion }}
          </q-chip>
        </q-item-section>
      </q-item>
      <q-item v-close-popup v-ripple clickable>
        <q-item-section>
          <q-chip icon="img:icon/vue.svg">
            Vue: {{ $vueVersion }}
          </q-chip>
        </q-item-section>
      </q-item>
      <q-item v-close-popup v-ripple clickable>
        <q-item-section>
          <q-chip icon="img:icon/quasar.svg">
            Quasar: {{ $quasarVersion }}
          </q-chip>
        </q-item-section>
      </q-item>
      <q-item v-close-popup v-ripple clickable>
        <q-item-section>
          <q-chip icon="img:icon/golang.svg">
            Go: {{ goVersion }}
          </q-chip>
        </q-item-section>
      </q-item>
      <q-item v-close-popup v-ripple clickable>
        <q-item-section>
          <q-chip icon="img:icon/gin.svg">
            Gin: {{ ginVersion }}
          </q-chip>
        </q-item-section>
      </q-item>
    </q-list>
  </q-menu>
</template>

<script setup>
import {computed, ref} from 'vue';
import {useStorageStore} from 'src/stores/storage'
import config from '../../../../package.json'

const storageStore = useStorageStore()
const showVersion = ref(false)
const goVersion = computed(() => storageStore.GetGqaGoVersion())
const ginVersion = computed(() => storageStore.GetGqaGinVersion())
const gqaVersion = config.version
</script>
