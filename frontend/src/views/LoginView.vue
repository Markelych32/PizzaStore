<template>
  <div class="login">
    <form class="login-form">
      <h2 class="login-title">Войти</h2>
      <div class="fields">
        <input
          v-model="formData.username"
          type="email"
          class="email"
          placeholder="example@pizza.ru"
          required
        />
        <div class="password-wrapper">
          <input
            v-model="formData.password"
            type="password"
            class="password"
            placeholder="Пароль"
            required
          />
          <i
            @click="changePasswordVisibility()"
            class="fa-regular fa-eye password-vis-btn"
          ></i>
        </div>
      </div>
      <p class="password-valid"></p>
      <button @click.prevent="login()" class="submit-btn">Войти</button>
    </form>
  </div>
</template>

<script>
import AXIOS from "@/http-common.js";

export default {
  name: "login",
  data() {
    return {
      formData: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      const data = JSON.stringify(this.formData);
      AXIOS.post("http://localhost:9090/auth/signin", data, {
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => {
          console.log(response);
          localStorage.setItem("userId", response.data.user_id);
          localStorage.setItem("userEmail", response.data.email);
          localStorage.setItem("token", response.data.token);
          this.$router.push("/");
        })
        .catch((error) => {
          alert("Неверный логин или пароль!");
        });
    },
    changePasswordVisibility() {
      const password = document.querySelector(".password");
      const visiblePasswordBtn = document.querySelector(".password-vis-btn");
      if (password.type === "password") {
        password.type = "text";
        visiblePasswordBtn.style.opacity = "1";
      } else {
        password.type = "password";
        visiblePasswordBtn.style.opacity = "0.3";
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.login {
  margin-top: 80px;
  font-family: Montserrat;
  display: flex;
  align-items: center;
  justify-content: center;
  .login-form {
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
    .login-title {
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
    opacity: 0.3;
  }
}
</style>
