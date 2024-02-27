import {api} from 'src/boot/axios'
import {Notify} from "quasar";

export interface ApiResult<T> {
  code: number,
  message: string,
  data: T
}

enum method {
  Get = 'GET', Post = 'POST',
  Put = 'PUT', Delete = 'DELETE'
}

export async function getAction<T>(url: string, params?: any): Promise<ApiResult<T>> {
  return api({
    url,
    params,
    method: method.Get
  })
}

export async function postAction<T>(url: string, params?: any): Promise<ApiResult<T>> {
  return api({
    url: url,
    method: method.Post,
    data: params,
  })
}

export async function postBlobAction<T>(url: string, params?: any): Promise<ApiResult<T>> {
  return api({
    url,
    params,
    method: method.Post,
    responseType: 'blob'
  })
}

export async function putAction<T>(url: string, params?: any): Promise<ApiResult<T>> {
  return api({
    url,
    params,
    method: method.Put
  })
}

export async function deleteAction<T>(url: string, params?: any): Promise<ApiResult<T>> {
  return api({
    url,
    params,
    method: method.Delete
  })
}

// @ts-ignore
export async function downloadAction(url: string, params?: JSON, fileName) {
  const data = await postBlobAction(url, params)
  // @ts-ignore
  if (!data || data.size === 0) {
    Notify.create({
      type: 'negative',
      message: 'download error!',
    })
    return
  }
  // @ts-ignore
  if (typeof window.navigator.msSaveBlob !== 'undefined') {
    // @ts-ignore
    window.navigator.msSaveBlob(new Blob([data]), fileName)
  } else {
    // @ts-ignore
    let urlHref = window.URL.createObjectURL(new Blob([data]))
    let link = document.createElement('a')
    link.style.display = 'none'
    link.href = urlHref
    link.setAttribute('download', fileName)
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
    window.URL.revokeObjectURL(urlHref)
  }
}
