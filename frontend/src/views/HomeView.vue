<template>
  <div class="home">
    <div class="title">
      <h1>Пицца</h1>
      <div class="sorting">
        <button @click="sortByName()" class="sort-btn">По названию</button>
        <button @click="sortByPrice()" class="sort-btn">По цене</button>
        <button @click="sortByDefault()" class="sort-btn">По умолчанию</button>
      </div>
    </div>
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
  .title {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    h1 {
      font-family: Montserrat;
      font-size: 32px;
      font-weight: 800;
      color: #f7d22d;
      margin-bottom: 10px;
    }
    .sorting {
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
      gap: 15px;
      .sort-btn {
        padding: 10px 15px;
        background-color: #f7d22d;
        border-radius: 8px;
        border: none;
        font-family: Montserrat;
      }
    }
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
