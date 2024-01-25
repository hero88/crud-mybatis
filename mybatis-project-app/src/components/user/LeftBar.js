import React from 'react';

const LeftBar = ({ user }) => {
  return (
    <div className="col-xl-2 col-lg-4">
      <div className="left-bar bg-pink text-white p-3">
        <div className="user-info text-center">
          <h4>{}</h4>
          {/* Display additional user information here */}
        </div>
        <ul className="nav flex-column">
          <li className="nav-item">
            <a className="btn btn-outline-light" href="#" > 
              <button type="button" class="btn btn-primary" style={{ width: '150px' }}>List User</button>
            </a>
          </li>
          <li className="nav-item">
            <a className="btn btn-outline-light" href="#">
              <button type="button" class="btn btn-primary" style={{ width: '150px' }}>User Detail</button>
            </a>
          </li>
          {/* Add more menu items as needed */}
        </ul>
      </div>
    </div>
  );
};

export default LeftBar;

