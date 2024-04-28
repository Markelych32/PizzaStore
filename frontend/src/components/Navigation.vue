<template>
  <nav>
    <router-link class="home" to="/"
      ><img
        src="@/assets/pizza-slice.svg"
        class="yellow"
        alt=""
        width="100px"
      />
      <h1>Pizza Store</h1></router-link
    >
    <div :key="this.authorized" class="container">
      <router-link class="cart" to="/cart"> Корзина </router-link>
      <router-link v-if="!this.authorized" class="login" to="/login"
        >Войти</router-link
      >
      <router-link v-if="!this.authorized" class="register" to="/register"
        >Регистрация</router-link
      >
      <button v-if="this.authorized" @click="logout()" class="logout">
        Выйти
      </button>
      <div v-if="this.authorized" class="user">Кабинет</div>
    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      authorized: localStorage.getItem("token"),
    };
  },
  methods: {
    logout() {
      localStorage.removeItem("token");
      localStorage.removeItem("userId");
      this.authorized = null;
    },
  },
};
</script>

<style lang="scss" scoped>
nav {
  .yellow {
    filter: invert(92%) sepia(73%) saturate(1324%) hue-rotate(329deg)
      brightness(98%) contrast(98%);
  }
  h1 {
    font-size: 36px;
    margin-left: 10px;
    color: #231f20;
  }
  padding-top: 20px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  font-family: Montserrat;
  font-weight: 700;
  font-size: 16px;
  .container {
    display: flex;
    width: 35%;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
  }
  .home {
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .cart {
    position: relative;
    padding: 10px 15px;
    background-color: #f7d22d;
    border-radius: 8px;
    .pizza-inside {
      position: absolute;
      right: -3px;
      top: -3px;
      width: 15px;
      height: 15px;
      border-radius: 50%;
      background-color: #f02e14;
    }
  }
  .login {
    color: #696f7a;
  }
  a {
    text-decoration: none;
    display: block;
    color: #231f20;
  }
  .logout {
    background: transparent;
    border: none;
    color: #696f7a;
    font-family: Montserrat;
    font-weight: 700;
    font-size: 16px;
    cursor: pointer;
  }
}
</style>
