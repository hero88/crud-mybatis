import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import ApiUser from "./service/ApiUser";



export default function EditUser() {
    const navigate = useNavigate();
    const { userId } = useParams();
    const [userData, setUserData] = useState({});

    // useEffect(() => {
    //     // Fetch user data from the API when the component mounts
    //     fetch(`http://localhost:8080/api/user/${userId}`)
    //         .then((response) => response.json())
    //         .then((data) => {
    //             setUserData(data); // Update the state with fetched user data
    //         })
    //         .catch((error) => console.error("Error fetching user data:", error));
    // }, [userId]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUserData({ ...userData, [name]: value });
    };

    const getUserById = async (id) => {
        const user = await ApiUser.getById(id);
    
        setUser(user.data);
      };
    
      const handleUpdateProduct = async () => {
        await ProductService.update(id, product);
    
        navigate('/products');
      };
    
      useEffect(() => {
        getProductById(id);
      }, []);







    // const handleFormSubmit = (e) => {
    //     e.preventDefault();

    //     // Call the API to update user data
    //     fetch(`http://localhost:8080/api/user/${userId}`, {
    //         method: "PUT",
    //         headers: {
    //             "Content-Type": "application/json",
    //         },
    //         body: JSON.stringify(userData),
    //     })
    //         .then((response) => response.json())
    //         .then((data) => {
    //             console.log("User data updated successfully:", data);
    //             // Handle success, e.g., redirect to user profile page
    //         })
    //         .catch((error) => console.error("Error updating user data:", error));
    // };

    return (
        <div className='edit-user template d-flex justify-content-center align-items-center vh-100'>
            <div className='form_container p-5 rounded bg-white'>
                <form onSubmit={handleFormSubmit}>
                    <h3 className='text-center'>Edit User {userId}</h3>
                    <div className='mb-2'>
                        <label htmlFor="email">Email</label>
                        <input
                            type="email"
                            placeholder="Enter Your Email"
                            className='form-control'
                            name="email"
                            value={userData.email}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="text">First Name</label>
                        <input
                            type="text"
                            placeholder="Enter Your First Name"
                            className='form-control'
                            name="firstName"
                            value={userData.firstName}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="text">Last Name</label>
                        <input
                            type="text"
                            placeholder="Enter Your Last Name"
                            className='form-control'
                            name="lastName"
                            value={userData.lastName}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="gender">Gender</label>
                        <select
                            id="gender"
                            className='form-control'
                            name="gender"
                            value={userData.gender}
                            onChange={handleInputChange}
                            required
                        >
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="text">Address</label>
                        <input
                            type="text"
                            placeholder="Enter Your Address"
                            className='form-control'
                            name="address"
                            value={userData.address}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="text">Age</label>
                        <input
                            type="number"
                            placeholder="Enter Your Age"
                            className='form-control'
                            name="age"
                            value={userData.age}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="text">Phone Number</label>
                        <input
                            type="text"
                            placeholder="Enter Your Phone Number"
                            className='form-control'
                            name="phoneNumber"
                            value={userData.phoneNumber}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className='mb-2'>
                        <label htmlFor="role">Role</label>
                        <select
                            id="role"
                            className='form-control'
                            name="role"
                            value={userData.role}
                            onChange={handleInputChange}
                            required
                        >
                            <option value="admin">Admin</option>
                            <option value="customer">Customer</option>
                        </select>
                    </div>
                    <div className='d-grid'>
                        <button type='submit' className='btn btn-primary'>
                            Save Changes
                        </button>
                    </div>
                    <p className='text-end mt-2'>
                        <Link to={`/list-user`} className='ms-2'>
                            Cancel
                        </Link>
                    </p>
                </form>
            </div>
        </div>
    );
}
