<template>
  <div class="q-pa-md ">
    <q-card class="window-height">
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
              2
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
              进行中 0
            </q-tab>
            <q-icon class="rotate-90" name="ti-layout-line-solid"/>
            <q-tab :ripple="false" name="pass">
              已通过 0

            </q-tab>
            <q-icon class="rotate-90" name="ti-layout-line-solid"/>
            <q-tab :ripple="false" name="no_pass">
              未通过
              0

            </q-tab>
          </q-tabs>
        </q-tab-panel>
      </q-tab-panels>
      <q-tab-panels v-model="tab3">
        <q-tab-panel class="q-pt-none" name="audit">

          <el-table :data="tableData" style="width: 100%">
            <el-table-column label="Date" width="180">
              <template #default="scope">
                <div style="display: flex; align-items: center">
                  <el-icon>
                    <timer/>
                  </el-icon>
                  <span style="margin-left: 10px">{{ scope.row.date }}</span>
                </div>
              </template>
            </el-table-column>
            <el-table-column label="Name" width="180">
              <template #default="scope">
                <el-popover effect="light" placement="top" trigger="hover" width="auto">
                  <template #default>
                    <div>name: {{ scope.row.name }}</div>
                    <div>address: {{ scope.row.address }}</div>
                  </template>
                  <template #reference>
                    <el-tag>{{ scope.row.name }}</el-tag>
                  </template>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="Operations">
              <template #default="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
                >Edit
                </el-button
                >
                <el-button
                    size="small"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)"
                >Delete
                </el-button
                >
              </template>
            </el-table-column>
          </el-table>

        </q-tab-panel>
        <q-tab-panel class="q-pt-none" name="pass">

          <div>pass</div>

        </q-tab-panel>
        <q-tab-panel class="q-pt-none" name="no_pass">

          <div>no_pass</div>

        </q-tab-panel>
      </q-tab-panels>
    </q-card>
  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import {Timer} from '@element-plus/icons-vue'


const tab = ref('video')
const tab2 = ref('all_video')
const tab3 = ref('')

interface User {
  date: string
  name: string
  address: string
}

const handleEdit = (index: number, row: User) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: User) => {
  console.log(index, row)
}

const tableData: User[] = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]

</script>

<style scoped>

</style>
