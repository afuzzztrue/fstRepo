// pages/sort/sort.js
Page({
  data: {
    categories: [
      { name: '结构', content: '榫卯结构相关内容' },
      { name: '家具', content: '传统家具相关内容' },
      { name: '木料', content: '木料材质相关内容' },
      { name: '历史', content: '榫卯历史相关内容' },
      { name: '教程', content: '制作教程相关内容' }
    ],
    currentIndex: 0,
    categoryData: {
      0: [
        { image: '../images/结构-1.png', title: '半隐燕尾榫' },
        { image: '../images/结构-2.png', title: '全隐斗底槽' },
        { image: '../images/结构-3.png', title: '口袋榫' },
        { image: '../images/结构-4.png', title: '圆木哨' },
        { image: '../images/结构-5.png', title: '肩榫接合' },
        { image: '../images/结构-6.png', title: '木方平接' },
        { image: '../images/结构-7.png', title: '木板直角拼接' },
        { image: '../images/结构-8.png', title: '榫槽边角结合' },
        { image: '../images/结构-9.png', title: '方形木哨贯穿带结合' }
      ],
      1: [
        { image: '../images/家具-1.png', title: '椅子' },
        { image: '../images/家具-2.png', title: '桌子' },
        { image: '../images/家具-3.png', title: '桌子2' },
        { image: '../images/家具-4.png', title: '柜子' },
        { image: '../images/家具-5.png', title: '桌角' },
        { image: '../images/家具-6.png', title: '抽屉' }
      ],
      2: [
        { image: '../images/紫檀木.png', title: '紫檀木' },
        { image: '../images/黄花梨.png', title: '黄花梨' },
        { image: '../images/红木.png', title: '红木' },
        { image: '../images/楠木.png', title: '楠木' },
        { image: '../images/鸡翅木.png', title: '鸡翅木' },
        { image: '../images/黄杨木.png', title: '黄杨木' }
      ],
      3: [
        { image: '../images/唐代.png', title: '唐代建筑' },
        { image: '../images/宋代.png', title: '宋代家具' },
        { image: '../images/明清.png', title: '明清工艺' },
        { image: '../images/现代.png', title: '现代传承' }
      ],
      4: [
        { image: '../images/入门.png', title: '入门教程' },
        { image: '../images/进阶.png', title: '进阶教程' },
        { image: '../images/高级.png', title: '高级教程' },
        { image: '../images/大师.png', title: '大师讲堂' }
      ]
    },
    currentData: []
  },
  onLoad(options) {
    this.setData({
      currentData: this.data.categoryData[0]
    })
  },
  switchCategory(e) {
    const index = e.currentTarget.dataset.index
    this.setData({
      currentIndex: index,
      currentData: this.data.categoryData[index]
    })
  },
  onReady() {

  },
  onShow() {

  },
  onHide() {

  },
  onUnload() {

  },
  onPullDownRefresh() {

  },
  onReachBottom() {

  },
  onShareAppMessage() {

  }
})