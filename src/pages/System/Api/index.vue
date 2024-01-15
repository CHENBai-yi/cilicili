<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-input v-model="queryParams.api_group" :label="$t('Api') + $t('Group')" dense outlined
                 style="width: 20%"/>
        <q-input v-model="queryParams.api_method" :label="$t('Api') + $t('Method')" dense outlined
                 style="width: 20%"/>
        <q-btn :label="$t('Search')" color="primary" @click="handleSearch"/>
        <q-btn :label="$t('Reset')" color="primary" @click="resetSearch"/>
      </q-card-section>
      <q-card-section>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                 :rows-per-page-options="pageOptions"
                 row-key="id" separator="cell" @request="onRequest">
          <template v-slot:top="props">
            <q-btn v-has="'api:add'" :label="$t('Add') + ' ' + $t('Api')" color="primary"
                   @click="showAddForm()"/>
            <q-space/>
            <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                   round @click="props.toggleFullscreen"/>
          </template>
          <template v-slot:body-cell-status="props">
            <q-td :props="props">
              <GqaDictShow :dictCode="props.row.status"/>
            </q-td>
          </template>
          <template v-slot:body-cell-stable="props">
            <q-td :props="props">
              <GqaDictShow :dictCode="props.row.stable"/>
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props" class="q-gutter-x-md">
              <q-btn v-has="'api:edit'" :label="$t('Edit')" color="primary" dense flat
                     @click="showEditForm(props.row)">
              </q-btn>
              <q-btn v-has="'api:delete'" :label="$t('Delete')" color="negative" dense flat
                     @click="handleDelete(props.row)">
              </q-btn>
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
import RecordDetail from './modules/recordDetail.vue'
import {computed, onMounted} from 'vue'

const url = {
  list: 'api/get-api-list',
  delete: 'api/delete-api-by-id',
}
const columns = computed(() => {
  return [
    {name: 'sort', align: 'center', label: t('Sort'), field: 'sort'},
    {name: 'api_group', align: 'center', label: t('Api') + t('Group'), field: 'api_group'},
    {name: 'api_method', align: 'center', label: t('Api') + t('Method'), field: 'api_method'},
    {name: 'api_path', align: 'center', label: t('Api') + t('Path'), field: 'api_path'},
    {name: 'memo', align: 'center', label: t('Memo'), field: 'memo'},
    {name: 'status', align: 'center', label: t('Status'), field: 'status'},
    {name: 'stable', align: 'center', label: t('Stable'), field: 'stable'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions',},
  ]
})
const {
  t,
  pagination,
  queryParams,
  pageOptions,
  GqaDictShow,
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
</script>
