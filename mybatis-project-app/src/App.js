import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./components/auth/Login";
import Register from "./components/auth/Register";
import ListUser from "./components/user/ListUser"
import EditUser from "./components/user/EditUser"
import ListCoin from "./components/coin/ListCoin"
import "bootstrap/dist/css/bootstrap.min.css";
import AddCoin from "./components/coin/AddCoin";
import ForgotPassword from "./components/auth/ForgotPassword";
import Home from "./components/auth/Home";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/forgot-password" element={<ForgotPassword/>}/>

                <Route path="/list-user" element={<ListUser/>}/>
                <Route path="/edit-user/:userId" element={<EditUser/>}/>

                <Route path="/list-coin" element={<ListCoin/>}/>
                <Route path="/add-coin/" element={<AddCoin/>}/>

                <Route path="/home" element={<Home/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
