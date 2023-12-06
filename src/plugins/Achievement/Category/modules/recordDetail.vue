<template>
  <q-dialog v-model="recordDetailVisible" position="top">
    <q-card style="width: 1400px; max-width: 80vw;">
      <q-card-section>
        <div class="text-h6">
          {{ formTypeName }}Obtain:
        </div>
      </q-card-section>

      <q-separator/>

      <q-card-section>
        <q-form ref="recordDetailForm">
          <gqa-form-top :recordDetail="recordDetail"></gqa-form-top>
          <div class="row">
            <q-input v-model="recordDetail.value.category" :rules="[val => val && val.length > 0 || '必须输入分类']"
                     class="col"
                     label="分类"/>
            <q-input v-model="recordDetail.value.code" :rules="[val => val && val.length > 0 || '必须输入成就代码']"
                     class="col"
                     label="成就代码"/>
            <q-input v-model="recordDetail.value.name" :rules="[val => val && val.length > 0 || '必须输入成就名']"
                     class="col"
                     label="成就名"/>
          </div>
          <div class="row">
            <q-input v-model="recordDetail.value.memo" class="col" label="成就描述" type="textarea"/>
          </div>
        </q-form>
      </q-card-section>

      <q-separator/>

      <q-card-actions align="right">
        <q-btn :label="$t('Save') + formTypeName" color="primary" @click="handleAddOrEidt"/>
        <q-btn v-close-popup :label="$t('Cancel')" color="negative"/>
      </q-card-actions>

      <q-inner-loading :showing="loading">
        <q-spinner-gears color="primary" size="50px"/>
      </q-inner-loading>
    </q-card>
  </q-dialog>
</template>

<script setup>
import useRecordDetail from 'src/composables/useRecordDetail'

const emit = defineEmits(['handleFinish'])
const url = {
  add: 'plugin-achievement/add-category',
  edit: 'plugin-achievement/edit-category',
  queryById: 'plugin-achievement/query-category-by-id',
}
const {
  formType,
  formTypeName,
  recordDetail,
  recordDetailVisible,
  loading,
  show,
  recordDetailForm,
  handleAddOrEidt
} = useRecordDetail(url, emit)

defineExpose({
  show,
  formType
})
</script>
