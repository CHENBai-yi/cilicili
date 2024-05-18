<template>
  <div class="inline-block row col-9">
    <div v-for="(item,index) in videoCatalog" :key="index">
      <div class="row ">
        <div class="col flex justify-between">
          <div class="text-h5 name">{{ item.section }} {{ item.title }}</div>
          <div class="chapter-num">{{ item.total }} 节 | {{ item.time }}</div>
        </div>
      </div>
      <div class="q-pl-md row">
        <n-ellipsis :tooltip="false" class="col no-margin desc" expand-trigger="click" line-clamp="2">
          {{ item.detail }}
        </n-ellipsis>
      </div>
      <q-btn :ripple="false" color="blue"
             flat @click="videoListShowChange(item)">
        <template #default>
          <div v-if="item.show">
            <span>收起列表</span>
            <q-icon name="keyboard_double_arrow_up"/>
          </div>
          <div v-else>
            <span>展开列表</span>
            <q-icon name="keyboard_double_arrow_down"/>
          </div>
        </template>

      </q-btn>
      <transition appear
                  enter-active-class="animated animate__fadeIn"
                  leave-active-class="animated animate__fadeOut"
      >
        <ul v-show="item.show" class="q-gutter-y-sm">
          <li v-for="(list,index) in item.detail_list" :key="list.tag" class="q-gutter-x-sm">
            <q-icon name="fas fa-video"/>
            <div class="type-text">{{ $t('Video') }}:</div>
            <span class="title_info">{{ list.tag }} {{ list.title }}  ({{ list.time }})</span>
            <n-button v-if="index===0" :bordered="false"
                      round secondary size="tiny" strong text-color="#f50101" type="error" @click.stop="tryWatch">
              <span class="text-weight-bolder">试看</span>
            </n-button>
          </li>
        </ul>
      </transition>
      <n-divider/>
    </div>
  </div>
  <CiliTryWatch ref="tryWatchComponent"/>
</template>

<script setup>

import {computed, ref} from "vue";
import useTheme from "src/composables/useTheme"
import {useCommonStore} from 'src/stores/common'
import CiliTryWatch from 'src/components/CiliTryWatch/index.vue'

const videoListShow = ref(true)
const videoListShowChange = (e) => {
  e.show = !e.show
}
const {darkTheme} = useTheme()

const commonStore = useCommonStore()
const videoCatalog = computed(() => commonStore.videoCatalog)
const tryWatchComponent = ref(void 0)
const tryWatch = () => {
  tryWatchComponent.value.tryWatch()
}
</script>

<style lang="scss" scoped>


.left_background {
  width: 120px;
  height: 33px;
}

ol, ul {
  list-style: none;
}

ul.content-box {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  height: 96px;
  width: 700px;
  margin: 42px 0 0 13px;
}

.wrapper {
  width: 1152px;
  margin: 0 auto;
}

.desc-box {
  width: 1120px;
  margin: 0 auto;
  font-size: 18px;
  letter-spacing: 0;
  line-height: 32px;
  word-break: break-all; //实现超过宽度自动换行
  display: flex;
  align-items: flex-start;
}

.desc-box-sm {
  width: 380px;
  font-size: 18px;
  letter-spacing: 0;
  line-height: 32px;
  word-break: break-all; //实现超过宽度自动换行
  display: flex;
  align-items: flex-start;
}

.type-text {
  display: inline-block;
  font-size: 14px;
  color: inherit;
  line-height: 24px;
  font-weight: 700;
}

.name {
  font-weight: 700;
  font-size: 16px;
  line-height: 24px;
  color: inherit;
  margin-bottom: 8px;
}

.desc {
  margin-bottom: 8px;
  line-height: 24px;
  font-size: 16px;
  color: inherit;
}

.title_info {
  display: inline-block;
  max-width: 600px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  vertical-align: middle;
  color: inherit;
}

.chapter-num {
  font-size: 12px;
  color: #93999f;
  line-height: 24px;
  font-weight: 400;
  float: right;
}

.n-button:hover {
  background-color: #f20d0d;
  color: white;
}

.n-button {
  color: #f50101;
}
</style>
