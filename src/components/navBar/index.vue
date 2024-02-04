<template>
  <div ref="der" :class="darkTheme">
    <transition appear
                enter-active-class="animated animate__fadeInDown"
                leave-active-class="animated animate__fadeOutUp">
      <div v-if="!navBarShow">
        <q-img
          class="rounded-borders absolute-full "
          height="150px"
          img-class="my-custom-image"
          spinner-color="white" src="https://cdn.quasar.dev/img/parallax2.jpg"
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
    <q-page-sticky v-if="navBarShow" :class="darkTheme" expand position="top">
      <cili-tool-bar class="shadow-24"/>

      <el-scrollbar class="q-pl-md col">
        <div class="scrollbar-flex-content ">
          <span v-for="item in 50" :key="item" class="scrollbar-demo-item">
            标题{{ item }}
          </span>

        </div>

      </el-scrollbar>
      <div class="relative-position">
        <q-btn :icon="open?'expand_less':'expand_more'" :ripple="false" color="primary" fab-mini flat push>
          <q-popup-proxy breakpoint="none"
                         @update:model-value="open=!open">
            <div class="  p-megamenu  p-component card ">
              <div class="row ">
                <div v-for="item in 100" class="p-megamenu-col-3 ">
                  <ul class="p-submenu-list p-megamenu-submenu"
                  >
                    <li class="p-megamenu-submenu-header p-submenu-header"
                    >Home Theather
                    </li>
                    <li

                    >
                      <div class="p-menuitem-content"><a class="p-menuitem-link"


                      ><span
                        class="p-menuitem-text">Projector</span><span

                        class="p-ink"></span></a></div>
                    </li>

                    <li

                    >
                      <div class="p-menuitem-content"><a class="p-menuitem-link"


                      ><span
                        class="p-menuitem-text">Speakers</span><span

                        class="p-ink"></span></a></div>
                    </li>

                    <li

                    >
                      <div class="p-menuitem-content"><a class="p-menuitem-link"


                      ><span
                        class="p-menuitem-text">TVs</span><span

                        class="p-ink"></span></a></div>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </q-popup-proxy>
        </q-btn>
      </div>
    </q-page-sticky>
  </transition>

</template>

<script setup>
import {onMounted, ref} from 'vue'
import CiliToolBar from './CiliToolBar/CiliToolBar.vue'
import useTheme from "src/composables/useTheme"


const {darkTheme} = useTheme()
const der = ref(null)
const navBarShow = ref(false)
onMounted(() => {
  const options = {
    root: null,
    rootMargin: '0px',
    threshold: 0.5  // 交叉区域比例
  };
  const observer = new IntersectionObserver(handleIntersection, options);
  observer.observe(der.value);
})
const handleIntersection = (entries) => {
  entries.forEach(entry => {
    console.log(entry)
    if (entry.isIntersecting) {
      navBarShow.value = false
      // 当元素进入视口时的操作
      console.log('Element is in viewport!');
    } else {
      // 当元素离开视口时的操作
      console.log('Element is out of viewport!');
      navBarShow.value = true
    }
  });
}

const op = ref();
const toggle = (event) => {
  op.value.toggle(event);
}
const open = ref(false)


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
@import "src/css/quasar.variables.scss"
.YL

  &__toolbar-input-container
    min-width: 100px
    width: 55%

    &:hover
      color: #000

.upload-item
  path
    fill: $text2

ul
  list-style: none

</style>
