import {defineStore} from 'pinia';
import {Cookies, SessionStorage} from 'quasar';
// import {usePermissionStore} from './permission';
import {postAction} from "src/api/manage";
import {getAvatar} from 'src/utils/common'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: undefined,
    username: undefined,
    nickname: undefined,
    realName: undefined,
    avatar: undefined,
    rememberMe: true,
    option: {expires: 7, sameSite: "Strict"}
  }),
  getters: {},
  actions: {
    async HandleLogin(loginForm: any) {
      if (!!this.GetToken()) return true;
      const res = await postAction('public/login', loginForm)
      if (res.code === 1) {
        // @ts-ignore
        window.$message.success(res.message, {render: window.$render})
        // @ts-ignore
        const token = res.data.token
        // @ts-ignore
        const username = res.data.username
        // @ts-ignore
        const nickname = res.data.nickname
        // @ts-ignore
        const realName = res.data.real_name
        // @ts-ignore
        const avatar = res.data.avatar
        this.SetToken(token)

        this.avatar = avatar
        // @ts-ignore
        Cookies.set('cili-avatar-frontend', avatar, this.option)
        this.username = username
        // @ts-ignore
        Cookies.set('cili-username-frontend', username, this.option)
        this.nickname = nickname
        // @ts-ignore
        Cookies.set('cili-nickname-frontend', nickname, this.option)
        this.realName = realName
        // @ts-ignore
        Cookies.set('cili-realName-frontend', realName, this.option)


        return true
      } else {
        return
      }
    },
    SetToken(token: any) {
      this.token = token
      if (this.rememberMe) {
        // @ts-ignore
        Cookies.set('cili-token-frontend', token, this.option)
      } else {
        SessionStorage.set('cili-token-frontend', token)
      }
    },
    ChangeRememberMe(type: any) {
      this.rememberMe = type
    },
    async HandleUserLogout() {
      if (!!this.GetToken()) {
        const res = await postAction("public/logout", {username: this.username, token: this.GetToken()})
        if (res.code === 1) {
          this.HandleLogout()
        }
        return res.message;
      }
    },
    HandleLogout() {
      // const permissionStore = usePermissionStore()
      // permissionStore.ClearMenu()
      SessionStorage.remove('cili-token-frontend')
      Cookies.remove('cili-token-frontend')
      Cookies.remove('cili-username-frontend')
      Cookies.remove('cili-nickname-frontend')
      Cookies.remove('cili-realName-frontend')
      Cookies.remove('cili-avatar-frontend')
      // dont delete dict
      // LocalStorage.remove('cili-dict')
      this.token = undefined
      this.username = undefined
      this.nickname = undefined
      this.realName = undefined
      this.avatar = undefined

    },
    GetToken() {
      if (SessionStorage.getItem('cili-token-frontend')) {
        return SessionStorage.getItem('cili-token-frontend')
      } else if (Cookies.get('cili-token-frontend')) {
        return Cookies.get('cili-token-frontend')
      } else {
        return this.token
      }
    },
    GetUsername() {
      if (this.username) {
        return this.username
      } else if (Cookies.get('cili-username-frontend')) {
        return Cookies.get('cili-username-frontend')
      } else {
        return ""
      }
    },
    GetNickname() {
      if (this.nickname) {
        return this.nickname
      } else if (Cookies.get('cili-nickname-frontend')) {
        return Cookies.get('cili-nickname-frontend')
      } else {
        return ""
      }
    },
    GetRealName() {
      if (this.realName) {
        return this.realName
      } else if (Cookies.get('cili-realName-frontend')) {
        return Cookies.get('cili-realName-frontend')
      } else {
        return ""
      }
    },
    GetAvatar() {
      if (this.avatar) {
        return getAvatar(this.avatar)
      } else if (Cookies.get('cili-avatar-frontend')) {
        return getAvatar(Cookies.get('cili-avatar-frontend'))
      } else {
        return 'static/images/logo.svg'
      }
    },
  },
});
