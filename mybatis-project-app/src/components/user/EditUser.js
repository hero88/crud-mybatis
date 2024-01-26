import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import ApiUser from "../service/ApiUser";

export default function EditUser() {
  const navigate = useNavigate();
  const { userId } = useParams();
  const [userData, setUserData] = useState({});

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUserData({
      ...userData,
      [name]: value,
    });
  };

  const getUserById = async (userId) => {
    const user = await ApiUser.getById(userId);
    setUserData(user.data);
  };

  const handleFormSubmit = async () => {
    await ApiUser.updateUser(userId, userData);
    navigate("/list-user");
  };

  useEffect(() => {
    getUserById(userId);
  }, [userId]);

  return (
    <div className='edit-user template d-flex justify-content-center align-items-center vh-100'>
      <div className='form_container p-5 rounded bg-white'>
        <form>
          <h3 className='text-center'>Edit User {userId}</h3>
          <div className='mb-2'>
            <label htmlFor='email'>Email</label>
            <input
              type='email'
              placeholder='Enter Your Email'
              className='form-control'
              name='email'
              value={userData.email}
              onChange={(e) => handleInputChange(e)}
              required
            />
          </div>
          <div className='mb-2'>
            <label htmlFor='text'>First Name</label>
            <input
              type='text'
              placeholder='Enter Your First Name'
              className='form-control'
              name='firstName'
              value={userData.firstName}
              onChange={(e) => handleInputChange(e)}
              required
            />
          </div>
          <div className='mb-2'>
            <label htmlFor='text'>Last Name</label>
            <input
              type='text'
              placeholder='Enter Your Last Name'
              className='form-control'
              name='lastName'
              value={userData.lastName}
              onChange={(e) => handleInputChange(e)}
              required
            />
          </div>
          <div className='mb-2'>
            <label htmlFor='gender'>Gender</label>
            <select
              id='gender'
              className='form-control'
              name='gender'
              value={userData.gender}
              onChange={(e) => handleInputChange(e)}
              required
            >
              <option value='male'>Male</option>
              <option value='female'>Female</option>
              <option value='other'>Other</option>
            </select>
          </div>
          <div className='mb-2'>
            <label htmlFor='text'>Address</label>
            <input
              type='text'
              placeholder='Enter Your Address'
              className='form-control'
              name='address'
              value={userData.address}
              onChange={(e) => handleInputChange(e)}
              required
            />
          </div>
          <div className='mb-2'>
            <label htmlFor='text'>Age</label>
            <input
              type='number'
              placeholder='Enter Your Age'
              className='form-control'
              name='age'
              value={userData.age}
              onChange={(e) => handleInputChange(e)}
              required
            />
          </div>
          <div className='mb-2'>
            <label htmlFor='text'>Phone Number</label>
            <input
              type='text'
              placeholder='Enter Your Phone Number'
              className='form-control'
              name='phoneNumber'
              value={userData.phoneNumber}
              onChange={(e) => handleInputChange(e)}
              required
            />
          </div>
          <div className='d-grid'>
            <button
              type='button'
              className='btn btn-primary'
              onClick={handleFormSubmit}
            >
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
