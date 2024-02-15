import {boot} from 'quasar/wrappers'

// "async" is optional;
// more info on params: https://v2.quasar.dev/quasar-cli/boot-files
export default boot(async ({app, router}) => {
  console.log("\n".concat(" %c CILI v", "1.27.1", " ")
      .concat("v1.27.0-12-7a8190826830403256b376aab9e4f6c38f8bdd76 Please give me one star,thank you", " %c https://gitee.com/cby-cby/cilicili", "\n", "\n"),
    "color: #fadfa3; background: #030307; padding:5px 0;", "background: #fadfa3; padding:5px 0;");
  // something to do
  app.provide('timeToMinute', timeToMinute)
})


// 秒转换分钟00:00:00格式
function timeToMinute(a: any) {
  var b = ""
  var h: string | number = parseInt(String(a / 3600)),
    m: string | number = parseInt(String(a % 3600 / 60)),
    s: string | number = parseInt(String(a % 3600 % 60));
  if (h > 0) {
    h = h < 10 ? '0' + h : h
    b += h + ":"
  }
  m = m < 10 ? '0' + m : m
  s = s < 10 ? '0' + s : s
  b += m + ":" + s
  return b;
}
