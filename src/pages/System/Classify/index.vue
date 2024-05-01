<template>
  <q-page padding>
    <q-card class="row" flat>
      <q-card-section class="col-2">
        <q-card-section>
          {{ $t('Select') + $t('Classify') }}
        </q-card-section>
        <q-separator/>
        <q-card-section v-if="deptList.length">
          <q-tree v-model:selected="selectDept" :nodes="deptList" default-expand-all
                  label-key="subject_name" node-key="subject_code" selected-color="primary"
                  @update:selected="handleSelectDept"/>
        </q-card-section>
      </q-card-section>
      <q-card-section class="col q-gutter-y-md">
        <q-card-section class="row q-gutter-x-md items-center">
          <q-input v-model="queryParams.category_name" :label="t('Category') + t('Name')" dense outlined
                   style="width: 20%"/>
          <q-btn :label="$t('Search')" color="primary" @click="handleSearch"/>
          <q-btn :label="$t('Reset')" color="primary" @click="resetSearch"/>
        </q-card-section>
        <q-card-section>
          <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                   :rows-per-page-options="pageOptions" row-key="id" separator="cell"
                   @request="onRequest">
            <template v-slot:top="props">
              <q-btn v-has="'classify:add'" :label="$t('Add') + ' ' + $t('Category')" color="primary"
                     @click="showAddForm()"/>
              <q-space/>
              <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                     round @click="props.toggleFullscreen"/>
            </template>
            <template v-slot:body-cell-sort="props">
              <q-td :props="props">
                {{props.row.id}}
              </q-td>
            </template>
            <template v-slot:body-cell-category_name="props">
              <q-td :props="props">
                {{props.row.category_name}}
                <q-popup-edit v-slot="scope" v-model="props.row.category_name"
                              :validate="val => val && val.length > 0">
                  <q-input v-model="scope.value" :rules="[val => scope.validate(val) || $t('NeedInput')]" autofocus
                           counter dense
                           no-error-icon>
                    <template v-slot:after>
                      <q-btn color="negative" dense flat icon="cancel"
                             @click.stop.prevent="scope.cancel"/>

                      <q-btn :disable="scope.validate(scope.value) === false || scope.initialValue === scope.value"
                             color="positive" dense flat
                             icon="check_circle"
                             @click.stop.prevent="scope.set(props.row._change=true)"/>
                    </template>
                  </q-input>
                </q-popup-edit>
              </q-td>
            </template>
            <template v-slot:body-cell-status="props">
              <q-td :props="props">
                <CiliDictShow :dictCode="props.row.status"/>
                <q-popup-edit v-slot="scope" v-model="props.row.status"
                              :validate="val => val && val.length > 0">
                  <q-input v-model="scope.value" :rules="[val => scope.validate(val) || $t('NeedInput')]" autofocus
                           counter dense hint="启用：onOff_on，禁用：onOff_off" style="width: 280px"
                           no-error-icon>
                    <template v-slot:after>
                      <q-btn color="negative" dense flat icon="cancel"
                             @click.stop.prevent="scope.cancel"/>
                      <q-btn :disable="scope.validate(scope.value) === false || scope.initialValue === scope.value"
                             color="positive" dense flat
                             icon="check_circle"
                             @click.stop.prevent="scope.set(props.row._change=true)"/>
                    </template>
                  </q-input>
                </q-popup-edit>
              </q-td>
            </template>
            <template v-slot:body-cell-actions="props">
              <q-td :props="props" class="q-gutter-x-md">
                <q-btn
                  v-if="props.row._change"
                  :label="$t('Save')" color="primary" flat @click="editButton(props.row,props.row._change=false)"/>
                <q-btn :label="$t('Delete')" color="negative" dense flat
                       @click="handleDelete(props.row)">
                </q-btn>
              </q-td>
            </template>
          </q-table>
        </q-card-section>
      </q-card-section>
    </q-card>
    <recordDetail ref="recordDetailDialog" :deptList="deptList" @handleFinish="handleFinish"/>
  </q-page>
</template>

<script setup>

import useTableData from 'src/composables/useTableData'
import RecordDetail from './modules/recordDetail.vue'
import {postAction} from 'src/api/manage'
import {computed, onMounted, reactive, ref,watch} from 'vue'
import {ChangeNullChildren2Array} from "src/utils/arrayAndTree";
const url = {
  edit: 'category/edit-category',
  queryById: 'user/query-user-by-id',
  list: 'category/get-category-list',
  delete: 'category/delete-category-by-id',
  resetPassword: 'user/reset-password',
  deptListUrl: 'subject/get-subject-list'
}
const columns = computed(() => {
  return [
    {name: 'sort', align: 'center', label: t('Sort'), field: 'sort'},
    {name: 'category_name', align: 'center', label: t('Category') + t('Name'), field: 'category_name'},
    {name: 'status', align: 'center', label: t('Status'), field: 'status'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const menuTree = computed(() => {
  return ChangeNullChildren2Array(tableData.value) || []
})
const recordDetail = reactive({})
const editButton = async (data) => {
  console.log(data)
  const res = await postAction(url.edit, data)
  if (res.code === 1) {
    $q.notify({
      type: 'positive',
      message: res.message,
    })
    show({
      id: recordDetail.value.id
    })
  }
}

const {
  $q,
  t,
  pagination,
  queryParams,
  pageOptions,
  CiliDictShow,
  loading,
  tableData,
  recordDetailDialog,
  showAddForm,
  showEditForm,
  onRequest,
  getTableData,
  handleSearch,
  handleFinish,
  handleDelete,
} = useTableData(url)
onMounted(() => {
  pagination.value.sortBy = 'sort'
  queryParams.value = {
    with_admin: true,
  }
  getTableData()
  getDeptList()
})

const selectDept = ref(null)
const handleSelectDept = (target) => {
  queryParams.value.subject_code = target
  getTableData()
}
const deptList = ref([])
const getDeptList = () => {
  deptList.value = []
  postAction(url.deptListUrl, {
    sort_by: 'sort',
    desc: false,
    page: 1,
    page_size: 99999
  }).then(res => {
    deptList.value = res.data.records
  })
}

const resetPassword = (row) => {
  $q.dialog({
    title: t('Reset') + t('Password'),
    message: t('Confirm') + t('Reset') + t('Password') + '?' + '(' + row.username + ')',
    cancel: true,
    persistent: true,
  }).onOk(async () => {
    const res = await postAction(url.resetPassword, {
      id: row.id,
    })
    if (res.code === 1) {
      $q.notify({
        type: 'positive',
        message: res.message,
      })
    }
    getTableData()
  })
}

const resetSearch = () => {
  selectDept.value = null
  queryParams.value = {
    with_admin: true,
  }
  pagination.value.sortBy = 'sort'
  getTableData()
}

</script>
