<template>
  <div v-if="!!content">
    <div v-if="dictCode === 'contentType_image'">
      <n-image
        :src="RealUrl(content)"
        height="80"
        lazy
        show-toolbar-tooltip
        style="max-width: 120px"
        width="120"
      />
    </div>
    <span v-if="dictCode === 'contentType_text'">
         <n-ellipsis style="max-width: 120px">
    {{ content }}
  </n-ellipsis>
      </span>
  </div>
  <div v-else>
    {{ dictLabel }}
  </div>
</template>

<script setup>
import {computed, onBeforeMount, ref, toRefs} from 'vue';
import {useQuasar} from 'quasar';
import {useStorageStore} from 'src/stores/storage';
import XEUtils from 'xe-utils'
import {RealUrl} from 'src/utils/convert'

const storageStore = useStorageStore();
const $q = useQuasar()
const props = defineProps({
  dictCode: {
    type: String,
    default: '',
  }, content: {
    type: String,
    default: '',
  },
  withExt1: {
    type: Boolean,
    required: false,
    default: false,
  },
  ext1: {
    type: String,
    default: '',
  },
})
const {dictCode, content, withExt1, ext1} = toRefs(props)
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
</script>
