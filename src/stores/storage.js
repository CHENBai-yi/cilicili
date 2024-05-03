import {defineStore} from 'pinia';
import {LocalStorage} from 'quasar';
import {postAction} from 'src/api/manage';
import {ArrayToTree} from 'src/utils/arrayAndTree'
import {CiliBackendDefault, CiliFrontendDefault} from "src/config/default"
import {RealUrl} from 'src/utils/convert'

export const useStorageStore = defineStore('storage', {
  state: () => ({
    CiliDict: undefined,
    CiliBackend: undefined,
    CiliFrontend: undefined,
    goVersion: undefined,
    ginVersion: undefined,
    pluginList: undefined,
    CiliCarousel: undefined,
    CiliAdvertising: undefined,
    CiliAdvertisingText: '',
    CiliAdvertisingImg: undefined,
    source: undefined
  }),
  getters: {},
  actions: {
    async SetCiliDict() {
      const res = await postAction('public/get-dict-all')
      if (res.code === 1) {
        const dictDetail = res.data.records
        for (let i of dictDetail) {
          i.value = i.dict_code
          i.label = i.dict_label
        }
        const dictList = ArrayToTree(dictDetail, "dict_code", "parent_code")
        let dict = {}
        for (let d of dictList) {
          dict[d.dict_code] = d.children
        }
        this.CiliDict = dict
        LocalStorage.set('cili-dict', dict)
      }
    },


    async SetCiliBackend() {
      const res = await postAction('public/get-config-backend-all')
      if (res.code === 1) {
        const backend = {}
        res.data.records.forEach(item => {
          backend[item.config_item] = item.item_custom ? item.item_custom : item.item_default
        })
        this.CiliBackend = backend
        LocalStorage.set('cili-backend', backend)
      }
    },

    async SetCiliFrontend() {
      const res = await postAction('public/get-config-frontend-all')
      if (res.code === 1) {
        const frontend = {}
        res.data.records.forEach(item => {
          frontend[item.config_item] = item.item_custom ? item.item_custom : item.item_default
        })
        this.CiliFrontend = frontend
        LocalStorage.set('cili-frontend', frontend)
      }
    },
    async SetCiliCarousel() {
      const res = await postAction('public/get-carousel-list')
      if (res.code === 1) {
        this.CiliCarousel = res.data.records
        this.CiliCarousel = this.CiliCarousel.map(item => {
          item.img = RealUrl(item.img)
          return item;
        })
        LocalStorage.set('cili-carousel', this.CiliCarousel)
      }
    },
    async SetCiliAdvertising() {
      const res = await postAction('public/get-adver-list')
      if (res.code === 1) {
        let arr = res.data.records
        if (arr) {
          let temp = arr.filter(item => 'contentType_text' === item.type)
          temp = temp.map(item => item.content)
          let str = '\t';
          temp.forEach(item => this.CiliAdvertisingText += item + str)
          LocalStorage.set('cili-advertising-text', this.CiliAdvertisingText)
          this.CiliAdvertisingImg = arr.filter(item => 'contentType_image' === item.type)
          this.CiliAdvertisingImg = this.CiliAdvertisingImg.map(item => {
            item.content = RealUrl(item.content)
            return item;
          })
          LocalStorage.set('cili-advertising-img', this.CiliAdvertisingImg)
        }
      }
    },
    SetCiliGoVersion(goVersion) {
      this.goVersion = goVersion
      LocalStorage.set('cili-goVersion', goVersion)
    },
    SetCiliGinVersion(ginVersion) {
      this.ginVersion = ginVersion
      LocalStorage.set('cili-ginVersion', ginVersion)
    },

    SetCiliPluginList(pluginList) {
      this.pluginList = pluginList
      LocalStorage.set('cili-pluginList', pluginList)
    },
    GetCiliAdvertisingText() {
      const advertising = LocalStorage.getItem("cili-advertising-text")
      if (advertising) {
        return advertising
      } else {
        return this.CiliAdvertisingText
      }
    },
    DelCiliAdvertisingText() {
      LocalStorage.remove("cili-advertising-text")
      this.CiliAdvertisingText = '';
    },
    DelCiliAdvertisingImg() {
      LocalStorage.removeItem("cili-advertising-img")
      this.CiliAdvertisingImg = undefined;
    },
    GetCiliAdvertisingImg() {
      const advertising = LocalStorage.getItem("cili-advertising-img")
      if (advertising) {
        return advertising
      } else {
        return this.CiliAdvertisingImg
      }
    },
    GetCiliCarousel() {
      const carousel = LocalStorage.getItem("cili-carousel")
      if (this.CiliCarousel) {
        return this.CiliCarousel
      } else {
        return carousel
      }
    },
    GetCiliDict() {
      const dict = LocalStorage.getItem("cili-dict")
      if (this.CiliDict) {
        return this.CiliDict
      } else {
        return dict
      }
    },
    GetCiliBackend() {
      const backend = LocalStorage.getItem("cili-backend")
      if (this.CiliBackend) {
        return this.CiliBackend
      } else if (backend) {
        return backend
      } else {
        return CiliBackendDefault
      }
    },
    GetCiliFrontend() {
      const frontend = LocalStorage.getItem("cili-frontend")
      if (this.CiliFrontend) {
        return this.CiliFrontend
      } else if (frontend) {
        return frontend
      } else {
        return CiliFrontendDefault
      }
    },
    GetCiliGoVersion() {
      const goversion = LocalStorage.getItem("cili-goVersion")
      if (this.goVersion) {
        return this.goVersion
      } else {
        return goversion
      }
    },
    GetCiliGinVersion() {
      const ginversion = LocalStorage.getItem("cili-ginVersion")
      if (this.ginVersion) {
        return this.ginVersion
      } else {
        return ginversion
      }
    },
    GetCiliPluginList() {
      const pluginList = LocalStorage.getItem("cili-pluginList")
      if (this.pluginList) {
        return this.pluginList
      } else {
        return pluginList
      }
    },

  },
});
