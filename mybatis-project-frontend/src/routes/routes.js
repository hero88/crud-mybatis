import config from "@/config";
import LoginLayout from "@/layouts/LoginLayout";
import ProfileLayout from "@/layouts/ProfileLayout";
import Home from "@/pages/Home";
import ListUser from "@/pages/ListUser";
import Login from "@/pages/Login";
import Profile from "@/pages/Profile";

//Public routes
const publicRoutes = [
  { path: config.routes.home, component: Home },
  { path: config.routes.profile, component: Profile, layout: ProfileLayout },
  { path: config.routes.listUser, component: ListUser, layout: ProfileLayout },
  { path: config.routes.login, component: Login, layout: LoginLayout },
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
