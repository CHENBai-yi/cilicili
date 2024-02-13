import {defineStore} from "pinia";
import {Cookies} from 'quasar';
// @ts-ignore
import CiliVideoDetail_Json from "/public/mockData/CiliVideoDetail.json"

export const useCommonStore = defineStore('common', {
  state: () => ({
    pos: 0,
    activeIndex: 0,
    originPos: 0,
    videoInfo: {},
    videoCatalog: {},
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
    },
    GetVideoDetail(url: string) {
      // postAction(url)
      this.videoInfo = CiliVideoDetail_Json.data.info
      this.videoCatalog = CiliVideoDetail_Json.data.catalog
      return {video: CiliVideoDetail_Json.data.video, videoList: CiliVideoDetail_Json.data.videoList}
    }
  }
})
