import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import ApiCoin from "../service/ApiCoin";

const AddCoin = () => {
  const [coinList, setCoinList] = useState([]);
  const [selectedCoin, setSelectedCoin] = useState('');
  const [quantity, setQuantity] = useState('');
  let accoutId = window.localStorage.getItem("userId");
  const [selectedCoinInfo, setSelectedCoinInfo] = useState("");

  useEffect(() => {
    const fetchCoinList = async () => {
      try {
        const response = await fetch(
          'http://localhost:8080/api/coinmarketcap?start=1&limit=100&sortBy=market_cap&sortType=desc&convert=USD'
        );
        const json = await response.json();
        const coinData = json.data.cryptoCurrencyList;
        setCoinList(coinData);
      } catch (error) {
        console.error('Error loading coin list:', error);
      }
    };

    fetchCoinList();
  }, []);

  const handleQuantityChange = (event) => {
    setQuantity(event.target.value);
  };

  const handleCoinChange = (event) => {
    const selectedCoinId = parseInt(event.target.value);
    setSelectedCoin(selectedCoinId);
    console.log('Selected Coin Id:', selectedCoinId);
    console.log(typeof(selectedCoinId));
    const coinInfo = coinList.find((coin) => coin.id === selectedCoinId);

    console.log('Selected Coin Info:', coinInfo);
    setSelectedCoinInfo(coinInfo);
  };
  

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    console.log('Selected Coin Info:', selectedCoinInfo);
  
    if (selectedCoinInfo) {
      const { name, symbol, marketPairCount } = selectedCoinInfo;
      console.log('Selected Coin Info:', { name, symbol, marketPairCount });
    }
  };
  

  return (
    <div>
      <nav className="navbar">
        {/* Navigation items */}
      </nav>

      <div className="container mt-5" style={{ width: '50%' }}>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="coinSelect" className="form-label">
              Select Coin
            </label>
            <select
              className="form-select"
              id="coinSelect"
              value={selectedCoin}
              onChange={handleCoinChange} 
              required
            >
              <option value="" disabled>
                Choose a coin
              </option>
              {coinList.map((coin) => (
                <option key={coin.id} value={coin.id}>
                  {coin.name} ({coin.symbol}), {coin.marketPairCount}
                </option>
              ))}
            </select>
          </div>

          <div className="mb-3">
            <label htmlFor="quantityInput" className="form-label">
              Enter Quantity
            </label>
            <input
              type="number"
              className="form-control"
              id="quantityInput"
              value={quantity}
              onChange={handleQuantityChange}
              required
            />
          </div>

          <button type="submit" className="btn btn-primary">
            Add Coin
          </button>

          <Link to="/home" className="btn btn-secondary ms-2">
            Cancel
          </Link>
        </form>
      </div>
    </div>
  );
};

export default AddCoin;
