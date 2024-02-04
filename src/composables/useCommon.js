import {useStorageStore} from 'src/stores/storage';
import {computed} from 'vue';
import {FormatDate, FormatDateTime} from 'src/utils/date'
import CiliDictShow from 'src/components/CiliDictShow/index.vue'
import CiliShowName from 'src/components/CiliShowName/index.vue'
import {useI18n} from 'vue-i18n';

export default function useCommon() {
  const {t} = useI18n()
  const storageStore = useStorageStore();
  const CiliFrontend = computed(() => storageStore.GetCiliFrontend());
  const CiliBackend = computed(() => storageStore.GetCiliBackend());

  const openLink = (url) => {
    window.open(url)
  }
  const showDateTime = computed(() => {
    return (datetime) => {
      return FormatDateTime(datetime)
    }
  })
  const showDate = computed(() => {
    return (datetime) => {
      return FormatDate(datetime)
    }
  })
  const selectOptionLabel = (opt) => {
    if (opt.name === 'system' || opt.parent_code === 'system' || opt.parent_code === 'log') {
      return t(opt.title)
    }
    return opt.title
  }
  const selectRouteLabel = (opt) => {
    if (opt.name === 'system' || opt.meta.parent_code === 'system' || opt.meta.parent_code === 'log') {
      return t(opt.meta.title)
    }
    return opt.meta.title
  }

  return {
    CiliDictShow,
    CiliShowName,
    showDateTime,
    showDate,
    CiliFrontend,
    CiliBackend,
    openLink,
    selectOptionLabel,
    selectRouteLabel,
  }
}
