<template>
  <q-btn dense flat icon="ion-ios-notifications-outline" round>
    <q-tooltip>
      {{ $t('Notice') }}
    </q-tooltip>
    <q-badge v-if="systemNum + messageNum + todoNum" color="negative" floating rounded>
      {{
        (systemNum + messageNum + todoNum) > 99
          ? "99+" :
          (systemNum + messageNum + todoNum)
      }}
    </q-badge>
    <q-menu anchor="bottom start" self="top middle">
      <q-card style="max-width: 400px">
        <q-tabs v-model="noticeType" active-color="primary" align="justify" class="text-grey" dense
                indicator-color="primary" narrow-indicator style="padding: 10px">
          <q-tab :label="$t('NoticeSystem')" name="system">
            <q-badge v-if="systemNum" color="negative" floating>
              {{ systemNum > 99 ? '99+' : systemNum }}
            </q-badge>
          </q-tab>
          <q-tab :label="$t('NoticeMessage')" name="message">
            <q-badge v-if="messageNum" color="negative" floating>
              {{ messageNum > 99 ? '99+' : messageNum }}
            </q-badge>
          </q-tab>

          <q-tab :label="$t('Todo')" name="todo">
            <q-badge v-if="todoNum" color="negative" floating>
              {{ todoNum > 99 ? '99+' : todoNum }}
            </q-badge>
          </q-tab>
        </q-tabs>

        <q-separator/>

        <q-tab-panels v-model="noticeType" animated>
          <q-tab-panel name="system" style="padding: 0">
            <NoticeSystem :systemData="systemData"/>
          </q-tab-panel>

          <q-tab-panel name="message" style="padding: 0">
            <NoticeMessage :messageData="messageData"/>
          </q-tab-panel>

          <q-tab-panel name="todo" style="padding: 0">
            <NoticeTodo :todoData="todoData"/>
          </q-tab-panel>
        </q-tab-panels>
      </q-card>
    </q-menu>
  </q-btn>
</template>

<script setup>
import {computed, inject, onMounted, ref} from 'vue'
import NoticeSystem from './NoticeSystem.vue'
import NoticeMessage from './NoticeMessage.vue'
import NoticeTodo from './NoticeTodo.vue'
import {postAction} from 'src/api/manage';
import {useUserStore} from 'src/stores/user'

const noticeType = ref('system')
const userStore = useUserStore()
const username = computed(() => userStore.GetUsername())
const bus = inject('bus')
const url = {
  list: 'notice/get-notice-list',
  todo: 'todo/get-todo-list'
}

const getNoticeAndTodoData = () => {
  getNoticeSystem()
  getNoticeMessage()
  getNoticeTodo()
}

const systemData = ref([])
const systemNum = ref(0)
const getNoticeSystem = () => {
  postAction(url.list, {
    notice_read: 'yesNo_no',
    notice_sent: 'yesNo_yes',
    notice_to_user: String(username.value),
    notice_type: 'noticeType_system',
    sort_by: 'created_at',
    desc: true,
    page: 1,
    page_size: 10
  }).then(res => {
    if (res.code === 1) {
      systemData.value = res.data.records
      systemNum.value = res.data.total
    }
  })
}

const messageData = ref([])
const messageNum = ref(0)
const getNoticeMessage = () => {
  postAction(url.list, {
    notice_read: 'yesNo_no',
    notice_sent: 'yesNo_yes',
    notice_to_user: String(username.value),
    notice_type: 'noticeType_message',
    sort_by: 'created_at',
    desc: true,
    page: 1,
    page_size: 10
  }).then(res => {
    if (res.code === 1) {
      messageData.value = res.data.records
      messageNum.value = res.data.total
    }
  })
}

const todoData = ref([])
const todoNum = ref(0)
const getNoticeTodo = () => {
  postAction(url.todo, {
    todo_status: 'yesNo_no',
    sort_by: 'created_at',
    desc: true,
    page: 1,
    page_size: 10
  }).then(res => {
    if (res.code === 1) {
      todoData.value = res.data.records
      todoNum.value = res.data.total
    }
  })
}

onMounted(() => {
  bus.on('noticeGetTableData', () => {
    getNoticeAndTodoData()
  })
  getNoticeAndTodoData()
})

defineExpose({
  getNoticeAndTodoData
})
</script>

<style lang="scss" scoped>
.q-badge--floating {
  top: -1px;
  right: -4px;
}
</style>
