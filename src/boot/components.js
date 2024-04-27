import CiliAvatar from 'src/components/common/CiliAvatar/index.vue'
import CiliFormTop from 'src/components/common/CiliFormTop/index.vue'
import CiliTreeTd from 'src/components/common/CiliTreeTd/index.vue'
import CiliVersionMenu from 'src/components/common/CiliVersionMenu/index.vue'
import {
  create,
  NButton,
  NCard,
  NDatePicker,
  NImage,
  NList,
  NListItem,
  NP,
  NPopconfirm,
  NSpace,
  NSwitch,
  NTag,
  NText,
  NThing,
  NUpload,
  NUploadDragger,NCarousel,NCarouselItem,NEllipsis
} from 'naive-ui'
// we globally register our component with Vue
export default ({app}) => {
  app.component('Cili-avatar', CiliAvatar)
  app.component('Cili-form-top', CiliFormTop)
  app.component('Cili-tree-td', CiliTreeTd)
  app.component('Cili-version-menu', CiliVersionMenu)
  //native ui
  const naive = create({
    components: [
      NImage, NPopconfirm, NButton,
      NList, NCard, NSwitch,NEllipsis,
      NListItem, NThing, NSpace, NTag, NDatePicker, NUpload, NP, NText, NUploadDragger,NCarousel,NCarouselItem
    ]
  })
  app.use(naive)
}
