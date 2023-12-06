<template>
  <q-tabs v-model="messageType" active-color="primary" align="justify" class="text-grey" dense indicator-color="primary"
          narrow-indicator style="width: 100%">
    <q-tab :label="$t('NoticeSystem')" name="noticeType_system"/>
    <q-tab :label="$t('NoticeMessage')" name="noticeType_message"/>
    <q-tab :label="$t('Todo')" name="todo"/>
  </q-tabs>
  <q-tab-panels v-model="messageType" animated style="width: 100%">
    <q-tab-panel name="noticeType_system">
      <NoticeSystem v-if="messageType === 'noticeType_system'"/>
    </q-tab-panel>

    <q-tab-panel name="noticeType_message">
      <NoticeMessage v-if="messageType === 'noticeType_message'"/>
    </q-tab-panel>

    <q-tab-panel name="todo">
      <NoticeTodo v-if="messageType === 'todo'"/>
    </q-tab-panel>
  </q-tab-panels>
</template>

<script setup>
import NoticeSystem from './NoticeSystem.vue'
import NoticeMessage from './NoticeMessage.vue'
import NoticeTodo from './NoticeTodo.vue'
import {ref} from 'vue';

const messageType = ref('noticeType_system')
const changeMessageType = (type) => {
  messageType.value = type
}
defineExpose({
  changeMessageType
})
</script>
