<template>
  <el-affix :offset="110" class="float-right on-left" position="bottom">
    <ul :class="darkTheme" class=" no-padding no-margin v-popover-content center" style="list-style: none">
      <li>
        <q-fab
          dense
          direction="left"
          external-label
          fab-mini
          flat
          icon="keyboard_arrow_left"
          padding="xs" rounded stack unelevated
        >
          <q-fab-action v-for="(item,index) in langOptions"
                        :label="item.label" color="primary"
                        @click="changeLang(item.value,item.label)"/>
          <template #tooltip>
            <q-tooltip :offset="[10, 10]" anchor="center left" self="center right">
              <em>{{ currenLangLabel }}</em>
            </q-tooltip>
          </template>
          <template #icon>
            <em>{{ lang.substring(lang.lastIndexOf("-") + 1) }}</em>
          </template>
        </q-fab>
      </li>
      <li>

        <q-btn :ripple="false" dense fab-mini flat rounded stack
               unelevated @click="changeTheme">
          <transition-group appear
                            enter-active-class="animated animate__fadeInUp"
          >
            <q-icon v-if="$q.dark.isActive" name='eva-sun-outline'></q-icon>
            <q-icon v-else name="eva-moon-outline"></q-icon>
          </transition-group>
          <q-tooltip :offset="[10, 10]" anchor="center left" self="center right">
            <em v-if="$q.dark.isActive">{{ $t('ChangeLightTheme') }}</em>
            <em v-else>{{ $t('ChangeDarkTheme') }}</em>
          </q-tooltip>
        </q-btn>

      </li>
      <li>
        <transition appear
                    enter-active-class="animated animate__fadeInUp"
        >
          <q-btn v-if="gotop" dense fab-mini flat icon="bi-chevron-bar-up" rounded stack
                 unelevated @click="toTop">
            <q-tooltip :offset="[10, 10]" anchor="center left" self="center right">
              <em>TOP</em>
            </q-tooltip>
          </q-btn>
        </transition>
      </li>
    </ul>
  </el-affix>
</template>

<script setup>
import {onMounted, onUnmounted, ref, watch} from "vue";
import {useI18n} from 'vue-i18n'
import {useSettingStore} from 'src/stores/setting'
import {useQuasar} from 'quasar'
import useTheme from "src/composables/useTheme"
import languages from 'quasar/lang/index.json'
import {i18n} from 'src/boot/i18n'

const gotop = ref(false)//控制返回顶部按钮的显隐
onMounted(() => window.addEventListener("scroll", handleScroll, true))
onUnmounted(() => window.removeEventListener("scroll", handleScroll, true))

const {darkTheme} = useTheme()
const stepHeight = ref(document.documentElement.clientHeight * 0.7)
const handleScroll = () => {

  let scrolltop = document.documentElement.scrollTop || document.body.scrollTop;

  scrolltop >= stepHeight.value ? (gotop.value = true) : (gotop.value = false);
}
const toTop = () => {

  let top = document.documentElement.scrollTop || document.body.scrollTop;
  // 实现滚动效果
  const timeTop = setInterval(() => {
    document.body.scrollTop = document.documentElement.scrollTop = top -= stepHeight.value;
    if (top <= 0) {
      clearInterval(timeTop);
    }
  }, 10);
}
const $q = useQuasar()
const changeTheme = () => {
  if ($q.dark.isActive) {
    $q.dark.set(true) // or false or "auto"
  } else {
    $q.dark.set(false) // or false or "auto"
  }
  // 切换
  $q.dark.toggle()
}


const langList = import.meta.glob('../../../node_modules/quasar/lang/*.mjs')
const settingStore = useSettingStore()
const appLanguages = languages.filter((lang) => ['ru', 'zh-CN', 'en-US'].includes(lang.isoName))

const langOptions = appLanguages.map((lang) => ({
  label: lang.nativeName,
  value: lang.isoName,
}))
const lang = ref($q.lang.isoName)
const {locale} = useI18n({useScope: 'global'})

onMounted(() => {
  lang.value = settingStore.GetLanguage()
})
watch(lang, (val) => {
  try {
    langList[`../../../node_modules/quasar/lang/${val}.mjs`]().then((lang) => {
      $q.lang.set(lang.default)
      locale.value = val
      settingStore.ChangeLanguage(val)
    })
  } catch (err) {
    console.log(err)
  }
})
const currenLangLabel = ref(i18n.global.t('Language'))
const changeLang = (e, label) => {
  currenLangLabel.value = label
  lang.value = e
}
</script>

<style scoped>

</style>
