<template>
  <q-dialog v-model="icon" class="z-max">
    <div style="width:340px">
      <q-card v-if="registerRapidly">
        <q-card-section class="row items-center">
          <div class="text-h6 ">快速注册</div>
          <div class="q-gutter-y-md" style="max-width: 600px">
            <q-tabs
              v-model="role"
              align="left"
              dense
              narrow-indicator
            >
              <q-tab class="text-purple" icon="img:static/images/ic_student.svg" label="学生" name="student"/>
              <q-tab class="text-orange" icon="img:static/images/teacher.svg" label="教师" name="teacher"/>
            </q-tabs>
          </div>
          <q-btn v-close-popup class="absolute-top-right on-left q-ma-sm" dense flat icon="ion-ios-close-circle-outline"
                 round/>
        </q-card-section>

        <q-card-section>
          <q-form
            class="row"
            @submit="onRegister"
          >
            <q-input v-model="emailAccount" :dense="true"
                     :rules="[ (val, rules) => rules.email(val) || $t('ValidEmail') ]"
                     class="col-12" clearable
                     label="邮箱注册"
                     standout="bg-teal text-white"
                     type="email"
            />
            <q-input v-if="role==='teacher'" v-model="realName" :dense="true"
                     :hint="$t('NicknameMessage')"
                     :rules="[ (val, rules) => !!val || $t('NeedInput') ]" class="col-12"
                     clearable
                     label="请输入真实姓名" standout="bg-teal text-white"
            />
            <q-input v-model="password" :dense="true"
                     :rules="[ (val, rules) => val.length>=8|| $t('ValidPassword8') ]" class="col-12"
                     clearable
                     label="请输入密码"
                     standout="bg-teal text-white" type="password"
            />
            <q-input v-model="twoPassword" :dense="true"
                     :rules="[ (val, rules) => password===val|| $t('TwoPasswordsError') ]" class="col-12"
                     clearable
                     label="请再次确认密码"
                     standout="bg-teal text-white" type="password"
            />
            <q-input v-model="emailCode" :dense="true" :rules="[ val => val.length >= 6 || $t('ValidEmailCode') ]"
                     bottom-slots
                     label="请输入邮箱验证码"
                     maxlength="6" standout="bg-teal text-white">
              <template v-slot:after>
                <CiliLink :disabled="!CodeSpanReSend" :weight=600 color="#4eb7ed" size="14px"
                          @click="handleEmailCode(false)">
                    <span
                      v-show="CodeSpanReSend">获取验证码
                    </span>
                  <span v-show="!CodeSpanReSend" ref="CodeSpan" class="reSend">
                      重新发送
                    </span>
                </CiliLink>
              </template>
            </q-input>

            <div class="col-12 flex items-center">
              <q-toggle v-model="accept" checked-icon="check"
                        color="green"
                        indeterminate-value="maybe"
                        unchecked-icon="clear"/>
              <span class="privacy_tip">同意</span>
              <el-link :underline="false" href="privacy.html" target="_blank" type="primary">
                《菠萝微课注册协议及隐私政策》
              </el-link>
            </div>
            <q-btn class="col-12" color="red" rounded type="submit">
              <h2 class="rl-modal-header login no-margin ">立即注册</h2>
            </q-btn>
            <div class="col-12 flex justify-center">
              <q-btn color="red" flat rounded @click="registerRapidly=false">
                <template #default>
                  <h2 class="rl-modal-header privacyTip no-margin">返回账号登录</h2>
                </template>
              </q-btn>
            </div>
          </q-form>
        </q-card-section>
      </q-card>

      <q-card v-else>
        <q-card-section class="row items-center q-pb-none">
          <q-tabs
            v-model="tab"
            align="justify"
            class="q-mb-lg"
            narrow-indicator
          >
            <q-tab :ripple="false" class="text-purple" name="showPhoneSignin" @click="codeSign=false">
              <h1 class="rl-modal-header">账号登录</h1>
            </q-tab>
            <q-tab :ripple="false" class="text-teal" name="showCodeSignin" @click="codeSign=true">
              <h1 class="rl-modal-header">验证码登录</h1>
            </q-tab>
          </q-tabs>
          <q-space/>
          <q-btn v-close-popup class="absolute-top-right on-left q-ma-sm" dense flat icon="ion-ios-close-circle-outline"
                 round/>
        </q-card-section>

        <q-card-section class="no-padding no-margin">
          <!--               @reset="onReset"
                        @submit="onSubmit"-->
          <q-form
            class="q-gutter-md"
            @submit="onLogin"
          >
            <q-tab-panels
              v-model="tab"
              :class="darkTheme"
              animated
              class="text-white text-center "
              transition-next="fade"
              transition-prev="fade"
            >
              <q-tab-panel class="row" name="showPhoneSignin">
                <q-input v-model="emailAccount" :dense="true"
                         :rules="[ (val, rules) => rules.email(val) || $t('ValidEmail') ]"
                         class="col-12" clearable
                         label="请输入登录邮箱号" reactive-rules standout="bg-teal text-white"
                         type="email"
                />
                <q-input v-model="password" :dense="true" :rules="[ val => val.length >= 8 || $t('ValidPassword8') ]"
                         :type="isPwd ? 'password' : 'text'"
                         class="col-12"
                         label="请输入密码" reactive-rules
                         standout="bg-teal text-white">
                  <template v-slot:append>
                    <q-icon
                      :name="isPwd ? 'visibility_off' : 'visibility'"
                      class="cursor-pointer"
                      @click="isPwd = !isPwd"
                    />
                  </template>
                </q-input>

                <q-checkbox
                  v-model="userStore.rememberMe"
                  checked-icon="task_alt"
                  class="privacy_tip"
                  color="red"
                  label="7天内自动登录"
                  unchecked-icon="crop_square"
                  @update:model-value="userStore.rememberMe"
                />
                <q-btn class="col-12" color="red" icon-right="send" rounded type="submit">
                  <h2 class="rl-modal-header login no-margin ">登录</h2>
                </q-btn>
                <div class="col-12 ">
                  <q-btn color="red" flat rounded @click="registerRapidly=true">
                    <template #default>
                      <h2 class="rl-modal-header privacyTip no-margin">快速注册</h2>
                    </template>
                  </q-btn>
                </div>
                <div class="col-12">
                  <p class="privacy_tip">登录即同意菠萝微课网
                    <el-link :underline="false" href="privacy.html" target="_blank" type="primary">《隐私政策》</el-link>
                  </p>
                </div>
              </q-tab-panel>


              <q-tab-panel class="row" name="showCodeSignin">
                <q-input v-model="email" :dense="true"
                         :rules="[ (val, rules) => rules.email(account) || $t('ValidEmail') ]"
                         :suffix="suffix" class="col-12"
                         clearable label="邮箱验证码登录仅限QQ邮箱"
                         standout="bg-teal text-white"
                />
                <q-input v-model="emailCode" :dense="true" :rules="[ val => val.length ===6 || $t('ValidEmailCode') ]"
                         bottom-slots
                         label="请输入邮箱验证码"
                         maxlength="6" standout="bg-teal text-white">
                  <template v-slot:after>
                    <CiliLink :disabled="!CodeSpanReSend" :weight=600 color="#2faee3" size="14px"
                              @click="handleEmailCode(true)">
                    <span
                      v-show="CodeSpanReSend">获取验证码
                    </span>
                      <span v-show="!CodeSpanReSend" ref="CodeSpan" class="reSend">
                      重新发送
                    </span>
                    </CiliLink>
                  </template>
                </q-input>

                <q-checkbox
                  v-model="userStore.rememberMe"
                  checked-icon="task_alt"
                  class="privacy_tip"
                  color="red"
                  label="7天内自动登录"
                  unchecked-icon="crop_square"
                  @update:model-value="userStore.rememberMe"
                />
                <q-btn class="col-12" color="red" icon-right="send" rounded type="submit">
                  <h2 class="rl-modal-header login no-margin ">登录</h2>
                </q-btn>
                <div class="col-12 ">
                  <q-btn color="red" flat rounded @click="registerRapidly=true">
                    <template #default>
                      <h2 class="rl-modal-header privacyTip no-margin">快速注册</h2>
                    </template>
                  </q-btn>
                </div>
                <div class="col-12">
                  <p class="privacy_tip">登录即同意菠萝微课网
                    <el-link :underline="false" href="privacy.html" target="_blank" type="primary">《隐私政策》</el-link>
                  </p>
                </div>

              </q-tab-panel>

            </q-tab-panels>
          </q-form>
        </q-card-section>
        <div class="erweima"></div>
      </q-card>
    </div>

  </q-dialog>
</template>

<script setup>
import {computed, inject, onMounted, reactive, ref, watch} from "vue";
import useTheme from "src/composables/useTheme"
import {postAction} from 'src/api/manage'
import {useUserStore} from "src/stores/user"
import {usePermissionStore} from "stores/permission";

const permissionStore = usePermissionStore()
const urls = reactive({
  registry: 'public/frontend/reg',
  getEmailCode: 'public/get-email-code'
})
const userStore = useUserStore()
const {darkTheme} = useTheme()
const icon = ref(false)
watch(icon, (newVal, oldVal) => {
  if (!newVal) {
    registerRapidly.value = newVal
  }
})
const registerRapidly = ref(false)
const suffix = ref('@qq.com')
const emailCode = ref('')
const realName = ref('')
const accept = ref('maybe')
const rememberMe = ref(false)
const isPwd = ref(true)
const tab = ref('showPhoneSignin')
const email = ref('')
const account = computed(() => email.value + suffix.value)
const emailAccount = ref('')
const password = ref('')
const twoPassword = ref('')
const code = ref('')
const showLoginFrom = () => {
  tab.value = 'showPhoneSignin'
  registerRapidly.value = false
  icon.value = true
}
const bus = inject('bus')
const showRegisterFrom = () => {
  showLoginFrom()
  registerRapidly.value = true
}
defineExpose({
  showLoginFrom, showRegisterFrom
})
const CodeSpan = ref(null)
const CodeSpanReSend = ref(true)

//注册

const role = ref('student')
const testEmail = (email) => {
  const pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  return pattern.test(email);
}
const handleEmailCode = (b) => {
  const params = {}
  if (b) {
    if (!email.value || !testEmail((email.value?.trim()) + suffix.value)) {
      window.$message.error("请输入正确的邮箱.", {render: window.$render})
      return
    }
    params.email = (email.value?.trim()) + suffix.value
  } else {
    if (!emailAccount.value || !testEmail(emailAccount.value?.trim())) {
      window.$message.error("请输入正确的邮箱.", {render: window.$render})
      return
    }
    params.email = emailAccount.value
  }
  params.login = b
  if (registerRapidly.value && !(typeof accept.value === "boolean" && accept.value)) {
    window.$message.warning("请勾选注册协议.", {render: window.$render})
    return
  }
  postAction(urls.getEmailCode, params)
    .then(res => {
      console.log(res)
      if (res.code === 1) {
        window.$message.success(res.message, {render: window.$render})
        const codeSpan = CodeSpan.value
        const innerText = codeSpan.innerText
        let time = 60;
        const timer = setInterval(() => {
          codeSpan.innerText = `${innerText} ${time--}`
          CodeSpanReSend.value = false
          if (time < 0) {
            clearInterval(timer)
            codeSpan.innerText = innerText
            CodeSpanReSend.value = true
          }
        }, 1000)
        return
      }
      window.$message.error(res.message, {render: window.$render})
    })
}


const onRegister = () => {
  postAction(urls.registry, {
    role_code: role.value
    , password: password.value
    , code: emailCode.value
    , email: emailAccount.value
    , real_name: realName.value
  })
    .then(res => {
      if (res.code === 1) {
        window.$message.success(res.message, {render: window.$render})
        code.value = ''
        showLoginFrom()
      } else {
        window.$message.error(res.message, {render: window.$render})
      }
    })
}
const codeSign = ref(false)
//登录
const onLogin = async (form) => {
  if (codeSign.value) {
    form.email = account.value
    form.code = emailCode.value
    form.password = 'codeSign'
  } else {
    form.email = emailAccount.value
    form.password = password.value
  }
  userStore.HandleLogin(form)
    .then(res => {
      if (res) {
        permissionStore.GetRoleButton()
        icon.value = false
        flushFrom()
      }
    })
}

onMounted(() => {
  bus.on('showLoginFrom', showLoginFrom)
})
const flushFrom = () => {
  emailAccount.value = ''
  email.value = ''
  code.value = ''
  password.value = ''
  twoPassword.value = ''
}
</script>

<style lang="scss" scoped>
.erweima {
  //background: url("erweima.png") no-repeat 0 0;
  width: 60px;
  height: 60px;
  position: absolute;
  bottom: 1px;
  right: 0;
  cursor: pointer;
  border-radius: 0 0 12px 0;
}

.rl-modal-header {
  font-size: 18px;
  line-height: 40px;
  font-weight: 700;
  color: inherit;

  &.login {
    font-size: 16px;
  }

  &.privacyTip {
    font-size: 14px;
  }
}

.privacy_tip {
  font-size: 14px;
  color: #9199a1;
  text-align: center;
}

.reSend {
  top: 12px;
  right: 14px;
  font-size: 14px;
  color: #9199a1;
  line-height: 24px;
}
</style>
