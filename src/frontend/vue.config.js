module.exports = {
  lintOnSave: false,
  runtimeCompiler: true,
  devServer: {
    port: 3000,
    proxy: {
        '/api': {
            target: 'http://localhost:8080',
            ws: true,
            changeOrigin: true
        }
    }
}, 
  configureWebpack: {    
    resolve: {
       symlinks: false
    }
  },
  transpileDependencies: [
    '@coreui/utils',
    '@coreui/vue'
  ]
}
