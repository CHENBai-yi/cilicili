<template>

  <transition appear
              enter-active-class="animated animate__fadeInLeft"
              leave-active-class="animated animate__fadeOutLeft">

    <div v-if="show" class="hui-couplets-ad hui-couplets-ad-l">
      <transition appear enter-active-class="animated animate__headShake">
        <div v-show="show2" class="dual_con">
          <n-carousel autoplay style="height:300px;width:100px">
            <a v-for="(item,index) in src" :key="index" :href="item.link">
              <img
                :src="item.content"
                class="carousel-img"
                style="object-fit: cover;"
              >
            </a>
          </n-carousel>
        </div>
        <!--        //images.h-ui.net/www/AD-100x300.gif-->
      </transition>
      <a class="hui-couplets-ad-close" href="#">X关闭</a>
    </div>
  </transition>
  <!--  <transition appear-->
  <!--              enter-active-class="animated animate__backInRight"-->
  <!--              leave-active-class="animated animate__backOutRight">-->
  <!--    <div class="hui-couplets-ad hui-couplets-ad-r">-->
  <!--      <div class="dual_con"><img height="300" src="//images.h-ui.net/www/AD-100x300.gif" width="100"></div>-->
  <!--      <a class="hui-couplets-ad-close" href="#">X关闭</a>-->
  <!--    </div>-->
  <!--  </transition>-->
</template>

<script setup>
import $ from 'jquery'
import {computed, onMounted, ref} from 'vue'
import {useStorageStore} from 'src/stores/storage'

const storageStore = useStorageStore();
const show = ref(true)
const show2 = ref(true)
const src = computed(() => storageStore.CiliAdvertisingImg)
onMounted(() => {
  const coupletsAd = $(".hui-couplets-ad");
  const coupletsClose = $("a.hui-couplets-ad-close");
  const screen_w = screen.width;
  if (screen_w > 1024) {
    coupletsAd.show()
  }
  $(window).scroll(function () {
    var scrollTop = $(window).scrollTop();
    coupletsAd.stop().animate({
      top: scrollTop + 260
    });
  });
  coupletsClose.click(function () {
    show.value = false
    return false
  });
  // setTimeout(() => {
  //   show.value = false
  // }, 10000);
  setInterval(() => {
    show2.value = !show2.value
    setTimeout(() => show2.value = !show2.value, 1)
  }, 4000)

})

</script>
<style lang="scss" scoped>
.hui-couplets-ad {
  top: 260px;
  position: absolute;
  width: 102px;
  overflow: hidden;
  display: none;
  z-index: 100;

  &.hui-couplets-ad-l {
    left: 6px;
  }

  &.hui-couplets-ad-r {
    right: 6px;
  }

  .hui-couplets-ad-content {
    border: #CCC solid 1px;
    width: 100px;
    height: 300px;
    overflow: hidden;
    background-color: #0C9;
  }

  .hui-couplets-ad-close {
    width: 100%;
    height: 24px;
    line-height: 24px;
    text-align: center;
    display: block;
    font-size: 13px;
    color: #555;
    text-decoration: none;
  }
}
</style>
