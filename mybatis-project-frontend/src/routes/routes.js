import config from "@/config";
import ProfileLayout from "@/layouts/ProfileLayout";
import Coin from "@/pages/Coin";
import Home from "@/pages/Home";
import ListUser from "@/pages/ListUser";
import Profile from "@/pages/Profile";

import AuthLayout from "@/layouts/AuthLayout";
import SignIn from "@/pages/SignIn";
import SignUp from "@/pages/SignUp";

//Public routes
const publicRoutes = [
  { path: config.routes.home, component: Home },
  { path: config.routes.profile, component: Profile, layout: ProfileLayout },
  { path: config.routes.listUser, component: ListUser, layout: ProfileLayout },
  { path: config.routes.coin, component: Coin, layout: ProfileLayout },
  { path: config.routes.signin, component: SignIn, layout: AuthLayout },
  { path: config.routes.signup, component: SignUp, layout: AuthLayout },
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
