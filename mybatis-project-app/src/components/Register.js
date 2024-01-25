import React from "react";
import {Link} from "react-router-dom";

export default function Register() {
    return (
        <div className='signup template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <div className='form_container p-5 rounded bg-white'>
                <form>
                    <h3 className='text-center'>Sign Up</h3>
                    <div className='mb-2'>
                        <label htmlFor="text">First Name</label>
                        <input type="text" placeholder="Enter Your First Name" className='form-control'/>
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="text">Last Name</label>
                        <input type="text" placeholder="Enter Your First Name" className='form-control'/>
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="email">Email</label>
                        <input type="email" placeholder="Enter Your Email" className='form-control'/>
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="password">Password</label>
                        <input type="password" placeholder="Enter Your Password" className='form-control'/>
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="gender">Gender</label>
                        <select id="gender" className='form-control'>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="text">Address</label>
                        <input type="text" placeholder="Enter Your Address" className='form-control'/>
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="text">Age</label>
                        <input type="number" placeholder="Enter Your Age" className='form-control'/>
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="text">Phone Number</label>
                        <input type="text" placeholder="Enter Your Phone Number" className='form-control'/>
                    </div>
                    {/*<div className='mb-2'>*/}
                    {/*    <input type="checkbox" className='custom-control custom-checkbox' id="check"/>*/}
                    {/*    <label htmlFor="check" className='custom-input-label ms-2'>*/}
                    {/*        Remember me*/}
                    {/*    </label>*/}
                    {/*</div>*/}
                    <div className='d-grid'>
                        <button className='btn btn-primary'>Sign Up</button>
                    </div>
                    <p className='text-end mt-2'>
                        Already registered?<Link to="/" className='ms-2'>Sign In</Link>
                    </p>
                </form>
            </div>
        </div>
    );
}