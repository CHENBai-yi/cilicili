<template>
  <div ref="videoRef" :style="{height:height,width:'100%'}" class='rounded-borders'
       @mouseenter="showMask?$emit('handlePlay',handlePlay):''"
       @mouseleave="!showMask?$emit('handlePause',handlePause):''"></div>
  <transition appear
              enter-active-class="animated animate__fadeIn"
              leave-active-class="animated animate__fadeOut"
  >
    <div v-if="showMask" class="relative-position">
      <div class="row  full-width no-wrap mask">
        <div class="col-11 flex justify-start q-gutter-sm ">
          <div class="flex items-center">
            <q-icon name="fab fa-youtube"/>
            <div class="q-pl-xs"> {{ testData.view }}</div>
          </div>
          <div class="flex items-center">
            <q-icon name="subtitles"/>

            <div class="q-pl-xs"> {{ testData.dm }}</div>
          </div>
        </div>
        <div class="col">
          {{ testData.time }}
        </div>
      </div>
    </div>
  </transition>
</template>

<script setup>
import DPlayer from 'dplayer'
import Hls from 'hls.js';
import {inject, onBeforeUnmount, onMounted, reactive, ref} from 'vue'
import v from 'src/css/variables.scss'

const props = defineProps({
  showShadow: {
    type: Boolean,
    default: true
  },
  view: {
    type: String,
    default: '1.3万'
  },
  dm: {
    type: Number,
    default: 113
  },
  time: {
    type: String,
    default: ''
  },
  height: {
    type: String,
    default: '100%'
  },
  // 是否自动播放
  autoplay: {
    type: Boolean,
    default: false
  },
  // 主题色
  theme: {
    type: String,
    default: v.DpTheme
  },
  // 视频是否循环播放
  loop: {
    type: Boolean,
    default: false
  },
  // 语言(可选值: 'en', 'zh-cn', 'zh-tw')
  lang: {
    type: String,
    default: 'zh-cn'
  },
  // 是否开启截图(如果开启，视频和视频封面需要允许跨域)
  screenshot: {
    type: Boolean,
    default: false
  },
  preventClickToggle: {
    type: Boolean,
    default: false
  },
  // 是否开启热键
  hotkey: {
    type: Boolean,
    default: true
  },
  // 视频是否预加载(可选值: 'none', 'metadata', 'auto')
  preload: {
    type: String,
    default: 'auto'
  },
  // 默认音量
  volume: {
    type: Number,
    default: 0.7
  },
  // 可选的播放速率，可以设置成自定义的数组
  playbackSpeed: {
    type: Array,
    default: [0.5, 0.75, 1, 1.25, 1.5, 2]
  },

  // 在左上角展示一个 logo，你可以通过 CSS 调整它的大小和位置
  logo: {
    type: String,
    default: ''
  },
  // 视频信息
  video: {
    type: Object,
    default: {
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
  },
  // 外挂字幕
  subtitle: {
    type: Object,
    default: {
      type: 'webvtt',
      fontSize: '25px',
      bottom: '10%',
      color: v.DpSubtitle,
    }
  },
  // 显示弹幕
  danmaku: {
    type: Object
  },
  // 自定义右键菜单
  contextmenu: {
    type: Array,
    default: []
  },
  // 自定义进度条提示点
  highlight: {
    type: Array,
    default: []
  },
  // 阻止多个播放器同时播放，当前播放器播放时暂停其他播放器
  mutex: {
    type: Boolean,
    default: true
  }
})
const videoRef = ref()
const state = reactive({
  instance: null
})
const dp = ref({})
const testData = reactive({
  view: props.view,
  dm: props.dm,
  time: props.time
})
const showMask = ref(props.showShadow)
const handlePlay = () => {
  showMask.value = false
  const audioDom = state.instance.video
  audioDom.muted = "true"
  audioDom && audioDom.click() // 【主要代码 - 解决报错】先模拟与页面进行交互，防止报错
  audioDom && audioDom.play() // 播放音频
}
const handlePause = () => {
  const audioDom = state.instance.video
  audioDom.src = props.video.url
  audioDom.pause()
  showMask.value = true
}


const emit = defineEmits(['isLoadCompleted'])
const timeToMinute = inject('timeToMinute')
const render = () => {
  let player = {
    container: videoRef.value,
    autoplay: props.autoplay,
    theme: props.theme,
    loop: props.loop,
    lang: props.lang,
    screenshot: props.screenshot,
    hotkey: props.hotkey,
    preload: props.preload,
    volume: props.volume,
    playbackSpeed: props.playbackSpeed,
    logo: props.logo,
    video: props.video,
    contextmenu: props.contextmenu,
    highlight: props.highlight,
    mutex: props.mutex,
    preventClickToggle: props.preventClickToggle
  }
  if (props.subtitle.url) {
    player.subtitle = props.subtitle
  }
  if (props.danmaku) {
    player.danmaku = props.danmaku
  }
  state.instance = new DPlayer(player)
  dp.value = state.instance
  state.instance.video.addEventListener('durationchange', loadShowMast);
}
onMounted(() => {
  if (props.showShadow)
    render()
})


const loadShowMast = (e) => {
  var timers = Math.ceil(state.instance.video.duration); //视频总时长
  testData.time = timeToMinute(timers);
  emit('isLoadCompleted', true)
}

// 销毁
onBeforeUnmount(() => {
  state.instance.video.removeEventListener('canplaythrough', loadShowMast);
  state.instance.destroy()
})

defineExpose({
  handlePlay, handlePause, dp, render
})
</script>

<style lang='scss' scoped>
.rounded-borders {
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.mask {
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 2;
  box-sizing: border-box;
  padding: 16px 8px 6px;
  width: 100%;
  height: 38px;
  background-image: linear-gradient(180deg, rgba(0, 0, 0, 0) 0%, rgba(0, 0, 0, .8) 100%);
  color: #fff;
  font-size: $subtitle-font-size;
  line-height: $icon-size;
  opacity: 1;
  display: -webkit-flex;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
