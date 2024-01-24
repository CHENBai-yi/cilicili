<template>
  <div class="row">
    <q-input v-model="recordDetail.value.id" class="col" dense hint="" label="ID" readonly stack-label/>
    <q-field :label="$t('CreatedAt')" class="col" dense hint="" readonly stack-label>
      <template v-slot:control>
        {{ showDateTime(recordDetail.value.created_at) }}
      </template>
    </q-field>
    <q-field :label="$t('CreatedBy')" class="col" dense hint="" readonly stack-label>
      <template v-slot:control>
        <CiliShowName v-if="recordDetail.value.created_by_user"
                      :customNameObject="recordDetail.value.created_by_user"/>
      </template>
    </q-field>
    <q-field :label="$t('UpdatedAt')" class="col" dense hint="" readonly stack-label>
      <template v-slot:control>
        {{ showDateTime(recordDetail.value.updated_at) }}
      </template>
    </q-field>
    <q-field :label="$t('UpdatedBy')" class="col" dense hint="" readonly stack-label>
      <template v-slot:control>
        <CiliShowName v-if="recordDetail.value.updated_by_user"
                      :customNameObject="recordDetail.value.updated_by_user"/>
      </template>
    </q-field>
  </div>
</template>

<script setup>
import {toRefs} from 'vue';
import CiliShowName from 'src/components/CiliShowName/index.vue'
import useCommon from 'src/composables/useCommon'

const {showDateTime} = useCommon()
const props = defineProps({
  recordDetail: {
    type: Object,
    required: true,
  }
})
const {recordDetail} = toRefs(props);
</script>
