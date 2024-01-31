import config from "@/config";
import LoginLayout from "@/layouts/LoginLayout";
import Home from "@/pages/Home";
import Login from "@/pages/Login";
import Profile from "@/pages/Profile";

//Public routes
const publicRoutes = [
  { path: config.routes.home, component: Home },
  { path: config.routes.profile, component: Profile },
  { path: config.routes.login, component: Login, layout: LoginLayout },
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
