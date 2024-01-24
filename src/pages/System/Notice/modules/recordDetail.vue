<template>
  <q-dialog v-model="recordDetailVisible" position="top">
    <q-card style="width: 1400px; max-width: 80vw;">
      <q-card-section class="text-h6">
        {{ formTypeName }} {{ $t('Notice') }}:
        {{ recordDetail.value.notice_title }}
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <Cili-form-top :recordDetail="recordDetail"></Cili-form-top>
        <q-form ref="recordDetailForm">
          <div class="row q-gutter-md">
            <q-input v-model="recordDetail.value.notice_title" :label="$t('Title')"
                     :rules="[val => val && val.length > 0 || $t('NeedInput')]" class="col"
                     hint="" outlined/>
            <q-select v-model="recordDetail.value.notice_type" :label="$t('Notice') + $t('Type')"
                      :options="dictOptions.noticeType" :rules="[val => val && val.length > 0 || $t('NeedInput')]"
                      class="col" emit-value hint=""
                      map-options
                      outlined/>
          </div>
          <q-input v-model="recordDetail.value.notice_content" :label="$t('Content')" hint="" outlined
                   type="textarea"/>
          <q-field :label="$t('SendTo')" hint="" outlined stack-label>
            <template v-slot:control>
              <q-option-group v-model="recordDetail.value.notice_to_user_type" :options="noticeToUserTypeOption"
                              inline name="noticeToUserType"
                              @update:model-value="changenoticeToUserType"/>
            </template>
          </q-field>
          <q-select v-if="recordDetail.value.notice_to_user_type === 'some' && changeTableData.length"
                    v-model="selectUser"
                    :label="$t('User')"
                    :options="changeTableData" :rules="[val => val && val.length > 0 || $t('NeedInput')]" clearable
                    emit-value hint="" map-options
                    multiple outlined/>
          <span v-if="recordDetail.value.notice_to_user_type === 'some' && !changeTableData.length">
                        {{ $t('NoUserNow') }}
                    </span>
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
import useTableData from 'src/composables/useTableData'
import {postAction} from 'src/api/manage'
import {computed, ref} from 'vue'

const emit = defineEmits(['handleFinish'])
const url = {
  list: 'user/get-user-list',
  add: 'notice/add-notice',
  queryById: 'notice/query-notice-by-id',
}
const {
  $q,
  t,
  dictOptions,
  formType,
  formTypeName,
  recordDetail,
  recordDetailVisible,
  loading,
  handleQueryById,
  recordDetailForm,
} = useRecordDetail(url, emit)
const {
  tableData,
  getTableData,
} = useTableData(url)

const show = (row) => {
  loading.value = true
  recordDetail.value = {}
  recordDetailVisible.value = true
  if (row && row.noticeToUserType === 'some') {
    changenoticeToUserType('some')
  }
  if (row && row.id) {
    handleQueryById(row.id)
  } else {
    recordDetail.value = {}
    recordDetailVisible.value = true
    loading.value = false
  }
}
defineExpose({
  show,
  formType,
  recordDetail
})

const noticeToUserTypeOption = computed(() => {
  return [
    {label: t('All') + t('User'), value: 'all'},
    {label: t('Some') + t('User'), value: 'some'},
  ]
})
const changeTableData = computed(() => {
  if (tableData.value.length) {
    for (let i of tableData.value) {
      i.label = i.nickname ? i.nickname : i.realName ? i.realName : i.username
      i.value = i.username
    }
    return tableData.value
  }
  return []
})
const selectUser = ref([])


const changenoticeToUserType = (val) => {
  if (val === 'some') {
    selectUser.value = []
    getTableData().then(() => {
      if (recordDetail.value.notice_to_user_type === 'some') {
        for (let u of recordDetail.value.notice_to_user ? recordDetail.value.notice_to_user : []) {
          selectUser.value.push(u.toUser)
        }
      }
    })
  }
}
const handleAddOrEidt = async () => {
  if (!recordDetail.value.notice_to_user_type) {
    $q.notify({
      type: 'negative',
      message: t('FixForm') + ': ' + t('SendTo'),
    })
    return
  }
  const success = await recordDetailForm.value.validate()
  if (success) {
    if (recordDetail.value.noticeToUserType === 'some') {
      recordDetail.value.notice_to_user = selectUser.value
    } else {
      recordDetail.value.notice_to_user = []
    }
    if (formType.value === 'edit') {
      return
    } else if (formType.value === 'add') {
      if (url === undefined || !url.add) {
        $q.notify({
          type: 'negative',
          message: t('UrlNotConfig'),
        })
        return
      }
      const res = await postAction(url.add, recordDetail.value)
      if (res.code === 1) {
        $q.notify({
          type: 'positive',
          message: res.message,
        })
        recordDetailVisible.value = false
      }
    } else {
      $q.notify({
        type: 'negative',
        message: t('CanNotAddOrEdit'),
      })
    }
    emit('handleFinish')
  } else {
    $q.notify({
      type: 'negative',
      message: t('FixForm'),
    })
  }
}
</script>
