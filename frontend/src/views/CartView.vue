<template>
  <div class="cart">
    <OrderItem orderName="Пицца" price="500" imgLink="" />
  </div>
</template>

<script>
import OrderItem from "@/components/OrderItem.vue";

export default {
  components: {
    OrderItem,
  },
};
</script>

<style lang="scss" scoped></style>
