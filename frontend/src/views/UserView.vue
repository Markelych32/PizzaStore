<template>
  <div class="user">
    <h1 class="user-title">Кабинет</h1>
    <div class="user-container">
      <h1 class="name">
        {{ this.response.first_name + " " + this.response.last_name }}
      </h1>
      <h2 class="email">{{ this.response.email }}</h2>
    </div>
    <div class="img-container">
      <img src="@/assets/pizza-cat.png" alt="" />
    </div>
  </div>
</template>

<script>
import AXIOS from "@/http-common.js";

export default {
  data() {
    return {
      response: {},
    };
  },
  created() {
    AXIOS.get(
      "http://localhost:9090/pizza-store/users/" +
        localStorage.getItem("userId"),
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    ).then((response) => {
      this.response = response.data;
    });
  },
};
</script>

<style lang="scss" scoped>
.user {
  font-family: Montserrat;
  .user-title {
    color: #f7d22d;
    font-size: 32px;
    font-weight: 800;
  }
  .img-container {
    width: 50%;
    margin: 0 auto;
    img {
      width: 100%;
      border-radius: 8px;
    }
  }
}
</style>
