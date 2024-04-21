<template>
  <div class="wrapper">
    <img class="pizza-man" src="@/assets/pizza-man.png" alt="" />
    <img class="pasta-woman" src="@/assets/pasta-woman.png" alt="" />
    <div class="content-wrapper">
      <Navigation />
      <router-view />
    </div>
    <Footer />
  </div>
</template>

<script>
import Navigation from "@/components/Navigation.vue";
import Footer from "@/components/Footer.vue";

export default {
  components: {
    Navigation,
    Footer,
  },
};
</script>

<style lang="scss">
body {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.wrapper {
  max-width: 80%;
  margin: 0 auto;
  .content-wrapper {
    min-height: 100vh;
  }
  .pizza-man {
    position: fixed;
    width: 230px;
    rotate: 15deg;
    left: -105px;
    top: 100px;
  }
  .pasta-woman {
    position: fixed;
    width: 230px;
    rotate: -20deg;
    right: -107px;
    bottom: 100px;
    transform: scale(-1, 1);
  }
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
