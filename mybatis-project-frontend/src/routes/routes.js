import config from "../config";
import Login from "../pages/Login";

//Public routes
const publicRoutes = [
  // { path: config.routes.home, component: Home },
  { path: config.routes.login, component: Login },
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
