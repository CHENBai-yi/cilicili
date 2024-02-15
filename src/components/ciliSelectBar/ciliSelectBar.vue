<template>
  <div class="  p-megamenu  p-component card ">
    <div class="row ">
      <div v-for="(group, index) in groupedItems" :key="index" class="p-megamenu-col-3 ">
        <ul class="p-submenu-list p-megamenu-submenu"
        >
          <!--          <li class="p-megamenu-submenu-header p-submenu-header">Home Theather-->
          <!--          </li>-->
          <li v-for="item in group" :key="item"
          >
            <div class="p-menuitem-content">
              <a class="p-menuitem-link"
              ><span
                class="p-menuitem-text">{{ item }}</span><span

                class="p-ink">

              </span>
              </a>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import {computed} from 'vue';

export default {
  props: {
    data: {
      type: Array,
      default: () => []
    }
  },
  setup(props) {
    const groupedItems = computed(() => {
      const groupSize = 4;
      return props.data.reduce((result, item, index) => {
        const groupIndex = Math.floor(index / groupSize);
        if (!result[groupIndex]) {
          result[groupIndex] = [];
        }
        result[groupIndex].push(item);
        return result;
      }, []);
    });

    return {
      groupedItems
    };
  }
};

</script>

<style lang="sass" scoped>
ul
  list-style: none
</style>
