<template>
  <q-page padding>
    <q-card class="row" flat>
      <q-card-section class="col-2">
        <q-card-section>
          {{ $t('Select') + $t('Dept') }}
        </q-card-section>
        <q-separator/>
        <q-card-section v-if="deptList.length">
          <q-tree v-model:selected="selectDept" :nodes="deptList" default-expand-all
                  label-key="dept_name" node-key="dept_code" selected-color="primary"
                  @update:selected="handleSelectDept"/>
        </q-card-section>
      </q-card-section>
      <q-card-section class="col q-gutter-y-md">
        <q-card-section class="row q-gutter-x-md items-center">
          <q-input v-model="queryParams.username" :label="$t('Username')" dense outlined style="width: 20%"/>
          <q-input v-model="queryParams.real_name" :label="$t('RealName')" dense outlined style="width: 20%"/>
          <q-btn :label="$t('Search')" color="primary" @click="handleSearch"/>
          <q-btn :label="$t('Reset')" color="primary" @click="resetSearch"/>
        </q-card-section>
        <q-card-section>
          <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                   :rows-per-page-options="pageOptions" row-key="id" separator="cell"
                   @request="onRequest">
            <template v-slot:top="props">
              <q-btn v-has="'user:add'" :label="$t('Add') + ' ' + $t('User')" color="primary"
                     @click="showAddForm()"/>
              <q-space/>
              <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                     round @click="props.toggleFullscreen"/>
            </template>
            <template v-slot:body-cell-avatar="props">
              <q-td :props="props">
                <Cili-avatar :src="props.row.avatar"/>
              </q-td>
            </template>
            <template v-slot:body-cell-gender="props">
              <q-td :props="props">
                <CiliDictShow :dictCode="props.row.gender"/>
              </q-td>
            </template>
            <template v-slot:body-cell-role="props">
              <q-td :props="props">
                <div class="column items-center q-gutter-xs">
                  <q-badge v-for="(item, index) in props.row.role" :key="index" class="col"
                           color="primary">
                    {{ item.role_name }}
                  </q-badge>
                </div>
              </q-td>
            </template>
            <template v-slot:body-cell-dept="props">
              <q-td :props="props">
                <div class="column items-center q-gutter-xs">
                  <q-badge v-for="(item, index) in props.row.dept" :key="index" class="col"
                           color="primary">
                    {{ item.dept_name }}
                  </q-badge>
                </div>
              </q-td>
            </template>
            <template v-slot:body-cell-status="props">
              <q-td :props="props">
                <CiliDictShow :dictCode="props.row.status"/>
              </q-td>
            </template>
            <template v-slot:body-cell-stable="props">
              <q-td :props="props">
                <CiliDictShow :dictCode="props.row.stable"/>
              </q-td>
            </template>
            <template v-slot:body-cell-actions="props">
              <q-td :props="props" class="q-gutter-x-md">
                <q-btn v-has="'user:edit'" :label="$t('Edit')" color="primary" dense flat
                       @click="showEditForm(props.row)">
                </q-btn>
                <q-btn-dropdown :label="$t('More')" color="primary" dense flat menu-anchor="bottom left"
                                menu-self="top left">
                  <q-list dense>
                    <q-item v-close-popup v-has="'user:password'" clickable
                            @click="resetPassword(props.row)">
                      <q-item-section>
                        <q-item-label>{{ $t('Reset') + $t('Password') }}</q-item-label>
                      </q-item-section>
                    </q-item>

                    <q-item v-close-popup v-has="'user:delete'" clickable
                            @click="handleDelete(props.row)">
                      <q-item-section>
                        <q-item-label>{{ $t('Delete') }}</q-item-label>
                      </q-item-section>
                    </q-item>
                  </q-list>
                </q-btn-dropdown>
              </q-td>
            </template>
          </q-table>
        </q-card-section>
      </q-card-section>
    </q-card>
    <recordDetail ref="recordDetailDialog" :deptList="deptList" @handleFinish="handleFinish"/>
  </q-page>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import RecordDetail from './modules/recordDetail.vue'
import {postAction} from 'src/api/manage'
import {computed, onMounted, ref} from 'vue'

const url = {
  list: 'user/get-user-list',
  delete: 'user/delete-user-by-id',
  resetPassword: 'user/reset-password',
  deptListUrl: 'dept/get-dept-list'
}
const columns = computed(() => {
  return [
    {name: 'avatar', align: 'center', label: t('Avatar'), field: 'avatar'},
    {name: 'username', align: 'center', label: t('Username'), field: 'username'},
    {name: 'nickname', align: 'center', label: t('Nickname'), field: 'nickname'},
    {name: 'real_name', align: 'center', label: t('RealName'), field: 'real_name'},
    {name: 'gender', align: 'center', label: t('Gender'), field: 'gender'},
    {name: 'role', align: 'center', label: t('Role'), field: 'role'},
    {name: 'dept', align: 'center', label: t('Dept'), field: 'dept'},
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
  CiliDictShow,
  loading,
  tableData,
  recordDetailDialog,
  showAddForm,
  showEditForm,
  onRequest,
  getTableData,
  handleSearch,
  handleFinish,
  handleDelete,
} = useTableData(url)

onMounted(() => {
  pagination.value.sortBy = 'username'
  queryParams.value = {
    with_admin: true,
  }
  getTableData()
  getDeptList()
})

const selectDept = ref(null)
const handleSelectDept = (target) => {
  queryParams.value.dept_code = target
  getTableData()
}
const deptList = ref([])
const getDeptList = () => {
  deptList.value = []
  postAction(url.deptListUrl, {
    sort_by: 'sort',
    desc: false,
    page: 1,
    page_size: 99999
  }).then(res => {
    deptList.value = res.data.records
  })
}

const resetPassword = (row) => {
  $q.dialog({
    title: t('Reset') + t('Password'),
    message: t('Confirm') + t('Reset') + t('Password') + '?' + '(' + row.username + ')',
    cancel: true,
    persistent: true,
  }).onOk(async () => {
    const res = await postAction(url.resetPassword, {
      id: row.id,
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

const resetSearch = () => {
  selectDept.value = null
  queryParams.value = {
    with_admin: true,
  }
  pagination.value.sortBy = 'username'
  getTableData()
}

</script>
