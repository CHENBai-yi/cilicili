<template>
  <q-btn dense flat icon="bi-grid" round @click="showPluginDialog">
    <q-tooltip>
      {{ $t('Switch') + ' ' + $t('Plugin') }}
    </q-tooltip>
  </q-btn>
  <q-dialog v-model="pluginDialogVisible" position="top">
    <q-card style="width: 1080px; max-width: 80vw;">
      <q-card-section>
        <div class="text-h6">
          {{ $t('Switch') + $t('Plugin') }}
        </div>
      </q-card-section>
      <q-card-section class="row items-stretch justify-start q-gutter-md">
        <q-btn v-for="item in topMenu" :key="item.id" class="q-hoverable cursor-pointer text-center"
               @click="changePlugin(item)">
          <div class="column items-center">
            <q-icon v-if="currentTopMenu === item.name" class="absolute all-pointer-events" color="negative"
                    name="check_box"
                    size="30px" style="top: 2px; right: 2px"/>
            <div class="q-focus-helper"></div>
            <q-avatar :icon="item.icon" size="80px"/>
            <div>{{ $t(item.title) }}</div>
          </div>
        </q-btn>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup>
import {computed, onMounted, ref} from 'vue';
import {usePermissionStore} from 'src/stores/permission';
import {useRoute} from 'vue-router';
import XEUtils from 'xe-utils'

const permissionStore = usePermissionStore();
const route = useRoute();

const currentTopMenu = ref('');

const findCurrentTopMenu = computed(() => {
  const name = route.name
  return XEUtils.searchTree(topMenu.value, item => item.name === name)[0]
})


const topMenu = computed(() => {
  return permissionStore.topMenu
})

onMounted(() => {
  currentTopMenu.value = findCurrentTopMenu.value?.name

})

const pluginDialogVisible = ref(false)
const showPluginDialog = () => {
  pluginDialogVisible.value = true
}

const emit = defineEmits(['changePlugin'])
const changePlugin = (item) => {
  currentTopMenu.value = item.name
  emit('changePlugin', item)
  pluginDialogVisible.value = false
}
</script>
