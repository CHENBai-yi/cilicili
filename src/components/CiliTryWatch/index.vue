<template>
  <q-dialog v-model="watch" v-close-popup class="z-max " persistent position="top">
    <q-card class="cili-try-watch">
      <!--      <n-watermark
              :font-size="16"
              :height="128"
              :line-height="16"
              :rotate="-15"
              :width="192"
              :x-offset="12"
              :y-offset="28"
              content="菠萝微课 独家课程"
              cross
              fullscreen
              selectable
            />-->
      <q-card-section class="row items-center q-pb-none">
        <div :class="$q.dark.isActive?'darkTheme':'text-white'" class="text-h2 video-title">课程预览</div>
        <q-space/>
        <q-btn v-close-popup :class="$q.dark.isActive?'darkTheme':'text-white'" dense flat icon="close" round/>
      </q-card-section>
      <q-card-section>
        <div v-if="dplayerObj.title" :class="$q.dark.isActive?'darkTheme':'text-white'"
             class="q-gutter-x-sm items-center flex row">
          <div class="q-gutter-x-sm items-center flex col-10">
            <q-icon name="bi-play-circle-fill"/>
            <span class="video-title title">{{ dplayerObj.title }}</span>
          </div>
          <div>
            <span class="video-title title">{{ dplayerObj.status }}</span>
          </div>
        </div>
      </q-card-section>
      <q-card-section>
        <n-watermark
          :font-size="16"
          :height="128"
          :line-height="16"
          :rotate="-10"
          :width="192"
          :x-offset="12"
          :y-offset="28"
          content="版权所属 菠萝微课"
          cross
          selectable
        >
          <cili-video-player
            ref="videoPlayer"
            :autoplay="true"
            :highlight="dplayerObj.highlight"
            :loop="false"
            :showShadow="false"
            :video="dplayerObj.video"
            height="330px"
            width="530px"
          />
          <div id="video_show" align="center" class="z-top" style=" display:none; position:absolute;top:0;">
            <span class="mask q-pt-xl text-white">

               <div v-if="list&&list.length>dplayerObj.index+1" class="q-gutter-y-lg">
                      <div>
                  <span>下一节：</span>
                  <span class="text-weight-bolder next">{{ (list[dplayerObj.index + 1])?.title }}</span>
                </div>
              <div>
                <q-btn color="red" label="下一节" @click.stop="switchSrc(list[dplayerObj.index+1])"/>
              </div>
               </div>
              <div v-else class="q-gutter-y-lg">
                 <div class="text-weight-bolder next">试看章节已结束，如果您喜欢本课程，请移步购买。</div>
              <div>
<!--                <q-btn color="red" label="立即购买" @click="()=>location.href='#/pay/pay.html'"/>-->
              </div>
              </div>
              <div class="q-mt-md">
                <n-button text @click.stop="flush">
             <template #icon>
              <q-icon name="replay"/>
          </template>
             重新观看
            </n-button>
              </div>

            </span>
            <!--     display:none;       <img alt="" src="public/pay/pc-fhy.gif" width="530" height="330">-->
            <p class="no-margin q-pt-xs"></p>
            <!--            #858585-->
            <p style="font-size: 12px;margin-bottom: 20px;color: white">该视频需要付费才可观看，如需继续观看，请点击下方购买按钮。如需完整课程请点击<a
              href="#/pay/pay.html" style="color: red">&nbsp;&nbsp;立即购买</a></p>
          </div>

        </n-watermark>

      </q-card-section>
      <q-card-section :class="$q.dark.isActive?'darkTheme':'text-white'"
                      class="q-gutter-y-sm q-px-md">
        <q-scroll-area
          :bar-style="barStyle"
          :thumb-style="thumbStyle"
          style="height: 150px;"
        >

          <n-button-group v-for="(item,index) in list"
                          class="flex column items-start
                          right-box q-mb-sm  q-pb-sm" vertical>
            <n-button :bordered="false" class="video-title title" focusable size="small" text
                      @click.stop="item.index=index;switchSrc(item)"
            >
              <n-ellipsis style="max-width: 500px">
                <span class="title text-weight-bolder ">
               {{ item.title }}</span>
              </n-ellipsis>
            </n-button>
          </n-button-group>
        </q-scroll-area>

      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup>
import {onUnmounted, onUpdated, reactive, ref} from 'vue'
import useTheme from "src/composables/useTheme"
import $ from 'jquery'
import Hls from "hls.js";
import CiliVideoPlayer from 'src/components/ciliVideoPlayer/CiliVideoPlayer.vue'

const props = defineProps({
  list: {
    type: Array,
    default: []
  },
  tryWatchTime: {
    type: Number,
    default: 10
  }
})
const dplayerObj = reactive({
  buy: undefined,
  title: '',
  href: '',
  status: '',
  index: '',
  video: {
    pic: "static/images/VCG41N1403887001.jpg",
    url: 'https://api.dogecloud.com/player/get.m3u8?vcode=5ac682e6f8231991&userId=17&ext=.m3u8', //视频地址
    type: 'customHls',
    customType: {
      customHls: function (video, player) {
        const hls = new Hls(); //实例化Hls  用于解析m3u8
        hls.loadSource(video.src);
        hls.attachMedia(video);
      }
    },
    quality: [
      {
        name: '高清',
        url: 'https://api.dogecloud.com/player/get.m3u8?vcode=5ac682e6f8231991&userId=17&ext=.m3u8',
        type: 'customHls',
      },
      {
        name: '标清',
        url: 'http://vjs.zencdn.net/v/oceans.mp4',
        type: 'normal',
      },
    ],
    defaultQuality: 1,
  },
  highlight: [
    {
      time: props.tryWatchTime,
      text: '这是第 10 秒',
      color: 'red'
    }
  ],
})
const {darkTheme} = useTheme()
const watch = ref(false)
const tryWatch = (list) => {
  let assign = Object.assign({}, list);
  assign.title = list.tag + ' ' + list.title
  switchSrc(assign)
  watch.value = true
}
const videoPlayer = ref(null)
defineExpose({tryWatch, videoPlayer})
const flush = () => {
  $('#video_show').hide();
  if (videoPlayer.value) {
    videoPlayer.value.render()
    let video = videoPlayer.value.dp.video
    video.addEventListener("timeupdate", function () {
      //用秒数来显示当前播放进度
      var timeDisplay = Math.floor(video.currentTime);
      //以秒为单位  2*60  两分钟
      if (timeDisplay > props.tryWatchTime) {
        //视频暂停操作
        video.pause()
        //手机端视频全屏展示关闭
        // this.webkitExitFullScreen();
        //去除视频地址src内容
        video.setAttribute('src', '');
        //将视频隐藏掉
        video.style.display = "none";
        //提示层显示
        $('#video_show').show();
      }
    }, false)
  }
}
const switchSrc = (item) => {
  dplayerObj.title = item.title
  dplayerObj.href = item.href
  dplayerObj.index = item.index
  dplayerObj.status = '正在播放'
  dplayerObj.video.pic = item.href
  dplayerObj.video.url = item.href
  dplayerObj.video.quality[0].url = item.href
  dplayerObj.video.quality[1].url = item.href
  flush()
}
const thumbStyle = reactive({
  right: '4px',
  borderRadius: '5px',
  // backgroundColor: '#027be3',
  width: '5px',
  opacity: 0.5
})
const barStyle = reactive({
  right: '2px',
  borderRadius: '9px',
  // backgroundColor: '#027be3',
  width: '9px',
  opacity: 0.2
})
onUpdated(() => {
  flush()
})
onUnmounted(() => {
  if (videoPlayer.value) {

  }
})
const nextCourse = (index) => {
  if (props.list && props.list.length > index) {
    switchSrc(props.list[index])
  }
}
</script>

<style lang="scss" scoped>
.cili-try-watch {
  background-color: #545c63;
}

.video-title {
  font-size: 16px;
  line-height: 24px;
  font-weight: 700;

}

.title {
  font-size: 14px;
}

.right-box {
  border-bottom: 1px solid rgba(255, 255, 255, .1);
}

.n-button {
  color: rgba(255, 255, 255, .6) !important;
}

/*按钮悬浮*/
.n-button:hover {
  color: #36ad6a !important;
  font-weight: bold !important;

}

/*按钮点击*/
.n-button:focus {
  color: #36ad6a !important;
}

:deep(video) {
  object-fit: cover;
}

.mask {
  width: 530px;
  height: 330px;
  display: inline-block;
  background: #1d1f21;
  text-align: center;
  opacity: 1;
  padding-top: 15%;

  .next {
    font-size: 18px;
    letter-spacing: 2px;
  }
}
</style>
