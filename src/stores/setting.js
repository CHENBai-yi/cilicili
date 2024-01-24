import {defineStore} from 'pinia';
import {Cookies, LocalStorage} from 'quasar';

export const useSettingStore = defineStore('setting', {
  state: () => ({
    language: undefined,
    sidebarWidth: undefined,
    darkTheme: undefined,
    sidebarDense: undefined,
    themeStyle: ''
  }),
  getters: {},
  actions: {
    SetThemeStyle(style) {
      this.themeStyle = style
      LocalStorage.set('cili-theme-style', style)
    },
    GetThemeStyle() {
      if (this.themeStyle) {
        return this.themeStyle
      } else if (LocalStorage.getItem('cili-theme-style')) {
        return LocalStorage.getItem('cili-theme-style')
      } else {
        return 'Gin-Quasar-Admin'
      }
    },
    SetDarkTheme(val) {
      this.darkTheme = val
      LocalStorage.set('cili-dark-theme', val)
    },
    GetDarkTheme() {
      if (this.darkTheme) {
        return this.darkTheme
      } else if (LocalStorage.getItem('cili-dark-theme')) {
        return LocalStorage.getItem('cili-dark-theme')
      } else {
        return false
      }
    },
    SetSidebarDense(val) {
      this.sidebarDense = val
      LocalStorage.set('cili-sidebar-dense', val)
    },
    GetSidebarDense() {
      if (this.sidebarDense) {
        return this.sidebarDense
      } else if (LocalStorage.getItem('cili-sidebar-dense')) {
        return LocalStorage.getItem('cili-sidebar-dense')
      } else {
        return false
      }
    },
    ChangeLanguage(lang) {
      this.language = lang
      Cookies.set('cili-language', lang)
    },
    GetLanguage() {
      if (this.language) {
        return this.language
      } else if (Cookies.get('cili-language')) {
        return Cookies.get('cili-language')
      } else {
        return 'zh-CN'
      }
    },
    SetSidebarWidth(width) {
      this.sidebarWidth = width
      LocalStorage.set('cili-sidebar-width', width)
    },
    GetSidebarWidth() {
      if (this.sidebarWidth) {
        return this.sidebarWidth
      } else if (LocalStorage.getItem('cili-sidebar-width')) {
        return LocalStorage.getItem('cili-sidebar-width')
      } else {
        return 200
      }
    },
  }
})
