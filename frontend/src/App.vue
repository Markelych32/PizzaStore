<template>
  <div>
    <Navigation />
    <router-view />
  </div>
</template>

<script>
import Navigation from "@/components/Navigation.vue";

export default {
  components: {
    Navigation,
  },
};
</script>

<style lang="scss">
body {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
::-webkit-scrollbar {
  width: 8px; /* ширина scrollbar */
}

::-webkit-scrollbar-track {
  background-color: transparent; /* цвет фона трека */
}

::-webkit-scrollbar-thumb {
  background-color: #666; /* цвет ползунка */
  border-radius: 6px; /* скругление углов ползунка */
}

::-webkit-scrollbar-thumb:hover {
  background-color: #444; /* цвет ползунка при наведении */
}
</style>
