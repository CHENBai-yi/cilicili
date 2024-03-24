<template>
  <div :style="{height:height+'px'}">
    <div ref="der" :class="darkTheme">
      <transition appear
                  enter-active-class="animated animate__fadeInDown"
                  leave-active-class="animated animate__fadeOutUp">
        <div v-if="!navBarShow">
          <q-img
            :src="CiliFrontendDefault.imageList.bannerImg"
            class="rounded-borders absolute-full "
            height="150px"
            img-class="my-custom-image" placeholder-src="https://cdn.quasar.dev/img/parallax2.jpg"
            spinner-color="white"
          />
          <cili-tool-bar/>
        </div>
      </transition>
    </div>
    <!-- place QPageSticky at end of page -->
    <transition appear
                enter-active-class="animated animate__fadeInDown"
                leave-active-class="animated animate__fadeOutUp"
    >
      <q-page-sticky v-if="navBarShow" :class="darkTheme" class="relative-position z-top" expand position="top">
        <cili-tool-bar class="shadow-24"/>

        <n-scrollbar class="col q-pl-md" x-scrollable>
          <div class="scrollbar-flex-content ">
            <span v-for="(item,index) in catagroy" :key="item" class="scrollbar-demo-item">
                      {{ item.category_name }}
            </span>
          </div>
        </n-scrollbar>
        <div class="relative-position q-mr-md">
          <q-btn :icon="open?'expand_less':'expand_more'" :ripple="false" color="#19181c" fab-mini flat push>
            <q-popup-proxy breakpoint="none"
                           @update:model-value="open=!open">
              <cili-select-bar/>
            </q-popup-proxy>
          </q-btn>
        </div>
      </q-page-sticky>
    </transition>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import CiliToolBar from './CiliToolBar/CiliToolBar.vue'
import CiliSelectBar from 'src/components/ciliSelectBar/ciliSelectBar.vue'
import useTheme from "src/composables/useTheme"
import {CiliFrontendDefault} from 'src/config/default'
import {getAction} from 'src/api/manage'

const urls = {
  list: 'category'
}
const catagroy = ref([])
const {darkTheme} = useTheme()
const der = ref(null)
const height = ref(152)
const change = ref(0)
const navBarShow = ref(false)
onMounted(async () => {
  const options = {
    root: null,
    rootMargin: '0px',
    threshold: 0.5  // 交叉区域比例
  };
  const observer = new IntersectionObserver(handleIntersection, options);
  observer.observe(der.value);
  const res = await getAction(urls.list)
  console.log(res)
  if (res && res.code === 200) {
    catagroy.value = res.data
  }
})
const handleIntersection = (entries) => {
  entries.forEach(entry => {
    console.log(entry)
    if (entry.isIntersecting) {
      navBarShow.value = false
      height.value += change.value
      // 当元素进入视口时的操作
      console.log('Element is in viewport!');
    } else {
      // 当元素离开视口时的操作
      console.log('Element is out of viewport!');
      navBarShow.value = true
      change.value = entry.boundingClientRect.top
      height.value -= change.value
    }
  });
}

const op = ref();
const toggle = (event) => {
  op.value.toggle(event);
}
const open = ref(false)
const bannerImage = ref({
  img1: 'https://cdn.quasar.dev/img/parallax2.jpg'
})

const items = ref([
  {

    items: [
      [
        {
          label: 'Living Room',
          items: [{label: 'Accessories'}, {label: 'Armchair'}, {label: 'Coffee Table'}, {label: 'Couch'}, {label: 'TV Stand'}]
        }
      ],
      [
        {
          label: 'Kitchen',
          items: [{label: 'Bar stool'}, {label: 'Chair'}, {label: 'Table'}]
        },
        {
          label: 'Bathroom',
          items: [{label: 'Accessories'}]
        }
      ],
      [
        {
          label: 'Bedroom',
          items: [{label: 'Bed'}, {label: 'Chaise lounge'}, {label: 'Cupboard'}, {label: 'Dresser'}, {label: 'Wardrobe'}]
        }
      ],
      [
        {
          label: 'Office',
          items: [{label: 'Bookcase'}, {label: 'Cabinet'}, {label: 'Chair'}, {label: 'Desk'}, {label: 'Executive Chair'}]
        }
      ]
    ]
  },
  {

    items: [
      [
        {
          label: 'Computer',
          items: [{label: 'Monitor'}, {label: 'Mouse'}, {label: 'Notebook'}, {label: 'Keyboard'}, {label: 'Printer'}, {label: 'Storage'}]
        }
      ],
      [
        {
          label: 'Home Theather',
          items: [{label: 'Projector'}, {label: 'Speakers'}, {label: 'TVs'}]
        }
      ],
      [
        {
          label: 'Gaming',
          items: [{label: 'Accessories'}, {label: 'Console'}, {label: 'PC'}, {label: 'Video Games'}]
        }
      ],
      [
        {
          label: 'Appliances',
          items: [{label: 'Coffee Machine'}, {label: 'Fridge'}, {label: 'Oven'}, {label: 'Vaccum Cleaner'}, {label: 'Washing Machine'}]
        }
      ]
    ]
  },
  {

    items: [
      [
        {
          label: 'Football',
          items: [{label: 'Kits'}, {label: 'Shoes'}, {label: 'Shorts'}, {label: 'Training'}]
        }
      ],
      [
        {
          label: 'Running',
          items: [{label: 'Accessories'}, {label: 'Shoes'}, {label: 'T-Shirts'}, {label: 'Shorts'}]
        }
      ],
      [
        {
          label: 'Swimming',
          items: [{label: 'Kickboard'}, {label: 'Nose Clip'}, {label: 'Swimsuits'}, {label: 'Paddles'}]
        }
      ],
      [
        {
          label: 'Tennis',
          items: [{label: 'Balls'}, {label: 'Rackets'}, {label: 'Shoes'}, {label: 'Training'}]
        }
      ]
    ]
  }
]);
</script>

<style lang="sass" scoped>


</style>
