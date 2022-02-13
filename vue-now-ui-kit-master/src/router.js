import Vue from "vue";
import VueRouter from "vue-router";

import Login from "./views/accounts/Login.vue";
import Register from "./views/accounts/Register.vue";
import ProfileEdit from "./views/accounts/ProfileEdit.vue";
import StudyBoard from "./views/study/StudyBoard.vue";
import Main from "./views/study/Main.vue";
import CreateStudyBoard from "./views/study/CreateStudyBoard.vue";
import MyPage from "./views/accounts/MyPage.vue";
import Admin from "./views/admins/Admin.vue";
import StudyRoom from "./views/study/StudyRoom.vue";
import StudyBoardUpdate from "./views/study/UpdateStudyBoard.vue";
import StudyBoardDetail from "./views/study/StudyBoardDetail.vue";
import fullCalendar from "vue-fullcalendar";
import Begin from "./views/accounts/Begin.vue";

Vue.component("full-calendar", fullCalendar);

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Begin",
    component: Begin,
  },
  {
    path: "/user/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/user/register",
    name: "Register",
    component: Register,
  },
  {
    path: "/user/modify/:user_no",
    name: "ProfileEdit",
    component: ProfileEdit,
  },
  {
    path: "/study/studyboard",
    name: "StudyBoard",
    component: StudyBoard,
  },
  {
    path: "/study/main",
    name: "Main",
    component: Main,
  },
  {
    path: "/study/studyroom/:study_no",
    name: "StudyRoom",
    component: StudyRoom,
  },
  {
    path: "/board/create/",
    name: "CreateStudyBoard",
    component: CreateStudyBoard,
  },
  {
    path: "/user/detail/",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/admin/userlist/",
    name: "Admin",
    component: Admin,
  },
  {
    path: `/study/board/detail/:boardno`,
    name: "StudyBoardDetail",
    component: StudyBoardDetail,
  },
  {
    path: `/study/board/update/:boardno`,
    name: "StudyBoardUpdate",
    component: StudyBoardUpdate,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

// import Index from './pages/Index.vue';
// import Landing from './pages/Landing.vue';
// import Login from './pages/Login.vue';
// import Profile from './pages/Profile.vue';
// import MainNavbar from './layout/MainNavbar.vue';
// import MainFooter from './layout/MainFooter.vue';

// Vue.use(VueRouter);

// export default new VueRouter({
//   linkExactActiveClass: 'active',
//   routes: [
//     {
//       path: '/',
//       name: 'index',
//       components: { default: Index, header: MainNavbar, footer: MainFooter },
//       props: {
//         header: { colorOnScroll: 400 },
//         footer: { backgroundColor: 'black' }
//       }
//     },
//     {
//       path: '/landing',
//       name: 'landing',
//       components: { default: Landing, header: MainNavbar, footer: MainFooter },
//       props: {
//         header: { colorOnScroll: 400 },
//         footer: { backgroundColor: 'black' }
//       }
//     },
//     {
//       path: '/login',
//       name: 'login',
//       components: { default: Login, header: MainNavbar },
//       props: {
//         header: { colorOnScroll: 400 }
//       }
//     },
//     {
//       path: '/profile',
//       name: 'profile',
//       components: { default: Profile, header: MainNavbar, footer: MainFooter },
//       props: {
//         header: { colorOnScroll: 400 },
//         footer: { backgroundColor: 'black' }
//       }
//     }
//   ],
//   scrollBehavior: to => {
//     if (to.hash) {
//       return { selector: to.hash };
//     } else {
//       return { x: 0, y: 0 };
//     }
//   }
// });
