<template>
  <q-infinite-scroll :offset="250" class="" @load="onLoad">
    <div :class="$q.screen.lt.sm?'column ':'row'">
      <div v-if="$q.screen.gt.sm" class="col-4">
        <div :class="$q.screen.lt.sm?'column q-mt-sm q-mx-sm':'row q-gutter-x-md q-ma-sm q-mb-md'">
          <CiliCarousel/>
        </div>
      </div>
      <div class="col">
        <div :class="$q.screen.lt.sm?'column q-ma-sm q-gutter-y-md':'row q-gutter-x-md q-mr-sm q-my-sm'">
          <div class="col order-first">
            <q-card bordered flat style="max-width: 500px">
              <div v-show="loaded" class="column">
                <div class="col">
                  <cili-video-player
                    :contextmenu="dplayerObj.contextmenu"
                    :highlight="dplayerObj.highlight"
                    :hotkey="false"
                    :preventClickToggle='true'
                    :video="dplayerObj.video"
                    @isLoadCompleted="isLoadCompleted"
                  />
                </div>
                <div class="q-px-sm q-pt-xs">

                  <CiliLink color="#19181c" font="15px" href="http://www.cilicili.site/admin">
                    <strong>
                      <n-ellipsis class="bili-video-card__info--author" line-clamp="2">
                        {{ testData.test1.title }}
                      </n-ellipsis>
                    </strong>
                  </CiliLink>
                </div>
                <div class="q-px-sm q-pb-xs">
                  <CiliLink href="http://www.cilicili.site/admin">
                    <div class="q-gutter-x-sm flex">
                      <n-tag v-if='!!testData.test1.faver' :bordered="false" size="small" type="warning">
                        {{ testData.test1.faver }}
                      </n-tag>
                      <n-tag v-else :bordered="false" size="small">
                        <q-icon name="eva-person"/>
                      </n-tag>
                      <n-ellipsis :tooltip="false" class="bili-video-card__info--author" line-clamp="1">
                        <span> {{ testData.test1.author }} · {{ testData.test1.time }}</span>
                      </n-ellipsis>
                    </div>
                  </CiliLink>
                </div>
              </div>
              <div v-show="!loaded">
                <q-item>
                  <q-item-section avatar>
                    <q-skeleton type="QAvatar"/>
                  </q-item-section>

                  <q-item-section>
                    <q-item-label>
                      <q-skeleton type="text"/>
                    </q-item-label>
                    <q-item-label caption>
                      <q-skeleton type="text" width="80%"/>
                    </q-item-label>
                  </q-item-section>
                </q-item>
                <q-item>
                  <q-item-section avatar/>

                  <q-item-section class="q-pl-sm">
                    <q-skeleton class="q-mb-sm" height="150px"/>

                    <div class="row items-center justify-between no-wrap">
                      <div class="row items-center">
                        <q-icon class="q-mr-sm" color="grey-4" name="chat_bubble_outline" size="18px"/>
                        <q-skeleton type="text" width="30px"/>
                      </div>

                      <div class="row items-center">
                        <q-icon class="q-mr-sm" color="grey-4" name="repeat" size="18px"/>
                        <q-skeleton type="text" width="30px"/>
                      </div>

                      <div class="row items-center">
                        <q-icon class="q-mr-sm" color="grey-4" name="favorite_border" size="18px"/>
                        <q-skeleton type="text" width="30px"/>
                      </div>
                    </div>
                  </q-item-section>
                </q-item>
              </div>
            </q-card>
          </div>

          <div class="col order-none">
            <q-card bordered flat style="max-width: 500px">
              <q-item>
                <q-item-section avatar>
                  <q-skeleton type="QAvatar"/>
                </q-item-section>

                <q-item-section>
                  <q-item-label>
                    <q-skeleton type="text"/>
                  </q-item-label>
                  <q-item-label caption>
                    <q-skeleton type="text" width="80%"/>
                  </q-item-label>
                </q-item-section>
              </q-item>

              <q-item>
                <q-item-section avatar/>

                <q-item-section class="q-pl-sm">
                  <q-skeleton class="q-mb-sm" height="150px"/>

                  <div class="row items-center justify-between no-wrap">
                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="chat_bubble_outline" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>

                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="repeat" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>

                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="favorite_border" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>
                  </div>
                </q-item-section>
              </q-item>
            </q-card>
          </div>
          <div class="col order-none">
            <q-card bordered flat style="max-width: 500px">
              <q-item>
                <q-item-section avatar>
                  <q-skeleton type="QAvatar"/>
                </q-item-section>

                <q-item-section>
                  <q-item-label>
                    <q-skeleton type="text"/>
                  </q-item-label>
                  <q-item-label caption>
                    <q-skeleton type="text" width="80%"/>
                  </q-item-label>
                </q-item-section>
              </q-item>

              <q-item>
                <q-item-section avatar/>

                <q-item-section class="q-pl-sm">
                  <q-skeleton class="q-mb-sm" height="150px"/>

                  <div class="row items-center justify-between no-wrap">
                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="chat_bubble_outline" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>

                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="repeat" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>

                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="favorite_border" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>
                  </div>
                </q-item-section>
              </q-item>
            </q-card>
          </div>
        </div>
        <div :class="$q.screen.lt.sm?'column q-ma-sm q-gutter-y-md':'row q-gutter-x-md q-mr-sm q-my-md'">
          <div class="col order-none">
            <q-card bordered flat style="max-width: 500px">
              <q-item>
                <q-item-section avatar>
                  <q-skeleton type="QAvatar"/>
                </q-item-section>

                <q-item-section>
                  <q-item-label>
                    <q-skeleton type="text"/>
                  </q-item-label>
                  <q-item-label caption>
                    <q-skeleton type="text" width="80%"/>
                  </q-item-label>
                </q-item-section>
              </q-item>

              <q-item>
                <q-item-section avatar/>

                <q-item-section class="q-pl-sm">
                  <q-skeleton class="q-mb-sm" height="150px"/>

                  <div class="row items-center justify-between no-wrap">
                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="chat_bubble_outline" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>

                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="repeat" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>

                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="favorite_border" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>
                  </div>
                </q-item-section>
              </q-item>
            </q-card>
          </div>
          <div class="col order-last">
            <q-card bordered flat style="max-width: 500px">
              <q-item>
                <q-item-section avatar>
                  <q-skeleton type="QAvatar"/>
                </q-item-section>

                <q-item-section>
                  <q-item-label>
                    <q-skeleton type="text"/>
                  </q-item-label>
                  <q-item-label caption>
                    <q-skeleton type="text" width="80%"/>
                  </q-item-label>
                </q-item-section>
              </q-item>

              <q-item>
                <q-item-section avatar/>

                <q-item-section class="q-pl-sm">
                  <q-skeleton class="q-mb-sm" height="150px"/>

                  <div class="row items-center justify-between no-wrap">
                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="chat_bubble_outline" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>

                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="repeat" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>

                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="favorite_border" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>
                  </div>
                </q-item-section>
              </q-item>
            </q-card>
          </div>
          <div class="col order-first">
            <q-card bordered flat style="max-width: 500px">
              <q-item>
                <q-item-section avatar>
                  <q-skeleton type="QAvatar"/>
                </q-item-section>

                <q-item-section>
                  <q-item-label>
                    <q-skeleton type="text"/>
                  </q-item-label>
                  <q-item-label caption>
                    <q-skeleton type="text" width="80%"/>
                  </q-item-label>
                </q-item-section>
              </q-item>

              <q-item>
                <q-item-section avatar/>

                <q-item-section class="q-pl-sm">
                  <q-skeleton class="q-mb-sm" height="150px"/>

                  <div class="row items-center justify-between no-wrap">
                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="chat_bubble_outline" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>

                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="repeat" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>

                    <div class="row items-center">
                      <q-icon class="q-mr-sm" color="grey-4" name="favorite_border" size="18px"/>
                      <q-skeleton type="text" width="30px"/>
                    </div>
                  </div>
                </q-item-section>
              </q-item>
            </q-card>
          </div>
        </div>
      </div>
    </div>
    <div v-for="(item, index) in items" :key="index"
         :class="$q.screen.lt.sm?'column q-ma-sm q-gutter-y-md':'row q-gutter-x-md q-ma-xs q-mb-md'"
    >
      <div class="col order-none">
        <q-card bordered flat style="max-width: 500px">
          <q-item>
            <q-item-section avatar>
              <q-skeleton type="QAvatar"/>
            </q-item-section>

            <q-item-section>
              <q-item-label>
                <q-skeleton type="text"/>
              </q-item-label>
              <q-item-label caption>
                <q-skeleton type="text" width="80%"/>
              </q-item-label>
            </q-item-section>
          </q-item>

          <q-item>
            <q-item-section avatar/>

            <q-item-section class="q-pl-sm">
              <q-skeleton class="q-mb-sm" height="150px"/>

              <div class="row items-center justify-between no-wrap">
                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="chat_bubble_outline" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>

                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="repeat" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>

                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="favorite_border" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>
              </div>
            </q-item-section>
          </q-item>
        </q-card>
      </div>
      <div class="col order-last">
        <q-card bordered flat style="max-width: 500px">
          <q-item>
            <q-item-section avatar>
              <q-skeleton type="QAvatar"/>
            </q-item-section>

            <q-item-section>
              <q-item-label>
                <q-skeleton type="text"/>
              </q-item-label>
              <q-item-label caption>
                <q-skeleton type="text" width="80%"/>
              </q-item-label>
            </q-item-section>
          </q-item>

          <q-item>
            <q-item-section avatar/>

            <q-item-section class="q-pl-sm">
              <q-skeleton class="q-mb-sm" height="150px"/>

              <div class="row items-center justify-between no-wrap">
                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="chat_bubble_outline" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>

                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="repeat" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>

                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="favorite_border" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>
              </div>
            </q-item-section>
          </q-item>
        </q-card>
      </div>
      <div class="col order-first">
        <q-card bordered flat style="max-width: 500px">
          <q-item>
            <q-item-section avatar>
              <q-skeleton type="QAvatar"/>
            </q-item-section>

            <q-item-section>
              <q-item-label>
                <q-skeleton type="text"/>
              </q-item-label>
              <q-item-label caption>
                <q-skeleton type="text" width="80%"/>
              </q-item-label>
            </q-item-section>
          </q-item>

          <q-item>
            <q-item-section avatar/>

            <q-item-section class="q-pl-sm">
              <q-skeleton class="q-mb-sm" height="150px"/>

              <div class="row items-center justify-between no-wrap">
                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="chat_bubble_outline" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>

                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="repeat" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>

                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="favorite_border" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>
              </div>
            </q-item-section>
          </q-item>
        </q-card>
      </div>
      <div class="col order-first">
        <q-card bordered flat style="max-width: 500px">
          <q-item>
            <q-item-section avatar>
              <q-skeleton type="QAvatar"/>
            </q-item-section>

            <q-item-section>
              <q-item-label>
                <q-skeleton type="text"/>
              </q-item-label>
              <q-item-label caption>
                <q-skeleton type="text" width="80%"/>
              </q-item-label>
            </q-item-section>
          </q-item>

          <q-item>
            <q-item-section avatar/>

            <q-item-section class="q-pl-sm">
              <q-skeleton class="q-mb-sm" height="150px"/>

              <div class="row items-center justify-between no-wrap">
                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="chat_bubble_outline" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>

                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="repeat" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>

                <div class="row items-center">
                  <q-icon class="q-mr-sm" color="grey-4" name="favorite_border" size="18px"/>
                  <q-skeleton type="text" width="30px"/>
                </div>
              </div>
            </q-item-section>
          </q-item>
        </q-card>
      </div>
    </div>
    <template v-slot:loading>
      <div class="row justify-center q-my-md">
        <q-spinner-dots color="primary" size="40px"/>
      </div>
    </template>
  </q-infinite-scroll>
</template>

<script setup>
import {reactive, ref} from 'vue'
import Hls from 'hls.js';
import CiliVideoPlayer from 'src/components/ciliVideoPlayer/CiliVideoPlayer.vue'
import CiliLink from 'src/components/ciliLink/CiliLink.vue'
import CiliCarousel from 'src/components/CiliCarousel/CiliCarousel.vue'

const items = ref([{}])
const onLoad = (index, done) => {
  setTimeout(() => {
    items.value.push({})
    done()
  }, 2000)
}
const testData = reactive({
  test1: {
    title: "【职场经验】无解问题、站队问题、工作生活边界问题",
    faver: '已关注',
    author: '崩溃大喵',
    time: '2-2'
  }
})
const loaded = ref(false)
const dplayerObj = reactive({
  video: {
    url: 'https://api.dogecloud.com/player/get.m3u8?vcode=5ac682e6f8231991&userId=17&ext=.m3u8', //视频地址
    type: 'customHls',
    customType: {
      customHls: function (video, player) {
        const hls = new Hls(); //实例化Hls  用于解析m3u8
        hls.loadSource(video.src);
        hls.attachMedia(video);
      }
    }
  },
  danmaku: {
    id: '9E2E3368B56CDBB4',
    api: 'https://api.prprpr.me/dplayer/',
    token: 'eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDczMTAwNjQsImlhdCI6MTcwNzA1MDgwNCwicGx0IjotMX0.7HlHWIzzKZFtR3DmoabYz1wDT4iWIfug2Zc9cFBccAc',
    maximum: 1000,
    addition: ['https://api.prprpr.me/dplayer/v3/bilibili?aid=4157142'],
    user: 'DIYgod',
    bottom: '15%',
    unlimited: true,
    speedRate: 0.5,
  },
  contextmenu: [
    {
      text: 'custom1',
      link: 'https://github.com/DIYgod/DPlayer',
    },
    {
      text: 'custom2',
      click: (player) => {
        console.log(player);
      },
    },
  ],
  highlight: [
    {
      time: 20,
      text: '这是第 20 秒',
    },
    {
      time: 120,
      text: '这是 2 分钟',
    },
  ],
})
const isLoadCompleted = (isLoadCompleted) => {
  console.log(isLoadCompleted)
  loaded.value = isLoadCompleted
}
</script>

<style lang="scss" scoped>
::v-deep .dplayer-menu,
.dplayer-mask {
  display: none !important;
}

::v-deep .dplayer-controller {
  display: none;
}

::v-deep .dplayer-controller-mask {
  display: none;
}


</style>
