import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const AddCoin = () => {
  const [coinList, setCoinList] = useState([]);
  const [selectedCoin, setSelectedCoin] = useState('');
  const [quantity, setQuantity] = useState('');

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

  const handleCoinChange = (event) => {
    setSelectedCoin(event.target.value);
  };

  const handleQuantityChange = (event) => {
    setQuantity(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Handle the submission logic, e.g., sending data to the server
    // You can access selectedCoin and quantity states here
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
                  {coin.name} ({coin.symbol})
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
