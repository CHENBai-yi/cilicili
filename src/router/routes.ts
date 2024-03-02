import {RouteRecordRaw} from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    name: 'index',
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {path: '', component: () => import('pages/IndexPage.vue')},
      {
        path: 'video', component: () => import('pages/CiliVideoDetailPage/CiliVideoDetail.vue'),
        children: [
          {path: '', component: () => import('src/components/CiliVideoInfo/CiliVideoInfo.vue'), meta: {index: 0}},
          {
            path: 'info',
            component: () => import('src/components/CiliVideoInfo/CiliVideoInfo.vue'),
            meta: {keepAlive: true, index: 0}
          },
          {
            path: 'catalogue',
            component: () => import('src/components/CiliVideoCatalogue/CiliVideoCatalogue.vue'),
            meta: {keepAlive: true, index: 1}
          },
          {
            path: 'comment',
            component: () => import('src/components/CiliVideoComment/CiliVideoComment.vue'),
            meta: {keepAlive: true, index: 2}
          }
        ]
      },
    ],
  }, {
    path: '/course',
    component: () => import('layouts/CiliVideoCourseLayout.vue'),
    children: [
      {path: '', component: () => import('pages/CiliVideoCourse/CiliVideoCourse.vue')},
    ],
  }, {
    path: '/notice',
    component: () => import('layouts/CiliNoticeLayout.vue'),
    children: [
      {
        path: '', component: () => import('components/navBar/CiliToolBar/CiliNotice/index.vue'),
        children: [
          {
            path: 'system',
            component: () => import('src/components/navBar/CiliToolBar/CiliNotice/module/CiliSystemNotice.vue')
          },
          {path: 'me', component: () => import('src/components/navBar/CiliToolBar/CiliNotice/module/CiliMyNotice.vue')},
        ]
      },
    ],
  },
  {
    path: '/create',
    component: () => import('layouts/CiliUploadVideoLayout.vue'),
    children: [
      {path: '', component: () => import('src/components/CiliVideoUpload/CiliVideoUpload.vue'), meta: {active: true}},
      {
        path: 'upload',
        component: () => import('src/components/CiliVideoUpload/CiliVideoUpload.vue'),
        meta: {active: true}
      },
      {path: 'manage', component: () => import('src/components/CiliManageHistory/CiliManageHistory.vue')},
    ],
  },
  {
    path: '/account',
    component: () => import('src/layouts/CiliPersonalCenterLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('src/components/CiliPersonalCenter/CiliPersonalCenter.vue'),
        meta: {active: true},
        children: [
          {
            path: 'setting',
            component: () => import('src/components/CiliPersonalCenter/CiliAccountInfo/CiliAccountInfo.vue'),
            meta: {active: true}
          }, {
            path: 'order',
            component: () => import('src/components/CiliPersonalCenter/CiliMyOrder/CiliMyOrder.vue'),
            meta: {active: true}
          },
        ],
      },
    ],
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*', component: () => import('pages/error/ErrorNotFound.vue'),
  },

];

export default routes;
