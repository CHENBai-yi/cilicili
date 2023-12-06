<template>
  <q-scroll-area :class="darkThemeSideBar" style="height: calc(100%);">
    <slot/>
    <q-list :dense="sidebarDense" class="menu-list">
      <template v-for="(childrenItem, index) in topMenuChildren" :key="index">
        <SideBarLeftItem :childrenItem="childrenItem" :initLevel="0"/>
      </template>
    </q-list>
  </q-scroll-area>
</template>

<script setup>
import {computed, toRefs} from 'vue';
import SideBarLeftItem from './SideBarLeftItem.vue'
import useTheme from 'src/composables/useTheme';
import {useSettingStore} from 'src/stores/setting';

const settingStore = useSettingStore();
const {darkThemeSideBar} = useTheme()
const props = defineProps({
  topMenuChildren: {
    type: Object || Array,
    required: false,
    default: () => {
      return null
    },
  },
})
const sidebarDense = computed(() => settingStore.GetSidebarDense())
const {topMenuChildren} = toRefs(props)
</script>

<style lang="scss" scoped>
.menu-list :v-deep(.q-item) {
  border-radius: 0 32px 32px 0
}
</style>
