<template>
  <q-table
    v-model:pagination="pagination"
    :columns="columns"
    :loading="loading"
    :rows="tableData"
    :rows-per-page-options="pageOptions"
    flat
    row-key="name"
    @request="onRequest"
  >
    <template v-slot:body="props">
      <q-tr :props="props">
        <q-td key="name" :props="props">
          {{ props.row.name }}
        </q-td>
        <q-td key="img" :props="props">
          <n-image
            :src="processApi+props.row.img"
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
        <q-td key="subject" :props="props">
          <q-badge color="purple">
            {{ props.row.subject }}
          </q-badge>
        </q-td>
        <q-td key="price" :props="props">
          <q-badge color="purple">
            {{ props.row.price }}
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
          <CiliDictShow :dict-code="props.row.status"/>
        </q-td>
        <q-td key="operation" :props="props">
          <q-btn v-if="props.row.reason" color="primary" flat label="查看审核结果"
                 @click="showResult(props.row.reason)">
          </q-btn>
          <q-btn v-if="props.row.logical_delete>0" color="red" flat label="下架" @click="deleteCourse(props.row.id)">
          </q-btn>
          <q-btn v-else color="primary" flat label="上架" @click="uploadCourse(props.row.id)">
          </q-btn>
        </q-td>
      </q-tr>
    </template>
  </q-table>
</template>

<script setup>
import {defineEmits, onMounted, reactive, ref} from "vue";
import {postAction} from "src/api/manage";
import CiliDictShow from "src/components/CiliDictShow/index.vue";
import UseCiliTableDate from "../../../composables/useCiliTableDate";
import {ElMessageBox} from "element-plus";
import {useI18n} from 'vue-i18n'

const emits = defineEmits(['refresh'])
const {t} = useI18n()
const processApi = ref(process.env.API)
const columns = reactive([
  {
    name: 'name',
    required: true,
    label: '标题',
    align: 'center',
    field: row => row.name,
    format: val => `${val}`,

  },
  {name: 'img', align: 'center', label: '封面', field: 'img'},
  {name: 'author', align: 'center', label: '作者', field: 'author'},
  {name: 'subject', align: 'center', label: '类别', field: 'subject'},
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
])
const url = {
  list: 'courses/get-course-info',
  delete: 'courses/delete-course-info-by-id',
  update: 'courses/update-courses'
}
const {
  getTableData,
  onRequest,
  loading,
  tableData,
  pageOptions,
  queryParams,
  pagination
} = UseCiliTableDate(url)
onMounted(() => {
  queryParams.value.status = 'onOffPass_pass'
  getTableData()
})
const showResult = (e) => {
  ElMessageBox.alert(
    `<strong>${e}</strong>`,
    '审核结果',
    {
      dangerouslyUseHTMLString: true,
      showConfirmButton: false,
      showCancelButton: false,
      type: 'info',
      center: true,
    }
  )
}
const deleteCourse = (id) => {
  ElMessageBox.confirm(
    t('AuditOrder'),
    {
      confirmButtonText: t('Confirm'),
      cancelButtonText: t('Cancel'),
      type: 'warning',
    }
  )
    .then(async () => {
      queryParams.value.id = id
      const res = await postAction(url.delete, queryParams.value)
      if (res && res.code === 1) {
        emits('refresh')
        getTableData()
        window.$message.success(res.message, {render: window.$render})
      } else {
        window.$message.error(res.message, {render: window.$render})
      }
    })
}
const uploadCourse = async (id) => {
  queryParams.value.id = id
  queryParams.value.logical_delete = 1
  const res = await postAction(url.update, queryParams.value)
  if (res && res.code === 1) {
    emits('refresh')
    getTableData()
    window.$message.success(res.message, {render: window.$render})
  } else {
    window.$message.error(res.message, {render: window.$render})
  }
}
</script>

<style scoped>
.q-table--no-wrap td {
  white-space: pre-wrap;
}
</style>
