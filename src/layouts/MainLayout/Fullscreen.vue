<template>
  <q-btn :icon="$q.fullscreen.isActive ? 'las la-compress' : 'ion-ios-qr-scanner'" dense flat round
         @click="$q.fullscreen.toggle()">
    <q-tooltip>
      {{ $t('FullScreen') }}
    </q-tooltip>
  </q-btn>
</template>

<script setup></script>
