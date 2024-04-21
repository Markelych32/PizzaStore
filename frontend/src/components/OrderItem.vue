<template>
  <div class="order-item">
    <div class="order-name">{{ orderName }}</div>
    <div class="price">{{ price }}</div>
    <img :src="imgLink" alt="" />
  </div>
</template>

<script>
export default {
  name: "OrderItem",
  props: { orderName: "", price: "", imgLink: "" },
};
</script>

<style lang="scss" scoped></style>
