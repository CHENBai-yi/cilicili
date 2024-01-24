<template>
  <q-dialog v-model="recordDetailVisible" position="top">
    <q-card style="width: 800px; max-width: 80vw;">
      <q-card-section class="text-h6">
        {{ formTypeName }} {{ $t('Api') }}:
        {{ recordDetail.value.api_path }}
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <q-form ref="recordDetailForm">
          <Cili-form-top :recordDetail="recordDetail"></Cili-form-top>
          <div class="row q-gutter-md">
            <q-input v-model.number="recordDetail.value.sort" :label="$t('Sort')"
                     :rules="[val => val >= 1 || $t('SortRule')]" class="col" hint=""
                     outlined type="number"/>
            <q-input v-model="recordDetail.value.api_group" :label="$t('Api') + $t('Group')"
                     :rules="[val => val && val.length > 0 || $t('NeedInput')]" class="col"
                     hint=""
                     outlined/>
            <q-select v-model="recordDetail.value.api_method" :label="$t('Api') + $t('Method')" :options="methodOptions"
                      :rules="[val => val && val.length > 0 || $t('NeedInput')]"
                      class="col" hint=""
                      outlined/>
            <q-field :label="$t('Status')" class="col" dense hint="" outlined stack-label>
              <template v-slot:control>
                <q-option-group v-model="recordDetail.value.status" :options="dictOptions.onOff"
                                color="primary" inline>
                  <template v-slot:label="opt">
                    <div class="row items-center">
                      <span>{{ $t(opt.label) }}</span>
                    </div>
                  </template>
                </q-option-group>
              </template>
            </q-field>
          </div>
          <div class="row q-gutter-md">
            <q-input v-model="recordDetail.value.api_path" :label="$t('Api') + $t('Path')"
                     :rules="[val => val && val.length > 0 || $t('NeedInput')]" class="col"
                     hint=""
                     outlined/>
          </div>
          <q-input v-model="recordDetail.value.memo" :label="$t('Memo')" class="col" hint="" outlined/>
        </q-form>
      </q-card-section>
      <q-separator/>
      <q-card-actions align="right">
        <q-btn :label="$t('Save')" color="primary" @click="handleAddOrEidt"/>
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
  add: 'api/add-api',
  edit: 'api/edit-api',
  queryById: 'api/query-api-by-id',
}
const {
  dictOptions,
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

const methodOptions = ['POST']
</script>
