import {boot} from 'quasar/wrappers'
import {ElAffix, ElBacktop, ElButton, ElLink, ElPopover, ElTag} from 'element-plus'

import NavBar from 'src/components/navBar/index.vue'
import CiliBackToTop from 'src/components/ciliBackToTop/index.vue'
import CiliToolBar from "src/components/navBar/CiliToolBar/CiliToolBar.vue"
import CiliLink from "src/components/ciliLink/CiliLink.vue"
//in main.js
import PrimeVue from 'primevue/config';

import OverlayPanel from 'primevue/overlaypanel';
import Button from 'primevue/button';
import MegaMenu from 'primevue/megamenu';
import TabMenu from 'primevue/tabmenu';

import AnimateOnScroll from 'primevue/animateonscroll';

//native UI
import {
  create,
  NButton,
  NCarousel,
  NEllipsis,
  NH1,
  NH2,
  NH6,
  NLayout,
  NLayoutContent,
  NLayoutSider,
  NList,
  NListItem,
  NScrollbar,
  NSpace,
  NTag,
  NThing
} from 'naive-ui'

export default boot(({app}) => {
  app.use(ElPopover)
  app.use(ElAffix)
  app.use(ElButton)
  app.use(ElTag)
  app.use(ElBacktop)
  app.use(ElLink)
  app.component('CiliBackToTop', CiliBackToTop)
  app.component('NavBar', NavBar)
  app.component('CiliLink', CiliLink)
  app.component('CiliToolBar', CiliToolBar)
  //prime UI
  app.use(PrimeVue);
  app.component('OverlayPanel', OverlayPanel);
  app.component('Button', Button);
  app.component('MegaMenu', MegaMenu);
  app.component('TabMenu', TabMenu);
  app.directive('animateonscroll', AnimateOnScroll);
  //native ui
  const naive = create({
    components: [NButton, NH6, NH2, NH1, NCarousel, NList, NListItem, NThing, NSpace, NTag, NEllipsis, NScrollbar, NLayout, NLayoutSider, NLayoutContent]
  })
  app.use(naive)
})
export {ElAffix, ElButton, ElPopover, ElTag, ElBacktop}
