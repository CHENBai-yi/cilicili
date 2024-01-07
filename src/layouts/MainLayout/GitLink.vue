<template>
  <q-fab :persistent="false" dense direction="down" flat icon="bi-github" padding="6px" round>
    <q-fab-action color="primary" external-label
                  icon="img:icon/github.svg" label="Github" label-position="left"
                  @click="openLink('https://gitee.com/cby-cby/cilicili')"/>
    <q-fab-action color="primary" external-label
                  icon="img:icon/gitee.svg" label="Gitee" label-position="left"
                  @click="openLink('https://gitee.com/cby-cby/cilicili')"/>
  </q-fab>
</template>

<script setup>
const openLink = (url) => {
  window.open(url)
}
</script>
