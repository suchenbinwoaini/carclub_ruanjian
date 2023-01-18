import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login";
import HomeView from "@/views/home/HomeView";
import ManageHome from "@/views/home/ManageHome";
Vue.use(VueRouter)
import store from "@/store";

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/employeeHome',
    name: 'HomeView',
    component: HomeView,
    redirect:'/home1',
    children: [
      {
        path: '/home1',
        name:'首页',
        component:() => import('../views/home/Home')
      },
      {
        path: '/money1',
        name:'缴费管理',
        component:() => import('../views/system/Money')
      },
      {
        path: '/caract1',
        name: '车务活动',
        component:() => import('../views/command/Caract')
      },
      {
        path: '/command1',
        name:'车务提醒',
        component:() => import('../views/command/Command')
      },
      {
        path: '/traffic1',
        name: '车务待办',
        component:() => import('../views/command/Traffic')
      },
      {
        path: '/maps1',
        name:'地图',
        component:() => import('../views/help/map')
      },
      {
        path: '/download1',
        name:'下载',
        component:() => import('../views/download')
      },
      {
        path: '/rescuerecords1',
        name: '救援记录',
        component:() => import('../views/help/RescueRecords')
      },
      {
        path: '/carinfo1',
        name: '救援车辆',
        component:() => import('../views/help/carinfo')
      },
      {
        path:'/travelinfo1',
        name: '旅行记录',
        component:() => import('../views/travell/travellinfo')
      },
      {
        path: '/travel1',
        name:'旅行信息',
        component:()=>import('../views/travell/travel')
      },
      {
        path: '/Vip1',
        name: '会员信息',
        component:() => import('../views/system/Vip')
      },
      {
        path: '/helps1',
        name:'帮助界面',
        component:() => import('../views/helps')
      }
    ]
  },
  {
    path: '/Admin',
    name:'管理员界面',
    component: () =>import('../views/home/Admin'),
    children: [
      {
        path: '/ahome',
        name:'主页',
        component:() => import('../views/home/Home')
      },
      {
        path: '/auser',
        name: '用户管理',
        component:() => import('../views/department/User')
      },
      {
        path: '/depart',
        name: '部门管理',
        component:() => import('../views/department/department')
      }
    ]
  },
  {
    path: '/manageHome',
    name: 'manage',
    component: ManageHome,
    redirect:'/home',
    children:[
      {
        path: '/home',
        name:'首页',
        component:() => import('../views/home/Home')
      },
      {
        path: '/user',
        name: '用户管理',
        component:() => import('../views/department/User')
      },
      {
        path: '/money',
        name:'缴费管理',
        component:() => import('../views/system/Money')
      },
      {
        path: '/caract',
        name: '车务活动',
        component:() => import('../views/command/Caract')
      },
      {
        path: '/command',
        name:'车务提醒',
        component:() => import('../views/command/Command')
      },
      {
        path: '/charts',
        name:'统计报表',
        component:() => import('../views/statistics/charts')
      },
      {
        path: '/Qcharts',
        name:'统计报表',
        component:() => import('../views/statistics/Qcharts')
      },
      {
        path: '/Mcharts',
        name:'统计报表',
        component:() => import('../views/statistics/Mcharts')
      },
      {
        path: '/download',
        name:'下载',
        component:() => import('../views/download')
      },
      {
        path: '/traffic',
        name: '服务信息',
        component:() => import('../views/command/Traffic')
      },
      {
        path: '/maps',
        name:'地图',
        component:() => import('../views/help/map')
      },
      {
        path: '/rescuerecords',
        name: '救援记录',
        component:() => import('../views/help/RescueRecords')
      },
      {
        path: '/carinfo',
        name: '救援车辆',
        component:() => import('../views/help/carinfo')
      },
      {
        path:'/travelinfo',
        name: '旅行记录',
        component:() => import('../views/travell/travellinfo')
      },
      {
        path: '/travel',
        name:'旅行信息',
        component:()=>import('../views/travell/travel')
      },
      {
        path: '/Vip',
        name: '会员信息',
        component:() => import('../views/system/Vip')
      },
      {
        path: '/helps',
        name:'帮助界面',
        component:() => import('../views/helps')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称，为了在Header组件中去使用
  store.commit("setPath")  // 触发store的数据更新
  next()  // 放行路由
})

export default router
