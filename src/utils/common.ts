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

function getVideoLength(pl: HTMLVideoElement) {
  const time = pl.duration;
  var totalSecond = Math.ceil(time);
  var lenght;
  // 得到分钟数加秒数
  if (totalSecond > 60) {
    var minute: any = Math.floor(totalSecond / 60);
    if (minute < 10) {
      minute = "0" + minute;
    }
    lenght = minute + ":" + (totalSecond % 60)
  } else {
    lenght = "00:" + totalSecond
  }
  return {size: totalSecond, length: lenght};
}

export {getAvatar, getVideoLength}
