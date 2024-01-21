<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-input v-model="queryParams.config_item" :label="$t('Config') + $t('Item')" dense outlined
                 style="width: 20%"/>
        <q-input v-model="queryParams.memo" :label="$t('Memo')" dense outlined style="width: 20%"/>
        <q-btn :label="$t('Search')" color="primary" @click="handleSearch"/>
        <q-btn :label="$t('Reset')" color="primary" @click="resetSearch"/>
      </q-card-section>
      <q-card-section>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                 :rows-per-page-options="pageOptions"
                 row-key="id" separator="cell" @request="onRequest">

          <template v-slot:top="props">
            <q-btn v-has="'config-backend:add'" :label="$t('Add') + ' ' + $t('Config')" color="primary"
                   @click="showAddForm()"/>
            <q-space/>
            <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                   round @click="props.toggleFullscreen"/>
          </template>

          <template v-slot:body-cell-item_custom="props">
            <q-td :props="props">
              <template v-if="props.row.config_item === 'captchaKeyLong'">
                <q-input v-model.number="props.row.item_custom" :rules="[val => val && val >= 2 && val <= 6 || '2-6']"
                         clearable dense hint="2-6"
                         no-error-icon outlined type="number"/>
              </template>
              <template v-else-if="props.row.config_item === 'captchaWidth'">
                <q-input v-model.number="props.row.item_custom"
                         :rules="[val => val && val >= 200 && val <= 260 || '200-260']" clearable dense hint="200-260"
                         no-error-icon outlined
                         type="number"/>
              </template>
              <template v-else-if="props.row.config_item === 'captchaHeight'">
                <q-input v-model.number="props.row.item_custom"
                         :rules="[val => val && val >= 60 && val <= 100 || '60-100']" clearable dense hint="60-100"
                         no-error-icon outlined
                         type="number"/>
              </template>
              <template v-else-if="props.row.config_item === 'jwtExpiresAt'">
                <q-input v-model.number="props.row.item_custom" clearable dense outlined type="number"/>
              </template>
              <template v-else-if="props.row.config_item === 'jwtRefreshAt'">
                <q-input v-model.number="props.row.item_custom" clearable dense outlined type="number"/>
              </template>
              <template v-else-if="props.row.config_item === 'avatarMaxSize'
                                || props.row.config_item === 'logoMaxSize'
                                || props.row.config_item === 'faviconMaxSize'
                                || props.row.config_item === 'bannerImageMaxSize'">
                <q-input v-model.number="props.row.item_custom" :rules="[val => val && val >= 1 && val <= 5 || '1-5']"
                         clearable dense hint="1-5"
                         no-error-icon outlined type="number"/>
              </template>
              <template v-else-if="props.row.config_item === 'fileMaxSize'">
                <q-input v-model.number="props.row.item_custom" :rules="[val => val && val >= 1 && val <= 10 || '1-10']"
                         clearable dense hint="1-10"
                         no-error-icon outlined type="number"/>
              </template>
              <template v-else>
                <q-input v-model="props.row.item_custom" clearable dense outlined/>
              </template>
            </q-td>
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
              <q-btn v-has="'config-backend:save'" :label="$t('Save')" color="primary" dense flat
                     @click="handleSave(props.row)">
              </q-btn>
              <q-btn v-has="'config-backend:reset'" :label="$t('Reset')" color="warning" dense flat
                     @click="handleReset(props.row)">
              </q-btn>
              <q-btn v-if="props.row.stable !== 'yesNo_yes'" v-has="'config-backend:delete'" :label="$t('Delete')"
                     color="negative" dense
                     flat @click="handleDelete(props.row)">
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
import RecordDetail from './modules/recordDetail.vue'
import useTableData from 'src/composables/useTableData'
import {postAction} from 'src/api/manage'
import {computed, onMounted} from 'vue'
import {useStorageStore} from 'src/stores/storage'
import useTheme from 'src/composables/useTheme';

const storageStore = useStorageStore()
const {darkThemeSelect} = useTheme()
const url = {
  list: 'config-backend/get-config-backend-list',
  edit: 'config-backend/edit-config-backend',
  delete: 'config-backend/delete-config-backend-by-id',
}
const columns = computed(() => {
  return [
    {name: 'sort', align: 'center', label: t('Sort'), field: 'sort'},
    {name: 'config_item', align: 'center', label: t('Config') + t('Item'), field: 'config_item'},
    {name: 'memo', align: 'center', label: t('Memo'), field: 'memo'},
    {name: 'item_default', align: 'center', label: t('Default'), field: 'item_default'},
    {name: 'item_custom', align: 'center', label: t('Custom'), field: 'item_custom'},
    {name: 'status', align: 'center', label: t('Status'), field: 'status'},
    {name: 'stable', align: 'center', label: t('Stable'), field: 'stable'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
  $q,
  t,
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

onMounted(() => {
  getTableData()
})

const handleReset = (row) => {
  row.item_custom = ''
}

const handleSave = async (row) => {
  row.item_custom = String(row.item_custom)
  const res = await postAction(url.edit, row)
  if (res.code === 1) {
    $q.notify({
      type: 'positive',
      message: res.message,
    })
    storageStore.SetGqaBackend()
  }
}
</script>
