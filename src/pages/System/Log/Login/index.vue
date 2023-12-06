<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-input v-model="queryParams.login_username" :label="$t('User')" dense outlined style="width: 20%"/>
        <q-select v-model="queryParams.login_success" :label="$t('Login') + $t('Success')"
                  :option-label="opt => Object(opt) === opt && 'label' in opt ? $t(opt.label) : '- Null -'"
                  :options="dictOptions.yesNo" dense
                  emit-value map-options outlined
                  style="width: 20%"/>
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
          <template v-slot:body-cell-login_success="props">
            <q-td :props="props">
              <q-badge :color="props.row.login_success === 'yesNo_no' ? 'negative' : 'positive'"
                       align="middle">
                <GqaDictShow :dictCode="props.row.login_success"/>
              </q-badge>
            </q-td>
          </template>
          <template v-slot:body-cell-created_at="props">
            <q-td :props="props">
              {{ showDateTime(props.row.created_at) }}
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props" class="q-gutter-x-md">
              <q-btn v-has="'log-login:detail'" :label="$t('Detail')" color="warning" dense flat
                     @click="showMemo(props.row.memo)">
              </q-btn>
              <q-btn v-has="'log-login:delete'" :label="$t('Delete')" color="negative" dense flat
                     @click="handleDelete(props.row)">
              </q-btn>
            </q-td>
          </template>
        </q-table>
      </q-card-section>
    </q-card>
    <q-dialog v-model="showMemoFlag">
      <q-card>
        <q-card-section>
          {{ memo }}
        </q-card-section>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {computed, onMounted, ref} from 'vue'
import useCommon from 'src/composables/useCommon'

const {showDateTime} = useCommon()
const url = {
  list: 'log/get-log-login-list',
  delete: 'log/delete-log-login-by-id',
}
const columns = computed(() => {
  return [
    {name: 'id', align: 'center', label: 'ID', field: 'id'},
    {name: 'login_username', align: 'center', label: t('User'), field: 'login_username'},
    {name: 'login_ip', align: 'center', label: 'IP', field: 'login_ip'},
    {name: 'login_browser', align: 'center', label: t('Browser'), field: 'login_browser'},
    {name: 'login_os', align: 'center', label: t('Os'), field: 'login_os'},
    {name: 'login_platform', align: 'center', label: t('Platform'), field: 'login_platform'},
    {name: 'created_at', align: 'center', label: t('CreatedAt'), field: 'created_at'},
    {name: 'login_success', align: 'center', label: t('Login') + t('Success'), field: 'login_success'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
  t,
  dictOptions,
  pagination,
  queryParams,
  pageOptions,
  GqaDictShow,
  loading,
  tableData,
  onRequest,
  getTableData,
  handleSearch,
  resetSearch,
  handleDelete,
} = useTableData(url)

onMounted(async () => {
  pagination.value.sortBy = 'created_at'
  pagination.value.descending = true
  getTableData()
})

const showMemoFlag = ref(false)
const memo = ref('')
const showMemo = (b) => {
  memo.value = b
  showMemoFlag.value = true
}
</script>
