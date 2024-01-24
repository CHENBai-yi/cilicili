<template>
  <q-dialog v-model="recordDetailVisible" position="top" @hide="hide">
    <q-card style="width: 1400px; max-width: 80vw;">
      <q-card-section>
        <div class="text-h6">
          {{ recordDetail.value.notice_title }}
        </div>
      </q-card-section>

      <q-separator/>

      <q-card-section class="scroll q-gutter-md" style="max-height: 80vh">
        <q-form ref="recordDetailForm">
          <Cili-form-top :recordDetail="recordDetail"></Cili-form-top>
          <q-input v-model="recordDetail.value.notice_title" :label="$t('Title')"
                   :rules="[val => val && val.length > 0 || $t('NeedInput')]" readonly/>

          <q-select v-model="recordDetail.value.notice_type" :label="$t('Notice') + $t('Type')"
                    :options="dictOptions.noticeType"
                    :rules="[val => val && val.length > 0 || $t('NeedInput')]" emit-value
                    map-options readonly/>

          <q-input v-model="recordDetail.value.notice_content" :label="$t('Content')" readonly
                   type="textarea"/>
        </q-form>
      </q-card-section>

      <q-inner-loading :showing="loading">
        <q-spinner-gears color="primary" size="50px"/>
      </q-inner-loading>
    </q-card>
  </q-dialog>
</template>


<script setup>
import useRecordDetail from 'src/composables/useRecordDetail'

const emit = defineEmits(['handleFinish', 'hide'])
const url = {
  queryById: 'notice/query-notice-read-by-id',
}
const {
  bus,
  dictOptions,
  formType,
  recordDetail,
  recordDetailVisible,
  loading,
  show,
  recordDetailForm,
} = useRecordDetail(url, emit)

defineExpose({
  show,
  formType,
  recordDetail
})
const hide = () => {
  bus.emit('noticeGetTableData')
  emit('hide')
}
</script>
