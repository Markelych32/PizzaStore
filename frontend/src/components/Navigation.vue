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
    <div class="container">
      <router-link class="cart" to="/cart">Корзина</router-link>
      <router-link class="login" to="/login">Войти</router-link>
      <router-link class="register" to="/register">Регистрация</router-link>
    </div>
  </nav>
</template>

<script>
export default {};
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
    width: 30%;
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
    padding: 10px 15px;
    background-color: #f7d22d;
    border-radius: 8px;
  }
  .login {
    color: #696f7a;
  }
  a {
    text-decoration: none;
    display: block;
    color: #231f20;
  }
}
</style>
