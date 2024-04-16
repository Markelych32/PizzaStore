<template>
  <div class="home">
    <img class="pizza-man" src="../assets/pizza-man.png" alt="" />
    <img class="pasta-woman" src="../assets/pasta-woman.png" alt="" />
    <h1>Пицца</h1>
    <div class="pizza-list">
      <PizzaCard v-for="item in 10" :key="item.id" />
    </div>
  </div>
</template>

<script>
import PizzaCard from "@/components/PizzaCard.vue";

export default {
  name: "HomeView",
  components: {
    PizzaCard,
  },
};
</script>

<style lang="scss" scoped>
.home {
  width: 80%;
  margin: 0 auto;
  h1 {
    font-family: Montserrat;
    font-size: 32px;
    font-weight: 800;
    color: #f7d22d;
    margin-bottom: 10px;
  }
  .pizza-list {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    justify-items: center;
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
</style>
