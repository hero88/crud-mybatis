import React from "react";
import '../assets/style.css'
import {Link} from "react-router-dom";

export default function ForgotPassword() {
    return (
        <div className='login template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <div className='form_container p-5 rounded bg-white'>
                <form>
                    <h3 className='text-center'>Forgot Password</h3>
                    <div className='mb-2'>
                        <label htmlFor="email">Email</label>
                        <input type="email" placeholder="Enter Your Email" className='form-control'/>
                    </div>
                    <div className='d-grid'>
                        <button className='btn btn-primary'>Send</button>
                    </div>
                    <div className='text-end mt-2'>
                        <Link to="/register" className='ms-2'>Sign up</Link><Link to="/" className='ms-2'>Sign In</Link>
                    </div>
                </form>
            </div>
        </div>
    );
}