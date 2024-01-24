<template>
  <q-dialog v-model="recordDetailVisible" position="top">
    <q-card style="width: 1200px; max-width: 80vw;">
      <q-card-section class="text-h6">
        {{ formTypeName }} {{ $t('Menu') }}:
        {{ recordDetail.value.title ? $t(recordDetail.value.title) : '' }}
      </q-card-section>
      <Cili-form-top :recordDetail="recordDetail" style="margin: 0 16px;"></Cili-form-top>
      <div class="row">
        <q-card-section class="q-gutter-md col-8">
          <q-form ref="recordDetailForm">
            <div class="row q-gutter-md">
              <q-input v-model="recordDetail.value.icon" :label="$t('Icon')" class="col" clearable hint=""
                       outlined>
                <template v-slot:prepend>
                  <q-icon :name="recordDetail.value.icon" class="cursor-pointer">
                    <q-popup-proxy v-model="iconData.showIconPicker">
                      <q-input v-model="iconData.filter" class="q-ma-md" clearable dense label="Filter"
                               outlined/>
                      <q-icon-picker v-model="recordDetail.value.icon" v-model:model-pagination="iconData.pagination"
                                     :filter="iconData.filter" :icons="icons"
                                     style="height: 300px; width: 300px; background-color: white;" tooltips/>
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
              <q-input v-model.number="recordDetail.value.sort" :label="$t('Sort')"
                       :rules="[val => val >= 1 || $t('SortRule')]" class="col" hint=""
                       outlined type="number"/>
            </div>
            <div class="row q-gutter-md">
              <q-input v-model="recordDetail.value.title" :label="$t('Menu') + $t('Name')"
                       :rules="[val => val && val.length > 0 || $t('NeetInput')]" class="col"
                       hint=""
                       outlined/>
              <q-input v-model="recordDetail.value.name" :disable="formType === 'edit'" :label="$t('Menu') + $t('Code')"
                       :rules="[val => val && val.length > 0 || $t('NeetInput')]"
                       class="col" hint=""
                       outlined/>
              <q-input v-model="recordDetail.value.parent_code" :label="$t('Parent') + $t('Code')" class="col" hint=""
                       outlined readonly/>
            </div>
            <div class="row q-gutter-md">
              <q-input v-model="recordDetail.value.path" class="col" hint="" label="URL" outlined/>
              <q-input v-model="recordDetail.value.redirect" :label="t('Redirect')" class="col" hint=""
                       outlined/>
              <q-input v-model="recordDetail.value.component" :label="$t('Component')" class="col" hint=""
                       outlined/>
            </div>

            <div class="row q-gutter-md">
              <q-field :label="$t('IsLink')" class="col" hint="" outlined stack-label>
                <template v-slot:control>
                  <q-option-group v-model="recordDetail.value.is_link" :options="dictOptions.yesNo"
                                  color="primary" inline>
                    <template v-slot:label="opt">
                      <div class="row items-center">
                        <span>{{ $t(opt.label) }}</span>
                      </div>
                    </template>
                  </q-option-group>
                </template>
              </q-field>
              <q-field :label="$t('KeepAlive')" class="col" hint="" outlined stack-label>
                <template v-slot:control>
                  <q-option-group v-model="recordDetail.value.keep_alive" :options="dictOptions.yesNo"
                                  color="primary" inline>
                    <template v-slot:label="opt">
                      <div class="row items-center">
                        <span>{{ $t(opt.label) }}</span>
                      </div>
                    </template>
                  </q-option-group>
                </template>
              </q-field>
              <q-field :label="$t('Hidden')" class="col" hint="" outlined stack-label>
                <template v-slot:control>
                  <q-option-group v-model="recordDetail.value.hidden" :options="dictOptions.yesNo"
                                  color="primary" inline>
                    <template v-slot:label="opt">
                      <div class="row items-center">
                        <span>{{ $t(opt.label) }}</span>
                      </div>
                    </template>
                  </q-option-group>
                </template>
              </q-field>
            </div>
            <q-input v-model="recordDetail.value.memo" :label="$t('Memo')" hint="" outlined type="textarea"/>
          </q-form>
        </q-card-section>
        <q-card-section class="q-gutter-md col">
          <q-field :disable="!recordDetail.value.parent_code" :label="$t('Parent') + $t('Menu')" dense hint="" outlined
                   stack-label>
            <template v-slot:control>
              <q-tree v-model:selected="recordDetail.value.parent_code" :nodes="menuTree" default-expand-all
                      node-key="name" selected-color="primary" style="width: 100%;"
                      @update:selected="handleSelectParentMenu">
                <template v-slot:default-header="prop">
                  <div class="row items-center">
                    <div>{{ t(prop.node.title) }}</div>
                  </div>
                </template>
              </q-tree>
            </template>
          </q-field>
        </q-card-section>
      </div>
      <q-separator/>
      <q-card-actions align="right">
        <q-btn :label="$t('Save')" color="primary" @click="handleAddOrEidt"/>
        <q-btn v-close-popup :label="$t('Cancel')" color="negative"/>
      </q-card-actions>
      <q-inner-loading :showing="loading">
        "primary" />
      </q-inner-loading>
    </q-card>
  </q-dialog>
</template>


<script setup>
import bootstrapIcons from '@quasar/quasar-ui-qiconpicker/src/components/icon-set/bootstrap-icons'
import materialIcons from '@quasar/quasar-ui-qiconpicker/src/components/icon-set/material-icons'
import ioniconsV4 from '@quasar/quasar-ui-qiconpicker/src/components/icon-set/ionicons-v4'
import themify from '@quasar/quasar-ui-qiconpicker/src/components/icon-set/themify'
import evaIcons from '@quasar/quasar-ui-qiconpicker/src/components/icon-set/eva-icons'
import fontawesomeV5 from '@quasar/quasar-ui-qiconpicker/src/components/icon-set/fontawesome-v5'
import useRecordDetail from 'src/composables/useRecordDetail'
import {ref, toRefs, watch} from 'vue'

const icons = ref(materialIcons.icons.concat(bootstrapIcons.icons).concat(ioniconsV4.icons).concat(themify.icons).concat(evaIcons.icons).concat(fontawesomeV5.icons))
const emit = defineEmits(['handleFinish'])
const url = {
  list: 'menu/get-menu-list',
  add: 'menu/add-menu',
  edit: 'menu/edit-menu',
  queryById: 'menu/query-menu-by-id',
}

const props = defineProps({
  menuTree: {
    type: Array,
    required: false,
    default: () => [],
  },
})
const {menuTree} = toRefs(props)

const {
  $q,
  t,
  dictOptions,
  formType,
  formTypeName,
  recordDetail,
  recordDetailVisible,
  loading,
  show,
  recordDetailForm,
  handleAddOrEidt
} = useRecordDetail(url, emit)

defineExpose({
  show,
  formType,
  recordDetail
})
const iconData = ref({
  filter: '',
  showIconPicker: false,
  pagination: {
    itemsPerPage: 35,
    page: 0
  }
})

watch(recordDetail, () => {
  iconData.value.showIconPicker = false
})

const handleSelectParentMenu = (target) => {
  if (target === recordDetail.value.name) {
    recordDetail.value.parent_code = ''
    $q.notify({type: 'warning', message: t('ParentNotThis')})
  }
}
</script>
