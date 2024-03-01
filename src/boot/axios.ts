import {boot} from 'quasar/wrappers';
import axios, {AxiosInstance} from 'axios';
import {Cookies, Dialog} from "quasar";
import {useUserStore} from "stores/user";
import {DemoMode, ForbiddenUrl} from "src/config/config";
// @ts-ignore
import {i18n} from './i18n'
import {ElMessageBox} from "element-plus";

declare module '@vue/runtime-core' {
  interface ComponentCustomProperties {
    $axios: AxiosInstance;
    $api: AxiosInstance;
  }
}

// Be careful when using SSR for cross-request state pollution
// due to creating a Singleton instance here;
// If any client changes this (global) instance, it might be a
// good idea to move this instance creation inside of the
// "export default () => {}" function below (which runs individually
// for each client)
const api = axios.create({
  baseURL: process.env.API, timeout: 120000, withCredentials: false
})

export default boot(({app, router}) => {
  const userStore = useUserStore()
  // @ts-ignore
  api.interceptors.request.use(request => {
    const token = userStore.GetToken()
    request.headers!.ciliLanguage = Cookies.get("cili-language-frontend") || "zh-CN"
    request.headers.setContentType('application/json;charset=utf-8')
    // @ts-ignore
    request.headers.setAuthorization(token)

    if (DemoMode && ForbiddenUrl.some(item => item === request.url)) {
      // @ts-ignore
      window.$message.error(i18n.global.t('DemoMode'), {render: window.$render})
      return
    }
    return request
  }, error => {
    // @ts-ignore
    window.$message.error(error, {render: window.$render})
    return Promise.reject(error)
  })

  api.interceptors.response.use(response => {
    // If the ExpiresAt of the JWT has expired,
    // but the RefreshAt has not expired,
    // the background will insert a Cili Refresh Token in the headers,
    // which will be saved here to form a token replacement logic
    if (response.headers['refresh-token'] && response.data.data.refresh) {
      userStore.SetToken(response.headers['refresh-token'])
      // store.dispatch('user/SetToken', response.headers['Cili-refresh-token'])
      // @ts-ignore
      window.$message.success(i18n.global.t('Refresh') + 'Token' + i18n.global.t('Success'), {render: window.$render})
      return api(response.config)
    }
    const responseData = response.data
    const {code} = responseData
    if (code === 1) {
      return response.data
    } else {
      switch (code) {
        case 0:
          if (responseData.data && responseData.data.reload) {
            userStore.HandleLogout()
            // @ts-ignore
            ElMessageBox.confirm(
              // @ts-ignore
              response.data.message || i18n.global.t('Please') + i18n.global.t('Relogin'),
              // @ts-ignore
              i18n.global.t('Authentication') + i18n.global.t('Failed'),
              {
                // @ts-ignore
                confirmButtonText: i18n.global.t('Relogin'),
                type: 'warning',
              }
            )
              .then(() => {
                router.push('/')
              })

          } else {
            // @ts-ignore
            window.$message.error(response.data.message || i18n.global.t('Operation') + i18n.global.t('Failed'), {render: window.$render})
            return response.data
          }
        default:
          return response.data
      }
    }
  }, error => {
    // 500
    if (error + '' === 'Error: Request failed with status code 500') {
      // @ts-ignore
      window.$message.error(i18n.global.t('Data') + i18n.global.t('Exception') + ',' + i18n.global.t('Please') + i18n.global.t('Relogin'), {render: window.$render})
    } else
      //Unauthorized
    if (error + '' === 'Error: Request failed with status code 401') {
      // @ts-ignore
      window.$message.error(error.config.url + i18n.global.t('Unauthorized'), {render: window.$render})
    } else if (error + '' === 'Error: Request failed with status code 511') {
      userStore.HandleLogout()
      Dialog.create({
        // @ts-ignore
        title: i18n.global.t('Authentication') + i18n.global.t('Failed'),
        // @ts-ignore
        message: error.response.data.message || i18n.global.t('Please') + i18n.global.t('Relogin'),
        persistent: true,
        ok: { // @ts-ignore
          push: true, color: 'negative', label: i18n.global.t('Relogin')
        },
      }).onOk(() => {
        router.push({name: 'login'})
      })
    } else
      // timeout
    if (error + '' === 'Error: timeout of 40000ms exceeded') {
      // @ts-ignore
      window.$message.error(i18n.global.t('Operation') + i18n.global.t('Timeout'), {render: window.$render})
    } else
      // network error
    if (error + '' === 'Error: Network Error') {
      // @ts-ignore
      window.$message.error(error.message, {render: window.$render})
      router.push({name: 'notFound'})
    } else if (error.response && error.response.status === 404) {
      // @ts-ignore
      window.$message.error(i18n.global.t('Request') + i18n.global.t('Address') + i18n.global.t('NotFound') + ' ' + error.response.request.responseURL, {render: window.$render})
    } else {
      // @ts-ignore
      window.$message.error(error.message, {render: window.$render})
    }
    return Promise.reject(error)
  })
  // for use inside Vue files (Options API) through this.$axios and this.$api

  app.config.globalProperties.$axios = axios
  // ^ ^ ^ this will allow you to use this.$axios (for Vue Options API form)
  //       so you won't necessarily have to import axios in each vue file

  app.config.globalProperties.$api = api
  // ^ ^ ^ this will allow you to use this.$api (for Vue Options API form)
  //       so you can easily perform requests against your app's API
})

export {api};
