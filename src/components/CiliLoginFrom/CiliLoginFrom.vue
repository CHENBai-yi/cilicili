<template>
  <q-dialog v-model="icon">
    <div style="width:340px">
      <q-card v-if="registerRapidly">
        <q-card-section class="row items-center">
          <div class="text-h6">快速注册</div>
          <q-space/>
          <q-btn v-close-popup class="absolute-top-right on-left q-ma-sm" dense flat icon="ion-ios-close-circle-outline"
                 round/>
        </q-card-section>

        <q-card-section>
          <q-form
            class="row"
          >
            <q-input v-model="account" :dense="true"
                     :rules="[ (val, rules) => rules.email(val) || $t('ValidEmail') ]"
                     class="col-12" clearable
                     label="邮箱验证码登录仅限QQ邮箱" standout="bg-teal text-white"
                     suffix="@qq.com" type="email"
            />
            <q-input v-model="password" :dense="true" :rules="[ val => val.length >= 6 || $t('ValidEmailCode') ]"
                     bottom-slots
                     label="请输入邮箱验证码"
                     maxlength="6" standout="bg-teal text-white">
              <template v-slot:after>
                <CiliLink :disabled="!CodeSpanReSend" :weight=600 color="#4eb7ed" size="14px"
                          @click="handleEmailCode">
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
            <q-btn class="col-12" color="red" rounded>
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
            <q-tab :ripple="false" class="text-purple" name="showPhoneSignin">
              <h1 class="rl-modal-header">账号登录</h1>
            </q-tab>
            <q-tab :ripple="false" class="text-teal" name="showCodeSignin">
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
                <q-input v-model="account" :dense="true"
                         :rules="[ (val, rules) => rules.email(val) || $t('ValidEmail') ]"
                         class="col-12" clearable
                         label="请输入登录邮箱号" reactive-rules standout="bg-teal text-white"
                         type="email"
                />
                <q-input v-model="password" :dense="true" :rules="[ val => val.length >= 6 || $t('ValidPassword') ]"
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
                  v-model="freeOfLogin"
                  checked-icon="task_alt"
                  class="privacy_tip"
                  color="red"
                  label="7天内自动登录"
                  unchecked-icon="crop_square"
                />
                <q-btn class="col-12" color="red" icon-right="send" rounded>
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
                <q-input v-model="account" :dense="true"
                         :rules="[ (val, rules) => rules.email(val) || $t('ValidEmail') ]"
                         class="col-12" clearable
                         label="邮箱验证码登录仅限QQ邮箱" standout="bg-teal text-white"
                         suffix="@qq.com" type="email"
                />
                <q-input v-model="password" :dense="true" :rules="[ val => val.length >= 6 || $t('ValidEmailCode') ]"
                         bottom-slots
                         label="请输入邮箱验证码"
                         maxlength="6" standout="bg-teal text-white">
                  <template v-slot:after>
                    <CiliLink :disabled="!CodeSpanReSend" :weight=600 color="#2faee3" size="14px"
                              @click="handleEmailCode">
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
                  v-model="freeOfLogin"
                  checked-icon="task_alt"
                  class="privacy_tip"
                  color="red"
                  label="7天内自动登录"
                  unchecked-icon="crop_square"
                />
                <q-btn class="col-12" color="red" icon-right="send" rounded>
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
import {ref, watch} from "vue";
import useTheme from "src/composables/useTheme"


const {darkTheme} = useTheme()
const icon = ref(false)
watch(icon, (newVal, oldVal) => {
  if (!newVal) {
    registerRapidly.value = newVal
  }
})
const accept = ref('maybe')
const freeOfLogin = ref(false)
const isPwd = ref(true)
const tab = ref('showPhoneSignin')
const account = ref('')
const password = ref('')
const code = ref('')
const showLoginFrom = () => {
  tab.value = 'showPhoneSignin'
  icon.value = true
}
const showRegisterFrom = () => {
  showLoginFrom()
  registerRapidly.value = true
}
defineExpose({
  showLoginFrom, showRegisterFrom
})
const CodeSpan = ref(null)
const CodeSpanReSend = ref(true)
const handleEmailCode = () => {
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
}
const registerRapidly = ref(false)
</script>

<style lang="scss" scoped>
.erweima {
  background: url("erweima.png") no-repeat 0 0;
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
