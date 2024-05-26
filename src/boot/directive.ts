import {boot} from 'quasar/wrappers'
import {useUserStore} from 'src/stores/user.js'

export default boot(({app}) => {
  const permissionStore = useUserStore()
  app.directive('has', (el, binding, vnode, prevVnode) => {
    //@ts-ignore
    const hasPermission = permissionStore.GetInitUserButton().some((item: string) => item === binding.value)
    if (!hasPermission && el.parentNode) {
      el.parentNode.removeChild(el)
    }
  })
})
