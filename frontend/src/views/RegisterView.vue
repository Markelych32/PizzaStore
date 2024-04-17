<template>
  <div class="register">
    <form class="register-form">
      <h2 class="register-title">Регистрация</h2>
      <div class="fields">
        <input
          v-model="formData.name"
          type="text"
          class="name"
          placeholder="Имя"
          required
        />
        <input
          v-model="formData.surname"
          type="text"
          class="surname"
          placeholder="Фамилия"
        />
        <input
          v-model="formData.email"
          type="email"
          class="email"
          placeholder="example@pizza.ru"
          required
        />
        <div class="password-wrapper">
          <input
            @input="checkPasswords"
            v-model="formData.password"
            type="password"
            class="password"
            placeholder="Пароль"
            required
          />
          <i
            @click="changePasswordVisibility(0)"
            class="fa-regular fa-eye password-vis-btn"
          ></i>
        </div>
        <div class="password-wrapper">
          <input
            @input="checkPasswords"
            v-model="passwordAgain"
            type="password"
            class="repeat-password password"
            placeholder="Повторите пароль"
            required
          />
          <i
            @click="changePasswordVisibility(1)"
            class="fa-regular fa-eye password-vis-btn"
          ></i>
        </div>
      </div>
      <p class="password-valid"></p>
      <button @click.prevent="register()" class="submit-btn">
        Регистрация
      </button>
    </form>
  </div>
</template>

<script>
import AXIOS from "@/http-common.js";
export default {
  name: "Register",
  data() {
    return {
      formData: {
        name: "",
        surname: "",
        email: "",
        password: "",
      },
      passwordAgain: "",
    };
  },
  methods: {
    register() {
      const data = JSON.stringify(this.formData);
      const passwordsCorrect = this.checkPasswords();
      if (passwordsCorrect) {
        //AXIOS.post("http://localhost:9090/register", data);
      }
    },
    changePasswordVisibility(n) {
      const passwords = document.querySelectorAll(".password");
      const visiblePasswordBtn = document.querySelectorAll(".password-vis-btn");
      if (passwords[n].type === "password") {
        passwords[n].type = "text";
        visiblePasswordBtn[n].style.opacity = "1";
      } else {
        passwords[n].type = "password";
        visiblePasswordBtn[n].style.opacity = "0.3";
      }
    },
    checkPasswords() {
      const passwordValidStr = document.querySelector(".password-valid");
      const passwords = document.querySelectorAll(".password");
      if (passwords[1].value !== "") {
        if (passwords[0].value === passwords[1].value) {
          passwordValidStr.innerHTML = "Пароли совпадают";
          return true;
        } else {
          passwordValidStr.innerHTML = "Пароли не совпадают";
          return false;
        }
      }
      passwordValidStr.innerHTML = "";
    },
  },
};
</script>

<style lang="scss" scoped>
.register {
  font-family: Montserrat;
  display: flex;
  align-items: center;
  justify-content: center;
  .register-form {
    display: flex;
    flex-direction: column;
    align-items: start;
    .submit-btn {
      padding: 15px 25px;
      background-color: #f7d22d;
      border-radius: 8px;
      border: none;
      color: #ffffff;
      font-size: 17px;
      font-weight: 600;
      cursor: pointer;
      align-self: center;
      transition: 0.2s;
      &:hover {
        color: #231f20;
      }
    }
    .register-title {
      color: #f7d22d;
      font-size: 32px;
    }
    .fields {
      display: flex;
      flex-direction: column;
      align-items: start;
      input {
        font-family: Montserrat;
        width: 28vw;
        font-size: 16px;
        padding: 15px;
        margin-top: 20px;
        border-radius: 8px;
        border: 1px solid #aaaaaa;
        box-shadow: 0px 0px 2px #d3d3d3;
        color: #231f20;
        font-weight: 400;
        outline: none;
        &:focus {
          border: 1px solid #231f20;
        }
      }
      .password-wrapper {
        position: relative;
      }
    }
  }
  .fa-eye {
    position: absolute;
    right: 15px;
    top: 50%;
    color: #bbbbbb;
    cursor: pointer;
  }
}
</style>
