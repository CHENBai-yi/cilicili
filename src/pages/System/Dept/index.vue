<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-btn v-has="'dept:addParent'" :label="$t('Add') + $t('Parent') + $t('Dept')" color="primary"
               @click="showAddParentForm()"/>
      </q-card-section>
      <q-card-section>
        <q-hierarchy :columns="columns" :data="deptTree" dense separator="cell">
          <template v-slot:body="props">
            <gqa-tree-td :treeTd="props" firstTd="sort"></gqa-tree-td>
            <td class="text-center">{{ props.item.dept_name }}</td>
            <td class="text-center">{{ props.item.dept_code }}</td>
            <td class="text-center">
              <GqaShowName :customNameObject="props.item.leader_user"/>
            </td>
            <td class="text-center">
              <GqaDictShow :dictCode="props.item.status"/>
            </td>
            <td class="text-center q-gutter-x-md">
              <q-btn v-has="'dept:edit'" :label="$t('Edit')" color="primary" dense flat
                     @click="showEditForm(props.item)">
              </q-btn>
              <q-btn-dropdown :label="$t('More')" color="primary" dense flat menu-anchor="bottom left"
                              menu-self="top left">
                <q-list dense>
                  <q-item v-close-popup v-has="'dept:addChildren'" clickable
                          @click="showAddChildrenForm(props.item.dept_code)">
                    <q-item-section>
                      <q-item-label>{{ $t('Add') + $t('Children') + $t('Dept') }}</q-item-label>
                    </q-item-section>
                  </q-item>

                  <q-item v-close-popup v-has="'dept:deptUser'" clickable
                          @click="showDeptUser(props.item)">
                    <q-item-section>
                      <q-item-label>{{ $t('Dept') + $t('User') }}</q-item-label>
                    </q-item-section>
                  </q-item>

                  <q-item v-close-popup v-has="'dept:delete'" clickable @click="handleDelete(props.item)">
                    <q-item-section>
                      <q-item-label>{{ $t('Delete') }}</q-item-label>
                    </q-item-section>
                  </q-item>
                </q-list>
              </q-btn-dropdown>
            </td>
          </template>
        </q-hierarchy>
      </q-card-section>
    </q-card>
    <recordDetail ref="recordDetailDialog" :deptTree="deptTree" @handleFinish="handleFinish"/>
    <DeptUser ref="deptUserDialog"/>
  </q-page>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {computed, onMounted, ref} from 'vue'
import {ChangeNullChildren2Array} from 'src/utils/arrayAndTree'
import DeptUser from './modules/DeptUser.vue'
import RecordDetail from './modules/recordDetail.vue'

const url = {
  list: 'dept/get-dept-list',
  delete: 'dept/delete-dept-by-id',
}
const columns = computed(() => {
  return [
    {name: 'sort', align: 'center', label: t('Sort'), field: 'sort'},
    {name: 'dept_name', align: 'center', label: t('Dept') + t('Name'), field: 'dept_name'},
    {name: 'dept_code', align: 'center', label: t('Dept') + t('Code'), field: 'dept_code'},
    {name: 'leader', align: 'center', label: t('Leader'), field: 'leader'},
    {name: 'status', align: 'center', label: t('Status'), field: 'status'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
  t,
  pagination,
  GqaDictShow,
  GqaShowName,
  tableData,
  recordDetailDialog,
  showAddForm,
  showEditForm,
  getTableData,
  handleFinish,
  handleDelete,
} = useTableData(url)

onMounted(() => {
  pagination.value.rowsPerPage = 99999
  getTableData()
})

const deptTree = computed(() => {
  return ChangeNullChildren2Array(tableData.value) || []

})

const deptUserDialog = ref(null)
const showDeptUser = (dept) => {
  deptUserDialog.value.show(dept)
}
const showAddParentForm = () => {
  showAddForm()
}
const showAddChildrenForm = (deptCode) => {
  recordDetailDialog.value.formType = 'add'
  recordDetailDialog.value.show()
  recordDetailDialog.value.recordDetail.value.parent_code = deptCode
}
</script>
