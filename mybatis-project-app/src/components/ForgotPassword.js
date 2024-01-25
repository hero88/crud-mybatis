import React, { useState } from "react";
import '../assets/style.css'
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

export default function ForgotPassword() {
    const navigate = useNavigate();
    const [email, setEmail] = useState({
        email: ""
    });

    const handleChange = (event) => {
        setEmail({
            ...email,
            [event.target.name]: event.target.value,
        });
    };

    const fetchEmail = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.put("http://localhost:8080/api/auth/forgot-password", email);
            if (response.status === 200) {
                const data = response.data;
                console.log(data);
                if (data.success) {
                    navigate("/");
                    alert(data.message);
                } else {
                    alert(data.message);
                }
            } else {
                alert("Error during sending email");
            }
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <div className='login template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <div className='form_container p-5 rounded bg-white'>
                <form>
                    <h3 className='text-center'>Forgot Password</h3>
                    <div className='mb-2'>
                        <label htmlFor="email">Email</label>
                        <input type="email" placeholder="Enter Your Email" className='form-control' value={email.email} onChange={handleChange} name="email" /> {/* Đổi "mail" thành "email" ở đây */}
                    </div>
                    <div className='d-grid'>
                        <button className='btn btn-primary' type="submit" onClick={fetchEmail}>Send</button>
                    </div>
                    <div className='text-end mt-2'>
                        <Link to="/register" className='ms-2'>Sign up</Link><Link to="/" className='ms-2'>Sign In</Link>
                    </div>
                </form>
            </div>
        </div>
    );
}
