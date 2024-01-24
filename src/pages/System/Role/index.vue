<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-input v-model="queryParams.role_code" :label="$t('Role') + $t('Code')" dense outlined
                 style="width: 20%"/>
        <q-input v-model="queryParams.role_name" :label="$t('Role') + $t('Name')" dense outlined
                 style="width: 20%"/>
        <q-btn :label="$t('Search')" color="primary" @click="handleSearch"/>
        <q-btn :label="$t('Reset')" color="primary" @click="resetSearch"/>
      </q-card-section>
      <q-card-section>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                 :rows-per-page-options="pageOptions"
                 row-key="id" separator="cell" @request="onRequest">
          <template v-slot:top="props">
            <q-btn v-has="'role:add'" :label="$t('Add') + ' ' + $t('Role')" color="primary"
                   @click="showAddForm()"/>
            <q-space/>
            <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                   round @click="props.toggleFullscreen"/>
          </template>
          <template v-slot:body-cell-default_page="props">
            <q-td :props="props">
              {{ $t(props.row.default_page_menu.title) }}
            </q-td>
          </template>
          <template v-slot:body-cell-stable="props">
            <q-td :props="props">
              <CiliDictShow :dictCode="props.row.stable"/>
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props" class="q-gutter-x-md">
              <q-btn v-has="'role:edit'" :label="$t('Edit')" color="primary" dense flat
                     @click="showEditForm(props.row)">
              </q-btn>
              <q-btn-dropdown :label="$t('More')" color="primary" dense flat menu-anchor="bottom left"
                              menu-self="top left">
                <q-list dense>
                  <q-item v-close-popup v-has="'role:permission'" clickable
                          @click="showRolePermission(props.row)">
                    <q-item-section>
                      <q-item-label> {{ $t('Role') + $t('Permission') }}</q-item-label>
                    </q-item-section>
                  </q-item>

                  <q-item v-close-popup v-has="'role:user'" clickable @click="showRoleUser(props.row)">
                    <q-item-section>
                      <q-item-label> {{ $t('Role') + $t('User') }}</q-item-label>
                    </q-item-section>
                  </q-item>

                  <q-item v-close-popup v-has="'role:delete'" clickable @click="handleDelete(props.row)">
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
    </q-card>
    <recordDetail ref="recordDetailDialog" @handleFinish="handleFinish"/>
    <RolePermissionDialog ref="rolePermissionDialog" @handleFinish="handleFinish"/>
    <RoleUserDialog ref="roleUserDialog"/>
  </q-page>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {computed, onMounted, ref} from 'vue'
import RolePermissionDialog from './modules/RolePermissionDialog.vue'
import RoleUserDialog from './modules/RoleUserDialog.vue'
import RecordDetail from 'src/pages/System/Role/modules/recordDetail.vue'

const url = {
  list: 'role/get-role-list',
  delete: 'role/delete-role-by-id',
}
const columns = computed(() => {
  return [
    {name: 'sort', align: 'center', label: t('Sort'), field: 'sort'},
    {name: 'role_code', align: 'center', label: t('Role') + t('Code'), field: 'role_code'},
    {name: 'role_name', align: 'center', label: t('Role') + t('Name'), field: 'role_name'},
    {name: 'default_page', align: 'center', label: t('Default') + t('Page'), field: 'default_page'},
    {name: 'stable', align: 'center', label: t('Stable'), field: 'stable'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
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
  resetSearch,
  handleFinish,
  handleDelete,
} = useTableData(url)

onMounted(() => {
  getTableData()
})

const rolePermissionDialog = ref(null)
const showRolePermission = (row) => {
  rolePermissionDialog.value.show(row)
}
const roleUserDialog = ref(null)
const showRoleUser = (row) => {
  roleUserDialog.value.show(row)
}

</script>
