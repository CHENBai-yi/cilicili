import {useI18n} from 'vue-i18n'
import {postAction} from 'src/api/manage'
import {ref} from 'vue'

export default function useTableData(url) {
  const pagination = ref({
    sortBy: 'created_at',
    descending: false,
    page: 1,
    rowsPerPage: 10,
  })

  const queryParams = ref({})
  const pageOptions = ref([10, 30, 50, 100])
  const tableData = ref([
    {
      id: 1,
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      desc: "本门课程作为2024年Flutter入门首选课，课程中结合了最新版的Flutter、Dart、主流插件技术进行企业级项目开发，并融入了大量Flutter版本更新问题的解决方案，以及与H5混合开发和通信、多屏适配等高频技能，课程中还将讲师近几年在一线开发中积累的大量开发、避坑等经验传授给大家，让各位小伙伴在实际开发中能更好的运用Flutter技术。",
      author: '尚硅谷',
      carbs: 24,
      protein: 4.0,
      kind: "java",
      price: 19.9,
      date: '2024年12月24 12:30:05',
      status: '审核中'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 37,
      protein: 4.3,
      date: '2024年12月24 12:30:05',
      status: '审核中'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 23,
      protein: 6.0,
      date: '2024年12月24 12:30:05',
      status: '审核中'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 67,
      protein: 4.3,
      date: '2024年12月24 12:30:05',
      status: '审核中'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 49,
      protein: 3.9,
      date: '2024年12月24 12:30:05',
      status: '审核中'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 94,
      protein: 0.0,
      date: '2024年12月24 12:30:05',
      status: '审核中'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 98,
      protein: 0,
      date: '2024年12月24 12:30:05',
      status: '审核中'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 87,
      protein: 6.5,
      date: '2024年12月24 12:30:05',
      calcium: '0%',
      status: '45%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 65,
      protein: 7,
      date: 54,
      calcium: '12%',
      status: '6%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    },
    {
      name: 'JavaSe零基础之基础语法',
      img: 'JavaSe零基础之基础语法.jpg',
      author: '尚硅谷',
      carbs: 51,
      protein: 4.9,
      date: '2024年12月24 12:30:05',
      calcium: '2%',
      status: '22%'
    }
  ])


  const {t} = useI18n()
  const loading = ref(false);
  const onRequest = async (props) => {
    if (url === undefined || !url.list) {
      window.$message.error(t('UrlNotConfig'), {render: window.$render})
      return
    }
    tableData.value = []
    loading.value = false
    // Assembly paging and filtering conditions
    const params = {}
    params.sort_by = props.pagination.sortBy
    params.desc = props.pagination.descending
    params.page = props.pagination.page
    params.page_size = props.pagination.rowsPerPage
    const allParams = Object.assign({}, params, queryParams.value)

    await postAction(url.list, allParams).then(res => {
      if (res.code === 1) {
        pagination.value = props.pagination
        pagination.value.rowsNumber = res.data.total
        tableData.value = res.data.records
      }
    }).finally(() => {
      loading.value = false
    })
  }
  const getTableData = () => onRequest({pagination: pagination.value, queryParams: queryParams.value})
  return {
    getTableData,
    onRequest,
    loading,
    tableData,
    pageOptions,
    queryParams,
    pagination
  }
}
