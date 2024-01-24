<template>
  <div>
    <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
             :rows-per-page-options="pageOptions"
             row-key="id" separator="cell" style="max-height: 70vh;" @request="onRequest">
      <template v-slot:body-cell-notice_type="props">
        <q-td :props="props">
          <CiliDictShow :dictCode="props.row.notice_type"/>
        </q-td>
      </template>

      <template v-slot:body-cell-notice_read="props">
        <q-td :props="props">
          <CiliDictShow
            :dictCode="props.row.notice_to_user.filter(item => item.to_user === username)[0].user_read"/>
        </q-td>
      </template>

      <template v-slot:body-cell-notice_sent="props">
        <q-td :props="props">
          <CiliDictShow :dictCode="props.row.notice_sent"/>
        </q-td>
      </template>

      <template v-slot:body-cell-created_at="props">
        <q-td :props="props">
          {{ showDateTime(props.row.created_at) }}
        </q-td>
      </template>

      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <div class="q-gutter-xs">
            <q-btn :label="$t('Read')" color="primary" @click="readNotice(props.row)"/>
          </div>
        </q-td>
      </template>
    </q-table>
    <NoticeDetail ref="noticeDetail" @hide="hide"/>
  </div>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {computed, onMounted, ref} from 'vue'
import {useI18n} from 'vue-i18n'
import NoticeDetail from './NoticeDetail.vue'
import {useUserStore} from 'src/stores/user'

const {t} = useI18n()
const userStore = useUserStore()
const url = {
  list: 'notice/get-notice-list',
}
const columns = computed(() => {
  return [
    {name: 'id', align: 'center', label: 'ID', field: 'id'},
    {name: 'notice_title', align: 'center', label: t('Title'), field: 'notice_title'},
    {name: 'notice_type', align: 'center', label: t('Type'), field: 'notice_type'},
    {name: 'notice_read', align: 'center', label: t('Read') + t('Status'), field: 'notice_read'},
    {name: 'notice_sent', align: 'center', label: t('Sent'), field: 'notice_sent'},
    {name: 'created_at', align: 'center', label: t('CreatedAt'), field: 'created_at'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
  pagination,
  queryParams,
  pageOptions,
  CiliDictShow,
  loading,
  tableData,
  onRequest,
  getTableData,
  showDateTime,
} = useTableData(url)

const username = computed(() => userStore.GetUsername())
onMounted(() => {
  queryParams.value = {
    notice_type: 'noticeType_system',
    notice_sent: 'yesNo_yes',
    notice_to_user: String(username.value),
  }
  pagination.value.sortBy = 'created_at'
  pagination.value.descending = true
  getTableData()
})
const noticeDetail = ref(null)
const readNotice = (row) => {
  noticeDetail.value.show(row)
}
const hide = () => {
  getTableData()
}
</script>
