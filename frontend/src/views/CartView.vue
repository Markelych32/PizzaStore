<template>
  <div class="cart">
    <h1 class="cart-title">Корзина</h1>
    <div v-if="!checkAuthorization()" class="unauthorized">
      Вы не авторизованы!
    </div>
    <div
      v-if="!this.response[0] && checkAuthorization()"
      :key="this.response"
      class="cart-empty"
    >
      Корзина пуста!
    </div>
    <div v-if="checkAuthorization()" class="orders-list">
      <OrderItem
        v-for="order in this.response"
        :key="order.id"
        :order="order"
      />
    </div>
  </div>
</template>

<script>
import AXIOS from "@/http-common.js";
import OrderItem from "@/components/OrderItem.vue";

export default {
  data() {
    return {
      response: {},
      userId: localStorage.getItem("userId"),
    };
  },
  components: {
    OrderItem,
  },
  methods: {
    checkAuthorization() {
      return localStorage.getItem("token");
    },
  },
  created() {
    if (localStorage.getItem("token")) {
      AXIOS.get(
        "http://localhost:9090/pizza-store/users/" + this.userId + "/pizzas",
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      ).then((response) => {
        this.response = response.data;
      });
    }
  },
};
</script>

<style lang="scss" scoped>
.cart-title {
  font-family: Montserrat;
  font-size: 32px;
  font-weight: 800;
  color: #f7d22d;
  margin-bottom: 10px;
}
.orders-list {
  display: flex;
  flex-direction: column;
  width: 80%;
  align-items: center;
  margin: 0 auto;
}
</style>
