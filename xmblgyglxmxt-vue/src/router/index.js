import { createRouter, createWebHistory } from 'vue-router'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    //前台一级路由
    {path: '/', name: 'front',redirect:'/front/index', component: () => import('../views/FrontView.vue'),
      children:[//前台二级路由
        {path: '/front/memberLogin', name: 'memberLogin',meta: {name: '用户登录' }, component: () => import('../views/front/memberLoginView.vue')},
        {path: '/front/memberReg', name: 'memberReg',meta: {name: '用户注册' }, component: () => import('../views/front/memberRegView.vue')},
        {path: '/front/memberpreson', name: 'memberpreson',meta: {qTis:true, name: '个人信息编辑' }, component: () => import('../views/front/memberpresonView.vue')},
        {path: '/front/memberpass', name: 'memberpass',meta: {qTis:true, name: '个人密码编辑' }, component: () => import('../views/front/memberpassView.vue')},
        {path: '/front/index', name: 'index',meta: {name: '主页' }, component: () => import('../views/front/indexView.vue')},
        {path: '/front/messagelist', name: 'messagelist',meta: {name: '反馈建议信息列表' }, component: () => import('../views/front/messagelistView.vue')},
        {path: '/front/newslist', name: 'newslist',meta: {name: '新闻资讯信息列表' }, component: () => import('../views/front/newslistView.vue')},
        {path: '/front/newsDateil', name: 'newsDateil',meta: {name: '新闻资讯信息详情' }, component: () => import('../views/front/newsDateilView.vue')},
        {path: '/front/xinxilist', name: 'xinxilist',meta: {name: '公寓信息' }, component: () => import('../views/front/xinxilistView.vue')},
        {path: '/front/xinxiDateil', name: 'xinxiDateil',meta: {name: '公寓信息详情' }, component: () => import('../views/front/xinxiDateilView.vue')},
        {path: '/front/shenqinglist', name: 'shenqinglist',meta: {name: '公寓申请信息' }, component: () => import('../views/front/shenqinglistView.vue')},
      ]},

      //后台一级路由
    {path: '/login', name: 'login',meta: { name: '登录' }, component: () => import('../views/loginView.vue')},
    {path: '/register', name: 'register',meta: { name: '注册' }, component: () => import('../views/registerView.vue')},
    {path: '/manager', name: 'manager',redirect:'/manager/home', component: () => import('../views/ManagerView.vue'),
    children:[//后台二级路由
      {path: '/manager/home', name: 'home',meta: {isAuth:true, name: '首页' }, component: () => import('../views/manager/HomeView.vue')},
      {path: '/manager/imgadv', name: 'imgadv',meta: {isAuth:true, name: '轮播图信息列表' }, component: () => import('../views/manager/imgadvView.vue')},
      {path: '/manager/news', name: 'news',meta: {isAuth:true, name: '新闻资讯信息列表' }, component: () => import('../views/manager/newsView.vue')},
      {path: '/manager/links', name: 'links',meta: {isAuth:true, name: '友情链接信息列表' }, component: () => import('../views/manager/linksView.vue')},
      {path: '/manager/admin', name: 'admin',meta: {isAuth:true, name: '员工信息列表' }, component: () => import('../views/manager/adminView.vue')},
      {path: '/manager/member', name: 'member',meta: {isAuth:true, name: '用户信息列表' }, component: () => import('../views/manager/memberView.vue')},
      {path: '/manager/person', name: 'person',meta: {isAuth:true, name: '个人信息' }, component: () => import('../views/manager/personView.vue')},
      {path: '/manager/passuedit', name: 'passuedit',meta: {isAuth:true, name: '密码编辑' }, component: () => import('../views/manager/passueditView.vue')},
      {path: '/manager/protype', name: 'protype',meta: {isAuth:true, name: '分类信息列表' }, component: () => import('../views/manager/protypeView.vue')},
      {path: '/manager/xinxi', name: 'xinxi',meta: {isAuth:true, name: '公寓信息列表' }, component: () => import('../views/manager/xinxiView.vue')},
      {path: '/manager/shuju', name: 'shuju',meta: {isAuth:true, name: '违规记录信息列表' }, component: () => import('../views/manager/shujuView.vue')},
      {path: '/manager/message', name: 'message',meta: {isAuth:true, name: '反馈建议信息列表' }, component: () => import('../views/manager/messageView.vue')},
      {path: '/manager/shenqing', name: 'shenqing',meta: {isAuth:true, name: '公寓申请管理' }, component: () => import('../views/manager/shenqingView.vue')},
    ]},


  ],
})

//前置路由守卫
router.beforeEach((to, from, next) => {
  //document.title = to.meta.name;
const admin = JSON.parse(localStorage.getItem('admin') || '{}')
  //console.log(JSON.stringify(admin))
  //后台管理路由鉴权
  if (to.meta.isAuth){
    if (JSON.stringify(admin)!=='{}'){
      next();
    }else{
      next('/login');
    }
  }else {
    next();
  }
});
//前置路由守卫
router.beforeEach((to, from, next) => {
  //document.title = to.meta.name;
  const member = JSON.parse(localStorage.getItem('member') || '{}')
  //console.log(JSON.stringify(member.status))
  //前台台管理路由鉴权
  if (to.meta.qTis){
    if (JSON.stringify(member)!=='{}'){
      next();
    }else{

      next('/front/memberLogin');
    }
  }else {
    next();
  }
});

//后置路由守卫
router.afterEach((to,from)=>{
  document.title = to.meta.name || '白领公寓管理系统';
})

export default router
