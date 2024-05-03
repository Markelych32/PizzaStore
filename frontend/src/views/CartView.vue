<template>
  <div class="cart">
    <div class="title">
      <h1 class="cart-title">Корзина</h1>
      <button v-if="this.response[0]" @click="clearCart()" class="clear-btn">
        <i class="fa-regular fa-trash-can"></i> Очистить корзину
      </button>
    </div>
    <router-link to="/login" v-if="!checkAuthorization()" class="warn">
      <h2>Вы не авторизованы! Войдите в аккаунт.</h2>
      <img src="@/assets/to-login.svg" class="yellow" alt="" />
    </router-link>
    <router-link
      to="/"
      v-if="!this.response[0] && checkAuthorization()"
      class="warn"
    >
      <h2>Ваша корзина пуста. Перейти к покупкам.</h2>
      <img src="@/assets/cart.svg" class="yellow" alt="" />
    </router-link>
    <div v-if="checkAuthorization() && this.response[0]" class="cart-items">
      <div class="orders-list">
        <OrderItem
          v-for="order in this.response"
          :key="order.id"
          :order="order"
        />
      </div>
      <div class="order-area">
        <h2>Ваша корзина</h2>
        <p>
          Количество товаров - {{ this.orderCount }} шт.
          <i class="pizza-icon fa-solid fa-pizza-slice"></i>
        </p>
        <h2>Сумма заказа: {{ this.orderAmount }} &#8381;</h2>
        <button @click="makeOrder()" class="make-order">Оформить заказ</button>
      </div>
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
      orderAmount: 0,
      orderCount: 0,
    };
  },
  components: {
    OrderItem,
  },
  methods: {
    updateCart() {
      this.orderAmount = 0;
      this.orderCount = 0;
      const amounts = document.querySelectorAll(".price");
      const counters = document.querySelectorAll(".amount");
      for (const amount of amounts) {
        this.orderAmount += Number(amount.innerText.split(" ")[0]);
      }
      for (const counter of counters) {
        this.orderCount += Number(counter.innerText);
      }
    },
    checkAuthorization() {
      return localStorage.getItem("token");
    },
    clearCart() {
      this.response = {};
      AXIOS.delete(
        "http://localhost:9090/pizza-store/user/" + this.userId + "/pizza",
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );
    },
    makeOrder() {
      AXIOS.post(
        "http://localhost:9090/pizza-store/user/" + this.userId + "/email",
        {},
        {
          headers: {
            Receiver: localStorage.getItem("userEmail"),
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      ).then(() => {
        this.clearCart();
        alert(
          "Заказ оформлен! На вашу почту отправлено письмо с подробной информацией."
        );
        location.reload();
      });
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
      )
        .then((response) => {
          this.response = response.data;
        })
        .catch((e) => {
          if (localStorage.getItem("token")) {
            alert("Время вашей сессии истекло!");
          }
        });
    }
  },
  mounted() {
    setTimeout(() => {
      this.updateCart();
      for (const element of document.querySelectorAll(".button")) {
        element.addEventListener("click", () => {
          this.updateCart();
        });
      }
      for (const element of document.querySelectorAll(".rem-item")) {
        element.addEventListener("click", () => {
          this.updateCart();
        });
      }
    }, 500);
  },
};
</script>

<style lang="scss" scoped>
.cart-items {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  .order-area {
    .pizza-icon {
      color: #f7d22d;
    }
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    position: sticky;
    top: 60px;
    width: 25%;
    padding: 20px;
    height: 350px;
    font-family: Montserrat;
    border-radius: 20px;
    transition: 0.1s;
    color: #0e0c0d;
    &:hover {
      box-shadow: 0px 2px 24px 2px #00000015;
    }
    h2 {
      font-size: 24px;
    }
    p {
      font-size: 16px;
    }
    .make-order {
      background-color: #f7d22d;
      border: none;
      font-family: Montserrat;
      font-size: 20px;
      width: 100%;
      padding: 20px 0;
      border-radius: 8px;
      font-weight: 600;
      cursor: pointer;
      color: #0e0c0d;
      transition: 0.1s;
      &:hover {
        box-shadow: 0px 2px 10px 2px #00000024;
      }
    }
  }
}
.unauthorized {
  h2 {
    color: #231f20;
    margin: 60px auto 40px;
    font-family: Montserrat;
    font-weight: 600;
    font-size: 24px;
    text-align: center;
  }
  text-decoration: none;
}
.yellow {
  filter: invert(92%) sepia(73%) saturate(1324%) hue-rotate(329deg)
    brightness(98%) contrast(98%);
  opacity: 0.7;
}
.warn {
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  width: 45%;
  h2 {
    color: #231f20;
    margin: 60px auto 40px;
    font-family: Montserrat;
    font-weight: 600;
    font-size: 24px;
    text-align: center;
  }
  text-decoration: none;
  img {
    margin: 0 auto;
    width: 40%;
  }
}
.title {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
  .cart-title {
    font-family: Montserrat;
    font-size: 32px;
    font-weight: 800;
    color: #f7d22d;
    margin-bottom: 10px;
  }
  .clear-btn {
    padding: 10px 15px;
    background-color: #ffffff;
    border-radius: 8px;
    border: 1px solid #231f20;
    font-size: 15px;
    font-family: Montserrat;
    font-weight: 600;
    cursor: pointer;
    &:active {
      background-color: #f3f3f3;
    }
  }
}
.orders-list {
  display: flex;
  flex-direction: column;
  width: 70%;
  align-items: center;
  margin: 0 0 auto;
  padding-bottom: 40px;
}
</style>
