const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: "dist",
  assetsDir: "static",
  devServer: {
    port: 8081, // your desired port number
  },
});
