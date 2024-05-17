import {defineStore} from 'pinia';
import {Cookies, LocalStorage, SessionStorage} from 'quasar';
// import {usePermissionStore} from './permission';
import {postAction} from "src/api/manage";
import {usePermissionStore} from "stores/permission";
import {RealUrl} from 'src/utils/convert'

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
    FlushUserInfo(res: any) {
      const token = res.token
      // @ts-ignore
      const username = res.username
      // @ts-ignore
      const nickname = res.nickname
      // @ts-ignore
      const realName = res.real_name
      // @ts-ignore
      const avatar = RealUrl(res.avatar)
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
    },
    async HandleLogin(loginForm: any) {
      if (!!this.GetToken()) return true;
      const res = await postAction('public/login', loginForm)
      if (res.code === 1) {
        // @ts-ignore
        window.$message.success(res.message, {render: window.$render})
        // @ts-ignore
        this.FlushUserInfo(res.data)
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
          await this.HandleLogout()
        }
        return res.message;
      }
    },
    async HandleLogout() {
      const permissionStore = usePermissionStore()
      this.token = void 0
      this.username = void 0
      this.nickname = void 0
      this.realName = void 0
      this.avatar = void 0
      SessionStorage.removeItem('cili-token-frontend')
      LocalStorage.removeItem('cili-token-frontend')
      Cookies.remove('cili-token-frontend')
      Cookies.remove('cili-username-frontend')
      Cookies.remove('cili-nickname-frontend')
      Cookies.remove('cili-realName-frontend')
      Cookies.remove('cili-avatar-frontend')
      permissionStore.ClearMenu()
      // dont delete dict
      // LocalStorage.remove('cili-dict')

    },
    GetToken() {
      if (SessionStorage.has('cili-token-frontend')) {
        return SessionStorage.getItem('cili-token-frontend')
      } else if (Cookies.has('cili-token-frontend')) {
        return Cookies.get('cili-token-frontend')
      } else {
        return this.token
      }
    },
    GetUsername() {
      if (this.username) {
        return this.username
      } else if (Cookies.has('cili-username-frontend')) {
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
        return this.avatar
      } else if (Cookies.get('cili-avatar-frontend')) {
        return Cookies.get('cili-avatar-frontend')
      } else {
        return 'static/images/logo.svg'
      }
    },
    SetAvatar(avatar: any) {
      let url = RealUrl(avatar);
      this.avatar = url
      Cookies.set('cili-avatar-frontend', url)
    },
  }
});
