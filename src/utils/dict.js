import {LocalStorage} from "quasar"
import {useStorageStore} from "src/stores/storage"
import XEUtils from 'xe-utils'

export async function DictOptions() {
  const detailLocal = LocalStorage.getItem('cili-dict')
  const storageStore = useStorageStore()
  if (detailLocal) {
    return detailLocal
  } else {
    await storageStore.SetCiliDict().then(() => {
      return LocalStorage.getItem('cili-dict')
    })
  }
}

export function GetDictLabel(dictCode) {
  const dictOptions = LocalStorage.getItem('cili-dict')
  if (dictCode !== '') {
    for (let dict in dictOptions) {
      const dictDetail = XEUtils.findTree(dictOptions[dict], item => item.dict_code === dictCode)
      if (dictDetail) {
        return dictDetail.item.dict_label
      }
    }
    return ""
  } else {
    return ""
  }
}
