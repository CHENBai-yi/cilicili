<template>
  <q-field :label="$t('Sidebar') + $t('Width') + ' ' + drawerWidth" stack-label>
    <template v-slot:control>
      <q-slider v-model="drawerWidth" :max="300" :min="200" @change="setDrawerWidth"/>
    </template>
  </q-field>
</template>

<script setup>
import {useSettingStore} from 'src/stores/setting';
import {onMounted, ref} from 'vue';

const settingStore = useSettingStore()

const drawerWidth = ref(220)
const setDrawerWidth = () => {
  settingStore.SetSidebarWidth(drawerWidth.value)
}

onMounted(() => {
  drawerWidth.value = settingStore.GetSidebarWidth()
})
</script>
