<template>
  <q-dialog v-model="recordDetailVisible">
    <q-card style="width: 900px; max-width: 80vw;">
      <div class="row justify-between items-center">
        <q-card-section>
          <div class="text-h6">
            {{ $t('Register') + $t('Button') }}:
            {{ recordDetail.value.title ? $t(recordDetail.value.title) : '' }}
          </div>
        </q-card-section>
      </div>

      <q-separator/>

      <q-card-section>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="recordDetail.value.button"
                 :rows-per-page-options="pageOptions" dense row-key="button_code"
                 separator="cell">

          <template v-slot:top>
            <q-btn dense icon="add" round @click="addButton"/>
          </template>

          <template v-slot:header-cell-button_name="props">
            <q-th :props="props">
              {{ props.col.label }}
              <q-icon name="edit" size="1.3em"/>
            </q-th>
          </template>
          <template v-slot:body-cell-button_name="props">
            <q-td key="button_name" :props="props">
              {{ props.row.button_name }}
              <q-popup-edit v-slot="scope" v-model="props.row.button_name"
                            :validate="val => val && val.length > 0">
                <q-input v-model="scope.value" :rules="[val => scope.validate(val) || $t('NeedInput')]" autofocus
                         counter dense
                         no-error-icon>
                  <template v-slot:after>
                    <q-btn color="negative" dense flat icon="cancel"
                           @click.stop.prevent="scope.cancel"/>

                    <q-btn :disable="scope.validate(scope.value) === false || scope.initialValue === scope.value"
                           color="positive" dense flat
                           icon="check_circle"
                           @click.stop.prevent="scope.set"/>
                  </template>
                </q-input>
              </q-popup-edit>
            </q-td>
          </template>

          <template v-slot:header-cell-button_code="props">
            <q-th :props="props">
              {{ props.col.label }}
              <q-icon name="edit" size="1.3em"/>
            </q-th>
          </template>
          <template v-slot:body-cell-button_code="props">
            <q-td key="button_code" :props="props">
              {{ props.row.button_code }}
              <q-popup-edit v-slot="scope" v-model="props.row.button_code" :validate="val =>
    val && val.length > 0
    && val.indexOf(recordDetail.value.name + ':') === 0
    && val.slice(recordDetail.value.name.length + 1).indexOf(':') === -1
    && !recordDetail.value.button.some(item => item.button_code === val)">
                {{
                  $t('Button') + $t('Code') + ' ' + $t('StartWith', {
                    name: recordDetail.value.name +
                      ':'
                  })
                }}
                <q-input v-model="scope.value" :rules="[val => scope.validate(val) || $t('FixForm')]" autofocus counter
                         dense
                         no-error-icon>
                  <template v-slot:after>
                    <q-btn color="negative" dense flat icon="cancel"
                           @click.stop.prevent="scope.cancel"/>

                    <q-btn :disable="scope.validate(scope.value) === false || scope.initialValue === scope.value"
                           color="positive" dense flat
                           icon="check_circle"
                           @click.stop.prevent="scope.set"/>
                  </template>
                </q-input>
              </q-popup-edit>
            </q-td>
          </template>

          <template v-slot:body-cell-actions="props">
            <q-td :props="props">
              <div class="q-gutter-xs">
                <q-btn :label="$t('Delete')" color="negative" dense @click="deleteButton(props.row)"/>
              </div>
            </q-td>
          </template>

        </q-table>
      </q-card-section>
      <q-card-actions align="right">
        <q-btn :label="$t('Save')" color="primary" @click="editButton"/>
        <q-btn v-close-popup :label="$t('Cancel')" color="negative"/>
      </q-card-actions>
      <q-inner-loading :showing="loading">
        <q-spinner-gears color="primary" size="50px"/>
      </q-inner-loading>
    </q-card>
  </q-dialog>
</template>

<script setup>
import useRecordDetail from 'src/composables/useRecordDetail'
import {computed, ref} from 'vue'
import {postAction} from 'src/api/manage';

const emit = defineEmits(['handleFinish'])
const pagination = ref({
  sortBy: 'button_code',
  descending: false,
  page: 1,
  rowsPerPage: 10,
})
const pageOptions = ref([10, 30, 50, 100])
const url = {
  edit: 'menu/edit-menu',
  queryById: 'menu/query-menu-by-id',
}
const {
  $q,
  t,
  recordDetail,
  recordDetailVisible,
  loading,
  show,
} = useRecordDetail(url, emit)

const columns = computed(() => {
  return [
    {name: 'menu_name', align: 'center', label: t('Menu') + t('Name'), field: 'menu_name'},
    {name: 'button_name', align: 'center', label: t('Button') + t('Name'), field: 'button_name'},
    {name: 'button_code', align: 'center', label: t('Button') + t('Code'), field: 'button_code'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})

defineExpose({
  show,
  recordDetail
})

const editButton = async () => {
  for (let b of recordDetail.value.button) {
    if (b.menu_name === '' || b.button_code === '' || b.button_name === '') {
      return
    }
  }
  const res = await postAction(url.edit, recordDetail.value)
  if (res.code === 1) {
    $q.notify({
      type: 'positive',
      message: res.message,
    })
    show({
      id: recordDetail.value.id
    })
  }
}

const deleteButton = (row) => {
  recordDetail.value.button = recordDetail.value.button.filter(item => item.button_code !== row.button_code)
}
const addButton = () => {
  recordDetail.value.button.push({
    menu_name: recordDetail.value.name,
    button_name: '',
    button_code: ''
  })
}
</script>
