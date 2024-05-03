<template>
  <q-layout :class="darkTheme" style="overflow-x: hidden;"
            view="hhh Lpr lFr">
    <q-pull-to-refresh
      bg-color="black"
      color="orange-2"
      icon="autorenew"
      @refresh="refresh"
    >
      <q-page-container style="overflow: hidden;">
        <router-view v-slot="{ Component }">
          <component :is="Component"/>
          <q-inner-loading :showing="componentLoading">
            <q-spinner-bars class="fixed" size="3em" style="top:50%;left:50%"/>
          </q-inner-loading>

        </router-view>
      </q-page-container>
      <CiliBackToTop/>
    </q-pull-to-refresh>
  </q-layout>
</template>
<script setup>
import useTheme from "src/composables/useTheme"
import {useStorageStore} from 'src/stores/storage'
import {inject, nextTick, onBeforeUnmount, onMounted, ref, toRef} from "vue";

let source = undefined;
const storageStore = useStorageStore();
const componentLoading = ref(true)
const bus = inject('bus')
const flush = () => {
  if (!storageStore.GetCiliAdvertisingText() && !storageStore.GetCiliAdvertisingImg()) {
    storageStore.SetCiliAdvertising();
    storageStore.SetCiliCarousel();
  }
}
flush()
const {darkTheme} = useTheme()
const refresh = (done) => {
  setTimeout(() => {
    done()
  }, 1000)
}

nextTick(() => {
  setTimeout(() => {
    componentLoading.value = false
  }, 500)
})
const initEventSource = () => {
  if (typeof (EventSource) !== 'undefined') {
    const evtSource = new EventSource(`${process.env.API}public/subscribe-carousel`) // 后端接口，要配置允许跨域属性
    source = toRef(evtSource)
    // 当从事件源接收到数据时触发
    evtSource.onmessage = function (event) {
      if (event) {
        let data = event?.data
        try {
          data = JSON.parse(data)
          if (data.code === 1) {
            console.log(data, "EventSource")
            storageStore.SetCiliAdvertising();
            storageStore.SetCiliCarousel();
          }
        } catch (e) {
        }
      }
    }
    /*// 与事件源的连接无法打开时触发
    evtSource.onerror = function (e) {
      console.log(e);
      evtSource.close(); // 关闭连接
    }
    // 也可以侦听命名事件，即自定义的事件
    evtSource.addEventListener('msg', function (e) {
      console.log(e.data)
    })*/
  } else {
    console.log('当前浏览器不支持使用EventSource接收服务器推送事件!');
  }
}
const clearLocalStorage = () => {
  localStorage.clear();
}
onBeforeUnmount(() => {
  window.removeEventListener('beforeunload', clearLocalStorage);
  if (!!source.value) {
    source.value?.close(); // 关闭连接
  }
})
onMounted(() => {
  window.addEventListener('beforeunload', clearLocalStorage);
  bus.on('changeRoute', (val) => {
    componentLoading.value = val
  })
  initEventSource()
})
</script>

