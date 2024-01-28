import React, { useState, useEffect } from 'react';
import '../../assets/style.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import ApiCoin from '../service/ApiCoin.js';
import 'sweetalert2/dist/sweetalert2.css';
import {Link} from "react-router-dom";


function ListUser() {
  const [coins, setCoins] = useState([]);
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [usersPerPage] = useState(15);
  const [loading, setLoading] = useState(true);
  let accoutId = window.localStorage.getItem("userId");

  const fetchData = async () => {
    try {
      const coinResponse = await ApiCoin.getAllCoins();
      const coinData = coinResponse.data.data.cryptoCurrencyList;
      setData(coinData);
  
      const userResponse = await ApiCoin.getAllCoinByUserId(accoutId);
      setCoins(userResponse.data);
      setLoading(false);
    } catch (error) {
      console.error('Error loading data:', error);
      setLoading(false);
    }
  };

  
useEffect(() => {
  fetchData();
}, [accoutId]);

  const handleDelete = async (coinId) => {
    try {
      await ApiCoin.delCoinById(coinId);

      const updatedCoins = coins.filter((coin) => coin.coinId !== coinId);
      setCoins(updatedCoins);

      fetchData();
    } catch (error) {
      console.error('Lỗi khi xóa coin:', error);
    }
  };

  const indexOfLastUser = currentPage * usersPerPage;
  const indexOfFirstUser = indexOfLastUser - usersPerPage;
  const currentCoins = coins.slice(indexOfFirstUser, indexOfLastUser);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);
  

  return (
    <div>
      <section className="tf__dashboard">
        <div className="tf__dashboard_area">
          <div className="row" style={{ marginTop: '50px' }}>

            <div className="col-xl-2 col-lg-4"></div>

            <div className="col-xl-10 col-lg-8">
              <div className="tf__dashboard_content">
                <div className="tf_dashboard_body">
                  <h3>List Customer</h3>
                  <div className="content">
                    <table className="table table-hover">
                      <thead>
                        <tr>
                          <th>CoinId</th>
                          <th>Name</th>
                          <th>Symbol</th>
                          <th>Quantity</th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentCoins.map((coin) => {
                          const selectedCoinData = data.find((c) => c.id === coin.coinId);
                          return (
                            <tr key={coin.id}>
                              <td>{coin.coinId}</td>
                              <td>{selectedCoinData.name}</td>
                              <td>{selectedCoinData.symbol}</td>
                              <td>{coin.quantity}</td>
                              <td>
                                <button type="button" className="btn btn-warning" style={{ marginRight: '10px' }}>
                                  <Link to={`/edit-coin/${coin.id}`}><i className="fa-solid fa-pen-to-square"></i></Link>
                                </button>

                                <button type="button" className="btn btn-danger" style={{ marginRight: '10px' }}
                                  onClick={() => handleDelete(coin.id)}> 
                                    <i className="fa-solid fa-trash"></i>
                                </button>
                              </td>
                            </tr>
                          );
                        })}
                      </tbody>
                    </table>
                  </div>
                </div>

                <div className="tf__pagination mt_50">
                  <div className="row">
                    <div className="col-12">
                      <nav>
                        <ul className="pagination justify-content-center">
                          {Array.from({ length: Math.ceil(coins.length / usersPerPage) }, (_, index) => (
                            <li key={index} className={`page-item ${currentPage === index + 1 ? 'active' : ''}`}>
                              <button onClick={() => paginate(index + 1)} className="page-link">
                                {index + 1}
                              </button>
                            </li>
                          ))}
                        </ul>
                      </nav>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default ListUser;
