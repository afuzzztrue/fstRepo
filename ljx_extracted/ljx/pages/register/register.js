// pages/register/register.js
const app = getApp();

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
    const baseUrl = app.globalData.baseUrl;
    console.log('baseUrl:', baseUrl);
    wx.request({
      url: baseUrl + '/api/user/register',
      method: 'POST',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data: {
        account: account,
        password: password,
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
        console.error('注册请求失败:', err);
        wx.showToast({ title: '注册失败: ' + (err.errMsg || '网络错误'), icon: 'none' });
      }
    });
  },

  goToLogin() {
    wx.navigateTo({
      url: '/pages/login/login'
    });
  }
});
