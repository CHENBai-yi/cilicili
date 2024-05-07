<template>
  <q-page :class="$q.dark.isActive?`nice-class-out ${darkTheme}`:'nice-class-out'"
          :style="$q.screen.lt.sm?{paddingTop: '70px'}:{paddingTop: '215px'}" style="overflow: hidden;">
    <div class="row">
      <div class="nice-class col-10 ">
        <ul v-if="!showNoData&&!!courses.courses&&courses.courses.length>0"
            class="nice-class-list  clearfix q-gutter-y-md flex wrap justify-start">
          <li v-for="(item,index) in courses.courses" :key="item.title" class="ali clearfix q-pb-md q-mr-md">
            <a :href="'#/video/'+item.title+'/'+item.id+'/info'">
              <img :src=item.pic>
            </a>
            <a class='a-tittile'>
              <n-ellipsis :line-clamp="2" :tooltip="false">
                <a :href="'#/video/'+item.title+'/'+item.id+'/info'" class='a-tittile'>{{ item.title }}</a>
              </n-ellipsis>
            </a>
            <p :class="$q.dark.isActive?`${darkTheme}`:''">
              {{ item.subtitle }}
            </p>
            <div class="li-bottom row flex items-center "><!---->
              <div class="li-vip " style="font-size: 12px; color: rgb(255, 129, 9);">
                <!--              ￥99.99-->
                {{ item.price }}
              </div>
              <div class="col">

<pre>
     <q-icon :class="$q.dark.isActive?`${darkTheme}`:''" name="bi-eye" style="float:none">&nbsp;{{ item.view }}</q-icon>
</pre>
              </div>
              <span class="stars col-3">
              <q-icon v-for="(star,index) in item.stars" :key="star"
                      name="bi-star-fill" style="font-size: 12px; color: rgb(255, 129, 9);"/>
              <q-icon v-for="(star,index) in (5-item.stars)" :key="star"
                      name="bi-star" style="font-size: 12px; color: rgb(255, 129, 9);"/>
            </span>
            </div>
          </li>
        </ul>
        <n-result v-if="showNoData" description="色即是空，空即是色" status="418" title="什么也没有"/>
      </div>
    </div>
    <!-- place QPageSticky at end of page -->
    <transition appear
                enter-active-class="animated animate__fadeIn"
    >
      <q-page-sticky v-if="$q.screen.gt.sm" :class="darkTheme" expand position="top">
        <n-space class="full-width justify-start q-pl-lg q-gutter-x-xl" vertical>
          <div class="flex justify-start row  q-ml-xl q-pt-sm">
            <h2 class="selectTitle no-margin q-pl-xl">{{ $t('Subject') }}:</h2>
            <div class="col-10 flex justify-start  items-center q-gutter-xs q-pa-md ">
              <n-tag class="selectTitle" round type="error" @click="queryParam.subject='';refresh()">
                {{ $t('All') }}
              </n-tag>
              <n-button v-for="(item,index) in courses.subject" :key="index" class="text-h6" quaternary
                        style="font-size:16px;font-weight:600"
                        @click="queryParam.subject=item;refresh()">
                {{ item }}
              </n-button>
            </div>
          </div>
          <n-divider class="no-margin"/>
          <div class="flex justify-start row  q-ml-xl q-pt-sm" @mouseenter="show=true"
               @mouseleave="show=false">
            <h2 class="selectTitle no-margin q-pl-xl">{{ $t('Kind') }}:</h2>
            <div class="col-10 flex justify-start  items-center q-gutter-xs q-pa-md ">
              <n-tag class="selectTitle subTitle" round type="error" @click="queryParam.kind='';refresh()">
                {{ $t('All') }}
              </n-tag>
              <n-button v-for="(item,index) in courses.classify" :key="index" class="text-h6" quaternary
                        style="font-size:18px;font-weight:500"
                        @click="queryParam.kind=item;refresh()">
                {{ item }}
              </n-button>
              <n-collapse-transition :show="show" class="q-gutter-md" style="margin-left:0;margin-top:0">
                <CiliLink v-for="(item,index) in courses.classify_more" :weight=500 color="" size="16px">
                  {{ item }}
                </CiliLink>

              </n-collapse-transition>
            </div>
          </div>
        </n-space>
      </q-page-sticky>

      <q-page-sticky v-else :class="darkTheme" class="relative-position z-top" expand position="top">
        <n-scrollbar class="col q-pl-md" x-scrollable>
          <div class="scrollbar-flex-content">
            <span v-for="(item,index) in courses.subject" :key="item" class="scrollbar-demo-item">
                      {{ item }}
            </span>
          </div>
        </n-scrollbar>
        <div class="relative-position q-mr-md">
          <q-btn :icon="open?'expand_less':'expand_more'" :ripple="false" color="#19181c" fab-mini flat push>
            <q-popup-proxy breakpoint="none"
                           @update:model-value="open=!open">
              <cili-select-bar :data="courses.classify"/>
            </q-popup-proxy>
          </q-btn>
        </div>
      </q-page-sticky>
    </transition>
  </q-page>
</template>

<script setup>
import useTheme from "src/composables/useTheme"
import {inject, onMounted, reactive, ref} from 'vue'
import {postAction} from 'src/api/manage'
import CiliSelectBar from 'src/components/ciliSelectBar/ciliSelectBar.vue'
import {useRoute} from 'vue-router'

const {darkTheme} = useTheme()
const bus = inject('bus')
const $route = useRoute()
const showNoData = ref(false)
const show = ref(false)
const open = ref(false)
const courses = ref({})
const urls = {
  list: 'courses/get-subject-categories'
}
const queryParam = reactive({
  subject: '',
  kind: '',
})
const refresh = async () => {
  const q = $route.query.query
  if (!!q) {
    bus.emit('searchValue', q)
    queryParam.query = q
  }
  const res = await postAction(urls.list, queryParam)
  if (res && res.code === 1) {
    const data = res.data
    if (!!data.courses && data.courses.length > 0) {
      courses.value = data
      showNoData.value = false
    } else {
      showNoData.value = !showNoData.value
    }
  }
}

onMounted(() => {
  bus.on('handleSearch', (query) => {
    queryParam.query = query
    refresh()
  })
  refresh()
})
</script>
<style lang="scss" scoped>
n-tag:hover {
  cursor: pointer;
}

ul {
  list-style: none;
}

.selectTitle {
  font-size: 18px;
  font-weight: 600;

  &.subTitle {
    font-weight: 500;
  }
}

.nice-class-out {
  background-color: #f9fafc;
  box-shadow: 0 5px 5px -5px #f2f3f5 inset;
}


#certify {
  position: relative;
  width: 1200px;
  margin: 0 auto
}

#certify .swiper-container {
  padding-bottom: 60px;
  height: 500px;
}

#certify .swiper-slide {
  width: 773px;
  height: 396px;
  background: #fff;
}

#certify .swiper-slide img {
  display: block;
  width: 70px;
  border-radius: 10px;
}

#certify .swiper-slide p {
  padding-top: 0;
  text-align: left;
  color: #636363;
  font-size: 14px;
  margin: 0;
  line-height: 20px;
}

#certify .swiper-pagination {
  width: 100%;
  bottom: 20px;
}

#certify .swiper-pagination-bullets .swiper-pagination-bullet {
  margin: 0 5px;
  border: 3px solid #fff;
  background-color: #d5d5d5;
  width: 10px;
  height: 10px;
  opacity: 1;
}

#certify .swiper-pagination-bullets .swiper-pagination-bullet-active {
  border: 3px solid #00aadc;
  background-color: #fff;
}

#certify .swiper-button-prev {
  left: 800px;
  top: 365px;
  text-align: center;
  width: 45px;
  line-height: 45px;
  background: none;
}

#certify .swiper-button-prev:hover {
  background-position: 0 -46px;
  background-size: 100%
}

#certify .swiper-button-next {
  right: 280px;
  top: 365px;
  width: 45px;
  text-align: center;
  line-height: 45px;
  background: none;
}

#certify .swiper-button-next:hover {
  background-position: 0 -139px;
  background-size: 100%
}

/* banner�ֲ�ͼ */
.slideBox {
  width: 1040px;
  height: 452px;
  overflow: hidden;
  position: relative;
}

.slideBox .hd {
  position: absolute;
  right: 50%;
  bottom: 15px;
  z-index: 1;
}

.slideBox .hd ul {
  overflow: hidden;
  zoom: 1;
  float: left;
}

.slideBox .hd ul li {
  float: left;
  margin-right: 8px;
  width: 10px;
  height: 10px;
  background: #fff;
  cursor: pointer;
  border-radius: 5px;
}

.slideBox .hd ul li.on {
  background: #f00;
}

.slideBox .bd {
  position: relative;
  height: 100%;
  z-index: 0;
}

.slideBox .bd li {
  zoom: 1;
  vertical-align: middle;
}

.slideBox .bd img {
  width: 1040px;
  height: 452px;
  display: block;
}

.lunbo-con-btn {
  position: absolute;
  height: 50px;
  width: 30px;
  margin-top: -25px;
  z-index: 2;
}

.lunbo-left-btn {
  top: 50%;
  left: 20px;
  background: url(http://static.mayikt.com/09178.png) center no-repeat;
}

.lunbo-right-btn {
  top: 50%;
  right: 20px;
  background: url(http://static.mayikt.com/0918.png) center no-repeat;
}

.lunbo-con-btn:hover {
  background-color: rgba(0, 0, 0, 0.2);
}

/* ��ʾ����һ����¼�����µ�¼ */
.log-in-again {
  width: 461px;
  height: 220px;
  background: rgba(255, 255, 255, 1);
  border-radius: 10px;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  z-index: 999;
  text-align: center;
}

.log-in-again h1 {
  font-size: 20px;
  font-family: MicrosoftYaHei-Bold;
  font-weight: bold;
  color: rgba(51, 51, 51, 1);
  margin-top: 25px;
}

.log-in-again p {
  margin-top: 50px;
  margin-bottom: 40px;
  font-size: 14px;
  font-family: MicrosoftYaHei;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
}

.log-in-again button {
  width: 140px;
  height: 40px;
  border: 1px solid rgba(223, 50, 37, 1);
  border-radius: 20px;
  outline: none;
  cursor: pointer;
}

.log-in-again #log-in-again-btn {
  background: rgba(223, 50, 37, 1);
  color: #fff;
  font-size: 14px;
  margin-right: 35px;
}

.log-in-again #log-in-cancel-btn {
  background: #fff;
  color: #DF3225;
  font-size: 14px;
}

/* ���ְ� */
.motaiceng {
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background-color: #000;
  opacity: 0.6;
  position: fixed;
  display: none;
  z-index: 888;
}

/* ��¼���� */
.header-load-concent {
  width: 400px;
  background-color: #fff;
  border-radius: 10px;
  position: absolute;
  z-index: 999;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: none;
}

.header-load-concent-in {
  padding: 0 30px;
}

.header-load-concent h1 {
  background-position: -5px -4px;
  height: 83px;
}

.change2 {
  background: url(http://static.mayikt.com/beijing.png) -5px -3px no-repeat;
}

.change {
  background: url(http://static.mayikt.com/beijing2.png) -5px -3px no-repeat;
}

.change-color {
  color: rgba(223, 50, 37, 1) !important;
}

.header-load-concent h1 span {
  color: rgba(51, 51, 51, 1);
  cursor: pointer;
  margin-left: 42px;
  width: 36px;
  height: 18px;
  font-size: 18px;
  font-family: MicrosoftYaHei-Bold;
  font-weight: bold;
  line-height: 65px;
}

.header-load-concent h1 .zhuce-span {
  margin-left: 34px;
}

.header-load-concent .load-del {
  position: absolute;
  color: rgba(153, 153, 153, 1);
  cursor: pointer;
  top: 2px;
  right: 0px;
  line-height: 28px;
  padding: 5px;
  font-size: 26px;
}

.header-load-concent .load-del i {
  font-size: 26px;
}

.header-load-concent .load-del:hover {
  color: rgba(223, 50, 37, 1);
}

/* ��¼���� */
.header-load-concent input:focus {
  border-color: rgba(223, 50, 37, 1);
  color: #333;
}

.header-loading-input {
  padding-left: 20px;
  border: 1px solid rgba(228, 228, 228, 1);
  border-radius: 10px;
  height: 50px;
}


.header-loading-input1 {
  background: url(http://static.mayikt.com/icon11.png) 303px center no-repeat;
}

.header-loading-input11 {
  background: url(http://static.mayikt.com/icon1.png) 303px center no-repeat;
}

.header-loading-input2 {
  background: url(http://static.mayikt.com/icon2.png) 300px center no-repeat;
}

.header-loading-input22 {
  background: url(http://static.mayikt.com/icon12.png) 300px center no-repeat;
}

.header-load-concent .inp-load1 {
  background: url(http://static.mayikt.com/icon11.png) no-repeat;
  background-position: 303px center;
}

.header-load-concent .inp-load2 {
  background: url(http://static.mayikt.com/icon2.png) no-repeat;
  background-position: 300px center;
}

.header-load-concent .inp-load {
  width: 268px;
  border: none;
  border-right: 1px solid rgba(228, 228, 228, 1);
  font-size: 16px;
  font-family: MicrosoftYaHei;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 46px;
  height: 48px;

}

.header-load-concent-in .img1 {
  cursor: pointer;
  position: absolute;
  top: 100px;
  right: 48px;
}

.header-load-concent-in .img2 {
  cursor: pointer;
  position: absolute;
  top: 180px;
  right: 48px;
}

.num-phone,
.num-possword {
  height: 13px;
  font-size: 12px;
  font-family: MicrosoftYaHei;
  font-weight: 400;
  color: rgba(223, 50, 37, 1);
  line-height: 20px;
  visibility: hidden;

}

.load-password {
  margin-top: 15px;
  border-bottom: 1px solid rgba(228, 228, 228, 1);
  padding-bottom: 28px;
}

.load-password #checkbox {
  width: 14px;
  height: 14px;
  background-color: #fff;
  -webkit-appearance: none;
  border: 1px solid rgba(153, 153, 153, 1);
  border-radius: 3px;
  outline: none;
  margin-top: -3px;

}

.load-password #checkbox:hover {
  border-color: red;
}

.load-password #checkbox:checked {
  background: url(http://static.mayikt.com/duigou.png) no-repeat;
  border: none;
}

.rember-possword {
  margin-left: 5px;
  font-size: 14px;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
}


.load-password label {
  float: left;
  cursor: pointer;
}

.forget-possword a {
  color: rgba(51, 51, 51, 1);
  font-size: 14px;
  font-weight: 400;
  float: right;
}

.forget-possword:hover a {
  color: rgba(223, 50, 37, 1);
}

.header-load-concent-in button {
  height: 50px;
  width: 338px;
  border: none;
  color: #fff;
  background-color: rgba(223, 50, 37, 1);
  border-radius: 5px;
  margin-top: 25px;
  margin-bottom: 22px;
  font-family: MicrosoftYaHei;
  outline: none;
  cursor: pointer;
  font-size: 16px;

}

.header-load-concent-in .line {
  width: 80px;
  margin-top: 26px;
  float: left;
  border-top: 1px solid rgba(228, 228, 228, 1);
}

/* ע�Ჿ�� */

.zhuce-public {
  width: 268px;
  border: none;
  border-right: 1px solid rgba(228, 228, 228, 1);
  font-size: 16px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 46px;
  height: 48px;
}

.zhece-idcode {
  width: 198px;
}

.zhuce-public:focus {
  border-color: rgba(223, 50, 37, 1);
  color: #333;
}

.header-zhuce-input1 {
  margin-top: 10px;
  background: url(http://static.mayikt.com/icon5.png) 300px center no-repeat;
}

.header-zhuce-input11 {
  background: url(http://static.mayikt.com/icon177.png) 300px center no-repeat;
}

.header-zhuce-input2 {
  background: url(http://static.mayikt.com/icon11.png) 303px center no-repeat;
}

.header-zhuce-input22 {
  background: url(http://static.mayikt.com/icon1.png) 303px center no-repeat;
}

.header-zhuce-input3 {
  background: url(http://static.mayikt.com/icon99.png) 300px center no-repeat;
}

.header-zhuce-input33 {
  background: url(http://static.mayikt.com/icon66.png) 300px center no-repeat;
}

.header-zhuce-input4 {
  background: url(http://static.mayikt.com/icon2.png) 300px center no-repeat;
}

.header-zhuce-input44 {
  background: url(http://static.mayikt.com/icon15.png) 300px center no-repeat;
}

.header-zhuce-input5 {
  background: url(http://static.mayikt.com/icon2.png) 300px center no-repeat;
}

.header-zhuce-input55 {
  background: url(http://static.mayikt.com/icon15.png) 300px center no-repeat;
}

.zhece-boder {
  border-bottom: 1px solid rgba(228, 228, 228, 1);
  margin-top: 14px;
}

.inp-zhece-line {
  height: 52px;
  width: 1px;
  border-left: 1px solid rgba(228, 228, 228, 1);
  position: absolute;
}

.inp-zhece-line1 {
  top: 104px;
  right: 82px;
}

.inp-zhece-line2 {
  top: 180px;
  right: 82px;
}

.inp-zhece-line3 {
  top: 178px;
  right: 82px;
}

.inp-zhece-line4 {
  top: 326px;
  right: 82px;
}

.inp-zhece-line5 {
  bottom: 286px;
  right: 82px;
}

.inp-zhece-line6 {
  bottom: 211px;
  right: 152px;
}

#btn-zhuce {
  margin-top: 0;
}

.zhuce-ma {
  height: 149px;
  width: 190px;
  border-radius: 5px;
  position: absolute;
  top: 197px;
  left: 400px;
  display: none;
}

/* �һ����� */
.ddd {
  height: 563px;
  width: 400px;
  border-radius: 10px;
  background: url(http://static.mayikt.com/beijing.png) -5px -3px #fff no-repeat;
  position: absolute;
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
  margin: auto;
  z-index: 999;
  display: none;
}

.ddd h1 {
  height: 72px;
  margin-bottom: 20px;
}

.find-possword-top {
  line-height: 56px;
  margin-left: 40px;
  font-size: 18px;
  font-family: MicrosoftYaHei-Bold;
  font-weight: bold;
  color: rgba(223, 50, 37, 1);
}

.ddd .load-del {
  padding: 5px;
  margin-left: 245px;
  cursor: pointer;
  color: rgba(153, 153, 153, 1);
  font-size: 26px;
}

.ddd .load-del i {
  font-size: 20px;
}

.ddd .load-del:hover {
  color: rgba(223, 50, 37, 1);
}

.send-msg {
  height: 50px;
  padding-left: 20px;
  border-radius: 10px;
  border: 1px solid rgba(228, 228, 228, 1);
  font-size: 16px;
  font-family: MicrosoftYaHei;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
}

.send-msg2 {
  padding-right: 5px;
}

.send-msg1 {
  background: url(http://static.mayikt.com/icon11.png) 303px center no-repeat;
}

.send-msg11 {
  background: url(http://static.mayikt.com/icon1.png) 303px center no-repeat;
}

.send-msg3 {
  background: url(http://static.mayikt.com/icon2.png) 303px center no-repeat;
}

.send-msg33 {
  background: url(http://static.mayikt.com/icon15.png) 303px center no-repeat;
}

.send-msg4 {
  background: url(http://static.mayikt.com/icon2.png) 303px center no-repeat;
}

.send-msg44 {
  background: url(http://static.mayikt.com/icon15.png) 303px center no-repeat;
}

.find-possword {
  background: url(http://static.mayikt.com/load%20@2x.png) no-repeat;
  background-position: -6px -4px;
}

.send-msg input {
  border: none;
  border-right: 1px solid rgba(228, 228, 228, 1);
  width: 268px;
  height: 48px;
  line-height: 46px;
  font-size: 16px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
}

.send-msg input:focus {
  border-color: rgba(223, 50, 37, 1);
}

#send-message {
  width: 198px;
}

#send-msg-code {
  width: 198px;
}

.send-msg span {
  cursor: pointer;
  color: rgba(51, 51, 51, 1);
}

#send-btn {
  width: 112px;
  float: right;
  color: rgba(51, 51, 51, 1);
  text-align: center;
  background-color: #fff;
  margin: 0;
  outline: none;
}

.send-msg1 {
}


.load-qq,
.logo-weixin {
  float: left;
  margin-bottom: 30px;
}

/* .load-qq:hover{
	color: rgba(223, 50, 37, 1) !important;
}
/* .logo-weixin :hover span{
	color: rgba(223, 50, 37, 1) !important;
} */

.load-qq {
  margin-left: 15px;
  margin-right: 42px;
  text-align: center;
}

.logo-weixin {
  margin-right: 15px;
}

.load-qq,
.logo-weixin {
  color: rgba(153, 153, 153, 1);
  font-size: 14px;
  text-align: center;
}

.load-qq img,
.logo-weixin img {
  margin-bottom: 6px;
}

.vip {
  float: right;
  margin-top: 12px;
  position: relative;
}

.vip .vip-pic {
  height: 50px;
  width: 50px;
  border-radius: 25px;
}

.vip .vip-v {
  position: absolute;
  bottom: 0;
  right: 0;
}

.showPersionMsg {
  position: absolute;
  width: 272px;
  min-height: 200px;
  z-index: 99;
  padding-top: 22px;
  top: 63px;
  right: 0;
  background: url(http://static.mayikt.com/jian.png) 240px 15px no-repeat;
  display: none;

}

.vip-msg .vip-msg-pic {
  float: left;
}

.vip-msg span {
  float: left;
  margin-left: 15px;
  font-family: PingFang-SC-Bold;
  font-weight: bold;
  color: rgba(233, 121, 0, 1);
}

.vip-msg {
  background: #fff;
  width: 272px;
  padding-left: 10px;
  padding-top: 10px;
  border-radius: 10px;
  padding-bottom: 35px;

}

.checkcolor {
  background-color: #ddd !important;
}

.vip-msg-in {

  width: 78px;
  background-position: 10px center;
  line-height: 36px;
  background-color: #f9fafc;
  border-radius: 3px;
  margin-top: 10px;
  float: left;
  padding-left: 38px;
}

.vip-msg-in a {
  height: 36px;
  color: #666;
  font-size: 14px;
}

.vip-msg-in1,
.vip-msg-in2 {
  margin-right: 15px;
}

.vip-msg-in1 {
  background: url(http://static.mayikt.com/icon1@2x.png) #F8FAFC 10px center no-repeat;
}

.vip-msg-in3 {
  background: url(http://static.mayikt.com/icon2@2x.png) #F8FAFC 10px center no-repeat;
}

.vip-msg-in2 {
  background: url(http://static.mayikt.com/icon3@2x.png) #F8FAFC 10px center no-repeat;
}

.vip-msg-in4 {
  background: url(http://static.mayikt.com/icon4@2x.png) #F8FAFC 10px center no-repeat;
}

/* �γ����鲿�� */
section {
  padding-top: 40px;
  box-shadow: 0 12px 24px 0 rgba(7, 17, 27, 0.2)
}


/*.section0 {
	background: linear-gradient(to bottom, rgba(53,155,255,0.8) -30%, rgba(225,225,225,0) 40%);
}

.section1 {
	background: linear-gradient(to bottom, rgba(29,29,29,0.8) -30%, rgba(225,225,225,0) 40%);
}

.section2 {
	background: linear-gradient(to bottom, rgba(0,126,240,0.8) -30%, rgba(225,225,225,0) 40%);

}



.section3 {
	background: linear-gradient(to bottom, rgba(259,207,9,0.8) -30%, rgba(225,225,225,0) 40%);
}*/


.banner {
  margin: 0 auto;
  width: 1200px;
  border-radius: 10px;
  position: relative;
}

.mayi-nav-con {
  box-shadow: 0px 4px 20px 0px rgba(7, 17, 27, 0.3);
  border-radius: 15px;
  overflow: hidden;
}

.nav-list {
  font-size: 13px;
  width: 160px;
  background: #2b3034;
  color: #bbbbbd;
  padding-top: 10px;
  padding-bottom: 10px;
  border-top-left-radius: 15px;
  border-bottom-left-radius: 15px;
  height: 432px;
}

.nav-list li {
  font-size: 14px;
  line-height: 54px;
  cursor: pointer;
  padding-left: 15px;
  border-bottom-left-radius: 24px;
  border-top-left-radius: 24px;
  background: url(http://static.mayikt.com/11@2x.png) 145px center no-repeat;
}

.nav-list li:hover {
  background-color: #505050;
  color: #fff;
}

.mayi {
  display: none;
  position: absolute;
  top: 0;
  left: 160px;
  width: 757px;
  background: #fff;
  padding-top: 42px;
  z-index: 999;
}

.mayi-top {
  padding-left: 30px;
}

.mayi h2 {
  float: left;
  font-size: 14px;
  font-family: MicrosoftYaHei-Bold;
  font-weight: bold;
  color: rgba(223, 50, 37, 1);

}

.mayi .line {
  border-top: 1px solid #e5e5e5;
  width: 580px;
  float: left;
  margin-top: 8px;
  margin-left: 18px;
}

.mayi-list {
  padding-left: 30px;
  margin-top: 10px;
  height: 160px;
  width: 600px;
}

.mayi-list li {
  float: left;
  margin-right: 18px;
  line-height: 37px;
}

.mayi-list li a {
  color: #666;
  font-size: 12px;
}

.mayi-list li:hover a {
  color: #F20A0A;
}

.mayi-concent {
  padding-left: 30px;
  background: #f8f9fb;
  height: 201px;
  padding-top: 25px;
}

.mayi-concent li {
  float: left;
  height: 54px;
  width: 360px;
  margin-bottom: 36px;
}

.mayi-concent li img {
  float: left;
  height: 53px;
  border-radius: 5px;
}

.mayi-concent-right {
  float: left;
  margin-left: 8px;
  color: #666;
  padding: 8px 0;
}

.mayi-concent-right h4 {
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 12px;
  line-height: 10px;
}

.mayi-concent-right span {
  float: left;
  font-size: 12px;
  line-height: 12px;
  margin-left: 18px;
}

.mayivip {
  color: #fff;
  width: 40px;
  background: #F20A0A;
  font-size: 12px;
  text-align: center;
  line-height: 14px;
  border-radius: 7px;
  float: left;
}

/* ���񲿷� */
.serve {
  background: rgba(248, 250, 252, 1);
  box-shadow: 0px 3px 7px 0px rgba(0, 0, 0, 0.06);
}

.serve-in {
  width: 1200px;
  margin: 0 auto;
  padding-top: 40px;
  text-align: center;
}

.serve-in-top {
  color: #666;
  font-size: 20px;
}

.serve-in-top h2 {
  letter-spacing: normal;
}

.serve-in-top .class-bottom {
  margin-bottom: 30px;
}

.li-bottom span {
  color: #999;
  font-size: 13px;
}

.li-bottom i {
  color: #999;
  font-size: 12px;
  float: left;
}

.li-bottom i img {
  margin-right: 5px;
  /* margin-top: 5px; */
  height: 16px;
}

.class-top1 {
  background: url(http://static.mayikt.com/20196271550221.png) no-repeat;
  height: 70px;
  width: 381px;
  margin: 0 auto;
}

.class-top2 {
  background: url(http://static.mayikt.com/0018.png) top center no-repeat;
  width: 225px;
  margin: 0 auto;
  height: 70px;
  font-weight: 500;
}

.class-top3 {
  background: url(http://static.mayikt.com/t3.png) no-repeat;
  width: 225px;
  margin: 0 auto;
  height: 70px;
}

.class-top4 {
  background: url(http://static.mayikt.com/04921.png) no-repeat;
  width: 225px;
  margin: 0 auto;
  height: 70px;
}

.class-top5 {
  background: url(http://static.mayikt.com/jphk.png) no-repeat;
  width: 225px;
  margin: 0 auto;
  height: 70px;
}

.class-top span {
  color: #F20A0A;
}

.class-bottom {
  color: #999;
  margin-bottom: 30px;
  font-size: 16px;
  font-weight: 500;

}

.serve-in li {
  float: left;
  width: 80px;
  margin-left: 60px;
  cursor: pointer;
}

.serve-in ul span {
  display: block;
  margin-bottom: 40px;
  font-size: 16px;
  color: #666;

}

.serve-in li:nth-child(1) {
  margin-left: 0;
}

/* ��ת��Ч���� */
.serve-in li a {
  height: 80px;
  width: 80px;
  display: block;
  text-align: center;
  -webkit-border-radius: 4px;
  border-radius: 4px;
}

.serve-in li:hover a {
  -webkit-animation: proRotate 0.3s ease-in-out 50ms alternate none 1;
  animation: proRotate 0.3s ease-in-out 50ms alternate none 1;
}

@-webkit-keyframes proRotate {
  0% {
    -webkit-transform: perspective(400px) rotateY(180deg);
  }

  100% {
    -webkit-transform: perspective(400px) rotateY(0deg);
  }
}

@keyframes proRotate {
  0% {
    transform: perspective(400px) rotateY(180deg);
  }

  100% {
    transform: perspective(400px) rotateY(0deg);
  }
}

.class-list-concent-in li,
.nice-class-list li,
.t-promopte li {
  position: relative;
}

.biaoqian {
  position: absolute;
  top: 5px;
  left: -10px;
  border-radius: 10px;
}

.nice-class-list li:hover img {
  transform: scale(1.1);
  box-shadow: 3px 0px 9px 0px rgba(7, 17, 27, 0.1), 7px 7px 8px 0px rgba(7, 17, 27, 0.1);
  transition: all 0.3s;
}

.courseLogo {
  height: 120px;
  width: 218px;
}

/* t��Ѷ������ */
.tx-class {
  color: #666;
  font-size: 20px;
  padding: 40px 0;
  margin: 0 auto;
  width: 1200px;
  text-align: center;
}

.tx-class img {
  border-radius: 10px;
}

.tx-class span {
  color: #F20A0A;
}

.class-top {
  letter-spacing: 5px;
  font-size: 20px;
  color: #666;
}

.tx-class li {
  float: left;
}

.tx-class li:nth-child(1) {
  margin-right: 15px;
}

.li1 {
  margin-bottom: 20px;
}

.nice-class-out li img {
  border-radius: 10px;
  height: 120px;
  width: 218px;
}

.nice-class li .a-tittile {
  display: block;
  font-size: 15px;
  line-height: 24px;
  font-family: PingFang-SC-Medium;
  color: inherit;
  height: 46px;
  font-weight: 600;
}


.li5 {
  margin: 0 20px;
}

.li6 {
  margin-right: 20px;
}


.nice-class-out li .courseLogo {
  border-radius: 10px;
}

.nice-class {

  margin: 0 auto;
  text-align: center;
}

.nice-class li {
  width: 216px;
  text-align: left;
}

.nice-class li:nth-child(1) {
  margin-left: 0;
}

.nice-class li:nth-child(6) {
  margin-left: 0;
}

.nice-class li a {
  display: block;
  font-size: 15px;
  line-height: 25px;
  margin-bottom: 5px;
  font-family: PingFang-SC-Medium;
  color: rgba(51, 51, 51, 1);
}


.nice-class li:hover a {
  color: #F20A0A;
}

.li-vip {
  color: #F20A0A;
  font-weight: bold;
  font-size: 12px;
}


.nice-class .ali {
  font-size: 14px;
  float: left;
}

.ali span {
  float: left;
  font-size: 12px;
}

.ali i {

  font-style: normal;
}

.al i span {
  font-size: 18px;
}

/* ��������� */
.help {
  width: 60px;
  position: fixed;
  top: 32%;
  right: 0;
  z-index: 10;
}

.help-right {
  border: 1px solid rgba(240, 240, 240, 1);
  box-shadow: 0px 3px 27px 0px rgba(0, 0, 0, 0.07);
  padding: 20px 11px;
  border-radius: 30px;
  background-color: #fff;
  width: 40px;
}

.help-right li {
  padding-bottom: 10px;
  width: 37px;
  border-bottom: 1px solid #f0f0f0;
  text-align: center;
  padding-top: 15px;
}

.help-right li:nth-child(6) {
  border: none;
}

.help-right li a {
  font-size: 12px;
  color: #F20A0A;
  line-height: 14px;
  height: 28px;
  display: block;
}

.help-left a {
  display: block;
  width: 21px;
  height: 21px;
  background: url(http://static.mayikt.com/1.png) no-repeat;
  background-position: center;
  margin: 6px 7px;
}

.weixin {
  position: absolute;
  bottom: 0px;
  right: 50px;
  height: 181px;
  width: 149px;
  background: url(http://static.mayikt.com/2019556965.png) no-repeat;
}

/*������������*/
.promopte {
  background-color: #fff;
}

.promopte-top-img {
  height: 90px;
  line-height: 90px;
  margin-bottom: 30px;
  color: #fff;
  font-size: 16px;
  font-weight: 700;
  text-align: center;
  background: url(http://static.mayikt.com/jnts.png) no-repeat;
  background-size: cover;
}

/*ÿ�ؽ�ʦ*/
.mt-teacher {
  height: 448px;
  text-align: center;
  padding-top: 30px;
  background: url(http://static.mayikt.com/3411.png) no-repeat;
  background-size: cover;

}

.mt-teacher h2 {
  margin-bottom: 15px;
  width: 225px;
  margin: 0 auto;
  height: 60px;
}

.mt-teacher {
  color: #fff;
}

.teacher-out {
  width: 1200px;
  margin: 40px auto;
  position: relative;
}

.teacher-left {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  opacity: 0.5;
  background: url(http://static.mayikt.com/icon712@2x.png) no-repeat;
}

.teacher-right {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  opacity: 0.5;
  background: url(http://static.mayikt.com/icon8@2x.png) no-repeat;
}

.teacher-left img {
  height: 50px;
}

.teacher-right img {
  height: 50px;
}

.teacher-left:hover {
  opacity: 1;
}

.teacher-right:hover {
  opacity: 1;
}

.teacher-show {
  width: 940px;
  height: 405px;
  overflow: hidden;
  position: relative;
  top: -30px;
  left: 0;
}

.teachers {

  top: 0;
  left: 0;
  width: 1000%;
}

.teacher {
  width: 293px;
  margin-right: 30px;
  height: 327px;
  margin-top: 70px;
  background-color: red;
  color: #fff;
  font-size: 50px;
  float: left;
  background: url(http://static.mayikt.com/352.png) #fff right bottom no-repeat;
  position: relative;
  box-shadow: 0 4px 8px 0 rgba(7, 17, 27, .05);
  border-radius: 10px;
}

.teacher h2 {
  line-height: 24px;
  margin-top: 70px;
  font-size: 20px;
  color: #07111b;
  font-weight: 700;
}

.teacher span {
  font-size: 14px;
  color: #4d555d;
  display: block;
  line-height: 24px;
  margin-bottom: 12px;
}

/* �������� */
/*Ч������ �޸�scale(�Ŵ��ֵ)*/
.teacher-photo {
  transition: All 0.4s ease-in-out;
  -webkit-transition: All 0.4s ease-in-out;
  -moz-transition: All 0.4s ease-in-out;
  -o-transition: All 0.4s ease-in-out;
}

.teacher:hover .teacher-photo {
  transform: scale(0.8);
  -webkit-transform: scale(0.8);
  -moz-transform: scale(0.8);
  -o-transform: scale(0.8);
  -ms-transform: scale(0.8);
}


/* ���Ϻÿ���Ч���� */
.tx-class-ul li:hover img {
  transform: translateY(-8px);
  transition: all 0.3s;
  box-shadow: 8px 0px 9px 0px rgba(7, 17, 27, 0.2), 7px 7px 8px 0px rgba(7, 17, 27, 0.2);
}

/* ����������Ч���� */
.t-promopte li:hover img {
  box-shadow: 8px 0px 9px 0px rgba(7, 17, 27, 0.1), 7px 7px 8px 0px rgba(7, 17, 27, 0.1);
  transform: scale(1.1);
  transition: all 0.3s;
}

.teacher:hover h2 {
  color: #fff;
}

.teacher:hover span {
  color: #fff;
}

.teacher:hover p {
  height: 100%;
  transform: translateY(-80px);
  transition: 1s;
}

.teacher:hover {
  background-color: #fff;
}

.teacher p {
  font-size: 14px;
  color: #4d555d;
  padding: 0 36px;
  text-overflow: ellipsis;
  overflow: hidden;
  line-height: 24px;
  word-break: break-word;
  height: 100px;
  text-align: justify;
}

.teacher-photo {
  display: block;
  position: absolute;
  width: 103px;
  height: 103px;
  border-radius: 50%;
  top: -55px;
  left: 95px;
}

/* ѧԱ���� */
.studentback {
  padding-top: 100px;
}

.studentback-top {
  width: 865px;
  margin: 0 auto;

}

.studentback-tittle {
  margin: 0 auto;
  text-align: center;
}

.studentback-tittle h4 {
  margin: 0 auto;
  height: 78px;
  padding-top: 59px;
  background: url(http://static.mayikt.com/beijing2@2x.png) top center no-repeat;
  width: 260px;
  font-size: 16px;
  font-family: MicrosoftYaHei;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  margin-bottom: 35px;
}

.student-count {
  color: #e2463a;
  font-size: 30px;
  font-weight: bold;
  margin-top: 12px;
  margin-bottom: 10px;
}

.student-num-in {
  padding-top: 40px;
  width: 132px;
  font-size: 16px;
  float: left;

}

.student-left-btn,
.student-right-btn {
  -moz-user-select: none;
  /*���*/
  -webkit-user-select: none;
  /*webkit�����*/
  -ms-user-select: none;
  /*IE10*/
  user-select: none;
}

.student-left-btn i {
  font-size: 28px;
  color: #e1e1e1;
}

.student-left-btn:hover i {
  color: #DF3225;
}

.student-right-btn i {
  font-size: 28px;
  color: #e1e1e1;
}

.student-right-btn:hover i {
  color: #DF3225;
}

.student1 {
  background: url(http://static.mayikt.com/11111.png) center 0 no-repeat;
}

.student2 {
  background: url(http://static.mayikt.com/zcyh.png) center 0 no-repeat;
  margin-left: 234px;
  margin-right: 234px;
}

.student3 {
  background: url(http://static.mayikt.com/bmxy.png) center 0 no-repeat;
}

.studentback-buttom {
  margin-bottom: 90px;
  margin-top: 50px;
  position: relative;

}

.studentback-buttom-con-t {
  margin-bottom: 35px;
}

.studentback-buttom-con {
  width: 768px;
  height: 391px;
  padding-left: 5px;
  padding-top: 5px;
  background: url(http://static.mayikt.com/3424.png) no-repeat;
  box-shadow: 0px 8px 32px 0px rgba(255, 10, 0, 0.05);
  border-radius: 15px 15px 80px 15px;
}

.studentback-buttom-con img {
  float: left;
  margin-right: 20px;
}

.studentback-buttom-con h2 {
  margin-right: 35px;
  float: left;
  font-size: 18px;
  font-family: MicrosoftYaHei-Bold;
  font-weight: bold;
  color: rgba(51, 51, 51, 1);
  margin-top: 24px;
}

.studentback-buttom-con h2 span {
  color: rgba(153, 153, 153, 1);
  font-size: 14px;
  font-weight: 400;
  line-height: 30px;
}

.studentback-buttom-con .peixun {
  height: 28px;
  width: 115px;
  border-radius: 14px;
  background: #DF3225;
  float: left;
  font-size: 14px;
  color: rgba(255, 255, 255, 1);
  line-height: 28px;
  text-align: center;
  margin-right: 18px;
  margin-top: 23px;


}

.student-story {
  width: 610px;
  margin-left: 60px;
  color: rgba(153, 153, 153, 1);
  font-size: 14px;
  margin-bottom: 50px;
}

.student-story h3 {
  color: rgba(51, 51, 51, 1);
  font-size: 16px;
  margin-bottom: 20px;
  padding-left: 30px;
}

.student-story1 h3 {
  background: url(http://static.mayikt.com/4@2x.png) center left no-repeat;
}

.student-story2 h3 {
  background: url(http://static.mayikt.com/6@2x.png) center left no-repeat;
}

#studentback-buttom-list li {
  position: absolute;
  padding: 3px;
  /* background-color: red; */
  cursor: pointer;
  border-radius: 10px;
}

#studentback-buttom-list .li111 {
  top: 135px;
  left: -365px;
}

#studentback-buttom-list .li222 {
  top: -15px;
  right: -140px;
}

#studentback-buttom-list .li333 {
  top: -15px;
  left: -160px;
}

#studentback-buttom-list .li444 {
  top: 320px;
  left: -260px;
}

#studentback-buttom-list .li555 {
  top: 150px;
  right: -320px;
}

.student-diandian {
  position: absolute;
  top: 55px;
  right: 105px;
  z-index: 10;
}

.footer-out {
  background-color: #494949;
}

.footer {

  height: 270px;
  margin: 0 auto;
  width: 1200px;
}

.footer h2 {
  height: 20px;
  font-size: 20px;
  font-family: PingFang-SC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  opacity: 0.9;
  margin-top: 42px;
}

.footer-l {
  float: left;
  width: 880px;
}

.footer-l a {
  color: #fff;
  opacity: 0.6;
}

.footer li {
  float: left;
}

.foot-l-top li {
  margin-right: 30px;
}

.foot-l-top {
  border-bottom: 1px solid #6d6d6d;
  margin-bottom: 30px;
}

.foot-l-top a {
  height: 15px;
  font-size: 16px;
  font-family: PingFang-SC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 62px;

}

.footer-r {
  text-align: center;
  float: right;
  width: 132px;
}

.footer-r img {
  margin-top: 50px;
  margin-bottom: 20px;
}

.footer-r span {
  font-size: 15px;
  color: #fff;
  opacity: 0.6;
}

.foot-l-bottom span {
  font-size: 14px;
  color: #fff;
  opacity: 0.6;
  height: 14px;
}

.span1 {
  margin-left: 25px;
  margin-right: 30px;
}

.foot-l-bottom a {
  height: 15px;
  font-size: 16px;
  font-family: PingFang-SC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  border-right: 1px solid #6d6d6d;
  margin-right: 10px;
  padding-right: 10px;
  display: block;
  margin-top: 3px;
  margin-bottom: 30px;
  font-size: 14px;
}

.gongsi {
  font-size: 14px;
  color: #fff;
  opacity: 0.6;
}

/*placeholder����ʽ����*/
input::-webkit-input-placeholder,
textarea::-webkit-input-placeholder {
  color: #999;
  font-size: 16px;
}

input:-moz-placeholder,
textarea:-moz-placeholder {
  color: #999;
  font-size: 16px;
  opacity: 1;
}

input::-moz-placeholder,
textarea::-moz-placeholder {
  color: #999;
  font-size: 16px;
  opacity: 1;
}

input:-ms-input-placeholder,
textarea:-ms-input-placeholder {
  color: #999;
  font-size: 16px;
}

.advertisement {
  position: relative;
  background-color: rgba(16, 22, 39, 0.8);
  height: 89px;
}

.advertisement a img {
  margin-top: -45px;

}

@media screen and (min-width: 0) and (max-width: 1850px) {
  .advertisement a img {
    width: 100%;
    margin-top: -47px;
  }
}

.advertisement a {
  width: 100%;
  position: fixed;
  bottom: 0;
  line-height: 0;
  z-index: 12;
  text-align: center;
  height: 89px;
  background-color: rgba(16, 22, 39, 0.8);
}

.advertisement-del {
  position: fixed;
  right: 0;
  bottom: 28px;
  color: #999;
  cursor: pointer;
  padding: 5px;
  height: 30px;
  width: 30px;
  border-radius: 15px;
  background: url(http://static.mayikt.com/del.png) no-repeat;
  z-index: 15;
}


.nice-class-list .ali p {
  font-size: 12px;
  font-family: PingFang SC;
  font-weight: 500;
  color: rgba(135, 135, 135, 1);
  line-height: 16px;
  margin-bottom: 10px;
  overflow: hidden;
  display: -webkit-box;
  text-overflow: ellipsis;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  white-space: normal;
  height: 30px;
}

.li-bottom .stars i {
  margin-left: -3px;
  font-size: 12px;
}

.li-bottom .actual-combat {
  padding: 0 15px;
}

.ali span {
  float: left;
  font-size: 12px;
  margin-left: 8px;
}

.nice-class .nice-class-list li:nth-child(5n+1) {
  margin-left: 0;
}

.li-bottom .stars {
  float: right;
}

.header-zhuce-input51 {
  background: url(http://static.mayikt.com/icon11.png) 300px center no-repeat;
}

a {
  text-decoration: none;
}
</style>
