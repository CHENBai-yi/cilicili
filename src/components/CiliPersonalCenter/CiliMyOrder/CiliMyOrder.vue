<template>
  <n-list class="rounded-borders" clickable hoverable>
    <template #header>
      <div class="text-h6">📄 付款信息</div>
    </template>
    <n-list-item>
      <n-thing content-style="margin-top: 10px;">
        <div class="q-gutter-sm">
          <div>
            <n-tag :bordered="false" size="small">订单类型:</n-tag>
            -
          </div>
          <div>
            <n-tag :bordered="false" size="small">支付方式:</n-tag>
            {{ order.method }}
          </div>
          <div>
            <n-tag :bordered="false" size="small">支付时间:</n-tag>
            {{ order.created_at }}
          </div>
          <div>
            <n-tag :bordered="false" size="small">平台交易号:</n-tag>
            {{ order.trade_no }}
          </div>
          <div>
            <n-tag :bordered="false" size="small">商品名称:</n-tag>
            {{ order.product }}
          </div>
          <div>
            <n-tag :bordered="false" size="small">付款金额:</n-tag>
            {{ order.price }}
          </div>
          <div>
            <n-tag :bordered="false" size="small">优惠金额:</n-tag>
            0.00
          </div>
        </div>
      </n-thing>
    </n-list-item>
  </n-list>
</template>

<script setup>
import {onMounted, reactive, ref} from 'vue'
import {getAction} from 'src/api/manage.ts'

const urls = reactive({
  get: 'memberShip/detail'
})
const order = ref({
  method: '',
  product: '',
  status: '',
  trade_no: '',
  created_at: '',
  price: ''
})
onMounted(async () => {
  const res = await getAction(urls.get)
  if (res.code === 1) {
    console.log(res.data)
    order.value = res.data
  }
})
</script>

<style scoped>

</style>
