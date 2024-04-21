<template>
  <div class="home">
    <h1>Пицца</h1>
    <div class="pizza-list">
      <PizzaCard
        v-for="pizza in this.response"
        :key="pizza.id"
        :pizza="pizza"
      />
    </div>
  </div>
</template>

<script>
import PizzaCard from "@/components/PizzaCard.vue";
import AXIOS from "@/http-common.js";

export default {
  name: "HomeView",
  components: {
    PizzaCard,
  },
  data() {
    return {
      response: [],
      errors: [],
    };
  },
  methods: {
    axiosGetPizzas() {
      AXIOS.get("http://localhost:9090/pizza-store/pizzas")
        .then((response) => {
          this.response = response.data;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },
  },
  created() {
    this.axiosGetPizzas();
  },
};
</script>

<style lang="scss" scoped>
.home {
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
    grid-gap: 10px;
    padding-bottom: 70px;
  }
}
</style>
