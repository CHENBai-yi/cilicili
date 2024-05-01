<template>
  <q-dialog v-model="recordDetailVisible" position="top">
    <q-card style="width: 800px; max-width: 80vw;">
      <q-card-section class="text-h6">
        {{ formTypeName }} {{ $t('Ad') }}:
        {{ recordDetail.value.memo }}
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <q-form ref="recordDetailForm">
          <Cili-form-top :recordDetail="recordDetail"></Cili-form-top>
          <div class="row q-gutter-md">
            <q-input v-model.number="recordDetail.value.sort" :label="$t('Sort')"
                     :rules="[val => val >= 1 || $t('SortRule')]" class="col" hint=""
                     outlined type="number"/>
            <q-input v-model="recordDetail.value.adver_path" :label="$t('Link') + $t('Address')"
                     :rules="[val => val && val.length > 0 || $t('NeedInput')]" class="col"
                     hint=""
                     outlined/>
            <q-input v-model="recordDetail.value.adver_page" :label="$t('Jump') + $t('Page')"
                     :rules="[val => val && val.length > 0 || $t('NeedInput')]" class="col"
                     hint=""
                     outlined/>
            <q-field :label="$t('Status')" class="col" dense hint="" outlined stack-label>
              <template v-slot:control>
                <q-option-group v-model="recordDetail.value.status" :options="dictOptions.onOff"
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
          <div class="row q-gutter-md">
            <q-select v-model="recordDetail.value.content_type" :label="$t('Content') + $t('Type')"
                      :options="dictOptions.contentType" :rules="[val => val && val.length > 0 || $t('NeedInput')]"
                      class="col" emit-value hint=""
                      map-options
                      outlined/>
            <q-input v-model="recordDetail.value.memo" :label="$t('Memo')" class="col" hint="" outlined/>
          </div>
          <CiliContentInput :record-detail="recordDetail.value"/>
          <div class="row q-gutter-md q-pt-sm">
            <div class="text-h6 q-pt-sm">有效时间:</div>
            <n-date-picker v-model:value="recordDetail.value.time" clearable panel type="datetimerange"/>
          </div>
        </q-form>
      </q-card-section>
      <q-separator/>
      <q-card-actions align="right">
        <q-btn :label="$t('Save')" color="primary" @click="handleAddOrEidt"/>
        <q-btn v-close-popup :label="$t('Cancel')" color="negative"/>
      </q-card-actions>
      <q-inner-loading :showing="loading">
        <q-spinner-gears color="primary" size="50px"/>
      </q-inner-loading>
    </q-card>
  </q-dialog>
</template>


<script setup>
import useRecordDetail from 'src/composables/useRecordDetail'
import CiliContentInput from 'src/components/CiliContentInput/index.vue'


const url = {
  add: 'advertising',
  edit: 'advertising/edit-advertising',
  queryById: 'advertising/query-advertising-by-id',
}
const emit = defineEmits(['handleFinish'])
const {
  dictOptions,
  formType,
  formTypeName,
  recordDetail,
  recordDetailVisible,
  loading,
  show,
  preShowCarousel,
  recordDetailForm,
  handleAddOrEidt
} = useRecordDetail(url, emit)

defineExpose({
  show,
  preShowCarousel,
  formType,
  recordDetail
})
</script>
<style lang="scss" scoped>
.carousel-img {
  width: 100%;
  max-height: 500px;
  min-height: 240px;
  height: 500px;
  object-fit: cover;
}

.carousel-img2 {
  width: 100%;
  max-height: 500px;
  min-height: 240px;
  height: 240px;
  object-fit: cover;

}
</style>
