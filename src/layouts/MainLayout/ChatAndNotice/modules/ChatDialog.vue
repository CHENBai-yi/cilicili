<template>
  <q-dialog v-model="chatDialogVisible" persistent transition-hide="scale" @before-show="beforeShow"
            @before-hide="beforeHide">
    <q-card bordered style="width: 800px; max-width: 50vw;">
      <q-toolbar>
        <q-toolbar-title>
          {{ CiliFrontend.subTitle }}
          {{ $t('ChatRoom') }}
        </q-toolbar-title>
        <q-btn v-close-popup dense flat icon="close"/>
      </q-toolbar>
      <q-separator/>
      <q-card-section horizontal>
        <q-card-section class="col-4" style="padding: 0">
          <q-scroll-area ref="userScroll" style="height: 50vh; width: 100%" visible>
            <q-list>
              <q-item v-for="(item, index) in tableData" :key="index" v-ripple clickable>
                <q-item-section avatar>
                  <Cili-avatar :src="item.user.avatar"/>
                </q-item-section>
                <q-item-section>
                  <q-item-label>
                    <CiliShowName :customNameObject="item.user"/>
                  </q-item-label>
                </q-item-section>
              </q-item>
            </q-list>
          </q-scroll-area>
          <q-inner-loading :showing="loading">
            <q-spinner-gears color="primary" size="50px"/>
          </q-inner-loading>
        </q-card-section>

        <q-separator inset vertical/>

        <q-card-section style="width: 100%; padding: 0">
          <q-scroll-area ref="messageScroll" style="height: 50vh; width: 100%" visible>
            <div v-if="oldMessage.length" style="margin-right: 15px; margin-left: 5px">
              <q-chat-message v-for="(item, index) in oldMessage" :key="index" :avatar="item.avatar"
                              :name="item.name" :sent="item.sent" :stamp="item.stamp" :text="item.text"/>
            </div>
          </q-scroll-area>
          <q-toolbar>
            <q-form ref="newMessageForm" class="Cili-form" style="width: 100%">
              <q-input v-model="newMessage" :label="$t('Content')"
                       :rules="[val => val && val.length > 0 || $t('NeedInput')]" class="col-auto" dense
                       outlined rounded
                       @keyup.enter.stop="sendMessage">
                <template v-slot:append>
                  <q-btn flat icon="send" rounded @click="sendMessage"></q-btn>
                </template>
              </q-input>
            </q-form>
          </q-toolbar>
        </q-card-section>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup>
import useTableData from 'src/composables/useTableData'
import {useQuasar} from 'quasar'
import {computed, nextTick, onMounted, ref, toRefs, watch} from 'vue'
import {useI18n} from 'vue-i18n'
import {CiliDefaultAvatar, CiliDefaultUsername} from "src/config/default"

const $q = useQuasar()
const {t} = useI18n()
const url = {
  list: 'user-online/get-user-online-list',
}
const props = defineProps({
  oldMessage: {
    type: Array,
    required: false,
    default: () => [],
  }
})
const {oldMessage} = toRefs(props)
const {
  CiliFrontend,
  pagination,
  CiliShowName,
  loading,
  tableData,
  getTableData,
} = useTableData(url)

onMounted(() => {
  pagination.value.sortBy = 'username'
  getTableData()
})

const myAvatar = computed(() => {
  const cookieAvatar = $q.cookies.get('cili-avatar')
  if (cookieAvatar) {
    if (cookieAvatar.substring(0, 4) === 'http') {
      return cookieAvatar
    } else if (cookieAvatar.substring(0, 12) === 'cili-upload:') {
      return process.env.API + cookieAvatar.substring(12)
    }
    return cookieAvatar
  } else {
    return CiliDefaultAvatar
  }
})
const myName = computed(() => {
  const nickname = $q.cookies.get('cili-nickname')
  const realName = $q.cookies.get('cili-realName')
  if (nickname) {
    return nickname
  } else if (realName) {
    return realName
  } else {
    return CiliDefaultUsername
  }
})
const chatDialogVisible = ref(false)
const messageScroll = ref(null)
watch(oldMessage.value, () => {
  // Scroll to the bottom only when Dialog is open
  if (chatDialogVisible.value) {
    nextTick(() => {
      messageScroll.value.setScrollPosition('vertical', oldMessage.value.length * 75, 200)
    })
  }
})
const newMessage = ref('')
const show = () => {
  chatDialogVisible.value = true
  // this.getTableData()
  nextTick(() => {
    // Don't scroll to the bottom of userList, keep it at the top
    // this.$refs.userScroll.setScrollPosition('vertical', this.tableData.length * 55)
    messageScroll.value.setScrollPosition('vertical', oldMessage.value.length * 75)
  })
}
defineExpose({
  show
})
const emit = defineEmits(['onSendMessage', 'changeShow', 'changeMessage'])
const beforeShow = () => {
  emit('changeShow', true)
}
const beforeHide = () => {
  emit('changeShow', false)
}
const newMessageForm = ref(null)
const sendMessage = async () => {
  const success = await newMessageForm.value.validate()
  if (success) {
    emit('onSendMessage', {
      name: myName.value,
      avatar: myAvatar.value,
      text: newMessage.value,
      message_type: 'chat',
    })
    newMessage.value = ''
    newMessageForm.value.reset()
    newMessageForm.value.focus()
  } else {
    $q.notify({
      type: 'negative',
      message: t('FixForm'),
    })
  }
}
</script>
