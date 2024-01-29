import React from "react";
import {BrowserRouter, Route, Routes, Navigate} from "react-router-dom";
import Login from "./components/auth/Login";
import Register from "./components/auth/Register";
import ListUser from "./components/user/ListUser"
import EditUser from "./components/user/EditUser"
import ListCoin from "./components/coin/ListCoin"
import "bootstrap/dist/css/bootstrap.min.css";
import AddCoin from "./components/coin/AddCoin";
import ForgotPassword from "./components/auth/ForgotPassword";
import Home from "./components/auth/Home";
// import Notfound from "./components/auth/Notfound";
import EditCoin from "./components/coin/EditCoin";

function App() {
    const accountRole = window.localStorage.getItem("role");

    const isAdmin = accountRole == "ADMIN";

    return (
        <BrowserRouter>
            <Routes>
                {/*<Route path="/404" element={<Notfound />} />*/}

                <Route path="/" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/forgot-password" element={<ForgotPassword />} />
                <Route path="/edit-user/:userId" element={<EditUser />} />
                <Route path="/add-coin/" element={<AddCoin />} />
                <Route path="/home" element={<Home />} />

                <Route path="/list-coin" element={<ListCoin />} />
                <Route path="/edit-coin/:coinId" element={<EditCoin />} />

                {isAdmin ? (
                    <>
                        <Route path="/list-user" element={<ListUser />} />
                        
                    </>
                ) : (
                    <Route path="*" element={<Navigate to="/404" />} />
                )}
            </Routes>
        </BrowserRouter>
    );
}

export default App;
