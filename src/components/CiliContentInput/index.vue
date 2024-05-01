<template>
  <div v-if="recordDetail.content_type === 'contentType_image'">
    <CiliUpload :multiple="false" :title="$t('Upload')+dictLabel" @setUrl="setUrl"/>
  </div>
  <div v-if="recordDetail.content_type === 'contentType_text'">
    <q-input v-model="recordDetail.content" :label="$t('Input')+dictLabel" class="col" hint="" outlined/>
  </div>
</template>

<script setup>
import {computed, onBeforeMount, ref, toRef, toRefs} from 'vue';
import {useQuasar} from 'quasar';
import {useStorageStore} from 'src/stores/storage';
import CiliUpload from 'src/components/CiliUpload/index.vue'
import XEUtils from 'xe-utils'

const storageStore = useStorageStore();
const $q = useQuasar()
const props = defineProps({
  recordDetail: {
    type: Object,
    default: {},
  }
})
const {recordDetail} = toRefs(props)
const dictList = ref([])

onBeforeMount(async () => {
  const detailLocal = $q.localStorage.getItem('cili-dict')
  if (detailLocal) {
    dictList.value = detailLocal
  } else {
    await storageStore.SetCiliDict()
    dictList.value = $q.localStorage.getItem('cili-dict')
  }
})

const dictLabel = computed(() => {
  const dictCode = toRef(recordDetail.value, "content_type")
  if (dictCode.value !== '') {
    for (let dict in dictList.value) {
      const dictDetail = XEUtils.findTree(dictList.value[dict], item => item.dict_code === dictCode.value)
      if (dictDetail) {
        return dictDetail.item.dict_label
      }
    }
    return ""
  } else {
    return ""
  }
})
const setUrl = (url) => {
  recordDetail.value.content = url
}
</script>
