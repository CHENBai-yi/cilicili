<template>
  <q-dialog v-model="watch" v-close-popup class="z-max " persistent position="top">
    <q-card class="cili-try-watch">
      <q-card-section class="row items-center q-pb-none">
        <div :class="$q.dark.isActive?'darkTheme':'text-white'" class="text-h2 video-title">课程预览</div>
        <q-space/>
        <q-btn v-close-popup :class="$q.dark.isActive?'darkTheme':'text-white'" dense flat icon="close" round/>
      </q-card-section>
      <q-card-section>
        <div :class="$q.dark.isActive?'darkTheme':'text-white'" class="q-gutter-x-sm items-center flex row">
          <div class="q-gutter-x-sm items-center flex col-10">
            <q-icon name="bi-play-circle-fill"/>
            <span class="video-title title">1-1 课程导学</span>
          </div>
          <div>
            <span class="video-title title">正在播放</span>
          </div>
        </div>
      </q-card-section>
      <q-card-section>
        <q-video
          :ratio="16/9"
          :src="computedSrc"
          style="width:500px"
        />
      </q-card-section>
      <q-card-section :class="$q.dark.isActive?'darkTheme':'text-white'"
                      class="q-gutter-y-sm q-px-md">
        <q-scroll-area
          :bar-style="barStyle"
          :thumb-style="thumbStyle"
          style="height: 150px;"
        >
          <div v-for="(item,index) in tryWatchList.list" class="video-title title right-box q-mb-sm  q-pb-sm">
            <CiliLink :weight=600 color=""
                      @click.stop="switchSrc(item.href)">
              {{ item.title }}
            </CiliLink>
          </div>
        </q-scroll-area>

      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup>
import {reactive, ref} from 'vue'
import useTheme from "src/composables/useTheme"


const {darkTheme} = useTheme()
const watch = ref(false)
const tryWatch = () => {
  watch.value = true
}
const computedSrc = ref('http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4')
defineExpose({tryWatch})
const tryWatchList = reactive({
  list: [
    {
      title: "1-1 课程导学",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    }, {
      title: "1-1 课程导学",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    }, {
      title: "1-1 课程导学",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    }, {
      title: "1-1 课程导学",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    }, {
      title: "1-1 课程导学",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    }, {
      title: "1-1 课程导学",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    }, {
      title: "1-1 课程导学",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    }, {
      title: "1-1 课程导学",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    }, {
      title: "1-1 课程导学",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "1-2 学习方法介绍",
      href: 'http://127.0.0.1:8889/video/02.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    },
    {
      title: "7-1 项目效果展示",
      href: '.第二节-为什么InnoDb引擎表必须有主键，并且推荐使用整型的自增方式？.mp4'
    }
  ]
})
const switchSrc = (src) => {
  computedSrc.value = src
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
</style>
