<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-input v-model="queryParams.teacher" :label="$t('Author')" dense outlined style="width: 20%"/>
        <q-select v-model="queryParams.status1" :label="$t('Wait')+$t('Audit')"
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
                 dense row-key="id" separator="cell" @request="onRequest">
          <template v-slot:top="props">
            <q-space/>
            <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                   round @click="props.toggleFullscreen"/>
          </template>
          <template v-slot:body="props">
            <q-tr :props="props">
              <q-td key="name" :props="props">
                {{ props.row.name }}
              </q-td>
              <q-td key="img" :props="props">
                <n-image
                  :src="api+props.row.img"
                  fallback-src="https://07akioni.oss-cn-beijing.aliyuncs.com/07akioni.jpeg"
                  height="72"
                  width="125"
                />
              </q-td>
              <q-td key="author" :props="props">
                <q-badge color="purple">
                  {{ props.row.author }}
                </q-badge>
              </q-td>
              <q-td key="kind" :props="props">
                <q-badge color="purple">
                  {{ props.row.kind }}
                </q-badge>
              </q-td>
              <q-td key="price" :props="props">
                <q-badge v-if="props.row.price>0" color="purple">
                  {{ '￥' + props.row.price }}
                </q-badge>
                <q-badge v-else color="positive" outline>
                  免费
                </q-badge>
              </q-td>
              <q-td key="carbs" :props="props">
                <q-badge color="orange">
                  {{ props.row.carbs }}
                </q-badge>
              </q-td>
              <q-td key="protein" :props="props">
                <q-badge color="primary">
                  {{ props.row.protein }}
                </q-badge>
              </q-td>
              <q-td key="date" :props="props">
                <q-badge color="teal">
                  {{ props.row.date }}
                </q-badge>
              </q-td>
              <q-td key="calcium" :props="props">
                <q-badge color="accent">
                  {{ props.row.calcium }}
                </q-badge>
              </q-td>
              <q-td key="status" :props="props">
                <Status :dictCode="props.row.status"/>
              </q-td>
              <q-td key="operation" :props="props">
                <q-btn v-if="props.row.status==='onOffPass_on'" :ripple="false" color="primary" flat label="审核通过"
                       @click="pass(props.row)"/>
                <PopConfirm v-if="props.row.status==='onOffPass_off'" :reason="props.row.reason"></PopConfirm>
                <q-btn v-if="props.row.status==='onOffPass_off'" :ripple="false" color="red" disable flat
                       label="驳回" @click="prompt(props.row)"/>
                <q-btn v-else :ripple="false" color="red" flat label="驳回" @click="prompt(props.row)"/>
              </q-td>
            </q-tr>
          </template>
        </q-table>
      </q-card-section>
    </q-card>
    <q-dialog v-model="showMemoFlag">
      <q-card>
        <q-card-section>
          {{ memo }}
        </q-card-section>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {computed, onMounted, reactive, ref} from 'vue'
import useCommon from 'src/composables/useCommon'
import {postAction} from 'src/api/manage'
import {useQuasar} from "quasar";
import Status from 'src/components/CiliDictShow/status.vue'
import PopConfirm from "src/components/CiliDictShow/PopConfirm.vue";

const {showDateTime} = useCommon()
const api = ref(process.env.API)
const $q = useQuasar()

const show = ref(false)

const url = {
  list: 'courses/get-course-info',
  delete: 'log/delete-log-login-by-id',
  update: 'courses/update-courses'
}
// const columns = computed(() => {
//   return [
//     {name: 'id', align: 'center', label: 'ID', field: 'id'},
//     {name: 'login_username', align: 'center', label: t('User'), field: 'login_username'},
//     {name: 'login_ip', align: 'center', label: 'IP', field: 'login_ip'},
//     {name: 'login_browser', align: 'center', label: t('Browser'), field: 'login_browser'},
//     {name: 'login_os', align: 'center', label: t('Os'), field: 'login_os'},
//     {name: 'login_platform', align: 'center', label: t('Platform'), field: 'login_platform'},
//     {name: 'created_at', align: 'center', label: t('CreatedAt'), field: 'created_at'},
//     {name: 'login_success', align: 'center', label: t('Login') + t('Success'), field: 'login_success'},
//     {name: 'duration', align: 'center', label: t('Duration'), field: 'duration'},
//     {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
//   ]
// })
const columns = computed(() => {
  return [
    {
      name: 'name',
      style: 'width: 250px;white-space: break-spaces;',
      required: true,
      label: '标题',
      align: 'center',
      field: row => row.name,
      format: val => `${val}`
    },
    {name: 'img', align: 'center', label: '封面', field: 'img'},
    {name: 'author', align: 'center', label: '作者', field: 'author'},
    {name: 'kind', align: 'center', label: '类别', field: 'kind'},
    {name: 'price', align: 'center', label: '价格', field: 'price'},
    {name: 'carbs', align: 'center', label: '总节数', field: 'carbs'},
    {name: 'protein', align: 'center', label: '总时长', field: 'protein'},
    {name: 'date', align: 'center', label: '上传日期', field: 'date'},
    {
      name: 'status',
      label: '状态',
      align: 'center',
      field: 'calcium',
      sortable: true,
      sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
    },
    {name: 'operation', align: 'center', label: '操作', field: 'operation'}
  ]
})
const {
  t,
  dictOptions,
  pagination,
  queryParams,
  pageOptions,
  CiliDictShow,
  loading,
  tableData,
  onRequest,
  getTableData,
  handleSearch,
  resetSearch,
  handleDelete,
} = useTableData(url)

onMounted(async () => {
  pagination.value.sortBy = 'created_at'
  pagination.value.descending = true
  getTableData()
})

const showMemoFlag = ref(false)
const memo = ref('')
const showMemo = (b) => {
  memo.value = b
  showMemoFlag.value = true
}
const prompt = (row) => {
  $q.dialog({
    title: '请输入驳回原因：',
    prompt: {
      model: '',
      type: 'text' // optional
    },
    cancel: true,
    persistent: true
  }).onOk(async data => {
    console.log('>>>> OK, received', data)
    const res = await postAction(url.update, {id: row.id, reason: data, status: 'onOffPass_off'})
    if (res && res.code === 1) {
      getTableData()
    }
  }).onCancel(() => {
    console.log('>>>> Cancel')
  }).onDismiss(() => {
    console.log('I am triggered on both OK and Cancel')
  })
}
const pass = async (row) => {
  const res = await postAction(url.update, {id: row.id, reason: '', status: 'onOffPass_pass'})
  if (res && res.code === 1) {
    getTableData()
  }
}
</script>
