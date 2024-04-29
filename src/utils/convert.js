import {CiliDefaultAvatar} from "src/config/default";

export const UpperFirst = (str) => {
  str = str.toLowerCase()
  return str.replace(/\b\w|\s\w/g, fw => {
    return fw.toUpperCase()
  })
}
export const RealUrl = (src) => {
  if (src) {
    if (src.substring(0, 4) === 'http') {
      // avatar is a link
      return src
    } else if (src.substring(0, 12) === 'cili-upload:') {
      // avatar is uploaded
      return process.env.API + src.substring(12)
    } else if (src.substring(0, 5) === 'video') {
      return process.env.API + src
    } else {
      return src
    }
  } else {
    // default avatar
    return CiliDefaultAvatar
  }
}
