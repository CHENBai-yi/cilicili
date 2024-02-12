import {RouteRecordRaw} from 'vue-router';

const routes: RouteRecordRaw[] = [

  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {path: '', component: () => import('pages/IndexPage.vue')},
      {
        path: 'video', component: () => import('pages/CiliVideoDetailPage/CiliVideoDetail.vue'),
        children: [
          {path: '', component: () => import('src/components/CiliVideoInfo/CiliVideoInfo.vue')},
          {
            path: 'info',
            component: () => import('src/components/CiliVideoInfo/CiliVideoInfo.vue'),
            meta: {keepAlive: true}
          },
          {
            path: 'catalogue',
            component: () => import('src/components/CiliVideoCatalogue/CiliVideoCatalogue.vue'),
            meta: {keepAlive: true}
          },
          {
            path: 'comment',
            component: () => import('src/components/CiliVideoComment/CiliVideoComment.vue'),
            meta: {keepAlive: true}
          },
        ]
      }
    ],
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*', component: () => import('pages/error/ErrorNotFound.vue'),
  },

];

export default routes;
