import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

export default function Register() {
    const [register, setRegister] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        gender: "",
        address: "",
        age: "",
        phoneNumber: ""
    });

    const handleChange = (event) => {
        setRegister({
            ...register,
            [event.target.name]: event.target.value,
        });
    };

    const fetchRegister = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/auth/register", register);

            if (response.status === 200) {
                const data = response.data;

                if (data.success) {
                    alert(data.message);
                } else {
                    alert(data.message);
                }
            } else {
                alert("Error during sign up!!!");
            }
        } catch (error) {
            console.error('Error during registration:', error);
        }
    };

    return (
        <div className='signup template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <div className='form_container p-5 rounded bg-white'>
                <form>
                    <h3 className='text-center'>Sign Up</h3>
                    <div className='mb-2'>
                        <label htmlFor="firstName">First Name</label>
                        <input type="text" name="firstName" placeholder="Enter Your First Name" className='form-control' onChange={handleChange} value={register.firstName} />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="lastName">Last Name</label>
                        <input type="text" name="lastName" placeholder="Enter Your Last Name" className='form-control' onChange={handleChange} value={register.lastName} />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="email">Email</label>
                        <input type="email" name="email" placeholder="Enter Your Email" className='form-control' onChange={handleChange} value={register.email} />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" placeholder="Enter Your Password" className='form-control' onChange={handleChange} value={register.password} />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="gender">Gender</label>
                        <select id="gender" name="gender" className='form-control' onChange={handleChange} value={register.gender}>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="address">Address</label>
                        <input type="text" name="address" placeholder="Enter Your Address" className='form-control' onChange={handleChange} value={register.address} />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="age">Age</label>
                        <input type="number" name="age" placeholder="Enter Your Age" className='form-control' onChange={handleChange} value={register.age} />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="phoneNumber">Phone Number</label>
                        <input type="text" name="phoneNumber" placeholder="Enter Your Phone Number" className='form-control' onChange={handleChange} value={register.phoneNumber} />
                    </div>
                    <div className='d-grid'>
                        <button className='btn btn-primary' type="submit" onClick={fetchRegister}>Sign Up</button>
                    </div>
                    <p className='text-end mt-2'>
                        Already registered?<Link to="/" className='ms-2'>Sign In</Link>
                    </p>
                </form>
            </div>
        </div>
    );
}
