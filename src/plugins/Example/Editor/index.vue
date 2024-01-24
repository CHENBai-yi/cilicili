<template>
  <q-page padding>
    <CiliEditor v-model="text"/>
  </q-page>
</template>

<script setup>
import {ref} from 'vue';
import CiliEditor from 'src/components/CiliEditor/index.vue'

const text = ref('<p>Hello Gin-Quasar-Admin</p>')
</script>
