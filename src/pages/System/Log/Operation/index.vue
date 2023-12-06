<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-input v-model="queryParams.operation_username" :label="$t('User')" dense outlined style="width: 20%"/>
        <q-btn :label="$t('Search')" color="primary" @click="handleSearch"/>
        <q-btn :label="$t('Reset')" color="primary" @click="resetSearch"/>
      </q-card-section>
      <q-card-section>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                 :rows-per-page-options="pageOptions"
                 row-key="id" separator="cell" @request="onRequest">
          <template v-slot:top="props">
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
            <q-td :props="props" class="q-gutter-x-xs">
              <q-btn v-has="'log-operation:detail'" :label="$t('Detail')" color="warning" dense flat
                     @click="showBody(props.row.operation_body)">
              </q-btn>
            </q-td>
          </template>
        </q-table>
      </q-card-section>
    </q-card>
    <q-dialog v-model="showBodyFlag">
      <q-card>
        <q-card-section class="q-pt-none">
                    <pre>
                        {{ body }}
                    </pre>
        </q-card-section>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {computed, onMounted, ref} from 'vue'

const url = {
  list: 'log/get-log-operation-list',
  delete: 'log/delete-log-operation-by-id',
}
const columns = computed(() => {
  return [
    {name: 'id', align: 'center', label: 'ID', field: 'id'},
    {name: 'operation_username', align: 'center', label: t('User'), field: 'operation_username'},
    {name: 'operation_ip', align: 'center', label: 'IP', field: 'operation_ip'},
    {name: 'operation_method', align: 'center', label: t('Method'), field: 'operation_method'},
    {name: 'operation_api', align: 'center', label: t('Api'), field: 'operation_api'},
    {name: 'operation_status', align: 'center', label: t('Status'), field: 'operation_status'},
    {name: 'created_at', align: 'center', label: t('CreatedAt'), field: 'created_at'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
  t,
  showDateTime,
  pagination,
  queryParams,
  pageOptions,
  loading,
  tableData,
  onRequest,
  getTableData,
  handleSearch,
  resetSearch,
} = useTableData(url)

onMounted(async () => {
  pagination.value.sortBy = 'created_at'
  pagination.value.descending = true
  getTableData()
})

const showBodyFlag = ref(false)
const body = ref('')
const showBody = (b) => {
  body.value = JSON.parse(b)
  showBodyFlag.value = true
}
</script>
