// pages/sort/sort.js
const app = getApp();
const baseUrl = app.globalData.baseUrl;

Page({
  data: {
    categories: [],
    currentIndex: 0,
    currentData: []
  },

  onLoad(options) {
    this.loadCategories();
  },

  loadCategories() {
    wx.request({
      url: baseUrl + '/api/category/list',
      success: res => {
        if (res.data.code === 200) {
          const categories = res.data.data;
          this.setData({ categories });
          if (categories.length > 0) {
            this.loadCategoryData(categories[0].categoryId);
          }
        }
      },
      fail: err => {
        console.error('获取分类失败', err);
      }
    });
  },

  loadCategoryData(categoryId) {
    wx.request({
      url: baseUrl + '/api/article/category/' + categoryId,
      success: res => {
        if (res.data.code === 200) {
          const currentData = res.data.data.map(item => ({
            image: item.coverImage,
            title: item.title
          }));
          this.setData({ currentData });
        }
      },
      fail: err => {
        console.error('获取分类内容失败', err);
      }
    });
  },

  switchCategory(e) {
    const index = e.currentTarget.dataset.index;
    const categoryId = this.data.categories[index].categoryId;
    this.setData({
      currentIndex: index
    });
    this.loadCategoryData(categoryId);
  },

  onReady() {},
  onShow() {},
  onHide() {},
  onUnload() {},
  onPullDownRefresh() {},
  onReachBottom() {},
  onShareAppMessage() {}
});