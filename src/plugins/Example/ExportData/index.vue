<template>
  <q-page padding>
    <div class="row q-gutter-md items-center" style="margin-bottom: 10px">
      <q-input v-model="queryParams.column_1" label="第1列" style="width: 20%"/>
      <q-btn color="primary" label="搜索" @click="handleSearch"/>
      <q-btn color="primary" label="重置" @click="resetSearch"/>
    </div>
    <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
             :rows-per-page-options="pageOptions"
             row-key="id" separator="cell" @request="onRequest">
      <template v-slot:top="props">
        <div class="row q-gutter-x-md">
          <q-btn color="primary" label="新增测试数据" @click="showAddForm()"/>
          <GqaUploader url="plugin-example/import-test-data" @importFinish="getTableData"/>
          <q-btn color="primary" label="下载模板" @click="downloadTemplate()"/>
          <q-btn color="primary" label="导出查询数据" @click="exportData()"/>
        </div>
        <q-space/>
        <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
               round @click="props.toggleFullscreen"/>
      </template>
      <template v-slot:body-cell-created_at="props">
        <q-td :props="props">
          {{ showDateTime(props.row.created_at) }}
        </q-td>
      </template>
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <div class="q-gutter-xs">
            <q-btn :label="$t('Edit')" color="primary" @click="showEditForm(props.row)"/>
            <q-btn :label="$t('Delete')" color="negative" @click="handleDelete(props.row)"/>
          </div>
        </q-td>
      </template>
    </q-table>
    <recordDetail ref="recordDetailDialog" @handleFinish="handleFinish"/>
  </q-page>
</template>

<script setup>
import {downloadAction} from 'src/api/manage';
import useTableData from 'src/composables/useTableData'
import {computed, onMounted} from 'vue'
import {useI18n} from 'vue-i18n'
import {FormatDateTimeShort} from 'src/utils/date'
import GqaUploader from 'src/components/GqaUploader/index.vue'
import useCommon from 'src/composables/useCommon'

const {showDateTime} = useCommon()
const {t} = useI18n()
const url = {
  list: 'plugin-example/get-test-data-list',
  delete: 'plugin-example/delete-test-data-by-id',
  export: 'plugin-example/export-test-data',
  template: 'plugin-example/download-template-test-data'
}
const columns = computed(() => {
  return [
    {name: 'column1', align: 'center', label: '第1列', field: 'column_1'},
    {name: 'column2', align: 'center', label: '第2列', field: 'column_2'},
    {name: 'column3', align: 'center', label: '第3列', field: 'column_3'},
    {name: 'column4', align: 'center', label: '第4列', field: 'column_4'},
    {name: 'column5', align: 'center', label: '第5列', field: 'column_5'},
    {name: 'created_at', align: 'center', label: '创建时间', field: 'created_at'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
  pagination,
  pageOptions,
  queryParams,
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
  pagination.value.sortBy = 'created_at'
  pagination.value.descending = true
  getTableData()
})

const exportData = () => {
  const timeStamp = Date.now()
  const params = {}
  params.sort_by = pagination.value.sortBy
  params.desc = pagination.value.descending
  const allParams = Object.assign({}, params, queryParams.value)
  downloadAction(url.export, allParams, "GqaExport-" + FormatDateTimeShort(timeStamp) + '.xlsx')
}

const downloadTemplate = () => {
  const filename = 'TestDataTemplate.xlsx'
  downloadAction(url.template, {
    filename: filename
  }, filename)
}
</script>
