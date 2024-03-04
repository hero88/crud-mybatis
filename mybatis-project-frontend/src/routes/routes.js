import config from "@/config";

import Coin from "@/pages/Coin";
import Home from "@/pages/Home";
import ListUser from "@/pages/ListUser";
import Profile from "@/pages/Profile";
import SignIn from "@/pages/SignIn";
import SignUp from "@/pages/SignUp";
import CoinDetail from "@/pages/CoinDetail";
import VerifyEmail from "@/pages/VerifyEmail";
import Company from "@/pages/Company";
import Employees from "@/pages/Employees";
import TimeTracking from "@/pages/TimeTracking";
import Payroll from "@/pages/Payroll";
import {
  CompanyLayout,
  VerifyLayout,
  AuthLayout,
  ProfileLayout,
} from "@/layouts";
import Insurance from "@/pages/Insurance";
import Holiday from "@/pages/Holiday";

//Public routes
const publicRoutes = [
  { path: config.routes.home, component: Home },
  { path: config.routes.coinDetail, component: CoinDetail },
  { path: config.routes.profile, component: Profile, layout: ProfileLayout },
  { path: config.routes.listUser, component: ListUser, layout: ProfileLayout },
  { path: config.routes.coin, component: Coin, layout: ProfileLayout },
  { path: config.routes.verify, component: VerifyEmail, layout: VerifyLayout },
  { path: config.routes.signin, component: SignIn, layout: AuthLayout },
  { path: config.routes.signup, component: SignUp, layout: AuthLayout },
  { path: config.routes.company, component: Company, layout: CompanyLayout },
  {
    path: config.routes.employees,
    component: Employees,
    layout: CompanyLayout,
  },
  {
    path: config.routes.timetracking,
    component: TimeTracking,
    layout: CompanyLayout,
  },
  {
    path: config.routes.payroll,
    component: Payroll,
    layout: CompanyLayout,
  },
  {
    path: config.routes.insurance,
    component: Insurance,
    layout: CompanyLayout,
  },
  {
    path: config.routes.holiday,
    component: Holiday,
    layout: CompanyLayout,
  },
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
