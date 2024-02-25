<template>
  <div class="q-pa-md ">
    <div class="row">
      <q-btn class="q-mb-md" color="white" label="Reset" push text-color="primary" @click="step = 1"/>
      <div class="col-6 flex justify-end">
        <div class="text-h4 ">发布新课程</div>
      </div>
    </div>
    <q-stepper
      ref="stepper"
      v-model="step"
      active-color="primary"
      animated
      done-color="secondary"
      header-nav
    >
      <q-step
        :done="step > 1"
        :header-nav="step > 1"
        :name="1"
        icon="settings"
        title="填写课程基本信息"
      >
        <el-form :model="form" label-width="120px">
          <div :class="$q.screen.lt.sm?'column':'row'">

            <div class="col-8">
              <el-form-item label="课程名称：">
                <el-input v-model="form.name"/>
              </el-form-item>
              <el-form-item label="课程卖点：">
                <n-dynamic-tags v-model:value="form.tags" :render-tag="renderTag"/>
              </el-form-item>
              <el-form-item label="课程目录：">

                <n-table :bordered="false">
                  <thead>
                  <tr align="center">
                    <th style="background: rgb(238, 243, 255); color: rgb(51, 51, 51);">章节
                    </th>
                    <th style="background: rgb(238, 243, 255); color: rgb(51, 51, 51);">标题</th>
                    <th style="background: rgb(238, 243, 255); color: rgb(51, 51, 51);">章节简介</th>
                    <th style="background: rgb(238, 243, 255); color: rgb(51, 51, 51);">操作</th>
                  </tr>
                  </thead>

                  <tbody>
                  <template v-for="(item,index) in form.catalog" :key="item.id">
                    <tr align="center">
                      <td width="100">
                        <n-input v-model:value="item.id" placeholder="请输入内容"/>
                      </td>
                      <td>
                        <n-input v-model:value="item.title" clearable maxlength="50" placeholder="请输入内容" show-count
                                 style="min-width: 50%"/>
                      </td>
                      <td>
                        <n-input v-model:value="item.desc" clearable maxlength="100" placeholder="请输入内容" show-count
                                 style="min-width: 50%"/>
                      </td>
                      <td class="no-wrap flex q-gutter-x-sm">
                        <n-button round size="medium" strong type="success"
                                  @click="handleAddBar(item.id,item.bar.length+1)">
                          添加小节
                        </n-button>
                        <n-button round size="medium" strong type="error" @click="handleDel(item.id,null)">
                          删除
                        </n-button>
                      </td>
                    </tr>
                    <tr v-for="(item2,index) in item.bar" v-if="item.bar.length>0"
                        :key="item2.id"
                        align="center">
                      <td width="100">
                        <n-input v-model:value="item2.id" placeholder="请输入内容"/>
                      </td>
                      <td colspan="2">
                        <n-input v-model:value="item2.title" clearable maxlength="50" placeholder="请输入小节标题"
                                 show-count
                                 style="min-width: 50%"/>
                      </td>
                      <td class="no-wrap flex q-gutter-x-sm">
                        <n-button round size="medium" strong type="error" @click="handleDel(item.id,item2.catalog)">
                          删除
                        </n-button>
                      </td>
                    </tr>
                  </template>

                  <tr align="center">
                    <td colspan="5">
                      <n-button dashed style="width:100%" @click="handleAdd(form.catalog.length+1)">
                        新增
                      </n-button>
                    </td>
                  </tr>
                  </tbody>

                </n-table>

              </el-form-item>
              <el-form-item label="课程介绍：">
                <el-input v-model="form.desc" type="textarea"/>
              </el-form-item>
              <el-form-item label="课程封面：">
                <CiliImageUpload @setPoster="setCoursePoster"/>
              </el-form-item>
            </div>
            <div class="col">
              <el-form-item label="授课教师：">
                <el-select v-model="form.teacher" placeholder="please select your zone">
                  <el-option label="Zone one" value="shanghai"/>
                  <el-option label="Zone two" value="beijing"/>
                </el-select>
              </el-form-item>
              <el-form-item label="课程分类：">
                <el-col :span="11">
                  <n-select v-model:value="form.kind" :options="options" clearable filterable/>
                </el-col>
                <el-col :span="2" class="text-center">
                  <span class="text-gray-500">-</span>
                </el-col>
                <el-col :span="11">
                  <n-select v-model:value="form.subject" :options="options" clearable filterable/>
                </el-col>
              </el-form-item>
              <el-form-item label="课程价格：">
                <q-radio v-model="form.price" checked-icon="task_alt" color="green" keep-color label="免费"
                         unchecked-icon="panorama_fish_eye"
                         val="免费" @update:model-value="price=''"/>
                <div>
                  <q-radio v-model="form.price" :val="price" checked-icon="task_alt" class="q-pr-xs"
                           label="自定义价格" unchecked-icon="panorama_fish_eye">
                  </q-radio>
                  <el-input-number v-model="price" :precision="2" :step="0.1"
                  />
                </div>
              </el-form-item>

            </div>
          </div>
        </el-form>
        <q-stepper-navigation>
          <q-btn color="primary" label="保存并下一步" @click="() => { done1 = true; step = 2 }"/>
        </q-stepper-navigation>
      </q-step>

      <q-step
        :done="step > 2"
        :header-nav="step > 2"
        :name="2"
        icon="assignment"
        title="创建课程大纲"

      >
        <q-layout class="q-gutter-xs">
          <q-drawer bordered class=" q-card q-pa-md" show-if-above side="left">
            <q-tree
              ref="tree"
              v-model:selected="selected"
              :nodes="form.catalog"
              accordion
              children-key="bar"
              default-expand-all
              dense
              no-nodes-label="未添加课程目录！！！"
              node-key="id"
            >
              <template v-slot:default-header="prop">
                <div class="row items-center no-wrap">
                  <q-icon :name="prop.node.hasOwnProperty('bar')? 'assignment':'eva-video-outline'" class="q-mr-sm"/>
                  <div class="text-weight-bold text-primary q-gutter-x-sm">
                    <span v-if="typeof prop.node.id=='number'">
                      {{ '第' + toChineseNumber(prop.node.id) + '章' }}
                    </span>
                    <span v-else>{{ prop.node.id }}</span>
                    <span v-if="!!prop.node.title">{{ prop.node.title }}</span>
                    <span v-else>未完成标题</span>
                  </div>
                </div>
              </template>

              <template v-slot:default-body="prop">
                <div v-if="prop.node.desc">
                  <span class="text-weight-bold">{{ prop.node.desc }}</span>
                </div>
              </template>
            </q-tree>
          </q-drawer>
          <q-drawer bordered class="q-card" show-if-above side="right">
            <div class="text-h6 text-weight-bolder q-pl-md">视频</div>
            <div class="flex justify-center q-gutter-sm q-pt-md">
              <q-uploader
                :filter="files => files.filter(file => !!selected)"
                :multiple="false"
                :url="uploadUrl"
                accept="video/*"
                bordered
                field-name="file"
                flat
                label="上传章节视频"
                square
                style="max-width: 280px"
                @added="addedVideo"
                @failed="failed"
                @removed="videoSrc=''"
                @uploaded="uploaded"
              />
            </div>
          </q-drawer>
          <q-page-container class="q-ma-sm ">
            <q-page class="q-gutter-y-md column">
              <video v-if="!videoSrc" :poster="poster" class="q-card full-width no-margin no-padding " controls
                     style="height:437px"/>
              <video v-else :src="videoSrc" class="q-card  no-margin  full-width no-padding " controls
                     style="height:437px"/>
              <q-card class="col">
                <q-card-section>
                  视频预览
                </q-card-section>
              </q-card>

            </q-page>
          </q-page-container>
        </q-layout>

        <q-stepper-navigation>
          <q-btn color="primary" label="保存并下一步" @click="() => { step = 3 }"/>
          <q-btn class="q-ml-sm" color="primary" flat label="返回" @click="step = 1"/>
        </q-stepper-navigation>
      </q-step>
      <q-step
        :done="step>3"
        :header-nav="step > 3"
        :name="3"
        icon="eva-cloud-upload-outline"
        title="提交发布"
      >
        <div class="flex items-center">
          上传视频，即表示您已同意&nbsp;<CiliLink color="#00a1d6">菠萝微课使用协议</CiliLink>
          ，请勿上传色情，反动等违法视频。
        </div>
        <q-stepper-navigation>
          <q-btn color="primary" label="确认上传" @click="onSubmit"/>
          <q-btn class="q-ml-sm" color="primary" flat label="返回" @click="step = 2"/>
        </q-stepper-navigation>
      </q-step>
    </q-stepper>
  </div>
</template>

<script setup>
import {h, reactive, ref} from 'vue'
import {NTag} from 'naive-ui'
import CiliImageUpload from 'src/components/CiliUploadComponent/CiliImageUpload/CiliImageUpload.vue'
import {toChineseNumber} from 'src/utils/ToChineseNumber'
import {useToast} from "primevue/usetoast";

const step = ref(1)
// do not use same name with ref
const form = reactive({
  name: '',
  teacher: '',
  catalog: [],
  kind: '',
  subject: '',
  type: [],
  price: '',
  desc: '',
  tags: ['掌握Flutter必备的Dart基础', '快速上手企业级实战项目开发', '快速上手企业级实战项目开发'],
  poster: ''
})
const price = ref(null)
const onSubmit = () => {
  step.value = 4
  console.log(form)
}

const options = reactive([
  {
    label: "Everybody's Got Something to Hide Except Me and My Monkey",
    value: 'song0',
    disabled: true,
    type: 'success'
  },
  {
    label: 'Drive My Car',
    value: 'song1',
    type: 'success'
  },
  {
    label: 'Norwegian Wood',
    value: 'song2'
  },
  {
    label: "You Won't See",
    value: 'song3',
    disabled: true
  },
  {
    label: 'Nowhere Man',
    value: 'song4'
  },
  {
    label: 'Think For Yourself',
    value: 'song5'
  },
  {
    label: 'The Word',
    value: 'song6'
  },
  {
    label: 'Michelle',
    value: 'song7',
    disabled: true
  },
  {
    label: 'What goes on',
    value: 'song8'
  },
  {
    label: 'Girl',
    value: 'song9'
  },
  {
    label: "I'm looking through you",
    value: 'song10'
  },
  {
    label: 'In My Life',
    value: 'song11',
    type: 'success'
  },
  {
    label: 'Wait',
    value: 'song12',
    type: 'success'
  }
])
const uploadUrl = ref(process.env.API + 'upload/upload-video')
const renderTag = (tag, index) => {
  return h(
    NTag,
    {
      type: index < 3 ? 'success' : 'error',
      disabled: index > 3,
      closable: true,
      onClose: () => {
        tags.value.splice(index, 1)
      }
    },
    {
      default: () => tag
    }
  )
}
const selected = ref('')

const toast = useToast();
const videoSrc = ref('');
const poster = ref('https://cdn.quasar.dev/img/parallax2.jpg');
const tree = ref(null)
const uploaded = ({xhr}) => {
  const {readyState, status, response} = xhr
  if (readyState === 4 && status === 200) {
    const resp = JSON.parse(response)
    if (resp.code === 0) {
      toast.add({severity: "error", summary: "Error", detail: resp.message, life: 3000});
    } else {
      videoSrc.value = resp.data.records
      tree.value.getNodeByKey(selected.value).url = videoSrc.value
      toast.add({severity: "success", summary: "Success", detail: resp.message, life: 3000});
    }
  }
}
const failed = (e) => {
  toast.add({severity: "error", summary: "Error", detail: "上传失败", life: 3000});
}
const addedVideo = async (e) => {
  videoSrc.value = window.URL.createObjectURL(await fileToBlob(e[0]))
}
const fileToBlob = (file) => {
  // 创建 FileReader 对象
  let reader = new FileReader();
  return new Promise(resolve => {
    // FileReader 添加 load 事件
    reader.addEventListener('load', (e) => {
      let blob;
      if (typeof e.target.result === 'object') {
        blob = new Blob([e.target.result])
      } else {
        blob = e.target.result
      }
      console.log(Object.prototype.toString.call(blob));
      resolve(blob)
    })
    // FileReader 以 ArrayBuffer 格式 读取 File 对象中数据
    reader.readAsArrayBuffer(file)
  })
}
const setCoursePoster = ({files, poster}) => {
  form.poster = poster
  console.log(form.catalog)
}
//章节处理
const handleAdd = (id) => {
  form.catalog.push({
    id: id,
    title: '',
    desc: '',
    bar: [],
    selectable: false
  })
  console.log(form.catalog)
}
const handleAddBar = (cId, catalog) => {
  form.catalog[cId - 1].bar.push({
    pId: cId,
    catalog: catalog,
    id: `${cId}-${catalog}`,
    title: '',
    desc: ''
  })
  console.log(form.catalog)
}
const handleDel = (cId, catalog) => {
  if (!!cId) {
    if (!!catalog) {
      form.catalog[cId - 1].bar = form.catalog[cId - 1].bar.filter(item => item.catalog !== catalog)
    } else {
      form.catalog = form.catalog.filter(item => item.id !== cId)
    }
    for (let i = 0; i < form.catalog.length; i++) {
      let id = i + 1
      form.catalog[i].id = id
      if (!!form.catalog[i].bar) {
        for (let j = 0; j < form.catalog[cId - 1].bar.length; j++) {
          let catalog = j + 1
          form.catalog[cId - 1].bar[j].catalog = catalog
          form.catalog[cId - 1].bar[j].pId = id
          form.catalog[cId - 1].bar[j].id = `${cId}-${catalog}`
        }
      }
    }
  }
  console.log(form.catalog, form.catalog.length)
}

</script>
