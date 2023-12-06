<template>
  <td class="text-left" data-th="Name">
    <div :class="treeTd.iconName(treeTd.item) != 'done' ? 'q-pl-lg' : ''"
         v-bind:style="treeTd.setPadding(treeTd.item)">
      <q-btn v-if="treeTd.iconName(treeTd.item) != 'done'" :icon="treeTd.iconName(treeTd.item)"
             dense flat @click="treeTd.toggle(treeTd.item)">
      </q-btn>
      <span class="q-ml-sm">{{ treeTd.item[firstTd] }}</span>
    </div>
  </td>
</template>

<script setup>
import {toRefs} from 'vue';

const props = defineProps({
  treeTd: {
    type: Object,
    required: true,
  },
  firstTd: {
    type: String,
    required: true,
  }
})
const {treeTd, firstTd} = toRefs(props)
</script>
