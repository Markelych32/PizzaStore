<template>
  <div class="order-item">
    <div class="img-container">
      <img :src="this.order.imgLink" alt="Pizza" />
    </div>
    <div class="order-text">
      <div class="order-name">{{ this.order.name }}</div>
      <div class="description">{{ this.order.description }}</div>
    </div>
    <div class="counter-container">
      <button @click="subOne($event)" class="sub-one">–</button>
      <div class="amount">{{ this.amount }}</div>
      <button @click="addOne($event)" class="add-one">+</button>
    </div>
    <div class="price">{{ this.order.price * this.amount }} &#8381;</div>
    <i @click="removeItem($event)" class="fa-regular fa-circle-xmark"></i>
  </div>
</template>

<script>
import AXIOS from "@/http-common";

export default {
  name: "OrderItem",
  props: { order: {} },
  data() {
    return {
      amount: 1,
    };
  },
  methods: {
    removeItem(event) {
      event.target.closest(".order-item").style.display = "none";
      AXIOS.delete(
        "http://localhost:9090/pizza-store/user/" +
          localStorage.getItem("userId") +
          "/pizza/" +
          this.order.id,
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );
    },
    addOne(event) {
      if (this.amount >= 1 && this.amount < 20) this.amount++;
      if (this.amount === 20) {
        event.target.disabled = true;
        event.target.classList.add("disabled");
      }
      const subOne = event.target.previousSibling.previousSibling;
      subOne.disabled = false;
      subOne.classList.remove("disabled");
    },
    subOne(event) {
      if (this.amount > 1) this.amount--;
      else {
        event.target.disabled = true;
        event.target.classList.add("disabled");
      }
      if (this.amount <= 20) {
        const addOne = event.target.nextSibling.nextSibling;
        addOne.disabled = false;
        addOne.classList.remove("disabled");
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.img-container {
  width: 22%;
  img {
    width: 100%;
    border-radius: 50%;
  }
}
.order-pizza {
  display: inline-flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  gap: 10px;
}
.order-item {
  font-family: Montserrat;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding-right: 30px;
  border-radius: 20px;
  transition: 0.1s;
  margin-bottom: 20px;
  &:hover {
    box-shadow: 0px 2px 24px 2px #00000015;
  }

  .order-text {
    max-width: 400px;
  }
  .order-name {
    color: #0e0c0d;
    font-weight: 800;
    font-size: 24px;
    margin-bottom: 20px;
  }
  .description {
    color: #686466;
    font-weight: 500;
    font-size: 13px;
  }
  .price {
    color: #f7d22d;
    font-size: 26px;
    font-weight: 800;
    width: 120px;
    text-align: center;
  }
  .counter-container {
    display: flex;
    flex-direction: row;
    align-items: center;
    background: #f3f3f7;
    border-radius: 8px;
    button {
      background: none;
      border: none;
      padding: 10px 15px;
      color: #696f7a;
      font-weight: 700;
      font-size: 20px;
      cursor: pointer;
    }
    .amount {
      text-align: center;
      width: 30px;
      font-size: 20px;
      color: #696f7a;
      font-weight: 700;
    }
    .disabled {
      color: #9da5b3;
    }
  }
  .fa-circle-xmark {
    font-size: 30px;
    transition: 0.1s;
    cursor: pointer;
    color: #696f7a;
    &:hover {
      color: #f7d22d;
    }
  }
}
</style>
