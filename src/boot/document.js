import {computed, watch} from 'vue';
import {useStorageStore} from 'src/stores/storage';
import {CiliFrontendDefault} from "src/config/default"

const storageStore = useStorageStore();
const CiliFrontend = computed(() => storageStore.GetCiliFrontend());

const createLink = () => {
  const toDelete = document.getElementsByName('Cili-link-href')
  if (toDelete && toDelete.length) {
    document.getElementsByTagName('head')[0].removeChild(toDelete[0])
  }
  const CiliLink = document.createElement('link')
  CiliLink.type = 'image/ico'
  CiliLink.rel = 'icon'
  CiliLink.setAttribute('name', 'Cili-link-href')
  if (CiliFrontend.value.favicon && CiliFrontend.value.favicon !== '') {
    const favicon = process.env.API + CiliFrontend.value.favicon.substring(12)
    CiliLink.href = favicon
  } else {
    CiliLink.href = 'favicon.ico'
  }
  document.getElementsByTagName('head')[0].appendChild(CiliLink)
}

watch(CiliFrontend, (newValue) => {
  document.title = newValue.subTitle
  createLink()
})

export default async () => {
  document.title = CiliFrontend.value.subTitle || CiliFrontendDefault.subTitle
  createLink()
}
