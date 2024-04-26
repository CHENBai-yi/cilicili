import {DictOptions} from 'src/utils/dict'
import {computed, inject, onMounted, reactive, ref} from 'vue'
import {postAction} from 'src/api/manage'
import {useQuasar} from 'quasar'
import {useI18n} from 'vue-i18n'
import useCommon from './useCommon'

export default function useRecordDetail(url, emit) {
  const {t} = useI18n()
  const $q = useQuasar()
  const dictOptions = ref({})
  const bus = inject('bus')
  onMounted(async () => {
    dictOptions.value = await DictOptions()
  })
  const formType = ref('')
  const formTypeName = computed(() => {
    if (formType.value === 'edit') {
      return t('Edit')
    } else if (formType.value === 'add') {
      return t('Add')
    } else {
      return t('Error')
    }
  })
  const recordDetail = reactive({})
  const recordDetailVisible = ref(false)
  const recordDetailVisible2 = ref(false)
  const loading = ref(false)
  const preShowCarousel = (row) => {
    if (!!row && row instanceof Array) {
      recordDetail.value = row.map(item => {
        return {url: item.image_url, sort: item.sort, memo: item.memo}
      }).sort((a, b) => a.sort - b.sort)
      recordDetailVisible2.value = true
    }
  }
  const show = (row) => {
    loading.value = true
    recordDetail.value = {}
    recordDetailVisible.value = true
    if (row && row.id) {
      handleQueryById(row.id)
    } else {
      recordDetail.value = {}
      recordDetailVisible.value = true
      loading.value = false
    }
  }
  const handleQueryById = async (id) => {
    postAction(url.queryById, {
      id,
    }).then(res => {
      if (res.code === 1) {
        recordDetail.value = res.data.records
      }
    }).finally(() => {
      loading.value = false
    })
  }

  const recordDetailForm = ref(null)

  const handleAddOrEidt = async () => {
    const success = await recordDetailForm.value.validate()
    if (success) {
      if (formType.value === 'edit') {
        if (url === undefined || !url.edit) {
          $q.notify({
            type: 'negative',
            message: t('UrlNotConfig'),
          })
          return
        }
        const res = await postAction(url.edit, recordDetail.value)
        if (res.code === 1) {
          $q.notify({
            type: 'positive',
            message: res.message,
          })
          recordDetailVisible.value = false
        }
      } else if (formType.value === 'add') {
        if (url === undefined || !url.add) {
          $q.notify({
            type: 'negative',
            message: t('UrlNotConfig'),
          })
          return
        }
        const res = await postAction(url.add, recordDetail.value)
        if (res.code === 1) {
          $q.notify({
            type: 'positive',
            message: res.message,
          })
          recordDetailVisible.value = false
        }
      } else {
        $q.notify({
          type: 'negative',
          message: t('CanNotAddOrEdit'),
        })
      }
      emit('handleFinish')
    } else {
      $q.notify({
        type: 'negative',
        message: t('FixForm'),
      })
    }
  }
  const {showDateTime, showDate, CiliFrontend, CiliBackend, CiliDictShow, CiliShowName,} = useCommon()
  return {
    $q,
    t,
    bus,
    CiliShowName,
    CiliDictShow,
    CiliFrontend,
    CiliBackend,
    dictOptions,
    showDateTime,
    showDate,
    loading,
    show,
    preShowCarousel,
    formType,
    formTypeName,
    recordDetail,
    recordDetailVisible,
    recordDetailVisible2,
    handleQueryById,
    recordDetailForm,
    handleAddOrEidt,
  }
}
