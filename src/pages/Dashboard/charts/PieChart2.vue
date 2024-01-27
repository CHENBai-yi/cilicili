<template>
  <q-card>
    <div ref="piechart" style="height: 400px;"></div>
    <q-resize-observer @resize="onResize"/>
  </q-card>
</template>

<script setup>
import {getAction} from "src/api/manage"
import {markRaw, onMounted, ref, watch} from 'vue';
import useTheme from 'src/composables/useTheme'
// const echarts = require('echarts')
import * as echarts from "echarts";

const url = {
  command: 'public/command',
}
const {darkThemeChart} = useTheme()
const chart = ref(null)
const piechart = ref(null)
onMounted(() => {
  init()
})
watch(() => darkThemeChart.value, () => {
  init()
})
const dataSet = ref([
  {value: 1048, name: 'Search Engine'},
  {value: 735, name: 'Direct'},
  {value: 580, name: 'Email'},
  {value: 484, name: 'Union Ads'},
  {value: 300, name: 'Video Ads'}
])
const getOption = (data) => {
  return {
    toolbox: {
      show: true,
      feature: {
        mark: {show: true},
        dataView: {show: true, readOnly: false},
        restore: {show: true},
        saveAsImage: {show: true}
      }
    },
    series: [
      {
        name: 'Nightingale Chart',
        type: 'pie',
        radius: [50, 150],
        center: ['50%', '50%'],
        roseType: 'area',
        itemStyle: {
          borderRadius: 8
        },
        data: data
      }
    ]
  }
}
const init = async () => {
  let ct = piechart.value;
  echarts.dispose(ct);
  chart.value = markRaw(echarts.init(ct, darkThemeChart.value));
  const res = await getAction(url.command)
  let options = {}
  if (res.code === 1) {
    options = getOption(res.data.commandStats)
  } else {
    options = getOption(dataSet.value)
  }

  chart.value.setOption(options);
}
const onResize = () => {
  chart.value?.resize()
}


</script>
