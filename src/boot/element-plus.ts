import {boot} from 'quasar/wrappers'
import {ElAffix, ElBacktop, ElButton, ElPopover, ElScrollbar, ElTag} from 'element-plus'

import NavBar from 'src/components/navBar/index.vue'
import CiliBackToTop from 'src/components/ciliBackToTop/index.vue'
//in main.js
import PrimeVue from 'primevue/config';

import OverlayPanel from 'primevue/overlaypanel';
import Button from 'primevue/button';
import MegaMenu from 'primevue/megamenu';

import AnimateOnScroll from 'primevue/animateonscroll';


export default boot(({app}) => {
  app.use(ElPopover)
  app.use(ElAffix)
  app.use(ElButton)
  app.use(ElTag)
  app.use(ElBacktop)
  app.use(ElScrollbar)
  app.component('CiliBackToTop', CiliBackToTop)
  app.component('NavBar', NavBar)
  app.use(PrimeVue);
  app.component('OverlayPanel', OverlayPanel);
  app.component('Button', Button);
  app.component('MegaMenu', MegaMenu);
  app.directive('animateonscroll', AnimateOnScroll);

})
export {ElAffix, ElButton, ElPopover, ElTag, ElBacktop}
