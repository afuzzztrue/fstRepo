// index.js
const app = getApp();
console.log(app,899)

Page({
  data: {
    dataList:[],
    bannerList:[
      '../images/轮播-1.jpg',
      '../images/轮播-2.jpg',
      '../images/轮播-3.jpg',
      '../images/轮播-4.jpg'
    ],
    hotList:[
      '../images/热门内容-1.jpg',
      '../images/热门内容-2.jpg',
      '../images/热门内容-3.jpg',
      '../images/热门内容-4.jpg'
    ]
    },
  //加载
  onLoad:function(){
    console.log('index加载页面的时候触发-onLoad')

    wx.request({
      url: 'http://tingapi.ting.baidu.com/v1/restserver/ting',
      data:{
        mothod:'baidu.ting.billboard.billList',
        type: 1,
        size:10,
        offset:0
      },
      success:res=>{
        //console.log(res,999);
        this.setData({
          dataList:res.data.list
        })
      }
    })
  },
 onShow(){
   console.log('index进入页面-onshow')
 },

 onHide(){
   console.log('index离开页面-onHide')
 },

 onReady(){
   console.log('index页面渲染完成-onReady')
 },

 onPullDownRefresh(){
   //重新获取第一页的数据
   console.log('index下拉刷新页面，获取最新数据-onPullDownRefresh')
 }
}
)
