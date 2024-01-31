import { getApp, getApps, initializeApp } from "firebase/app";

const firebaseConfig = {
  apiKey: "AIzaSyBmsTQcHu_LHgw9YIImAb1pGV5Tf7ojsZA",
  authDomain: "mybatis-crud-app.firebaseapp.com",
  projectId: "mybatis-crud-app",
  storageBucket: "mybatis-crud-app.appspot.com",
  messagingSenderId: "325767796392",
  appId: "1:325767796392:web:0db0dcb009ac05582f549d",
};

const app = getApps.length > 0 ? getApp() : initializeApp(firebaseConfig);

export { app };
