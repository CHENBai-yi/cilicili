<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-input v-model="queryParams.subject_code" :label="$t('Classify') + $t('Code')" dense outlined
                 style="width: 20%"/>
        <q-input v-model="queryParams.subject_name" :label="$t('Classify') + $t('Name')" dense outlined
                 style="width: 20%"/>
        <q-btn :label="$t('Search')" color="primary" @click="handleSearch"/>
        <q-btn :label="$t('Reset')" color="primary" @click="resetSearch"/>
      </q-card-section>
      <q-card-section>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                 :rows-per-page-options="pageOptions"
                 row-key="id" separator="cell" @request="onRequest">
          <template v-slot:top="props">
            <q-btn v-has="'subject:add'" :label="$t('Add') + ' ' + $t('Classify')" color="primary"
                   @click="showAddForm()"/>
            <q-space/>
            <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                   round @click="props.toggleFullscreen"/>
          </template>
          <template v-slot:body-cell-default_page="props">
            <q-td :props="props">
              {{ $t(props.row.default_page_menu.title) }}
            </q-td>
          </template>
          <template v-slot:body-cell-stable="props">
            <q-td :props="props">
              <CiliDictShow :dictCode="props.row.stable"/>
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props" class="q-gutter-x-md">
              <q-btn v-has="'subject:edit'" :label="$t('Edit')" color="primary" dense flat
                     @click="showEditForm(props.row)">
              </q-btn>
              <q-btn v-has="'subject:delete'" :label="$t('Delete')" color="negative" dense flat @click="handleDelete(props.row)"/>
            </q-td>
          </template>
        </q-table>
      </q-card-section>
    </q-card>
    <recordDetail ref="recordDetailDialog" @handleFinish="handleFinish"/>
  </q-page>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {computed, onMounted, ref} from 'vue'
import RecordDetail from './modules/recordDetail.vue'

const url = {
  list: 'subject/get-subject-list',
  delete: 'subject/delete-subject-by-id',
}
const columns = computed(() => {
  return [
    {name: 'sort', align: 'center', label: t('Sort'), field: 'sort'},
    {name: 'subject_code', align: 'center', label: t('Classify') + t('Code'), field: 'subject_code'},
    {name: 'subject_name', align: 'center', label: t('Classify') + t('Name'), field: 'subject_name'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
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
  resetSearch,
  handleFinish,
  handleDelete,
} = useTableData(url)

onMounted(() => {
  getTableData()
})

const rolePermissionDialog = ref(null)
const showRolePermission = (row) => {
  rolePermissionDialog.value.show(row)
}
const roleUserDialog = ref(null)
const showRoleUser = (row) => {
  roleUserDialog.value.show(row)
}

</script>
