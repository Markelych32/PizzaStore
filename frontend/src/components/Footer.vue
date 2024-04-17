<template>
  <div class="footer">
    <router-link class="home" to="/"
      ><img src="@/assets/pizza-slice.svg" class="yellow" alt="" width="60px" />
      <h1>Pizza Store</h1></router-link
    >
    <div class="footer-content">
      <div class="info">
        <div class="other">
          <h3>Калорийность и состав</h3>
          <h3>Правовая информация</h3>
        </div>
        <div>
          <h3>Мы в соцсетях</h3>
          <div class="socials">
            <p>YouTube</p>
            <p>Telegram</p>
            <p>ВКонтакте</p>
            <p>WhatsApp</p>
          </div>
        </div>
        <div class="copyright">
          <p>Powered by Vue 3 & SpringBoot</p>
          <p>AMTeam Все праав защищены © 2024</p>
        </div>
      </div>
      <div class="map">
        <h3>Мы на карте</h3>
        <iframe
          src="https://yandex.ru/map-widget/v1/?um=constructor%3Ad0f7a9176026dc5eceea5d9691b66ba73b1386640eefacaa4a32c05d96da254f&amp;source=constructor"
          width="100%"
          height="200"
          frameborder="0"
        ></iframe>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.footer {
  display: flex;
  flex-direction: column;
  font-family: Montserrat;
  padding-bottom: 70px;
  .footer-content {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .yellow {
    filter: invert(92%) sepia(73%) saturate(1324%) hue-rotate(329deg)
      brightness(98%) contrast(98%);
  }
  .home {
    display: flex;
    flex-direction: row;
    align-items: center;
    text-decoration: none;
    padding-bottom: 10px;
    h1 {
      font-size: 24px;
      margin-left: 10px;
      color: #231f20;
    }
  }
  .socials {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    p {
      font-size: 16px;
      font-weight: 600;
      color: #545454;
    }
  }
  .other {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .info {
    width: 35%;
    h3 {
      color: #0e0c0d;
      font-size: 16px;
    }
    .copyright {
      color: #545454;
      font-size: 14px;
      font-weight: 600;
      margin-top: 30px;
    }
  }
  .map {
    width: 50%;
    h3 {
      color: #0e0c0d;
      font-size: 16px;
    }
  }
}
</style>
