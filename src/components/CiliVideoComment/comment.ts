import {dayjs} from 'undraw-ui'
import {postAction} from 'src/api/manage'

const urls = {
  list: 'comments/list_comments'
}
const comments = [
  {
    id: '1',
    parentId: null,
    uid: '1',
    address: '来自上海',
    content:
      '缘生缘灭，缘起缘落，我在看别人的故事，别人何尝不是在看我的故事?别人在演绎人生，我又何尝不是在这场戏里?谁的眼神沧桑了谁?我的眼神，只是沧桑了自己[喝酒]',
    likes: 2,
    contentImg: 'https://gitee.com/undraw/undraw-ui/raw/master/public/docs/normal.webp',
    createTime: dayjs().subtract(10, 'minute').toString(),
    user: {
      username: '落🤍尘',
      avatar: 'https://static.juzicon.com/avatars/avatar-200602130320-HMR2.jpeg?x-oss-process=image/resize,w_100',
      level: 6,
      homeLink: '/1'
    }
  }
]

async function getComment(a: number, b: number, id: any) {
  const res = await postAction(urls.list, {page: a, page_size: b, id: id})
  if (res.code === 1) {
    // @ts-ignore
    return {data: res.data.records, total: res.data.total}
  }
  return {data: comments, total: comments.length}
}

export {getComment}
