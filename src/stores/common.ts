import {defineStore} from "pinia";
import {Cookies} from 'quasar';
import {postAction} from 'src/api/manage'
// @ts-ignore
import CiliVideoDetail_Json from "/public/mockData/CiliVideoDetail.json"

export const useCommonStore = defineStore('common', {
  state: () => ({
    pos: 0,
    activeIndex: 0,
    originPos: 0,
    videoInfo: {},
    videoCatalog: {},
    currentAddress: ''
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
    async GetVideoDetail(url: string, data: Object) {
      const res = await postAction(url, data)
      let video, videoList
      if (res.code === 1) {
        const {records}: any = res.data
        video = records.video
        videoList = records.video_list
        this.videoCatalog = records.catalog
      }

      return {video: video, videoList: videoList}
    },
    SetCurrentVideoAddress(url: string) {
      Cookies.set("cili-currentAddress", url)
      this.currentAddress = url
    },
    getCurrentVideoAddress(): string | null {
      if (!!this.currentAddress)
        return this.currentAddress;
      else if (Cookies.has("cili-currentAddress"))
        return Cookies.get("cili-currentAddress")
      else return ""
    },
    delCurrentVideoAddress() {
      Cookies.remove("cili-currentAddress")
      // @ts-ignore
      this.currentAddress = undefined
    },
  }
})
