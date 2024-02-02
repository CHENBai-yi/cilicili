import {boot} from 'quasar/wrappers'
import {ElAffix, ElBacktop, ElButton, ElPopover, ElTag} from 'element-plus'
import 'element-plus/dist/index.css'

export default boot(({app}) => {
  app.use(ElPopover)
  app.use(ElAffix)
  app.use(ElButton)
  app.use(ElTag)
  app.use(ElBacktop)
})
export {ElAffix, ElButton, ElPopover, ElTag, ElBacktop}

