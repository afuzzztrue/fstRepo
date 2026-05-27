// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        if (res.code) {
          wx.request({
            url: this.globalData.baseUrl + '/api/user/wxLogin',
            method: 'POST',
            data: {
              code: res.code,
              openid: 'wx_' + res.code
            },
            success: loginRes => {
              if (loginRes.data.code === 200) {
                wx.setStorageSync('userInfo', loginRes.data.data)
                this.globalData.userInfo = loginRes.data.data
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    baseUrl: 'http://localhost:8081'
  }
})
