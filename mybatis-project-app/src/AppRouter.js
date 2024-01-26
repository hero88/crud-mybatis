import React from "react";
import {BrowserRouter, Route, Routes, Navigate} from "react-router-dom";
import Login from "./components/auth/Login";
import Register from "./components/auth/Register";
import ListUser from "./components/user/ListUser"
import EditUser from "./components/user/EditUser"
import ListCoin from "./components/coin/ListCoin"
import "bootstrap/dist/css/bootstrap.min.css";
import ForgotPassword from "./components/auth/ForgotPassword";
import Home from "./components/auth/Home";

function App() {
    const accessToken = localStorage.getItem("accessToken");
    const userRole = localStorage.getItem("role");
  
    const isAdmin = userRole === "ADMIN";
    const isUser = userRole === "USER";
  
    return (
      <BrowserRouter>
        <Routes>
          <Route
            path="/"
            element={
                accessToken ? (
                isAdmin ? (
                  <Navigate to="/list-coin" />
                ) : isUser ? (
                  <Navigate to="/home" />
                ) : null
              ) : (
                <Login/>
              )
            }
          />
          <Route path="/register" element={<Register />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />
  
          {accessToken && (
            <>
              {isUser && (
                <>
                  <Route path="/list-user" element={<ListUser />} />
                  <Route path="/edit-user/:userId" element={<EditUser />} />
                </>
              )}
              {isAdmin && <Route path="/list-coin" element={<ListCoin />} />}
              <Route path="/home" element={<Home />} />
            </>
          )}
        </Routes>
      </BrowserRouter>
    );
  }
  
  export default App;