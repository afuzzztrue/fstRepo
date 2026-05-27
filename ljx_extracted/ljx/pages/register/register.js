// pages/register/register.js
const app = getApp();
const baseUrl = app.globalData.baseUrl;

Page({
  data: {
    account: '',
    password: '',
    confirmPassword: '',
    nickname: ''
  },

  inputAccount(e) {
    this.setData({ account: e.detail.value });
  },

  inputPassword(e) {
    this.setData({ password: e.detail.value });
  },

  inputConfirmPassword(e) {
    this.setData({ confirmPassword: e.detail.value });
  },

  inputNickname(e) {
    this.setData({ nickname: e.detail.value });
  },

  doRegister() {
    const { account, password, confirmPassword, nickname } = this.data;
    if (!account || !password) {
      wx.showToast({ title: '请填写完整信息', icon: 'none' });
      return;
    }
    if (password !== confirmPassword) {
      wx.showToast({ title: '两次密码不一致', icon: 'none' });
      return;
    }
    wx.request({
      url: baseUrl + '/api/user/register',
      method: 'POST',
      data: {
        account,
        password,
        nickname: nickname || account
      },
      success: res => {
        if (res.data.code === 200) {
          wx.showToast({ title: '注册成功', icon: 'success' });
          setTimeout(() => {
            wx.navigateTo({ url: '/pages/login/login' });
          }, 1000);
        } else {
          wx.showToast({ title: res.data.message, icon: 'none' });
        }
      },
      fail: err => {
        wx.showToast({ title: '注册失败', icon: 'none' });
      }
    });
  },

  goToLogin() {
    wx.navigateTo({
      url: '/pages/login/login'
    });
  }
});
