<template>
  <q-page padding>
    <q-card flat>
      <q-card-section class="row q-gutter-x-md items-center">
        <q-input v-model="queryParams.config_item" :label="$t('Config') + $t('Item')" dense outlined
                 style="width: 20%"/>
        <q-input v-model="queryParams.memo" :label="$t('Memo')" dense outlined style="width: 20%"/>
        <q-btn :label="$t('Search')" color="primary" @click="handleSearch"/>
        <q-btn :label="$t('Reset')" color="primary" @click="resetSearch"/>
      </q-card-section>
      <q-card-section>
        <q-table v-model:pagination="pagination" :columns="columns" :loading="loading" :rows="tableData"
                 :rows-per-page-options="pageOptions"
                 row-key="id" separator="cell" @request="onRequest">
          <template v-slot:top="props">
            <q-btn v-has="'config-frontend:add'" :label="$t('Add') + ' ' + $t('Config')" color="primary"
                   @click="showAddForm()"/>
            <q-space/>
            <q-btn :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'" class="q-ml-md" dense flat
                   round @click="props.toggleFullscreen"/>
          </template>
          <template v-slot:body-cell-item_default="props">
            <q-td :props="props">
              <template v-if="props.row.config_item === 'logo'">
                <gqa-avatar :src="props.row.item_default"/>
              </template>
              <template v-else-if="props.row.config_item === 'favicon'">
                <gqa-avatar src="favicon.ico"/>
              </template>
              <template v-else-if="props.row.config_item === 'bannerImage'">
                Default
              </template>
              <template v-else-if="props.row.config_item === 'loginLayoutStyle'">
                <GqaDictShow :dictCode="props.row.item_default"/>
              </template>
              <template v-else-if="props.row.config_item === 'pluginLoginLayout'">
                系统默认
              </template>
              <template v-else-if="props.row.config_item === 'portalPage'">
                <GqaDictShow :dictCode="props.row.item_default"/>
              </template>
              <template v-else-if="props.row.config_item === 'showGit'">
                <GqaDictShow :dictCode="props.row.item_default"/>
              </template>
              <template v-else-if="props.row.config_item === 'starColor'">
                <div class="row justify-center items-center q-gutter-x-xs">
                  {{ props.row.item_default }}
                  <div :style="{ background: props.row.item_default }"
                       style="width: 10px; height: 10px; border-radius: 100%;"></div>
                </div>
              </template>
              <template v-else>
                {{ props.row.item_default }}
              </template>
            </q-td>
          </template>
          <template v-slot:body-cell-item_custom="props">
            <q-td :props="props">
              <template v-if="props.row.config_item === 'bannerImage'">
                <q-file v-model="bannerImage" :accept="gqaBackend.bannerImageExt"
                        :max-file-size="gqaBackend.bannerImageMaxSize * 1024 * 1024" clearable dense max-files="1"
                        outlined
                        @rejected="rejected">
                  <template v-if="props.row.item_custom !== ''" v-slot:prepend>
                    <gqa-avatar :src="props.row.item_custom"/>
                  </template>
                  <template v-if="bannerImage" v-slot:after>
                    <q-btn color="primary" dense flat icon="cloud_upload"
                           @click="handleUploadBannerImage"/>
                  </template>
                </q-file>
              </template>
              <template v-else-if="props.row.config_item === 'logo'">
                <q-file v-model="logoFile" :accept="gqaBackend.logoExt"
                        :max-file-size="gqaBackend.logoMaxSize * 1024 * 1024" clearable dense max-files="1"
                        outlined @rejected="rejected">
                  <template v-slot:prepend>
                    <gqa-avatar :src="props.row.item_custom"/>
                  </template>
                  <template v-if="logoFile" v-slot:after>
                    <q-btn color="primary" dense flat icon="cloud_upload" @click="handleUploadLogo"/>
                  </template>
                </q-file>
              </template>
              <template v-else-if="props.row.config_item === 'favicon'">
                <q-file v-model="faviconFile" :accept="gqaBackend.faviconExt"
                        :max-file-size="gqaBackend.faviconMaxSize * 1024 * 1024" clearable dense max-files="1"
                        outlined
                        @rejected="rejected">
                  <template v-slot:prepend>
                    <gqa-avatar :src="props.row.item_custom"/>
                  </template>
                  <template v-if="faviconFile" v-slot:after>
                    <q-btn color="primary" dense flat icon="cloud_upload"
                           @click="handleUploadFavicon"/>
                  </template>
                </q-file>
              </template>
              <template v-else-if="props.row.config_item === 'loginLayoutStyle'">
                <q-option-group v-model="props.row.item_custom" :options="dictOptions.loginLayoutStyle"
                                color="primary" inline>
                  <template v-slot:label="opt">
                    <div class="row items-center">
                      <span>{{ $t(opt.label) }}</span>
                    </div>
                  </template>
                </q-option-group>
              </template>
              <template v-else-if="props.row.config_item === 'pluginLoginLayout'">
                <q-select v-model="props.row.item_custom"
                          :label="$t('Choose') + $t('Plugin') + '(' + pluginList.length + ')'" :options="pluginList"
                          dense
                          emit-value map-options
                          option-label="plugin_name" option-value="plugin_code" outlined
                          @update:model-value="handelChangePlugin"/>
              </template>
              <template v-else-if="props.row.config_item === 'showGit'">
                <q-option-group v-model="props.row.item_custom" :options="dictOptions.yesNo" color="primary"
                                inline>
                  <template v-slot:label="opt">
                    <div class="row items-center">
                      <span>{{ $t(opt.label) }}</span>
                    </div>
                  </template>
                </q-option-group>
              </template>
              <template v-else-if="props.row.config_item === 'starColor'">
                <q-input v-model="props.row.item_custom" dense outlined>
                  <template v-if="props.row.item_custom" v-slot:prepend>
                    <div :style="{ background: props.row.item_custom }"
                         style="width: 10px; height: 10px; border-radius: 100%;"></div>
                  </template>
                  <template v-slot:append>
                    <q-icon class="cursor-pointer" name="colorize">
                      <q-popup-proxy cover transition-hide="scale" transition-show="scale">
                        <q-color v-model="props.row.item_custom"/>
                      </q-popup-proxy>
                    </q-icon>
                  </template>
                </q-input>
              </template>
              <template v-else>
                <q-input v-model="props.row.item_custom" clearable dense outlined/>
              </template>
            </q-td>
          </template>
          <template v-slot:body-cell-status="props">
            <q-td :props="props">
              <GqaDictShow :dictCode="props.row.status"/>
            </q-td>
          </template>
          <template v-slot:body-cell-stable="props">
            <q-td :props="props">
              <GqaDictShow :dictCode="props.row.stable"/>
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props" class="q-gutter-x-md">
              <q-btn v-has="'config-frontend:save'" :label="$t('Save')" color="primary" dense flat
                     @click="handleSave(props.row)">
              </q-btn>
              <q-btn v-has="'config-frontend:reset'" :label="$t('Reset')" color="warning" dense flat
                     @click="handleReset(props.row)">
              </q-btn>
              <q-btn v-if="props.row.stable !== 'yesNo_yes'" v-has="'config-frontend:delete'" :label="$t('Delete')"
                     color="negative" dense
                     flat @click="handleDelete(props.row)">
              </q-btn>
            </q-td>
          </template>
        </q-table>
      </q-card-section>
    </q-card>
    <recordDetail ref="recordDetailDialog" @handleFinish="handleFinish"/>
  </q-page>
</template>

<script setup>
import {computed, onMounted, ref} from 'vue'
import useTableData from 'src/composables/useTableData'
import {postAction} from 'src/api/manage'
import {useStorageStore} from 'src/stores/storage'
import RecordDetail from './modules/recordDetail.vue'
import {CheckComponent} from 'src/utils/check'

const storageStore = useStorageStore()
const url = {
  list: 'config-frontend/get-config-frontend-list',
  edit: 'config-frontend/edit-config-frontend',
  delete: 'config-frontend/delete-config-frontend-by-id',
  uploadBannerImage: 'upload/upload-banner-image',
  uploadLogo: 'upload/upload-logo',
  uploadFavicon: 'upload/upload-favicon',
}
const columns = computed(() => {
  return [
    {name: 'sort', align: 'center', label: t('Sort'), field: 'sort'},
    {name: 'config_item', align: 'center', label: t('Config') + t('Item'), field: 'config_item'},
    {name: 'memo', align: 'center', label: t('Memo'), field: 'memo'},
    {name: 'item_default', align: 'center', label: t('Default'), field: 'item_default'},
    {name: 'item_custom', align: 'center', label: t('Custom'), field: 'item_custom'},
    {name: 'status', align: 'center', label: t('Status'), field: 'status'},
    {name: 'stable', align: 'center', label: t('Stable'), field: 'stable'},
    {name: 'actions', align: 'center', label: t('Actions'), field: 'actions'},
  ]
})
const {
  $q,
  t,
  gqaBackend,
  dictOptions,
  pagination,
  queryParams,
  pageOptions,
  GqaDictShow,
  loading,
  tableData,
  recordDetailDialog,
  showAddForm,
  onRequest,
  getTableData,
  handleSearch,
  resetSearch,
  handleFinish,
  handleDelete,
} = useTableData(url)

onMounted(() => {
  getTableData()
})

const bannerImage = ref(null)
const logoFile = ref(null)
const faviconFile = ref(null)

const pluginList = computed(() => {
  if (Array.isArray($q.localStorage.getItem('gqa-pluginList'))) {
    return $q.localStorage.getItem('gqa-pluginList')
  }
  return []
})

const pluginsFile = import.meta.glob('../../../plugins/**/LoginLayout/index.vue')
const handelChangePlugin = (cp) => {
  const cc = CheckComponent(cp, pluginsFile, 4)
  if (cc[2]) {
    $q.notify({
      type: 'negative',
      message: t('PluginNotSupportLogin'),
    })
    const pll = tableData.value.filter((item) => {
      return item.config_item === 'pluginLoginLayout'
    })
    pll[0].item_custom = ''
  }
}

const handleReset = (row) => {
  row.item_custom = ''
}
const handleSave = async (row) => {
  const res = await postAction(url.edit, row)
  if (res.code === 1) {
    $q.notify({
      type: 'positive',
      message: res.message,
    })
    storageStore.SetGqaFrontend()
  }
}
const handleUploadBannerImage = () => {
  if (!bannerImage.value) {
    $q.notify({
      type: 'negative',
      message: t('Please') + t('Select') + t('File'),
    })
    return
  }
  let form = new FormData()
  form.append('file', bannerImage.value)
  postAction(url.uploadBannerImage, form).then((res) => {
    if (res.code === 1) {
      const bi = tableData.value.filter((item) => {
        return item.config_item === 'bannerImage'
      })
      bi[0].item_custom = res.data.records
      bannerImage.value = null
      $q.notify({
        type: 'positive',
        message: t('Upload') + t('Success'),
      })
    }
  })
}
const handleUploadLogo = () => {
  if (!logoFile.value) {
    $q.notify({
      type: 'negative',
      message: t('Please') + t('Select') + t('File'),
    })
    return
  }
  let form = new FormData()
  form.append('file', logoFile.value)
  postAction(url.uploadLogo, form).then((res) => {
    if (res.code === 1) {
      const logo = tableData.value.filter((item) => {
        return item.config_item === 'logo'
      })
      logo[0].item_custom = res.data.records
      logoFile.value = null
      $q.notify({
        type: 'positive',
        message: t('Upload') + t('Success'),
      })
    }
  })
}
const handleUploadFavicon = () => {
  if (!faviconFile.value) {
    $q.notify({
      type: 'negative',
      message: t('Please') + t('Select') + t('File'),
    })
    return
  }
  let form = new FormData()
  form.append('file', faviconFile.value)
  postAction(url.uploadFavicon, form).then((res) => {
    if (res.code === 1) {
      const favicon = tableData.value.filter((item) => {
        return item.config_item === 'favicon'
      })
      favicon[0].item_custom = res.data.records
      faviconFile.value = null
      $q.notify({
        type: 'positive',
        message: t('Upload') + t('Success'),
      })
    }
  })
}
const rejected = (rejectedEntries) => {
  $q.notify({
    type: 'negative',
    message: t('SizeOrExtError'),
  })
}
</script>
