import React, { useState, useEffect } from 'react';
import '../../assets/style.css';
import {Link} from "react-router-dom";
import '@fortawesome/fontawesome-free/css/all.min.css';
import ApiCoin from '../service/ApiCoin.js';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.css';


function ListUser() {
  const [coins, setCoins] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [usersPerPage] = useState(15);

const handleGetAll = async () => {
  const response = await ApiCoin.getAlls();

  setCoins(response.data);
  console.log(response.data);
};

useEffect(() => {
  handleGetAll();
}, []);

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
                          <th>#</th>
                          <th>Name</th>
                          <th>Symbol</th>
                          <th>Slug</th>
                          <th>cmcRank</th>
                          <th>marketPairCount</th>
                          <th>totalSupply</th>
                          <th>maxSupply</th>
                          <th>Role</th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentCoins.map((coin) => (
                          <tr key={coin.id} className={coin.isActive ? 'active-row' : 'inactive-row'}>
                            <td>{coin.name}</td>
                            {/* <td>{user.email}</td>
                            <td>{user.firstName}</td>
                            <td>{user.lastName}</td>
                            <td>{user.gender}</td>
                            <td>{user.address}</td>
                            <td>{user.age}</td>
                            <td>{user.phoneNumber}</td>
                            <td>{user.role}</td> */}
                            <td>
                              {coin.isActive ? (
                                <button type="button" className="btn btn-danger">
                                  <i className="fa-solid fa-ban"></i>
                                </button>
                              ) : (
                                <button type="button" className="btn btn-success">
                                  <i className="fa-solid fa-ban"></i>
                                </button>
                              )}
                            </td>
                          </tr>
                        ))}
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
