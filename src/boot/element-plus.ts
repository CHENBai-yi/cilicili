import {boot} from 'quasar/wrappers'
import 'uno.css';
import {
  ElAffix,
  ElBacktop,
  ElButton,
  ElCard,
  ElCheckbox,
  ElCheckboxGroup,
  ElCol,
  ElCollapse,
  ElCollapseItem,
  ElDatePicker,
  ElDivider,
  ElDropdown,
  ElForm,
  ElFormItem,
  ElIcon,
  ElInput,
  ElInputNumber,
  ElLink,
  ElOption,
  ElPopover,
  ElRadio,
  ElRadioGroup,
  ElSelect,
  ElTag,
  ElTimePicker,
  ElCarouselItem,
  ElAvatar,
  ElCarousel,
  ElDialog,
  ElDropdownItem,
  ElDropdownMenu,
  ElEmpty,
  ElImage,
  ElInfiniteScroll,
  ElPagination,
  ElScrollbar,
} from 'element-plus'

import NavBar from 'src/components/navBar/index.vue'
import CiliBackToTop from 'src/components/ciliBackToTop/index.vue'
import CiliToolBar from "src/components/navBar/CiliToolBar/CiliToolBar.vue"
import CiliLink from "src/components/ciliLink/CiliLink.vue"
//primeVue ui
import PrimeVue from 'primevue/config';
import ToastService from 'primevue/toastservice';
import OverlayPanel from 'primevue/overlaypanel';
import Button from 'primevue/button';
import MegaMenu from 'primevue/megamenu';
import TabMenu from 'primevue/tabmenu';
import FileUpload from 'primevue/fileupload';
import Toast from 'primevue/toast';

import AnimateOnScroll from 'primevue/animateonscroll';

//native UI
import {
  create,
  NA,
  NButton,
  NCard,
  NCarousel,
  NCascader,
  NCheckbox,
  NCollapseTransition,
  NDivider,
  NDynamicInput,
  NDynamicTags,
  NEllipsis,
  NGradientText,
  NH1,
  NH2,
  NH6,
  NImage,
  NInput,
  NInputNumber,
  NLayout,
  NLayoutContent,
  NLayoutSider,
  NList,
  NListItem,
  NResult,
  NScrollbar,
  NSelect,
  NSpace,
  NSwitch,
  NTable,
  NTag,
  NThing,
  NUpload
} from 'naive-ui'
//评论组件
import UndrawUi from "undraw-ui"

export default boot(({app}) => {
  app.use(ElForm)
  app.use(ElFormItem)
  app.use(ElSelect)
  app.use(ElOption)
  app.use(ElCol)
  app.use(ElDatePicker)
  app.use(ElTimePicker)
  app.use(ElCheckboxGroup)
  app.use(ElCheckbox)
  app.use(ElRadioGroup)
  app.use(ElRadio)
  app.use(ElInput)
  app.use(ElInputNumber)
  app.use(ElCarouselItem)

  //ElForm,
  //   ElFormItem,ElSelect,ElOption,ElCol,ElDatePicker,ElTimePicker,
  //   ElCheckboxGroup,ElCheckbox,ElRadioGroup,ElRadio,ElInput
  app.use(ElPopover)
  app.use(ElAffix)
  app.use(ElButton)
  app.use(ElTag)
  app.use(ElCard)
  app.use(ElDropdown)
  app.use(ElBacktop)
  app.use(ElLink)
  app.use(ElCollapse)
  app.use(ElCollapseItem)
  app.use(ElIcon)
  app.use(ElDivider)
  app.use(ElAvatar)
  app.use(ElCarousel)
  app.use(ElDialog)
  app.use(ElDropdownItem)
  app.use(ElDropdownMenu)
  app.use(ElEmpty)
  app.use(ElImage)
  app.use(ElInfiniteScroll)
  app.use(ElPagination)
  app.use(ElScrollbar)


  app.component('CiliBackToTop', CiliBackToTop)
  app.component('NavBar', NavBar)
  app.component('CiliLink', CiliLink)
  app.component('CiliToolBar', CiliToolBar)
  // //评论组件
  app.use(UndrawUi)
  //prime UI
  app.directive('animateonscroll', AnimateOnScroll);
  app.use(PrimeVue);
  app.use(ToastService);
  app.component('OverlayPanel', OverlayPanel);
  app.component('Button', Button);
  app.component('MegaMenu', MegaMenu);
  app.component('TabMenu', TabMenu);
  app.component('FileUpload', FileUpload);
  app.component('Toast', Toast);
  //native ui
  const naive = create({
    components: [
      NDivider, NButton, NH6, NH2, NH1, NCarousel, NList, NGradientText, NCard,
      NListItem, NThing, NSpace, NTag, NEllipsis, NScrollbar, NA,
      NLayout, NLayoutSider, NLayoutContent, NSwitch, NCollapseTransition,
      NSelect, NDynamicTags, NTable, NDynamicInput, NCheckbox, NInputNumber, NInput, NCascader, NImage, NUpload, NResult
    ]
  })
  app.use(naive)
})
export {
  ElAffix, ElButton, ElPopover, ElTag, ElBacktop, ElCarouselItem,
  ElAvatar,
  ElCarousel,
  ElDialog,
  ElDropdownItem,
  ElDropdownMenu,
  ElEmpty,
  ElImage,
  ElInfiniteScroll,
  ElPagination,
  ElScrollbar
}
