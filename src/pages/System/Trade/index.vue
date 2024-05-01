<template>
  <q-page padding>
    <q-card flat>
      <!--      <q-card-section class="row q-gutter-x-md items-center">
              <q-input v-model="queryParams.login_username" :label="$t('User')" dense outlined style="width: 20%"/>
              <q-select v-model="queryParams.login_success" :label="$t('Login') + $t('Success')"
                        :option-label="opt => Object(opt) === opt && 'label' in opt ? $t(opt.label) : '- Null -'"
                        :options="dictOptions.yesNo" dense
                        emit-value map-options outlined
                        style="width: 20%"/>
              <q-btn :label="$t('Search')" color="primary" @click="handleSearch"/>
              <q-btn :label="$t('Reset')" color="primary" @click="resetSearch"/>
            </q-card-section>-->
      <q-card-section>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                 :rows-per-page-options="pageOptions"
                 row-key="id" separator="cell" @request="onRequest">
          <template v-slot:top="props">
            <div class="row items-center">
              <span id="bgc"></span><span class="text-h6 personal-concent">交易信息：</span>
            </div>
            <q-space/>
            <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                   round @click="props.toggleFullscreen"/>
          </template>
          <template v-slot:body-cell-login_success="props">
            <q-td :props="props">
              <q-badge :color="props.row.login_success === 'yesNo_no' ? 'negative' : 'positive'"
                       align="middle">
                <CiliDictShow :dictCode="props.row.login_success"/>
              </q-badge>
            </q-td>
          </template>
          <template v-slot:body-cell-created_at="props">
            <q-td :props="props">
              {{ showDateTime(props.row.created_at) }}
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props" class="q-gutter-x-md">
              <q-btn v-has="'order:detail'" :label="$t('Detail')" color="warning" dense flat
                     @click="showMemo(props.row.uId)">
              </q-btn>
            </q-td>
          </template>
        </q-table>
      </q-card-section>
    </q-card>
    <q-dialog v-model="showMemoFlag">
      <n-list class="rounded-borders" clickable hoverable>
        <n-list-item>
          <n-thing content-style="margin-top: 10px;">
            <div class="q-gutter-sm">
              <div>
                <n-tag :bordered="false" size="small" type="info">订单类型:</n-tag>
                -
              </div>
              <div>
                <n-tag :bordered="false" size="small" type="info">支付方式:</n-tag>
                {{ order.method }}
              </div>
              <div>
                <n-tag :bordered="false" size="small" type="info">支付时间:</n-tag>
                {{ order.created_at }}
              </div>
              <div>
                <n-tag :bordered="false" size="small" type="info">平台交易号:</n-tag>
                {{ order.trade_no }}
              </div>
              <div>
                <n-tag :bordered="false" size="small" type="info">商品名称:</n-tag>
                {{ order.product }}
              </div>
              <div>
                <n-tag :bordered="false" size="small" type="info">付款金额:</n-tag>
                {{ order.price }}
              </div>
              <div>
                <n-tag :bordered="false" size="small" type="info">优惠金额:</n-tag>
                {{ order.discount }}
              </div>
            </div>
          </n-thing>
        </n-list-item>
      </n-list>
    </q-dialog>
  </q-page>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {computed, onMounted, ref} from 'vue'
import useCommon from 'src/composables/useCommon'
import {getAction} from 'src/api/manage'

const {showDateTime} = useCommon()
const url = {
  list: 'memberShip/member-ship-list',
  detail: 'memberShip/detail',
}

const columns = computed(() => {
  return [
    {name: 'trade_no', align: 'center', label: '订单编号', field: 'trade_no'},
    {name: 'price', align: 'center', label: '付款金额', field: 'price'},
    {name: 'type', align: 'center', label: '订单类型', field: 'type'},
    {name: 'username', align: 'center', label: '购买人', field: 'username'},
    {name: 'uId', align: 'center', label: '购买人编号', field: 'uId'},
    {name: 'created_at', align: 'center', label: t('CreatedAt'), field: 'created_at'},
    {name: 'days', align: 'center', label: '购买天数', field: 'days'},
    {name: 'status', align: 'center', label: '订单状态', field: 'status'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
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
const order = ref({
  method: '-',
  product: '-',
  status: '-',
  trade_no: '-',
  created_at: '-',
  price: '-',
  discount: '-'
})
onMounted(async () => {
  pagination.value.sortBy = 'created_at'
  pagination.value.descending = true
  getTableData()
})

const showMemoFlag = ref(false)
const memo = ref('')
const showMemo = async (id) => {
  const res = await getAction(url.detail, {id})
  if (res.code === 1) {
    console.log(res.data)
    order.value = {...order.value, ...res.data}
    showMemoFlag.value = true
  }

}

</script>
<style scoped>
#bgc {
  height: 20px;
  width: 8px;
  border-radius: 4px;
  background-color: #DF3225;
  display: block;
  margin-right: 20px;
  margin-top: 2px;
}

.personal-concent {
  font-size: 20px;
  font-weight: bold;


}
</style>
