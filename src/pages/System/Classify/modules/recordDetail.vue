<template>
  <q-dialog v-model="recordDetailVisible" position="top">
    <q-card style="width: 1200px; max-width: 80vw;">
      <q-card-section class="text-h6">
        {{ formTypeName }} {{ $t('User') }}:
        {{
          recordDetail.value.nickname ? recordDetail.value.nickname :
            recordDetail.value.real_name ? recordDetail.value.real_name : ""
        }}
      </q-card-section>
      <Cili-form-top :recordDetail="recordDetail" style="margin: 0 16px;"></Cili-form-top>
      <div class="row">
        <q-card-section class="q-gutter-md col-8">
          <q-form ref="recordDetailForm">
            <div class="row q-gutter-md">
              <q-input v-model="recordDetail.value.category_name" :disable="formType === 'edit'" :label="$t('Category')+$t('Name')"
                       :rules="[val => val && val.length > 0 || $t('NeedInput')]"
                       class="col" hint=""
                       lazy-rules outlined/>
              <q-input v-model="recordDetail.value.subject_code" :model-value="selectDept" readonly  :disable="formType === 'edit'" :label="$t('Classify')+$t('Name')"
                       :rules="[val => val && val.length > 0 || $t('NeedInput')]"
                       class="col" hint=""
                       lazy-rules outlined/>
              <q-field :label="$t('Status')" class="col" dense hint="" outlined stack-label>
                <template v-slot:control>
                  <q-option-group v-model="recordDetail.value.status" :disable="recordDetail.value.username === 'admin'"
                                  :options="dictOptions.onOff" color="primary" inline>
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
        <q-card-section class="q-gutter-md col">
          <q-field :label="$t('Select') + $t('Classify')" dense hint="" outlined stack-label>
            <template v-slot:control>
              <q-tree v-model:selected="selectDept" :nodes="deptList" default-expand-all
                      label-key="subject_name" node-key="subject_code" selected-color="primary"
                      @update:selected="handleSelectDept"/>
            </template>
          </q-field>
        </q-card-section>
      </div>

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
import {postAction} from 'src/api/manage'
import {useStorageStore} from 'src/stores/storage'
import {computed, ref, toRefs} from 'vue'
import XEUtils from 'xe-utils'

const storageStore = useStorageStore()
const CiliBackend = computed(() => storageStore.GetCiliBackend())
const emit = defineEmits(['handleFinish'])
const url = {
  add: 'category/add-category',
  edit: 'user/edit-user',
  queryById: 'user/query-user-by-id',
  avatarUrl: 'upload/upload-avatar',
  deptListUrl: 'subject/get-subject-list'
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
  // show,
  // handleQueryById,
  recordDetailForm,
  handleAddOrEidt
} = useRecordDetail(url, emit)

const show = (row) => {
  loading.value = true
  recordDetail.value = {}
  recordDetailVisible.value = true
  if (row && row.id) {
    handleQueryById(row.id)
  } else {
    selectDept.value = []
    recordDetail.value = {}
    recordDetailVisible.value = true
    loading.value = false
  }
}

const handleQueryById = async (id) => {
  postAction(url.queryById, {
    id,
  }).then(res => {
    if (res.code === 1) {
      selectDept.value = []
      recordDetail.value = res.data.records
      for (let d of res.data.records.dept) {
        selectDept.value.push(d.dept_code)
      }

    }
  }).finally(() => {
    loading.value = false
  })
}

defineExpose({
  show,
  formType
})

const props = defineProps({
  deptList: {
    type: Array,
    required: false,
    default: () => [],
  },
})
const {deptList} = toRefs(props)

const avatarFile = ref(null)
const handleUpload = () => {
  if (!avatarFile.value) {
    $q.notify({
      type: 'negative',
      message: t('Please') + t('Select') + t('File'),
    })
    return
  }
  let form = new FormData()
  form.append('file', avatarFile.value)
  postAction(url.avatarUrl, form).then((res) => {
    if (res.code === 1) {
      recordDetail.value.avatar = res.data.records
      avatarFile.value = null
      $q.notify({
        type: 'positive',
        message: t('Upload') + t('Success'),
      })
    }
  })
}
const rejected = (rejectedEntries) => {
  $q.notify({
    type: 'negative',
    message: t('FileRejected'),
  })
}

const selectDept = ref([])
const handleSelectDept = (target) => {
  recordDetail.value.subject_code=target
  recordDetail.value.dept = []
  for (let t of target) {
    const tt = XEUtils.filterTree(deptList.value, item => item.dept_code === t)
    recordDetail.value.dept.push(tt[0])
  }
}
</script>
