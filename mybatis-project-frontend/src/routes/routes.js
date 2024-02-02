import config from "@/config";
import AuthLayout from "@/layouts/AuthLayout";
import Home from "@/pages/Home";
import SignIn from "@/pages/SignIn";
import SignUp from "@/pages/SignUp";

//Public routes
const publicRoutes = [
  { path: config.routes.home, component: Home },
  { path: config.routes.signin, component: SignIn, layout: AuthLayout },
  { path: config.routes.signup, component: SignUp, layout: AuthLayout },
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
