<template>
  <q-dialog v-model="recordDetailVisible" position="top">
    <q-card style="width: 1000px; max-width: 80vw;">
      <q-card-section class="text-h6">
        {{ formTypeName }}{{ $t('Dict') }}:
        {{ recordDetail.value.dict_label }}
        <div v-if="recordDetail.value.parent_code" class="text-subtitle2 text-deep-orange">
          {{ $t('DictHelp') }} , {{ $t('WithoutOther') }} '_'
        </div>
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <gqa-form-top :recordDetail="recordDetail"></gqa-form-top>
        <q-form ref="recordDetailForm">
          <div class="row q-gutter-md">
            <q-input v-model.number="recordDetail.value.sort" :label="$t('Sort')"
                     :rules="[val => val >= 1 || $t('SortRule')]" class="col" hint=""
                     outlined type="number"/>
            <template v-if="recordDetail.value.parent_code">
              <q-input v-model="recordDetail.value.parent_code" :label="$t('Parent') + $t('Code')"
                       :rules="[val => val && val.length > 0 || $t('NeedInput')]" class="col"
                       disable hint="" lazy-rules
                       outlined/>
            </template>
          </div>
          <div class="row q-gutter-md">
            <q-input v-model="recordDetail.value.dict_label" :label="$t('Dict') + $t('Name')"
                     :rules="[val => val && val.length > 0 || $t('NeedInput')]" class="col"
                     hint="" lazy-rules
                     outlined/>
            <template v-if="recordDetail.value.parent_code">
              <q-input v-model="recordDetail.value.dict_code" :rules="[val =>
                                val && val.length > 0
                                && val.indexOf(recordDetail.value.parent_code + '_') === 0
                                && val.slice(recordDetail.value.parent_code.length + 1).indexOf('_') === -1
                                || $t('FixForm')]" class="col" hint="" label-slot
                       lazy-rules outlined>
                <template v-slot:label>
                                    <span class="text-weight-bold text-deep-orange">
                                        {{ $t('Dict') }}{{ $t('Code') }}
                                        ( {{ $t('StartWith', {name: recordDetail.value.parent_code + '_ '}) }},
                                        {{ $t('WithoutOther') }} _ )
                                    </span>
                </template>
              </q-input>
            </template>
            <!-- val && val.length > 0 && val.indexOf(recordDetail.value.parent_code) !== 0        || $t('NeedInput')-->
            <template v-else>
              <q-input v-model="recordDetail.value.dict_code" :label="$t('Dict') + $t('Code')"
                       :rules="[val => val && val.length > 0 || $t('NeedInput')]" class="col"
                       hint="" lazy-rules
                       outlined/>
            </template>
          </div>
          <div class="row q-gutter-md">
            <q-input v-model="recordDetail.value.dict_ext_1" class="col" hint="" label="Ext1" lazy-rules
                     outlined/>
            <q-input v-model="recordDetail.value.dict_ext_2" class="col" hint="" label="Ext2" lazy-rules
                     outlined/>
            <q-input v-model="recordDetail.value.dict_ext_3" class="col" hint="" label="Ext3" lazy-rules
                     outlined/>
            <q-input v-model="recordDetail.value.dict_ext_4" class="col" hint="" label="Ext4" lazy-rules
                     outlined/>
            <q-input v-model="recordDetail.value.dict_ext_5" class="col" hint="" label="Ext5" lazy-rules
                     outlined/>
          </div>
          <div class="row q-gutter-md">
            <q-field :label="$t('Status')" class="col" hint="" outlined stack-label>
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
          <q-input v-model="recordDetail.value.memo" :label="$t('Memo')" hint="" outlined type="textarea"/>
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
  add: 'dict/add-dict',
  edit: 'dict/edit-dict',
  queryById: 'dict/query-dict-by-id',
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
</script>
