import {defineStore} from "pinia";
import {LocalStorage} from 'quasar';

export const useCommonStore = defineStore('common', {
  state: () => ({
    pos: '',
    activeIndex: 0,
    originPos: ''
  }),
  getters: {},
  actions: {
    SetPos(pos: any) {
      this.pos = pos
    },
    GetPos() {
      return this.pos
    },
    SetActiveIndex(activeIndex: number) {
      this.activeIndex = activeIndex
      LocalStorage.set('cili-activeIndex', activeIndex)
    },
    GetActiveIndex(): number | null {
      if (LocalStorage.has('cili-activeIndex')) {
        return LocalStorage.getItem('cili-activeIndex')
      }
      return this.activeIndex
    }
  }
})
