<template>
  <div style="height: 800px; margin-bottom: 150px;">
    <div class="invest-money"><h1>充值中心</h1>
      <div :class="darkTheme" class="pay-concent">
        <div class="clearfix flex no-wrap justify-between">
          <div id="hy" class="pay-concent-vip pay-concent-public pay-concent-public-active">
            <div id="order-contcent">会员充值</div>
            <span class="pay-concent-price q-pb-xl">¥39.99</span>
            <ul class="order-details"><h3 class="text-weight-bolder no-padding no-margin" style="line-height:unset">
              用户名：198****6235</h3>
              <li></li>
              <h3 class="text-weight-bolder q-mb-none q-pb-xs">订单详情：</h3>
              <li class="text-weight-medium">商品名称：蚂蚁课堂</li>
              <li class="text-weight-medium">应付金额：￥39.99</li>
              <li class="text-weight-medium">有限期：永久权限</li>
            </ul>
          </div>
          <div
            style="display: flex;flex-direction: column;align-items: flex-start;align-content: flex-start;flex-wrap: nowrap;">
            <h4>支付方式：</h4>
            <div class="container" style="display: flex; flex-direction: row; justify-content: flex-end;">
              <div><a class="payment-method" href="#">
                <div class="Alipay"><img src="pay/icon332@2x.png"> <span>支付宝支付</span></div>
                <div>
                  <form
                    action="https://openapi.alipay.com/gateway.do?charset=UTF-8&amp;method=alipay.trade.page.pay&amp;sign=q6jGX7NWXLbeZnvFl%2Fzi9WTzytMkZQBuKpYLdwAOGj%2FUd2UGezB%2FFCkliwzI3P5EA8eQN90lPtpSI91fj9qWJZNu2jksVKKnFztxaq4k83Yqqb%2FccD8aOjSE5eQKY18zmWK3%2BvXjPpMzRL5xVUchbjrMVAvmvPYLwbXkG8lZScuzxcVPYutPnDJreVzBgnpwWbmwsGZ%2FW9ClVEiXGeQMr9ZJs31DHWBvYkUNM72aifoiIMGvsHSdfbZKIapHCDXsQn71ZJKyJNr1BZ2Uk6jdPUU46WJNRx3xAWFS6g%2BgvzicvfWLYEbwunxECJ12LqWdmafrHXKZQ2taO00dAUDyww%3D%3D&amp;return_url=http%3A%2F%2Fwww.mayikt.com%2FpaySuccess.html&amp;notify_url=http%3A%2F%2F47.92.32.162%3A8083%2Fapi%2Fasync%2FaliPay&amp;version=1.0&amp;app_id=2021003112631675&amp;sign_type=RSA2&amp;timestamp=2024-04-10+22%3A27%3A12&amp;alipay_sdk=alipay-sdk-java-dynamicVersionNo&amp;format=json"
                    method="post"
                    name="punchout_form">
                    <input name="biz_content" type="hidden"
                           value="{&quot;out_trade_no&quot;:&quot;1778067210112204800&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;,&quot;subject&quot;:&quot;购买每特教育IT教学网站课程&quot;,&quot;total_amount&quot;:&quot;39.99&quot;}">
                    <input style="display:none" type="submit" value="立即支付">
                  </form>

                </div>
              </a></div>
              <div> &nbsp; &nbsp; &nbsp; &nbsp;</div>
              <div><a class="payment-method" href="#">
                <div class="Alipay"><img src="pay/weixinPay.png"> <span>微信支付</span></div>
              </a></div>
            </div>
          </div>
        </div>
        <div :class="darkTheme" class="take-care-vip">注意：会员可以享受所有VIP课程（包括单品课程）的观看</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import useTheme from "src/composables/useTheme"


const {darkTheme} = useTheme()
</script>

<style lang="scss" scoped>
.pay-concent {
  width: 800px;
  padding: 50px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 3px 27px 0px rgba(0, 0, 0, 0.07);
  border-radius: 30px;
  margin: 0 auto 63px;

  div {
    margin-bottom: 20px;
    font-size: 16px;
    font-family: MicrosoftYaHei-Bold;
    font-weight: bold;
  }
}

.pay-concent-price {
  text-align: center;
  font-size: 32px;
  font-weight: 400;
  display: flex;
  justify-content: center;
  color: #fff;
}

.pay-concent-public-active {
  border: 2px solid rgba(107, 153, 255, 1);
}

.pay-concent-public {
  background: url(http://kc.mayikt.com/2019-7-30-002.png) no-repeat;
  float: left;
  width: 255px;
  height: 420px;
  box-shadow: 0px 2px 13px 0px rgba(0, 0, 0, 0.07);
  border-radius: 16px;
}

#order-contcent {
  width: 210px;
  margin: auto;
  margin-top: 22px;
  font-weight: 400;
  color: #fff;
  line-height: 25px;
  padding-right: 30px;
  background: url(http://kc.mayikt.com/2019-07-30-003.png) right center no-repeat;
}

a {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  vertical-align: baseline;
  background: transparent;
  text-decoration: none;
}


.invest-money {
  height: 120px;
  background: url(http://kc.mayikt.com/95209.png);
  background-size: cover;

  h1 {
    width: 1150px;
    padding-left: 200px;
    margin: 0 auto;
    line-height: 90px;
    color: rgba(102, 102, 102, 1);
    font-size: 26px;
    font-weight: 400;
  }
}


.clearfix::before, .clearfix::after {
  content: "";
  height: 0;
  line-height: 0;
  display: block;
  visibility: hidden;
  clear: both;
}

.take-care-vip {
  background: url(http://kc.mayikt.com/2019-7-30-001.png) left center no-repeat;
  padding-left: 25px;
  color: #333;
  margin-bottom: 20px;
}

.Alipay {
  width: 154px;
  height: 54px;
  border: 1px solid rgba(222, 222, 222, 1);
  text-align: center;
  line-height: 54px;

  &:hover {
    padding: 0;
    border: 3px solid rgba(243, 9, 10, 1);
    background: url(http://kc.mayikt.com/duigou@2x.png) right bottom no-repeat;
  }
}

.order-details * {
  font-size: 18px;
}

.order-details li {
  margin-top: 10px;
}

ul {
  display: block;
  list-style-type: disc;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
  unicode-bidi: isolate;
  text-decoration: none;
  list-style: none;
}

.payment-method {
  margin-top: 5px;
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}
</style>
