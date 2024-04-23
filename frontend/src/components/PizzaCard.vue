<template>
  <div class="container">
    <div>
      <div class="img-container">
        <img :src="pizza.imgLink" alt="Pizza" />
      </div>
      <div class="name">
        <h2>{{ pizza.name }}</h2>
      </div>
    </div>
    <div class="description">
      <p>{{ pizza.description }}</p>
    </div>
    <div class="price">
      <h2>{{ pizza.price }} &#8381;</h2>
      <button @click="addToCart()" id="add-to-cart-btn">В корзину</button>
    </div>
  </div>
</template>

<script>
import AXIOS from "@/http-common.js";

export default {
  name: "Pizza",
  props: { pizza: {} },
  data() {
    return {
      userId: localStorage.getItem("userId"),
    };
  },
  methods: {
    addToCart() {
      AXIOS.post(
        "http://localhost:9090/pizza-store/user/" +
          this.userId +
          "/pizza/" +
          this.pizza.id,
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 8px;
  width: 260px;
  height: 460px;
  font-family: Montserrat;
  border-radius: 10px;
  box-shadow: 0px 0px 0px 0px transparent;
  &:hover {
    box-shadow: 0px 0px 20px 0px #e2e2e2;
  }
  img {
    width: 100%;
  }
  .name {
    color: #5e5e5e;
    font-weight: 800;
    h2 {
      margin: 0;
      font-size: 24px;
    }
  }
  .description {
    color: #686466;
    font-weight: 500;
    font-size: 13px;
    p {
      margin: 0;
    }
  }
  .price {
    color: #231f20;
    font-weight: 700;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    h2 {
      margin: 0;
    }
  }
  #add-to-cart-btn {
    background-color: #f7d22d;
    border: none;
    padding: 10px 20px;
    outline: none;
    border-radius: 8px;
    font-weight: 700;
    font-size: 14px;
    color: #fff;
    transition: 0.2s;
    cursor: pointer;
    &:hover {
      color: #473e43;
    }
  }
}
</style>
