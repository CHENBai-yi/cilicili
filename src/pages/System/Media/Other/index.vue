<template>
  <q-page padding>
    <q-card flat>
      <q-card-section>
        <n-tag :bordered="false" class="q-mb-sm">
          建议686*200px，图片大小小于1M，jpg/png/jpeg格式。
        </n-tag>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                 :rows-per-page-options="pageOptions"
                 binary-state-sort row-key="id" @request="onRequest">
          <template v-slot:top="props">
            <div class="q-gutter-sm">
              <q-btn v-has="'notice:add'" :label="$t('Add')" color="primary"
                     @click="showAddForm()"/>
              <q-btn v-has="'notice:add'" :label="$t('Flush')" color="positive"
                     @click="showAddForm()"/>
            </div>
            <q-space/>
            <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                   round @click="props.toggleFullscreen"/>
          </template>
          <template v-slot:body-cell-time="props">
            <q-td :props="props" class="q-gutter-y-sm">
              <div>开始时间：
                <n-tag type="info">{{ XEUtils.toDateString(props.row.time[0]) }}</n-tag>
              </div>
              <div>结束时间：
                <n-tag type="info">{{ XEUtils.toDateString(props.row.time[1]) }}</n-tag>
              </div>
            </q-td>
          </template>
          <template v-slot:body-cell-content="props">
            <q-td :props="props" class="q-gutter-y-sm">
              <CiliContentShow :content="props.row.content" :dict-code="props.row.content_type"/>
            </q-td>
          </template>
          <template v-slot:body-cell-content_type="props">
            <q-td :props="props" class="q-gutter-y-sm">
              <CiliContentShow :dict-code="props.row.content_type"/>
            </q-td>
          </template>
          <template v-slot:body-cell-status="props">
            <q-td :props="props" class="q-gutter-x-xs">
              <span :class="!props.row.is_show?'text-negative':''">{{ $t('No') }}</span>
              <n-switch v-model:value="props.row.is_show" :checked-value="true" :unchecked-value="false" size="medium"
                        @update:value="handleChange(props.row.is_show,props.row.id)">
                <template #checked-icon>
                  <q-icon color="green" name="check"/>
                </template>
                <template #unchecked-icon>
                  <q-icon color="red" name="clear"/>
                </template>
              </n-switch>
              <span :class="props.row.is_show?'text-positive':''">{{ $t('Yes') }}</span>
            </q-td>
          </template>
          <template v-slot:body-cell-link="props">
            <q-td :props="props" class="q-gutter-y-sm">
              <n-tag :bordered="false" type="success">
                {{ props.row.adver_path }}
              </n-tag>
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props" class="q-gutter-x-md">
              <q-btn v-has="'media-advertising:edit'" :label="$t('Edit')" color="primary" dense flat
                     @click="showEditForm(props.row)"/>
              <q-btn v-has="'media-advertising:delete'" :label="$t('Delete')" color="primary" dense flat
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
import {computed, onMounted, ref} from 'vue'
import RecordDetail from './modules/recordDetail.vue'
import CiliContentShow from 'src/components/CiliContentShow/index.vue'
import XEUtils from "xe-utils"

const url = {
  list: 'advertising/list',
  delete: 'advertising/delete-advertising-by-id',
  send: 'notice/send-notice',
  change: 'advertising/change-status',

}
const columns = computed(() => {
  return [
    {name: 'id', align: 'center', label: '序号', field: 'id'},
    {name: 'time', align: 'center', label: '有效时间', field: 'time', style: 'height: 60px',},
    {name: 'content_type', align: 'center', label: t('Type'), field: 'content_type'},
    {name: 'content', align: 'center', label: t('Content'), field: 'content'},
    {name: 'memo', align: 'center', label: t('Memo'), field: 'memo'},
    {name: 'sort', align: 'center', label: t('Sort'), field: 'sort'},
    {name: 'page', align: 'center', label: '跳转页面', field: 'adver_page'},
    {name: 'link', align: 'center', label: '链接地址', field: 'adver_path'},
    {name: 'status', align: 'center', label: '是否启用', field: 'status'},
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
onMounted(async () => {
  pagination.value.sortBy = 'sort'
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
const handleChange = async (value, id) => await postAction(url.change, {id, is_show: value});
</script>
