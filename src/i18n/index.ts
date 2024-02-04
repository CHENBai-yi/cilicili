import languages from "./languages"

export const Translator = () => {
  const temp = {
    'zh-CN': {},
    'en-US': {},
    'ru': {}
  }
  for (let key in languages) {
    // @ts-ignore
    temp['zh-CN'][key] = languages[key]['zh-CN']
    // @ts-ignore
    temp['en-US'][key] = languages[key]['en-US']
    // @ts-ignore
    temp['ru'][key] = languages[key]['ru']
  }
  return temp
}
