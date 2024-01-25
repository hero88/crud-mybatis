import React, { useState, useEffect } from 'react';
import '../App.css';
import {Link} from "react-router-dom";
import LeftBar from './LeftBar';
import EditUser from './EditUser';
import '@fortawesome/fontawesome-free/css/all.min.css';
import ApiUser from './service/ApiUser.js';


function ListUser() {
  const [users, setUsers] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [usersPerPage] = useState(5);

const handleGetAll = async () => {
  const response = await ApiUser.getAlls();

  setUsers(response.data);
};

useEffect(() => {
  handleGetAll();
}, []);

  const indexOfLastUser = currentPage * usersPerPage;
  const indexOfFirstUser = indexOfLastUser - usersPerPage;
  const currentUsers = users.slice(indexOfFirstUser, indexOfLastUser);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const handleButtonClick = async (userId, isActive) => {
    let active = true
    if (isActive === true) {
      active = false;
    }
    const requestBody = {
      isActive: active,
    };

    await ApiUser.supended(userId, requestBody);

    handleGetAll();
  };

  return (
    <div>
      <section className="tf__dashboard">
        <div className="tf__dashboard_area">
          <div className="row" style={{ marginTop: '50px' }}>

            <LeftBar/>

            <div className="col-xl-8 col-lg-8">
              <div className="tf__dashboard_content">
                <div className="tf_dashboard_body">
                  <h3>List Customer</h3>
                  <div className="content">
                    <table className="table table-hover">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Email</th>
                          <th>First Name</th>
                          <th>Last Name</th>
                          <th>Gender</th>
                          <th>Address</th>
                          <th>Age</th>
                          <th>Phone number</th>
                          <th>Role</th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentUsers.map((user) => (
                          <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.email}</td>
                            <td>{user.firstName}</td>
                            <td>{user.lastName}</td>
                            <td>{user.gender}</td>
                            <td>{user.address}</td>
                            <td>{user.age}</td>
                            <td>{user.phoneNumber}</td>
                            <td>{user.role}</td>
                            <td>
                                <button type="button" className="btn btn-warning" style={{ marginRight: '10px' }} >
                                <Link to={`/edit-user/${user.id}`}><i className="fa-solid fa-pen-to-square"></i></Link></button>

                                <button type="button" className="btn btn-danger" onClick={() => handleButtonClick(user.id, user.isActive)}><i className="fa-solid fa-ban"></i></button>
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
                          {Array.from({ length: Math.ceil(users.length / usersPerPage) }, (_, index) => (
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

            <div className="col-xl-2 col-lg-4">
              {/* Include your sidebar content or component here */}
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default ListUser;
