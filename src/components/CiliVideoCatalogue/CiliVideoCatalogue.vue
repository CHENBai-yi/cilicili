<template>
  <div :class="$q.screen.lt.sm?'q-ma-sm':'q-ml-xl'">
    <div v-for="(item,index) in 50">
      <div class="row">
        <div class="col-8 flex justify-between">
          <div class="text-h5 name">第一章 课程导学</div>
          <div class="chapter-num">13 节 | 213分钟</div>
        </div>
      </div>
      <div class="q-pl-md row">
        <n-ellipsis :tooltip="false" class="col-12 col-md-8 no-margin desc" expand-trigger="click" line-clamp="2">
          本次课程我们将了解计算机技术与软件专业技术资格(水平)考试中系统架构设计师专业的一般情况。内容包含软考介绍、证书价值介绍、考试介绍、考试科目介绍等。通过对系统架构设计师考试的三个科目
          (综合知识、案例分析、论文写作)的真题和考点分布的分析，总结行之有效的备考策略，并教会您典型真题的解题技巧。...
        </n-ellipsis>
      </div>
      <q-btn :ripple="false" color="blue"
             flat @click="videoListShowChange">
        <template #default>
          <div v-if="videoListShow">
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
        <ul v-show="videoListShow" class="q-gutter-y-sm">
          <li class="q-gutter-x-sm">
            <q-icon name="fas fa-video"/>
            <div class="type-text">{{ $t('Video') }}:</div>
            <span class="title_info">2-1 软件架构概念&常见分类与建模方法  (17:38)</span>
          </li>
          <li class="q-gutter-x-sm">
            <q-icon name="fas fa-video"/>
            <div class="type-text">{{ $t('Video') }}:</div>
            <span class="title_info">2-1 软件架构概念&常见分类与建模方法  (17:38)</span>
          </li>
          <li class="q-gutter-x-sm">
            <q-icon name="fas fa-video"/>
            <div class="type-text">{{ $t('Video') }}:</div>
            <span class="title_info">2-1 软件架构概念&常见分类与建模方法  (17:38)</span>
          </li>
          <li class="q-gutter-x-sm">
            <q-icon name="fas fa-video"/>
            <div class="type-text">{{ $t('Video') }}:</div>
            <span class="title_info">2-1 软件架构概念&常见分类与建模方法  (17:38)</span>
          </li>
          <li class="q-gutter-x-sm">
            <q-icon name="fas fa-video"/>
            <div class="type-text">{{ $t('Video') }}:</div>
            <span class="title_info">2-1 软件架构概念&常见分类与建模方法  (17:38)</span>
          </li>
        </ul>
      </transition>
      <n-divider/>
    </div>
  </div>
</template>

<script setup>

import {reactive, ref} from "vue";
import useTheme from "src/composables/useTheme"

const videoListShow = ref(true)
const videoListShowChange = () => {
  videoListShow.value = !videoListShow.value
}
const {darkTheme} = useTheme()
const testData = reactive({
  learn_more: [
    '掌握Flutter必备的Dart基础',
    'Flutter项目开发避坑技巧',
    '掌握全新版Flutter核心技能',
    'Flutter版本更新与适配技术',
    '快速上手企业级实战项目开发',
    '移动端原生开发必备新技能'
  ],
  into: " 本门课程作为2024年Flutter入门首选课，课程中结合了最新版的Flutter、Dart、主流插件技术进行企业级项目开发，并融入了大量Flutter版本更新问题的解决方案，以及与H5混合开发和通信、多屏适配等高频技能，课程中还将讲师近几年在一线开发中积累的大量开发、避坑等经验传授给大家，让各位小伙伴在实际开发中能更好的运用Flutter技术。"
})
</script>

<style lang="scss" scoped>
.Cili_left {
  width: 800px;
  height: 220px;
  background-image: url("/studyed-left.png");
  background-size: cover;
  background-position: center;
}

.left_background {
  width: 120px;
  height: 33px;
}

.title-box {
  width: 115px;
  height: 28px;
  background-size: cover;
  background-position: center;
  margin: 22px 0 30px 0;

  &.learn {
    background-image: url("/studyed-title.png");
  }

  &.detail {
    background-image: url("/course-desc-title.png");
  }
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
</style>
