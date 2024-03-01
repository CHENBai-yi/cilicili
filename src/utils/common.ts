const avatarPriffix = "cili-upload:"
const getAvatar = (newValue: string): string => {
  if (newValue) {
    if (newValue.startsWith(avatarPriffix)) {
      return process.env.API + newValue.replace(avatarPriffix, '')
    } else {
      return process.env.API + newValue
    }
  }
  return ""
}
export {getAvatar}
