import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import ApiCoin from "../service/ApiCoin";

export default function EditCoin() {
const navigate = useNavigate();
  const { coinId } = useParams();
  const [coinData, setCoinData] = useState({});



  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCoinData({
      ...coinData,
      [name]: value,
    });
  };

  const getUserById = async (coinId) => {
    const user = await ApiCoin.getCoinById(coinId);
    setCoinData(user.data);
  };

  const handleFormSubmit = async () => {
    await ApiCoin.updateCoin(coinId, coinData);
      navigate("/list-coin");
  };

  useEffect(() => {
    getUserById(coinId);
  }, [coinId]);

  return (
    <div className='edit-user template d-flex justify-content-center align-items-center vh-100'>
      <div className='form_container p-5 rounded bg-white'>
        <form>
          <h3 className='text-center'>Edit Coin {coinId}</h3>
  
          <div className='mb-2'>
            <label htmlFor='text'>Coin</label>
            <input
              type='text'
              className='form-control'
              name='firstName'
              value={coinData.name}
              readOnly
              required
            />
          </div>
  
          <div className='mb-2'>
            <label htmlFor='text'>Quantity</label>
            <input
              type='number'
              placeholder='Enter Your Age'
              className='form-control'
              name='quantity'
              value={coinData.quantity}
              onChange={(e) => handleInputChange(e)}
              required
            />
          </div>
  
          <div className='d-grid'>
            <button type='button' className='btn btn-primary' onClick={handleFormSubmit}>
              Save Changes
            </button>
          </div>
          <p className='text-end mt-2'>
            <Link to={`/list-coin`} className='ms-2'>
              Cancel
            </Link>
          </p>
        </form>
      </div>
    </div>
  );  
}
