<template>
  <q-page padding>
    <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
             :rows-per-page-options="pageOptions"
             row-key="id" separator="cell" @request="onRequest">
      <template v-slot:top="props">
        <q-btn color="primary" label="新增分类" @click="showAddForm()"/>
        <q-space/>
        <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
               round @click="props.toggleFullscreen"/>
      </template>
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <div class="q-gutter-xs">
            <q-btn color="primary" label="编辑" @click="showEditForm(props.row)"/>
            <q-btn color="negative" label="删除" @click="handleDelete(props.row)"/>
          </div>
        </q-td>
      </template>
    </q-table>
    <recordDetail ref="recordDetailDialog" @handleFinish="handleFinish"/>
  </q-page>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {computed, onMounted} from 'vue'

const url = {
  list: 'plugin-achievement/get-category-list',
  delete: 'plugin-achievement/delete-category-by-id',
}
const columns = computed(() => {
  return [
    {name: 'category', align: 'center', label: '分类', field: 'category'},
    {name: 'code', align: 'center', label: '成就代码', field: 'code'},
    {name: 'name', align: 'center', label: '成就名', field: 'name'},
    {name: 'memo', align: 'center', label: '成就描述', field: 'memo'},
    {name: 'actions', align: 'center', label: '操作', field: 'actions'},
  ]
})
const {
  pagination,
  pageOptions,
  loading,
  tableData,
  recordDetailDialog,
  showAddForm,
  showEditForm,
  onRequest,
  getTableData,
  handleFinish,
  handleDelete,
} = useTableData(url)

onMounted(() => {
  pagination.value.sortBy = 'created_at'
  pagination.value.descending = true
  getTableData()
})
</script>
