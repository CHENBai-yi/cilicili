<template>
  <q-dialog v-model="recordDetailVisible" position="top">
    <q-card style="width: 800px; max-width: 80vw;">
      <q-card-section class="text-h6">
        {{ formTypeName }} {{ $t('Role') }}:
        {{ recordDetail.value.role_name }}
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <Cili-form-top :recordDetail="recordDetail"></Cili-form-top>
        <q-form ref="recordDetailForm">
          <div class="row q-gutter-md">
            <q-input v-model.number="recordDetail.value.sort" :label="$t('Sort')"
                     :rules="[val => val >= 1 || $t('SortRule')]" class="col" hint=""
                     outlined type="number"/>
            <q-input v-model="recordDetail.value.role_code" :disable="formType === 'edit'"
                     :label="($t('Role') + $t('Code'))" :rules="[val => val && val.length > 0 || $t('NeetInput')]"
                     class="col" hint=""
                     outlined/>
            <q-input v-model="recordDetail.value.role_name" :label="($t('Role') + $t('Name'))"
                     :rules="[val => val && val.length > 0 || $t('NeetInput')]" class="col"
                     hint="" outlined/>
          </div>
          <q-input v-model="recordDetail.value.memo" :label="$t('Memo')" hint="" outlined type="textarea"/>
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
  add: 'role/add-role',
  edit: 'role/edit-role',
  queryById: 'role/query-role-by-id',
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
  formType,
  recordDetail
})
</script>
