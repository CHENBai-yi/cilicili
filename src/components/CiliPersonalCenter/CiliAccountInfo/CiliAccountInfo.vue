<template>
  <q-page :class="darkTheme" class="q-card">
    <div class="q-pl-lg q-pt-lg row items-center">
      <span id="bgc"></span><span class="text-h6 personal-concent">账号信息：</span></div>
    <div class="q-gutter-md q-pl-xl">

      <div class="row items-center"><span class="security-nav-name">&nbsp;&nbsp;昵&nbsp;&nbsp;&nbsp;称：</span>
        <q-input v-model="form.user.nickname" :borderless="open[0]" :readonly="open[0]"
                 :rules="[ (val, rules) => val.length<10|| $t('LengthLessThan')+10 ]"
                 class="security-nav-name"
        />
        <span v-if="!open[0]" class="q-gutter-x-md">
          <n-button
            secondary
            strong
            type="tertiary"
            @click="form.user.nickname=form.nickname_bak,open[0]=true"
          >
            取消
          </n-button>
          <n-button
            secondary
            strong
            type="success"
            @click="open[0]=true,save({nickname:form.user.nickname})"
          >
            保存
          </n-button>
        </span>
        <n-button v-if="open[0]" strong text type="info" @click="open[0]=false,form.nickname_bak=form.user.nickname">修改
        </n-button>
      </div>
      <div class="row items-center">
        <div class="security-nav-name">&nbsp;&nbsp;&nbsp;头&nbsp;&nbsp;像：</div>
        <q-avatar size="60px" square>
          <img :src="form.user.avatar">
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
            :headers="{Authorization:form.user.token}"
            :max=1
            accept="image/*"
            class="flex items-center"
            @change="handleChange"
            @finish="handleFinish"
            @before-upload="beforeUpload"
          >
            <n-button strong text type="info">更换头像</n-button>
          </n-upload>
        </div>


      </div>
      <div class="row items-center"><span class="security-nav-name">&nbsp;&nbsp;手机号：</span>
        <q-input v-model="form.user.mobile" :borderless="open[3]" :readonly="open[3]" class="security-nav-name"/>
        <div class="q-gutter-x-md">
          <span v-if="!open[3]" class="q-gutter-x-md">
          <n-button
            secondary
            strong
            type="tertiary"
            @click="form.user.mobile=form.mobile_bak,open[3]=true"
          >
            取消
          </n-button>
          <n-button
            secondary
            strong
            type="success"
            @click="open[3]=true,save({mobile:form.mobile})"
          >
            保存
          </n-button>
        </span>
          <n-button v-if="open[3]" class="q-ml-lg" strong text type="info"
                    @click="open[3]=false,form.mobile_bak=form.user.mobile">修改
          </n-button>
        </div>
      </div>
      <div class="row items-center"><span class="security-nav-name">真实姓名：</span>
        <q-input v-model="form.user.real_name" :borderless="open[1]" :readonly="open[1]" class="security-nav-name"
        />
        <div class="q-gutter-x-md">
          <span v-if="!open[1]" class="q-gutter-x-md">
          <n-button
            secondary
            strong
            type="tertiary"
            @click="form.user.real_name=form.real_name_bak,open[1]=true"
          >
            取消
          </n-button>
          <n-button
            secondary
            strong
            type="success"
            @click="open[1]=true,save({real_name:form.user.real_name})"
          >
            保存
          </n-button>
        </span>
          <n-button v-if="open[1]" class="q-ml-lg" strong text type="info"
                    @click="open[1]=false,form.real_name_bak=form.user.real_name">修改
          </n-button>
        </div>

      </div>
      <div class="row items-center"><span class="security-nav-name">性别：</span>
        <div>
          <q-btn-toggle
            v-model="form.user.gender"
            :disable="open[2]"
            :options="options"
            push
            toggle-color="primary"
          />
        </div>
        <div class="q-gutter-x-md q-pl-md">
          <span v-if="!open[2]" class="q-gutter-x-md">
          <n-button
            secondary
            strong
            type="tertiary"
            @click="form.user.gender=form.gender_bak,open[2]=true"
          >
            取消
          </n-button>
          <n-button
            secondary
            strong
            type="success"
            @click="open[2]=true,save({gender:form.user.gender})"
          >
            保存
          </n-button>
        </span>
          <n-button v-if="open[2]" strong text type="info" @click="open[2]=false,form.gender_bak=form.user.gender">修改
          </n-button>
        </div>
      </div>
      <div class="items-center row">
        <div class="security-nav-name">登录密码</div>
        <n-button class="q-pl-md" strong text type="info" @click="showPasswordDialog">修改</n-button>
      </div>


    </div>
    <ChangePasswordDialog ref="changePasswordDialog"/>
  </q-page>
</template>

<script setup>
import {computed, nextTick, reactive, ref, watchSyncEffect} from 'vue'
import {useUserStore} from 'src/stores/user'
import useTheme from "src/composables/useTheme"
import ChangePasswordDialog from 'src/components/CiliPersonalCenter/ChangePasswordDialog/ChangePasswordDialog.vue'
import {getAction, postAction} from "../../../api/manage";
import XEUtils from "xe-utils";
import {RealUrl} from 'src/utils/convert'

const userStore = useUserStore()

const {darkTheme} = useTheme()

const form = reactive({
  user: {
    avatar: userStore.GetAvatar(),
    nickname: userStore.GetNickname() || userStore.GetUsername(),
    real_name: userStore.GetRealName(),
    mobile: '',
    gender: '',
    token: computed(() => userStore.GetToken())
  }
})
const cleanForm = () => {
  form.user.avatar = ''
  form.user.nickname = ''
  form.user.real_name = ''
  form.user.mobile = ''
  form.user.gender = ''
}
const getUserInfoUrl = 'users'
const HandleGetUserInfo = async () => {
  const res = await getAction(getUserInfoUrl)
  if (res) {
    const {UserDto} = res
    try {
      UserDto.avatar = RealUrl(UserDto.avatar)
      userStore.FlushUserInfo(XEUtils.omit(UserDto, 'token'))
      form.user = {...form.user, ...UserDto}
    } catch (e) {
      console.debug(e)
    }
  }
}

watchSyncEffect(() => {
  if (userStore.token) {
    HandleGetUserInfo()
  } else {
    cleanForm()
  }
})
nextTick(() => HandleGetUserInfo())
const open = reactive([
  true, true, true, true, true, true
])
const options = reactive([
  {label: '男', value: 'gender_male'},
  {label: '女', value: 'gender_female'},
  {label: '未知', value: 'gender_unknown'}
])
const text = ref('')
const ph = ref('')
const dense = ref(false)
const upload = ref(null)
const fileListLength = ref(0)
const uploadUrl = ref(process.env.API + 'upload/upload-avatar')
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
      save({avatar: res.data.records}).then(res => {
        if (res) {
          fileListLength.value = 0
        }
      })
    } else {
      window.$message.error(res.message, {render: window.$render});
    }
  }
  return file;
};
const handleClick = async () => {
  upload.value?.submit();
}
const beforeUpload = async (data) => {
  console.log(data.file.file?.type)
  if (!/image\/*/.test(data.file.file?.type)) {
    window.$message.error("只能上传图片文件，请重新上传", {render: window.$render});
    return false;
  }
  return true;
}
const changeInfoUrl = 'user/change-info'
const save = async (data) => {
  const res = await postAction(changeInfoUrl, data)
  if (res.code === 1) {
    await HandleGetUserInfo()
    window.$message.success(res.message, {render: window.$render});

    return true
  }
  return false
}
const changePasswordDialog = ref(null)
const showPasswordDialog = () => {
  changePasswordDialog.value.show()
}
</script>

<style scoped>
.personal-concent {
  font-size: 20px;
  font-weight: bold;


}

#bgc {
  height: 18px;
  width: 8px;
  border-radius: 4px;
  background-color: #DF3225;
  display: block;
  margin-right: 20px;
  margin-top: 2px;
}

.security-nav-name {
  font-size: 16px;
  font-weight: bold;

}
</style>
