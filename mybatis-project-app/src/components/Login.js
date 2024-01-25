import React, {useState} from "react";
import '../assets/style.css'
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";

export default function Login() {
    const navigate = useNavigate();
    const [login, setLogin] = useState({
        email: "",
        password: ""
    });

    const handleChange = (event) => {
        setLogin({
            ...login,
            [event.target.name]: event.target.value,
        });
    };

    const fetchLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/auth/authentication", login);
            if (response.status === 200) {
                const data = response.data;
                console.log(data);
                if (data.success) {
                    window.localStorage.setItem("token", data.data.token);
                    navigate("/home");
                } else {
                    alert(data.message);
                }
            } else {
                alert("Error during login");
            }
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div className='login template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <div className='form_container p-5 rounded bg-white'>
                <form onSubmit={fetchLogin}>
                    <h3 className='text-center'>Sign In</h3>
                    <div className='mb-2'>
                        <label htmlFor="email">Email</label>
                        <input type="email" name="email" placeholder="Enter Your Email" className='form-control' onChange={handleChange} value={login.email}/>
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" placeholder="Enter Your Password" className='form-control' onChange={handleChange} value={login.password}/>
                    </div>
                    <div className='mb-2'>
                        <input type="checkbox" className='custom-control custom-checkbox' id="check"/>
                        <label htmlFor="check" className='custom-input-label ms-2'>
                            Remember me
                        </label>
                    </div>
                    <div className='d-grid'>
                        <button className='btn btn-primary' type="submit">Login</button>
                    </div>
                    <div className='text-end mt-2'>
                        <Link to="/forgot-password" className='ms-2'>Forgot Password?</Link><Link to="/register" className='ms-2'>Sign up</Link>
                    </div>
                </form>
            </div>
        </div>
    );
}
