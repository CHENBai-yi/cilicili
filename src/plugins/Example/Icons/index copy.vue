<template>
  <q-page padding>
    <q-input v-model="filter" clearable dense label="Filter" outlined style="width: 100%"/>
    <q-tabs v-model="tab" active-color="primary" align="justify" class="text-grey" dense indicator-color="primary"
            narrow-indicator>
      <q-tab label="material-icons" name="material-icons"/>
      <q-tab label="mdi-v6" name="mdi-v6"/>
      <q-tab label="ionicons-v4" name="ionicons-v4"/>
      <q-tab label="eva-icons" name="eva-icons"/>
      <q-tab label="fontawesome-v5" name="fontawesome-v5"/>
      <q-tab label="themify" name="themify"/>
      <q-tab label="line-awesome" name="line-awesome"/>
      <q-tab label="bootstrap-icons" name="bootstrap-icons"/>
    </q-tabs>
    <q-tab-panels v-model="tab" animated>
      <q-tab-panel name="material-icons">
        <div class="row" style="width: 95%">
          <q-icon-picker v-model="materialIconsData.value" v-model:model-pagination="materialIconsData.pagination"
                         :filter="filter" icon-set="material-icons" size="30px" style="height: 60vh" tooltips
                         @click="copyIcon(materialIconsData.value)"/>
        </div>
      </q-tab-panel>
      <q-tab-panel name="mdi-v6">
        <div class="row" style="width: 95%">
          <q-icon-picker v-model="mdiV6Data.value" v-model:model-pagination="mdiV6Data.pagination"
                         :filter="filter" icon-set="mdi-v6" size="30px" style="height: 60vh" tooltips
                         @click="copyIcon(mdiV6Data.value)"/>
        </div>
      </q-tab-panel>
      <q-tab-panel name="ionicons-v4">
        <div class="row" style="width: 95%">
          <q-icon-picker v-model="ioniconsV4Data.value" v-model:model-pagination="ioniconsV4Data.pagination"
                         :filter="filter" icon-set="ionicons-v4" size="30px" style="height: 60vh" tooltips
                         @click="copyIcon(ioniconsV4Data.value)"/>
        </div>
      </q-tab-panel>
      <q-tab-panel name="eva-icons">
        <div class="row" style="width: 95%">
          <q-icon-picker v-model="evaIconsData.value" v-model:model-pagination="evaIconsData.pagination"
                         :filter="filter" icon-set="eva-icons" size="30px" style="height: 60vh" tooltips
                         @click="copyIcon(evaIconsData.value)"/>
        </div>
      </q-tab-panel>
      <q-tab-panel name="fontawesome-v5">
        <div class="row" style="width: 95%">
          <q-icon-picker v-model="fontawesomeV5Data.value" v-model:model-pagination="fontawesomeV5Data.pagination"
                         :filter="filter" icon-set="fontawesome-v5" size="30px" style="height: 60vh" tooltips
                         @click="copyIcon(fontawesomeV5Data.value)"/>
        </div>
      </q-tab-panel>
      <q-tab-panel name="themify">
        <div class="row" style="width: 95%">
          <q-icon-picker v-model="themifyData.value" v-model:model-pagination="themifyData.pagination"
                         :filter="filter" icon-set="themify" size="30px" style="height: 60vh" tooltips
                         @click="copyIcon(themifyData.value)"/>
        </div>
      </q-tab-panel>
      <q-tab-panel name="line-awesome">
        <div class="row" style="width: 95%">
          <q-icon-picker v-model="lineAwesomeData.value" v-model:model-pagination="lineAwesomeData.pagination"
                         :filter="filter" icon-set="line-awesome" size="30px" style="height: 60vh" tooltips
                         @click="copyIcon(lineAwesomeData.value)"/>
        </div>
      </q-tab-panel>
      <q-tab-panel name="bootstrap-icons">
        <div class="row" style="width: 95%">
          <q-icon-picker v-model="bootstrapIconsData.value"
                         v-model:model-pagination="bootstrapIconsData.pagination" :filter="filter"
                         icon-set="bootstrap-icons"
                         size="30px" style="height: 60vh" tooltips @click="copyIcon(bootstrapIconsData.value)"/>
        </div>
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<script setup>
import {ref} from 'vue';
import {copyToClipboard, useQuasar} from 'quasar'
import {useI18n} from 'vue-i18n'

const $q = useQuasar();
const {t} = useI18n();
const tab = ref('material-icons');
const filter = ref('')

const materialIconsData = ref({
  value: '',
  pagination: {
    itemsPerPage: 100,
    page: 0
  }
});
const mdiV6Data = ref({
  value: '',
  pagination: {
    itemsPerPage: 100,
    page: 0
  }
})
const ioniconsV4Data = ref({
  value: '',
  pagination: {
    itemsPerPage: 100,
    page: 0
  }
})
const evaIconsData = ref({
  value: '',
  pagination: {
    itemsPerPage: 100,
    page: 0
  }
})
const fontawesomeV5Data = ref({
  value: '',
  pagination: {
    itemsPerPage: 100,
    page: 0
  }
});
const themifyData = ref({
  value: '',
  pagination: {
    itemsPerPage: 100,
    page: 0
  }
})
const lineAwesomeData = ref({
  value: '',
  pagination: {
    itemsPerPage: 100,
    page: 0
  }
})
const bootstrapIconsData = ref({
  value: '',
  pagination: {
    itemsPerPage: 100,
    page: 0
  }
})
const copyIcon = (item) => {
  if (item) {
    copyToClipboard(item).then(() => {
      $q.notify({
        type: 'positive',
        message: t('CopyToClipboard') + ' ' + t('Success') + ': ' + item,
      })
      materialIconsData.value.value = '';
      mdiV6Data.value.value = '';
      ioniconsV4Data.value.value = ''
      evaIconsData.value.value = '';
      fontawesomeV5Data.value.value = '';
      fontawesomeV5Data.value.value = '';
      themifyData.value.value = '';
      lineAwesomeData.value.value = '';
      bootstrapIconsData.value.value = '';
    }).catch(() => {
      $q.notify({
        type: 'negative',
        message: t('CopyToClipboard') + ' ' + t('Failed'),
      })
    })
  }

}
</script>
