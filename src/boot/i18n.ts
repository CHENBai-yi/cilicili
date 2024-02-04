import {boot} from 'quasar/wrappers';
import {createI18n} from 'vue-i18n';

import {Translator} from 'src/i18n'
import {useSettingStore} from 'stores/setting'

let i18n
export default boot(({app}) => {
  const settingStore = useSettingStore()
  i18n = createI18n({
    legacy: false,
// @ts-ignore
    locale: settingStore.GetLanguage,
    fallbackLocale: 'zh-CN',
    messages: Translator(),

    // silentTranslationWarn: true,
    // silentFallbackWarn: true,
    globalInjection: true,
    missingWarn: false,
    fallbackWarn: false,
  })
  // Set i18n instance on app
  app.use(i18n);
});
export {i18n}
