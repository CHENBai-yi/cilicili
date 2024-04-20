import {postAction} from 'src/api/manage'

const urls = {
  list: 'comments/list_comments',
  userInfo: 'comments/user_comment_detail',
  add: 'comments/user_comment_add/',
  like: 'comments/add_favorite/',
  delete: 'comments/del_comments/',
}
const comments = [{}]

async function getComment(a: number, b: number, id: any) {
  const res = await postAction(urls.list, {page: a, page_size: b, id: id, sort_by: 'created_at', desc: true})
  if (res.code === 1) {
    // @ts-ignore
    return {data: res.data.records, total: res.data.total}
  }
  return {data: comments, total: comments.length}
}

export {getComment, urls, postAction}
