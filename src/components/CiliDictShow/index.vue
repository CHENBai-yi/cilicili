<template>
  <div>
    <q-chip v-if="dictCode === 'onOffPass_pass'" color="positive" dense text-color="white">
      {{ $t(dictLabel) }}
    </q-chip>
    <q-chip v-else-if="dictCode === 'onOffPass_off'" color="negative" dense text-color="white">
      {{ $t(dictLabel) }}
    </q-chip>
    <span v-else-if="dictCode === 'yesNo_yes'
        || dictCode === 'yesNo_no'
        || dictCode === 'gender_male'
        || dictCode === 'gender_female'
        || dictCode === 'gender_unknown'">
            {{ $t(dictLabel) }}
        </span>
    <span v-else>
            {{ dictLabel }}
        </span>
  </div>
</template>

<script setup>
import {computed} from 'vue'

const props = defineProps({
  dictCode: {
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
const dictLabel = computed(() => {
  switch (props.dictCode) {
    case 'onOffPass_on':
      return '待审核';
    case 'onOffPass_off':
      return '未通过';
    case 'onOffPass_pass':
      return '通过';
  }
})
</script>
