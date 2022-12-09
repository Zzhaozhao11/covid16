const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,  // 是否开启eslint保存检测
  publicPath:'./', //根路径防止打包后出现空白
  devServer: {
    host: "127.0.255.255", // 启动服务器域名
    port: "1234", // 启动服务器端口号
    open: true, // 是否自动打开浏览器
  },
})
