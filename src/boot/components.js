import CiliAvatar from 'src/components/common/CiliAvatar/index.vue'
import CiliFormTop from 'src/components/common/CiliFormTop/index.vue'
import CiliTreeTd from 'src/components/common/CiliTreeTd/index.vue'
import CiliVersionMenu from 'src/components/common/CiliVersionMenu/index.vue'

// we globally register our component with Vue
export default ({app}) => {
  app.component('Cili-avatar', CiliAvatar)
  app.component('Cili-form-top', CiliFormTop)
  app.component('Cili-tree-td', CiliTreeTd)
  app.component('Cili-version-menu', CiliVersionMenu)
}
