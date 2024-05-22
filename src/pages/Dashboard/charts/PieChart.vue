<template>
  <q-card>
    <div ref="piechart" style="height: 298px;"></div>
    <q-resize-observer @resize="onResize"/>
  </q-card>
</template>

<script setup>
import {markRaw, onMounted, ref, watch} from 'vue';
import useTheme from 'src/composables/useTheme'
// const echarts = require('echarts')
import * as echarts from "echarts";
import {getAction} from "src/api/manage"

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
const init = async () => {
  let ct = piechart.value;
  echarts.dispose(ct);
  chart.value = markRaw(echarts.init(ct, darkThemeChart.value));
  const res = await getAction(url.command)
  let options = {}

  if (res.code === 1) {
    let dataSet = [
      {
        value: res.data.info.connected_clients,
        name: '客户端连接数量',
      },
      {
        value: res.data.info.used_memory_peak_perc.replace('%', ''),
        name: '内存使用峰值',
      },
      {
        value: res.data.info.mem_fragmentation_ratio,
        name: '内存碎片化比例',
      }
    ]
    options = getOption(dataSet)
  } else {
    options = getOption(dataSet.value)
  }


  chart.value.setOption(options);
}
const onResize = () => {
  chart.value?.resize()
}


const dataSet = ref([
  {
    value: 20,
    name: 'Perfect',
  },
  {
    value: 40,
    name: 'Good',
  },
  {
    value: 60,
    name: 'Commonly',
  }
])
const getOption = (data) => {
  return {
    backgroundColor: '#0b182b',
    borderColor: '#535b69',
    borderRadius: 8,
    series: [
      {
        type: 'gauge',
        startAngle: 90,
        endAngle: -270,
        pointer: {
          show: false
        },
        progress: {
          show: true,
          overlap: false,
          roundCap: true,
          clip: false,
          itemStyle: {
            borderWidth: 1,
            borderColor: '#464646'
          }
        },
        axisLine: {
          lineStyle: {
            width: 40
          }
        },
        splitLine: {
          show: false,
          distance: 0,
          length: 10
        },
        axisTick: {
          show: false
        },
        axisLabel: {
          show: false,
          distance: 50
        },
        data: [
          {
            value: data[0].value,
            name: data[0].name,
            title: {
              offsetCenter: ['0%', '-30%']
            },
            detail: {
              valueAnimation: true,
              offsetCenter: ['0%', '-15%'],
              formatter: '{value}台'
            }
          },
          {
            value: data[1].value,
            name: data[1].name,
            title: {
              offsetCenter: ['0%', '0%']
            },
            detail: {
              valueAnimation: true,
              offsetCenter: ['0%', '15%'],
              formatter: '{value}%'
            }
          },
          {
            value: data[2].value,
            name: data[2].name,
            title: {
              offsetCenter: ['0%', '30%']
            },
            detail: {
              valueAnimation: true,
              offsetCenter: ['0%', '45%'],
              formatter: '{value}%'
            }
          }
        ],
        title: {
          fontSize: 16,
          color: 'white'
        },
        detail: {
          height: 4,
          backgroundColor: '',
          fontSize: 14,
          color: 'white',
          borderColor: 'inherit',
          borderRadius: 20,
          borderWidth: 1,
        }
      }
    ]
  }
}
</script>
