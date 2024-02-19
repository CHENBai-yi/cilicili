<template>
  <q-layout :class="darkTheme" style="overflow-x: hidden;"
            view="hHh Lpr lff">
    <q-header :class="darkTheme" elevated>

      <q-toolbar>
        <q-btn v-if="$q.screen.lt.sm" dense flat icon="menu" round @click="drawer = !drawer"/>
        <q-icon color="red" name="img:logo.svg" size="40px"/>
        <q-toolbar-title>
          <div class="text-h5">创作中心</div>
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="drawer"
      :breakpoint="400"
      :width="200"
      bordered
      no-swipe-open
      show-if-above
    >
      <q-scroll-area style="height: calc(100% - 150px); margin-top: 150px; border-right: 1px solid #ddd">
        <q-list padding>
          <q-item v-ripple clickable to="/">
            <q-item-section avatar>
              <q-icon name="bi-house"/>
            </q-item-section>

            <q-item-section>
              首页
            </q-item-section>
          </q-item>
          <q-separator/>
          <q-item v-ripple :active="$route.meta.active" clickable to="/create/upload">
            <q-item-section avatar>
              <q-icon name="ion-ios-create"/>
            </q-item-section>

            <q-item-section>
              视频投稿
            </q-item-section>
          </q-item>
          <q-item v-ripple clickable to="/create/manage">
            <q-item-section avatar>
              <q-icon name="manage_history"/>
            </q-item-section>

            <q-item-section>
              内容管理
            </q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>

      <q-img class="absolute-top" src="https://cdn.quasar.dev/img/material.png" style="height: 150px">
        <div class="absolute-bottom bg-transparent">
          <q-avatar class="q-mb-sm" size="56px">
            <img src="https://cdn.quasar.dev/img/boy-avatar.png">
          </q-avatar>
          <div class="text-weight-bold">Razvan Stoenescu</div>
          <div>@rstoenescu</div>
        </div>
      </q-img>
    </q-drawer>
    <q-page-container style="overflow: hidden;">
      <router-view v-slot="{ Component }">
        <component :is="Component"/>
        <q-inner-loading :showing="componentLoading">
          <q-spinner-bars
            :class="darkTheme" size="3em"
          />
        </q-inner-loading>
      </router-view>
    </q-page-container>
    <CiliBackToTop/>
    <Toast class="z-top"/>
  </q-layout>
</template>

<script setup>
import {inject, nextTick, onMounted, ref} from 'vue'
import useTheme from "src/composables/useTheme"

const {darkTheme} = useTheme()
const drawer = ref(false)

const toggleLeftDrawer = () => {
  drawer.value = !drawer.value
}

const componentLoading = ref(true)
const bus = inject('bus')
onMounted(() => {
  bus.on('changeRoute', (val) => {
    componentLoading.value = val
  })
})
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


</script>
