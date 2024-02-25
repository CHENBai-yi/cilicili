import {boot} from 'quasar/wrappers'
import {EventBus} from 'quasar'

// "async" is optional;
// more info on params: https://v2.quasar.dev/quasar-cli/boot-files
export default boot(async ({app, router}) => {
  const bus = new EventBus()
  app.provide('bus', bus)
})
