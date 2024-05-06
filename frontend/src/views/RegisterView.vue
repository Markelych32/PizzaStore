<template>
  <div class="register">
    <form class="register-form">
      <h2 class="register-title">Регистрация</h2>
      <div class="fields">
        <input
          v-model="formData.first_name"
          type="text"
          class="name"
          placeholder="Имя"
          required
          pattern="[A-Za-zА-Яа-яЁё]{2,40}"
        />
        <input
          v-model="formData.last_name"
          type="text"
          class="surname"
          placeholder="Фамилия"
          pattern="[A-Za-zА-Яа-яЁё]{2,40}"
        />
        <input
          v-model="formData.email"
          type="email"
          class="email"
          placeholder="example@pizza.ru"
          required
          pattern="^\S+@\S+\.\S+$"
        />
        <div class="password-wrapper">
          <input
            @input="checkPasswords"
            v-model="formData.password"
            type="password"
            class="password"
            placeholder="Пароль"
            required
            pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,30}$"
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
            pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,30}$"
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
        email: "",
        password: "",
        first_name: "",
        last_name: "",
      },
      passwordAgain: "",
    };
  },
  methods: {
    checkInputs() {
      const requiredInputs = Array.from(document.querySelectorAll(":required"));
      for (const input of requiredInputs) {
        if (input.checkValidity() === false) return false;
      }
      return true;
    },
    register() {
      const data = JSON.stringify(this.formData);
      const passwordsCorrect = this.checkPasswords();
      if (passwordsCorrect && this.checkInputs()) {
        AXIOS.post("http://localhost:9090/auth/signup", data, {
          headers: {
            "Content-Type": "application/json",
          },
        }).then(() => {
          this.$router.push("/login");
        });
      } else {
        alert("Неверные данные!");
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
    box-shadow: 0px 4px 24px 2px rgba(34, 60, 80, 0.06);
    padding: 20px 40px;
    border-radius: 24px;
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
        &:invalid:not(:placeholder-shown) {
          border-color: red;
        }
        &:valid:not(:placeholder-shown) {
          border-color: green;
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
    opacity: 0.3;
  }
}
</style>
