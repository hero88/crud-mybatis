import React from 'react';
import '../../assets/style.css'

const NotFound = () => {
  return (
    <div className="not-found">
      <h1>404 - Not Found</h1>
      <p>The page you are looking for might be under construction or does not exist.</p>
      <img alt="Page not found" />
    </div>
  );
};

export default NotFound;