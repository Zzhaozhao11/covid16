const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer: {
    host: "localhost", // 启动服务器域名
    port: "1234", // 启动服务器端口号
    open: true, // 是否自动打开浏览器
  },
})
