import {defineStore} from "pinia";
import {Cookies} from 'quasar';

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
      Cookies.set('cili-activeIndex', String(activeIndex))
    },
    GetActiveIndex(): number | null {
      if (Cookies.has('cili-activeIndex')) {
        return Number.parseInt(Cookies.get('cili-activeIndex'))
      }
      return this.activeIndex
    }
  }
})
