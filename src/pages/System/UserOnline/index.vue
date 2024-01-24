<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-input v-model="queryParams.username" :label="$t('Username')" dense outlined style="width: 20%"/>
        <q-btn :label="$t('Search')" color="primary" @click="handleSearch"/>
        <q-btn :label="$t('Reset')" color="primary" @click="resetSearch"/>
      </q-card-section>
      <q-card-section>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                 :rows-per-page-options="pageOptions"
                 row-key="id" separator="cell" @request="onRequest">
          <template v-slot:top="props">
                        <span class="text-h6">
                            {{ $t('UserOnline') }}
                        </span>
            <q-space/>
            <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                   round @click="props.toggleFullscreen"/>
          </template>
          <template v-slot:body-cell-avatar="props">
            <q-td :props="props">
              <Cili-avatar :src="props.row.avatar"/>
            </q-td>
          </template>
          <template v-slot:body-cell-username="props">
            <q-td :props="props">
              {{ props.row.user.username }}
            </q-td>
          </template>
          <template v-slot:body-cell-nickname="props">
            <q-td :props="props">
              {{ props.row.user.nickname }}
            </q-td>
          </template>
          <template v-slot:body-cell-real_name="props">
            <q-td :props="props">
              {{ props.row.user.real_name }}
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props" class="q-gutter-x-xs">
              <q-btn v-has="'user-online:kick'" :label="$t('KickOut')" color="negative" dense flat
                     @click="handleKickOut(props.row)">
              </q-btn>
            </q-td>
          </template>
        </q-table>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {postAction} from 'src/api/manage'
import {computed, onMounted} from 'vue'

const url = {
  list: 'user-online/get-user-online-list',
  kick: 'user-online/kick-online-user',
}
const columns = computed(() => {
  return [
    {name: 'avatar', align: 'center', label: t('Avatar'), field: 'avatar'},
    {name: 'username', align: 'center', label: t('Username'), field: 'username'},
    {name: 'nickname', align: 'center', label: t('Nickname'), field: 'nickname'},
    {name: 'real_name', align: 'center', label: t('RealName'), field: 'real_name'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
  $q,
  t,
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

onMounted(() => {
  pagination.value.sortBy = 'username'
  getTableData()
})

const handleKickOut = (row) => {
  $q.dialog({
    title: t('KickOut') + t('User'),
    message: t('Confirm') + t('KickOut') + t('User') + '?' + '(' + row.username + ')',
    cancel: true,
    persistent: true,
  }).onOk(async () => {
    const res = await postAction(url.kick, {
      username: row.username
    })
    if (res.code === 1) {
      $q.notify({
        type: 'positive',
        message: res.message,
      })
    }
    getTableData()
  })
}
</script>
