import {boot} from 'quasar/wrappers'
import {Cookies, getCssVar, setCssVar} from 'quasar'
import {ThemeStyleQuasar} from 'src/config/default'


export default boot(({app}) => {
  const primary = Cookies.get('Cili-theme-primary') || getCssVar('primary') || ThemeStyleQuasar.primary
  const secondary = Cookies.get('Cili-theme-secondary') || getCssVar('secondary') || ThemeStyleQuasar.secondary
  const accent = Cookies.get('Cili-theme-accent') || getCssVar('accent') || ThemeStyleQuasar.accent
  const positive = Cookies.get('Cili-theme-positive') || getCssVar('positive') || ThemeStyleQuasar.positive
  const negative = Cookies.get('Cili-theme-negative') || getCssVar('negative') || ThemeStyleQuasar.negative
  const info = Cookies.get('Cili-theme-info') || getCssVar('info') || ThemeStyleQuasar.info
  const warning = Cookies.get('Cili-theme-warning') || getCssVar('warning') || ThemeStyleQuasar.warning
  const light = Cookies.get('Cili-theme-light') || getCssVar('light') || ThemeStyleQuasar.light
  const dark = Cookies.get('Cili-theme-dark') || getCssVar('dark') || ThemeStyleQuasar.dark

  setCssVar("primary", primary)
  setCssVar("secondary", secondary)
  setCssVar("accent", accent)
  setCssVar("positive", positive)
  setCssVar("negative", negative)
  setCssVar("info", info)
  setCssVar("warning", warning)
  setCssVar("light", light)
  setCssVar("dark", dark)
})
