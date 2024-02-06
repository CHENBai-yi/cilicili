import {boot} from 'quasar/wrappers'
import {ElAffix, ElBacktop, ElButton, ElLink, ElPopover, ElTag} from 'element-plus'

import NavBar from 'src/components/navBar/index.vue'
import CiliBackToTop from 'src/components/ciliBackToTop/index.vue'
//in main.js
import PrimeVue from 'primevue/config';

import OverlayPanel from 'primevue/overlaypanel';
import Button from 'primevue/button';
import MegaMenu from 'primevue/megamenu';

import AnimateOnScroll from 'primevue/animateonscroll';

//native UI
import {create, NButton, NCarousel, NEllipsis, NList, NListItem, NScrollbar, NSpace, NTag, NThing} from 'naive-ui'

export default boot(({app}) => {
  app.use(ElPopover)
  app.use(ElAffix)
  app.use(ElButton)
  app.use(ElTag)
  app.use(ElBacktop)
  app.use(ElLink)
  app.component('CiliBackToTop', CiliBackToTop)
  app.component('NavBar', NavBar)
  app.use(PrimeVue);
  app.component('OverlayPanel', OverlayPanel);
  app.component('Button', Button);
  app.component('MegaMenu', MegaMenu);
  app.directive('animateonscroll', AnimateOnScroll);
  //native ui
  const naive = create({
    components: [NButton, NCarousel, NList, NListItem, NThing, NSpace, NTag, NEllipsis, NScrollbar]
  })
  app.use(naive)
})
export {ElAffix, ElButton, ElPopover, ElTag, ElBacktop}
