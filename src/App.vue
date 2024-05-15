<template>
  <n-message-provider>
    <MessageApi/>
  </n-message-provider>
  <router-view/>

</template>

<script lang="ts">
import {defineComponent} from 'vue';
import {useMeta} from 'quasar'
import MessageApi from "./message-api.vue"
import {NMessageProvider} from "naive-ui";

export default defineComponent({
  name: 'App',
  components: {MessageApi, NMessageProvider},
  setup() {
    useMeta({
      // 设置页面 title
      title: '菠萝微课',
      // 可选的；设置最终的 title 为“Index Page - My Website”,
      // optional; sets final title as "Index Page - My Website", useful for multiple level meta
      titleTemplate: (title: any) => `${title}`,

      // meta tags
      meta: {
        description: {
          name: 'description',
          content: '菠萝微课是IT技能学习平台。一家专注做视频课程的网站,提供及时更新的技术视频帮助更多自学者拿到心仪的offer.'
        },
        keywords: {
          name: 'keywords',
          content: '嗤哩嗤哩,一家专注做视频课程的网站,cilicili,CILICILI,嗤哩网,Cilicili-Admin,Cilicili后台管理系统'
        },
        equiv: {'http-equiv': 'Content-Type', content: 'text/html; charset=UTF-8'},
        // note: for Open Graph type metadata you will need to use SSR, to ensure page is rendered by the server
        ogTitle: {
          property: 'og:title',
          // optional; similar to titleTemplate, but allows templating with other meta properties
          template(ogTitle: any) {
            return `${ogTitle} - CiliCili`
          }
        }
      },
      // CSS tags
      // link: {
      //   material: {rel: 'stylesheet', href: 'https://fonts.googleapis.com/icon?family=Material+Icons'}
      // },

      // JS tags
      script: {
        ldJson: {
          type: 'application/ld+json',
          innerHTML: `{
      "@context": "https://schema.org/",
      "@type": "Recipe",
      "name": "菠萝微课",
      "author": {
        "@type": "Person",
        "name": "ChenBaiYi"
      },
      "datePublished": "2018-03-10",
      "description": "菠萝微课是IT技能学习平台。一家专注做视频课程的网站,提供及时更新的技术视频帮助更多自学者拿到心仪的offer.",
      "prepTime": "PT20M"
    }`
        }
      },

      // <html> attributes
      htmlAttr: {
        'xmlns:cc': 'http://creativecommons.org/ns#', // 生成 <html xmlns:cc="http://creativecommons.org/ns#">,
        empty: undefined // 生成 <html empty>
      },

      // <body> attributes
      bodyAttr: {
        'action-scope': 'xyz', // 生成 <body action-scope="xyz">
        empty: undefined // 生成 <body empty>
      },

      // <noscript> tags
      noscript: {
        default: 'This is content for browsers with no JS (or disabled JS)'
      }

    })
  }
});
</script>
