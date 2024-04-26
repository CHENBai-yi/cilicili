<template>
  <div class="q-pa-md ">
    <q-card class="window-height scroll">
      <q-tabs
        v-model="tab"
        active-color="primary"
        align="left"
        class="text-grey"
        dense
        indicator-color="primary"
        narrow-indicator
      >
        <q-tab alert name="video">
          <template #default>
            <div class="text-h6 text-weight-bold">视频管理</div>
          </template>
        </q-tab>
      </q-tabs>

      <q-separator/>

      <q-tab-panels v-model="tab" animated>
        <q-tab-panel class="q-pa-none q-pt-sm" name="video">
          <q-tabs
            v-model="tab2"
            active-color="blue"
            align="left"
            dense
            indicator-color="transparent"
            inline-label
            narrow-indicator
            no-caps
          >
            <q-tab :ripple="false" name="all_video">
              全部稿件&nbsp;<q-badge align="middle" color="teal" outline>
              {{ total }}
            </q-badge>
            </q-tab>
          </q-tabs>

        </q-tab-panel>

      </q-tab-panels>
      <q-tab-panels v-model="tab2">
        <q-tab-panel class="no-margin no-padding" name="all_video">
          <q-tabs
            v-model="tab3"
            active-color="blue"
            align="left"
            dense
            indicator-color="transparent"
            inline-label
            narrow-indicator
            no-caps
          >
            <q-tab :ripple="false" name="audit">
              进行中 {{ on }}
            </q-tab>
            <q-icon class="rotate-90" name="ti-layout-line-solid"/>
            <q-tab :ripple="false" name="pass">
              已通过 {{ pass }}

            </q-tab>
            <q-icon class="rotate-90" name="ti-layout-line-solid"/>
            <q-tab :ripple="false" name="no_pass">
              未通过
              {{ off }}
            </q-tab>
          </q-tabs>
        </q-tab-panel>
      </q-tab-panels>
      <q-tab-panels v-model="tab3" class="scroll">
        <q-tab-panel class="q-pa-none" name="audit">
          <CiliGoingTable @refresh="getCoursesCount"/>
        </q-tab-panel>
        <q-tab-panel class="q-pa-none" name="pass">

          <CiliPassTable @refresh="getCoursesCount"/>

        </q-tab-panel>
        <q-tab-panel class="q-pa-none" name="no_pass">
          <CiliNoPassTable @refresh="getCoursesCount"/>
        </q-tab-panel>
      </q-tab-panels>
    </q-card>

  </div>

</template>

<script setup>
import {onMounted, ref} from 'vue'
import useTheme from "src/composables/useTheme"
import {postAction} from "src/api/manage"
import CiliGoingTable from './module/CiliGoingTable.vue'
import CiliPassTable from './module/CiliPassTable.vue'
import CiliNoPassTable from './module/CiliNoPassTable.vue'

const {darkTheme} = useTheme()
const tab = ref('video')
const tab2 = ref('all_video')
const tab3 = ref('audit')
const urls = ref('courses/get-courses-count')
const total = ref(null)
const pass = ref(null)
const on = ref(null)
const off = ref(null)

const getCoursesCount = async () => {
  const res = await postAction(urls.value, {})
  if (res.code) {
    const {data} = res
    total.value = data?.total
    pass.value = data?.pass
    on.value = data?.on
    off.value = data?.off
  }
}
onMounted(() => getCoursesCount())
</script>

<style scoped>
.q-table--no-wrap td {
  white-space: pre-wrap;
}
</style>
