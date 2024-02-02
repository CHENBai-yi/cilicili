<template>
  <q-layout view="hhh lpR fff">
    <q-page-container>
      <router-view/>
      <el-affix :offset="110" class="float-right no-padding no-margin " position="bottom">
        <ul class=" no-padding no-margin v-popover-content" style="list-style: none">
          <li>
            <q-btn dense fab-mini flat rounded stack unelevated>
              中
              <q-tooltip :offset="[10, 10]" anchor="center left" self="center right">
                <em>切换到简体中文</em>
              </q-tooltip>
            </q-btn>
          </li>
          <li>
            <q-btn dense fab-mini flat icon="eva-moon-outline" rounded stack unelevated>
              <q-tooltip :offset="[10, 10]" anchor="center left" self="center right">
                <em>切换深色外观</em>
              </q-tooltip>
            </q-btn>
          </li>
          <li>
            <q-btn v-if="gotop" dense fab-mini flat icon="bi-chevron-bar-up" rounded stack
                   unelevated @click="toTop">
              <q-tooltip :offset="[10, 10]" anchor="center left" self="center right">
                <em>TOP</em>
              </q-tooltip>
            </q-btn>
          </li>
        </ul>
      </el-affix>
    </q-page-container>
  </q-layout>

</template>
<script setup>
import {onMounted, onUnmounted, ref} from "vue";

const gotop = ref(false)//控制返回顶部按钮的显隐
onMounted(() => window.addEventListener("scroll", handleScroll, true))
onUnmounted(() => window.removeEventListener("scroll", handleScroll, true))

const handleScroll = () => {

  let scrolltop = document.documentElement.scrollTop || document.body.scrollTop;

  scrolltop > 50 ? (gotop.value = true) : (gotop.value = false);
}
const toTop = () => {

  let top = document.documentElement.scrollTop || document.body.scrollTop;
  // 实现滚动效果
  const timeTop = setInterval(() => {
    document.body.scrollTop = document.documentElement.scrollTop = top -= 50;
    if (top <= 0) {
      clearInterval(timeTop);
    }
  }, 15);
}
</script>

