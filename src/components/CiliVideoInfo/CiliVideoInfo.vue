<template>
  <div :class="$q.screen.lt.sm?'q-ma-sm glossy':'q-ml-xl glossy'">
    <div :class="$q.screen.lt.sm?'':'Cili_left'">
      <div :class="$q.dark.isActive?'left_background bg-white q-mb-sm rounded-borders':''">
        <div class="title-box learn no-margin no-padding"></div>
      </div>

      <div>
        <ul :class="$q.screen.lt.sm?'flex flex-start no-padding q-gutter-md ':'content-box'" class="text-black q-mt-xs">
          <li v-for="(item,index) in testData.learn_more" :class="$q.dark.isActive&&$q.screen.lt.sm?'text-white':''">
            {{ item }}
          </li>
        </ul>
      </div>
    </div>
    <div id="classDesc" class="course-desc-container ">
      <div class="wrapper no-margin ">
        <div :class="$q.dark.isActive?'left_background bg-white q-mb-sm rounded-borders':''">
          <div class="title-box detail"></div>
        </div>

        <div :class="$q.screen.lt.sm?'desc-box-sm q-pl-xs':'desc-box'">
          {{ testData.into }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

import {onMounted, reactive, ref} from "vue";
import useTheme from "src/composables/useTheme"
import {postAction} from "src/api/manage"
import {useRoute} from 'vue-router'

const {darkTheme} = useTheme()
const $router = useRoute()
const testData = ref({
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
const urls = reactive({
  info: 'courses/get-course-info-by-id'
})
onMounted(async () => {
  const res = await postAction(urls.info, {name: $router.params.name, id: $router.params.id})
  if (res.code === 1) {
    testData.value = res.data.records
  }
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
  flex-wrap: wrap;
  justify-content: flex-start;
  height: 96px;
  width: 700px;
  margin: 42px 0 0 13px;
  //align-items: center;
  li {
    margin: 5px 10px;
  }
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
</style>
