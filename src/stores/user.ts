import {defineStore} from 'pinia';
import {Cookies, SessionStorage} from 'quasar';
// import {usePermissionStore} from './permission';
import {postAction} from "src/api/manage";

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
        this.username = username
        // @ts-ignore
        Cookies.set('cili-username', username, this.option)
        this.nickname = nickname
        // @ts-ignore
        Cookies.set('cili-nickname', nickname, this.option)
        this.realName = realName
        // @ts-ignore
        Cookies.set('cili-realName', realName, this.option)
        this.avatar = avatar
        // @ts-ignore
        Cookies.set('cili-avatar', avatar, this.option)
        return true
      } else {
        return
      }
    },
    SetToken(token: any) {
      this.token = token
      if (this.rememberMe) {
        // @ts-ignore
        Cookies.set('cili-token', token, this.option)
      } else {
        SessionStorage.set('cili-token', token)
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
      SessionStorage.remove('cili-token')
      Cookies.remove('cili-token')
      Cookies.remove('cili-username')
      Cookies.remove('cili-nickname')
      Cookies.remove('cili-realName')
      Cookies.remove('cili-avatar')
      // dont delete dict
      // LocalStorage.remove('cili-dict')
      this.token = undefined
      this.username = undefined
      this.nickname = undefined
      this.realName = undefined
      this.avatar = undefined
    },
    GetToken() {
      if (SessionStorage.getItem('cili-token')) {
        return SessionStorage.getItem('cili-token')
      } else if (Cookies.get('cili-token')) {
        return Cookies.get('cili-token')
      } else {
        return this.token
      }
    },
    GetUsername() {
      if (this.username) {
        return this.username
      } else if (Cookies.get('cili-username')) {
        return Cookies.get('cili-username')
      } else {
        return ""
      }
    },
    GetNickname() {
      if (this.nickname) {
        return this.nickname
      } else if (Cookies.get('cili-nickname')) {
        return Cookies.get('cili-nickname')
      } else {
        return ""
      }
    },
    GetRealName() {
      if (this.realName) {
        return this.realName
      } else if (Cookies.get('cili-realName')) {
        return Cookies.get('cili-realName')
      } else {
        return ""
      }
    },
    GetAvatar() {
      if (this.avatar) {
        return this.avatar
      } else if (Cookies.get('cili-avatar')) {
        return Cookies.get('cili-avatar')
      } else {
        return ""
      }
    },
  },
});
