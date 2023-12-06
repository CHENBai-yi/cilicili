<template>
  <q-select v-if="searchVisible" v-model="currentMenu" :option-label="opt => selectOptionLabel(opt)"
            :options="searchMenu" borderless dense input-debounce="0"
            map-options option-value="name" outlined style="width: 200px;" use-input
            @filter="filterFn" @update:model-value="changeRouter">
  </q-select>
  <q-btn dense flat icon="ion-ios-search" round @click="showSearch">
    <q-tooltip>
      {{ $t('Search') + ' ' + $t('Menu') }}
    </q-tooltip>
  </q-btn>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import {usePermissionStore} from 'src/stores/permission';
import useCommon from 'src/composables/useCommon'
import {useRouter} from 'vue-router';
import {useI18n} from 'vue-i18n';

const permissionStore = usePermissionStore();
const router = useRouter()
const {t} = useI18n()

const {selectOptionLabel} = useCommon()

const searchVisible = ref(false)
const showSearch = () => {
  searchVisible.value = !searchVisible.value
}

const searchMenu = ref([])
onMounted(() => {
  searchMenu.value = permissionStore.searchMenu
})

const currentMenu = ref(null)
const changeRouter = (item) => {
  if (item.is_link === 'yesNo_yes') {
    window.open(item.path, '_blank')
  } else {
    router.push({path: item.path})
  }
  searchVisible.value = false
}

const filterFn = (val, update) => {
  if (val === '') {
    update(() => {
      searchMenu.value = permissionStore.searchMenu
    })
    return
  }
  update(() => {
    searchMenu.value = permissionStore.searchMenu.filter(v => {
      return t(v.title).indexOf(val) > -1
    })
  })
}
</script>
