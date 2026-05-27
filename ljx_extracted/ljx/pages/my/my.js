// pages/my/my.js
const app = getApp();
const baseUrl = app.globalData.baseUrl;

Page({
  data: {
    userInfo: null,
    isLogin: false,
    followCount: 0,
    followerCount: 0
  },

  onShow() {
    const userInfo = wx.getStorageSync('userInfo');
    if (userInfo) {
      this.setData({
        userInfo,
        isLogin: true
      });
      this.loadFollowCount(userInfo.userId);
    } else {
      this.setData({
        userInfo: null,
        isLogin: false
      });
    }
  },

  loadFollowCount(userId) {
    wx.request({
      url: baseUrl + '/api/follow/count/' + userId,
      success: res => {
        if (res.data.code === 200) {
          this.setData({
            followCount: res.data.data.followCount,
            followerCount: res.data.data.followerCount
          });
        }
      }
    });
  },

  goToLogin() {
    wx.navigateTo({
      url: '/pages/login/login'
    });
  },

  logout() {
    wx.removeStorageSync('userInfo');
    app.globalData.userInfo = null;
    this.setData({
      userInfo: null,
      isLogin: false,
      followCount: 0,
      followerCount: 0
    });
    wx.showToast({ title: '已退出登录', icon: 'success' });
  },

  onLoad(options) {},
  onReady() {},
  onHide() {},
  onUnload() {},
  onPullDownRefresh() {},
  onReachBottom() {},
  onShareAppMessage() {}
});
