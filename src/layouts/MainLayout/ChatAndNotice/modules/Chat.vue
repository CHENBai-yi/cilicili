<template>
  <q-btn dense flat icon="mdi-chat-processing-outline" round @click="showChat">
    <q-badge v-if="badgeCount" color="negative" floating>
      {{ badgeCount }}
    </q-badge>
    <q-tooltip>
      {{ $t('ChatRoom') }}
    </q-tooltip>
  </q-btn>
  <ChatDialog ref="chatDialog" :oldMessage="oldMessage" @changeShow="changeShow" @onSendMessage="onSendMessage"/>
</template>

<script setup>
import {ref, toRefs} from 'vue';
import ChatDialog from './ChatDialog.vue'

const props = defineProps({
  oldMessage: {
    type: Array,
    required: true,
    default: () => [],
  },
})
const {oldMessage} = toRefs(props)
const badgeCount = ref(0)
const chatDialog = ref(null)
const showChat = () => {
  chatDialog.value.show()
  badgeCount.value = 0
}
const emit = defineEmits(['sendMessage', 'changeChatDialogShow'])
const changeShow = (event) => {
  emit('changeChatDialogShow', event)
}
const onSendMessage = (event) => {
  emit('sendMessage', event)
}
const receiveMessage = (count) => {
  badgeCount.value += count
}
defineExpose({
  receiveMessage
})
</script>
