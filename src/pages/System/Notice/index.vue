<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-input v-model="queryParams.notice_title" :label="$t('Title')" dense outlined style="width: 20%"/>
        <q-select v-model="queryParams.notice_type" :label="$t('Notice') + $t('Type')" :options="dictOptions.noticeType"
                  dense
                  emit-value map-options outlined style="width: 20%"/>
        <q-select v-model="queryParams.notice_sent" :label="$t('Sent')"
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
            <q-btn v-has="'notice:add'" :label="$t('Add') + ' ' + $t('Notice')" color="primary"
                   @click="showAddForm()"/>
            <q-space/>
            <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                   round @click="props.toggleFullscreen"/>
          </template>
          <template v-slot:body-cell-notice_type="props">
            <q-td :props="props">
              <GqaDictShow :dictCode="props.row.notice_type"/>
            </q-td>
          </template>
          <template v-slot:body-cell-notice_sent="props">
            <q-td :props="props">
              <GqaDictShow :dictCode="props.row.notice_sent"/>
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props" class="q-gutter-x-md">
              <q-btn v-if="props.row.notice_sent === 'yesNo_no'" v-has="'notice:send'" :label="$t('Send')"
                     color="warning" dense
                     flat @click="sendMessage(props.row)">
              </q-btn>
              <q-btn v-has="'notice:delete'" :label="$t('Delete')" color="negative" dense flat
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
import {postAction} from 'src/api/manage'
import {computed, onMounted} from 'vue'

const url = {
  list: 'notice/get-notice-list',
  delete: 'notice/delete-notice-by-id',
  send: 'notice/send-notice',
}
const columns = computed(() => {
  return [
    {name: 'id', align: 'center', label: 'ID', field: 'id'},
    {name: 'notice_title', align: 'center', label: t('Title'), field: 'notice_title'},
    {name: 'notice_type', align: 'center', label: t('Type'), field: 'notice_type'},
    {name: 'notice_sent', align: 'center', label: t('Sent'), field: 'notice_sent'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
  $q,
  t,
  dictOptions,
  pagination,
  queryParams,
  pageOptions,
  GqaDictShow,
  loading,
  tableData,
  recordDetailDialog,
  showAddForm,
  onRequest,
  getTableData,
  handleSearch,
  resetSearch,
  handleFinish,
  handleDelete,
} = useTableData(url)

onMounted(async () => {
  pagination.value.sortBy = 'id'
  pagination.value.descending = true
  getTableData()
})

const sendMessage = (row) => {
  $q.dialog({
    title: t('Confirm') + t('Send'),
    message: t('Confirm') + t('Send'),
    cancel: true,
    persistent: true,
  }).onOk(async () => {
    const res = await postAction(url.send, row)
    if (res.code === 1) {
      $q.notify({
        type: 'positive',
        message: t('Send') + ' ' + t('Success'),
      })
      getTableData()
    }
  })
}
</script>
