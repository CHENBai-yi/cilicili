<template>
  <div class="  p-megamenu  p-component card ">
    <div class="row ">
      <div v-for="item in 20" class="p-megamenu-col-3 ">
        <ul class="p-submenu-list p-megamenu-submenu"
        >
          <li class="p-megamenu-submenu-header p-submenu-header"
          >Home Theather
          </li>
          <li
          >
            <div class="p-menuitem-content"><a class="p-menuitem-link"
            ><span
              class="p-menuitem-text">Projector</span><span

              class="p-ink"></span></a></div>
          </li>

          <li

          >
            <div class="p-menuitem-content"><a class="p-menuitem-link"


            ><span
              class="p-menuitem-text">Speakers</span><span

              class="p-ink"></span></a></div>
          </li>

          <li
          >
            <div class="p-menuitem-content"><a class="p-menuitem-link"
            ><span
              class="p-menuitem-text">TVs</span><span
              class="p-ink"></span></a></div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ciliSelectBar"
}
</script>

<style lang="sass" scoped>
ul
  list-style: none
</style>
