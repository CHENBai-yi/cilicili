<template>
  <q-page padding>
    <q-card flat>
      <q-card-section>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                 :rows-per-page-options="pageOptions" row-key="id" separator="cell"
                 @request="onRequest">
          <template v-slot:top="props">
                        <span class="text-bold text-h6">
                            {{ t('Cron') }}
                        </span>
          </template>
          <template v-slot:body-cell-status="props">
            <q-td :props="props">
              <q-icon v-if="props.row.id === 0" color="negative" name="close" size="xs"></q-icon>
              <q-icon v-else color="positive" name="check" size="xs"></q-icon>
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props" class="q-gutter-x-xs">
              <q-btn :label="$t('Start')" color="primary" @click="StartCron(props.row.uuid)"/>
              <q-btn :label="$t('Stop')" color="primary" @click="StopCron(props.row.uuid)"/>
            </q-td>
          </template>
        </q-table>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script setup>
import {postAction} from 'src/api/manage';
import useTableData from 'src/composables/useTableData'
import {computed, onMounted} from 'vue'

const url = {
  list: 'cron/get-cron-list',
  start: 'cron/start-cron',
  stop: 'cron/stop-cron'
}
const columns = computed(() => {
  return [
    {name: 'uuid', align: 'center', label: "uuid", field: 'uuid'},
    {name: 'id', align: 'center', label: "id", field: 'id'},
    {name: 'name', align: 'center', label: t("Name"), field: 'name'},
    {name: 'spec', align: 'center', label: t("Interval"), field: 'spec'},
    {name: 'status', align: 'center', label: t("Status"), field: 'status'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions',},
  ]
})
const {
  $q,
  t,
  pagination,
  pageOptions,
  loading,
  tableData,
  onRequest,
  getTableData,
} = useTableData(url)

onMounted(() => {
  getTableData()
})

const StartCron = (uuid) => {
  postAction(url.start, {
    uuid,
  }).then(res => {
    if (res.code === 1) {
      $q.notify({
        type: 'positive',
        message: res.message,
      })
      getTableData()
    }
  })
}

const StopCron = (uuid) => {
  postAction(url.stop, {
    uuid,
  }).then(res => {
    if (res.code === 1) {
      $q.notify({
        type: 'positive',
        message: res.message,
      })
      getTableData()
    }
  })
}
</script>
