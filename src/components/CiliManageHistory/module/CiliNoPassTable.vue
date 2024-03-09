<template>
  <q-table
    v-model:pagination="pagination"
    :columns="columns"
    :loading="loading"
    :rows="tableData"
    :rows-per-page-options="pageOptions"
    flat
    row-key="name"
    @request="onRequest"
  >
    <template v-slot:body="props">
      <q-tr :props="props">
        <q-td key="name" :props="props">
          {{ props.row.name }}
        </q-td>
        <q-td key="img" :props="props">
          <n-image
            :src="processApi+props.row.img"
            fallback-src="https://07akioni.oss-cn-beijing.aliyuncs.com/07akioni.jpeg"
            height="72"
            width="125"
          />
        </q-td>
        <q-td key="author" :props="props">
          <q-badge color="purple">
            {{ props.row.author }}
          </q-badge>
        </q-td>
        <q-td key="subject" :props="props">
          <q-badge color="purple">
            {{ props.row.subject }}
          </q-badge>
        </q-td>
        <q-td key="price" :props="props">
          <q-badge color="purple">
            {{ props.row.price }}
          </q-badge>
        </q-td>
        <q-td key="carbs" :props="props">
          <q-badge color="orange">
            {{ props.row.carbs }}
          </q-badge>
        </q-td>
        <q-td key="protein" :props="props">
          <q-badge color="primary">
            {{ props.row.protein }}
          </q-badge>
        </q-td>
        <q-td key="date" :props="props">
          <q-badge color="teal">
            {{ props.row.date }}
          </q-badge>
        </q-td>
        <q-td key="calcium" :props="props">
          <q-badge color="accent">
            {{ props.row.calcium }}
          </q-badge>
        </q-td>
        <q-td key="status" :props="props">
          <CiliDictShow :dict-code="props.row.status"/>
        </q-td>
        <q-td key="operation" :props="props">
          <div>
            <q-btn color="primary" flat label="编辑章节" @click="editCourse(props.row.id)"/>
            <q-btn color="primary" flat label="编辑小节" @click="editCourse2(props.row.id)"/>
          </div>
          <q-btn color="black" flat label="重新提交审核" @click="reAudit(props.row.id)"/>
          <q-btn color="red" flat label="查看原因" @click="showResult(props.row.reason)"/>
        </q-td>
      </q-tr>
    </template>
  </q-table>
  <q-dialog v-model="dialog" :position="position">
    <q-card :class="darkTheme">
      <q-card-section>
        <div class="text-h6">编辑：</div>
      </q-card-section>
      <q-separator inset/>
      <q-card-section>
        <div class="column q-gutter-y-md">
          <div class="row q-gutter-x-md flex no-wrap">
            <q-input v-model="editFrom.name" label="章节标题" outlined/>
            <q-input v-model="editFrom.author" label="作者" outlined/>
            <q-input v-model="editFrom.subject" label="分类" outlined/>
            <q-input v-model="editFrom.price" label="价格" outlined prefix="￥" type="number"/>
          </div>
          <div class="col">
            <q-input v-model="editFrom.desc" label="详情" outlined type="textarea"/>
          </div>
          <div class="row items-center">
            <div class="security-nav-name">课程封面：</div>
            <q-avatar size="150px" square>
              <img :src="processApi+editFrom.img">
            </q-avatar>

            <div class="col no-wrap flex items-center q-gutter-x-sm q-ml-md">
              <n-button
                v-if="fileListLength"
                secondary
                strong
                type="success"
                @click="handleClick"
              >
                保存
              </n-button>
              <n-upload
                ref="upload"
                :action="uploadUrl"
                :default-upload="false"
                :headers="{Authorization:userStore.GetToken()}"
                :max=1
                accept="image/*"
                class="flex items-center"
                @change="handleChange"
                @finish="handleFinish"
                @before-upload="beforeUpload"
              >
                <n-button strong text type="info">更换</n-button>
              </n-upload>
            </div>


          </div>
        </div>
        <div class="q-gutter-x-md q-mt-md flex justify-end">
          <q-btn :label="t('Save')" color="primary" @click="saveCourse"/>
          <q-btn :label="t('Cancel')" color="red" @click="dialog=false"/>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>
  <q-dialog v-model="dialog2" :position="position">
    <q-card :class="darkTheme">
      <q-card-section>
        <div class="text-h6">编辑：</div>
      </q-card-section>
      <q-separator inset/>
      <q-card-section>
        <div class="column q-gutter-y-md">
          <div class="row q-gutter-x-md flex no-wrap">
            <q-input v-model="editFrom.name" label="章节标题" outlined readonly/>
            <q-input v-model="editFrom.author" label="作者" outlined readonly/>
            <q-input v-model="editFrom.subject" label="分类" outlined readonly/>
            <q-input v-model="editFrom.price" label="价格" outlined prefix="￥" readonly type="number"/>
          </div>
          <div class="col">
            <q-input v-model="editFrom.desc" label="详情" outlined readonly type="textarea"/>
          </div>
        </div>
        <div class="q-gutter-x-md q-mt-md ">
          <q-tree
            ref="tree"
            v-model:selected="selected"
            :nodes="editFrom2.catalog"
            accordion
            children-key="bar"
            default-expand-all
            dense
            no-nodes-label="未添加课程目录！！！"
            node-key="id"
            @update:selected="selectedTree"
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
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>
  <q-dialog v-model="dialog3" position="bottom">
    <q-card :class="darkTheme">
      <q-card-section>
        <div class="text-h6">编辑小节：</div>
      </q-card-section>
      <q-separator inset/>
      <q-card-section>
        <div class="column q-gutter-y-md">
          <div class="col">

            <video v-if="!videoSrc" :poster="poster" class="q-card full-width no-margin no-padding " controls
                   style="height:200px;"/>
            <video v-else :src="videoSrc" class="q-card  no-margin  full-width no-padding " controls
                   style="height:200px;"/>
          </div>
          <div class="col flex justify-center">
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
        </div>
        <div class="q-gutter-x-md q-mt-md flex justify-end">
          <q-btn :label="t('Save')" color="primary"/>
          <q-btn :label="t('Cancel')" color="red" @click="dialog3=false"/>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

</template>

<script setup>
import {defineEmits, onMounted, reactive, ref} from 'vue'
import useTheme from "src/composables/useTheme"
import {postAction} from 'src/api/manage'
import {useToast} from "primevue/usetoast";
import {toChineseNumber} from 'src/utils/ToChineseNumber'
import CiliDictShow from "src/components/CiliDictShow/index.vue";
import UseCiliTableDate from "../../../composables/useCiliTableDate";
import {ElMessageBox} from "element-plus";
import {useUserStore} from 'src/stores/user'
import {useI18n} from 'vue-i18n'

const userStore = useUserStore()
const upload = ref(null)
const fileListLength = ref(0)
const handleClick = async () => {
  upload.value?.submit();
}
const handleChange = (options) => {
  fileListLength.value = options.fileList.length;
}
const handleFinish = ({
                        file,
                        event
                      }) => {
  if (event.target) {
    const res = JSON.parse(event.target.response)
    if (res.code === 1) {
      editFrom.value.img = res.data.records
      fileListLength.value = 0
    } else {
      window.$message.error(res.message, {render: window.$render});
    }
  }
  return file;
};
const beforeUpload = async (data) => {
  console.log(data.file.file?.type)
  if (!/image\/*/.test(data.file.file?.type)) {
    window.$message.error("只能上传图片文件，请重新上传", {render: window.$render});
    return false;
  }
  return true;
}
const emits = defineEmits(['refresh'])
const {t} = useI18n()
const processApi = ref(process.env.API)
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
const uploadUrl = ref(processApi.value + 'upload/upload-video')
const toast = useToast();
const {darkTheme} = useTheme()
const dialog = ref(false)
const dialog2 = ref(false)
const dialog3 = ref(false)
const position = ref('top')
const selected = ref('')
const selectedTree = (e) => {
  dialog3.value = true
}
const createThumbnailUrl = (file) => {
  if (!file) return undefined
  message.info(
    'createThumbnailUrl 产生了图片的 URL，你传什么都会变成 07akioni'
  )
  toast.add({severity: "error", summary: "Error", detail: '只能上传png格式的图片文件，请重新上传', life: 3000});
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(
        'https://07akioni.oss-cn-beijing.aliyuncs.com/07akioni.jpeg'
      )
    }, 1000)
  })
}
const editFrom2 = reactive({
  id: 1,
  name: 'JavaSe零基础之基础语法',
  url: '',
  catalog: [
    {
      "id": 1,
      "title": "aaa",
      "desc": "aaa",
      "bar": [
        {
          "pId": 1,
          "catalog": 1,
          "id": "1-1",
          "title": "aaaa",
          "desc": ""
        }
      ],
      "selectable": false
    },
    {
      "id": 2,
      "title": "aa",
      "desc": "aaa",
      "bar": [
        {
          "pId": 2,
          "catalog": 1,
          "id": "2-1",
          "title": "aaa",
          "desc": ""
        }
      ],
      "selectable": false
    }
  ]
})
const editFrom = ref({
  id: 1,
  name: 'JavaSe零基础之基础语法',
  img: 'JavaSe零基础之基础语法.jpg',
  desc: "本门课程作为2024年Flutter入门首选课，课程中结合了最新版的Flutter、Dart、主流插件技术进行企业级项目开发，并融入了大量Flutter版本更新问题的解决方案，以及与H5混合开发和通信、多屏适配等高频技能，课程中还将讲师近几年在一线开发中积累的大量开发、避坑等经验传授给大家，让各位小伙伴在实际开发中能更好的运用Flutter技术。",
  author: '尚硅谷',
  price: '39.9',
  subject: 'java',
  protein: 4.0,
  date: '2024年12月24 12:30:05',
  status: '审核中'
})
const editCourse = (id) => {
  editFrom.value = Object.assign({}, tableData.value.filter(item => item.id === id)[0])
  dialog.value = true
}
const editCourse2 = (id) => {
  console.log(id)
  dialog2.value = true
}
const columns = reactive([
  {
    name: 'name',
    required: true,
    label: '标题',
    align: 'center',
    field: row => row.name,
    format: val => `${val}`,

  },
  {name: 'img', align: 'center', label: '封面', field: 'img'},
  {name: 'author', align: 'center', label: '作者', field: 'author'},
  {name: 'subject', align: 'center', label: '类别', field: 'subject'},
  {name: 'price', align: 'center', label: '价格', field: 'price'},
  {name: 'carbs', align: 'center', label: '总节数', field: 'carbs'},
  {name: 'protein', align: 'center', label: '总时长', field: 'protein'},
  {name: 'date', align: 'center', label: '上传日期', field: 'date'},
  {
    name: 'status',
    label: '状态',
    align: 'center',
    field: 'calcium',
    sortable: true,
    sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
  },
  {name: 'operation', align: 'center', label: '操作', field: 'operation'}
])

const url = {
  list: 'courses/get-course-info',
  update: 'courses/update-courses'
}
const {
  getTableData,
  onRequest,
  loading,
  tableData,
  pageOptions,
  queryParams,
  pagination
} = UseCiliTableDate(url)
onMounted(() => {
  queryParams.value.status = 'onOffPass_off'
  getTableData()
})
const showResult = (e) => {
  ElMessageBox.alert(
    `<font color="red"><strong>${e}</strong></font>`,
    '未通过原因',
    {
      dangerouslyUseHTMLString: true,
      showConfirmButton: false,
      showCancelButton: false,
      type: 'error',
      center: true,
    }
  )
}
const reAudit = (id) => {
  ElMessageBox.confirm(' <div><p> 1.内容合法性：上传的视频必须符合所有适用的法律法规，包括但不限于不涉及侵犯版权、诽谤、淫秽、恶意攻击、种族歧视等违法违规内容。</p><p> 2.版权规范：确保上传的视频不侵犯任何第三方的知识产权，包括但不限于音乐、图像、文字等。请仅上传您有权使用的内容。</p><p>3.广告宣传：禁止包含未经许可的广告或推广内容。上传者需确保视频内容不违反广告法规定，如虚假宣传等。</p><p>4.不当行为：视频内容不得涉及违法行为、暴力、虐待、恐怖主义等不当行为。我们鼓励积极向上、健康向善的内容。</p><p>5.隐私尊重：严禁上传侵犯他人隐私的视频。确保您具有合法获取并分享涉及他人的内容的权利。</p><p>6.技术规范：上传的视频必须符合平台的技术规范，包括但不限于分辨率、格式等要求。</p><p> 请上传者遵守以上审核细则，违规者将面临视频被删除或账号被禁的处理。</p></div>',
    t('审核细则'),
    {
      confirmButtonText: t('我已阅读并知晓上述内容'),
      cancelButtonText: t('Cancel'),
      dangerouslyUseHTMLString: true,
      showCancelButton: false,
      type: 'warning',
      center: true
    }
  )
    .then(async () => {
      queryParams.value.status = 'onOffPass_on'
      queryParams.value.id = id
      const res = await postAction(url.update, queryParams.value)
      if (res && res.code === 1) {
        emits('refresh')
        queryParams.value.status = 'onOffPass_off'
        getTableData()
        window.$message.success(res.message, {render: window.$render})
      } else {
        window.$message.error(res.message, {render: window.$render})
      }
    })
}
const saveCourse = async () => {
  const res = await postAction(url.update, editFrom.value)
  if (res && res.code === 1) {
    getTableData()
    dialog.value = false
    window.$message.success(res.message, {render: window.$render})
  } else {
    window.$message.error(res.message, {render: window.$render})
  }
}
</script>

<style scoped>
.q-table--no-wrap td {
  white-space: pre-wrap;
}
</style>
