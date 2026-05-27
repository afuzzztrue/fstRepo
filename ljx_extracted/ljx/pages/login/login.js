// pages/login/login.js
const app = getApp();

Page({
  data: {
    account: '',
    password: ''
  },

  inputAccount(e) {
    this.setData({ account: e.detail.value });
  },

  inputPassword(e) {
    this.setData({ password: e.detail.value });
  },

  doLogin() {
    const { account, password } = this.data;
    if (!account || !password) {
      wx.showToast({ title: '请输入账号和密码', icon: 'none' });
      return;
    }
    const baseUrl = app.globalData.baseUrl;
    wx.request({
      url: baseUrl + '/api/user/login',
      method: 'POST',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data: `account=${encodeURIComponent(account)}&password=${encodeURIComponent(password)}`,
      success: res => {
        if (res.data.code === 200) {
          wx.setStorageSync('userInfo', res.data.data);
          app.globalData.userInfo = res.data.data;
          wx.showToast({ title: '登录成功', icon: 'success' });
          setTimeout(() => {
            wx.switchTab({ url: '/pages/my/my' });
          }, 1000);
        } else {
          wx.showToast({ title: res.data.message, icon: 'none' });
        }
      },
      fail: err => {
        wx.showToast({ title: '登录失败', icon: 'none' });
      }
    });
  },

  goToRegister() {
    wx.navigateTo({
      url: '/pages/register/register'
    });
  }
});
