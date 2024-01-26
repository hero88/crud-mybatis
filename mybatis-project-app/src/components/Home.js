import React, { useState, useEffect } from 'react';
import {Link} from "react-router-dom";
import $ from 'jquery';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../assets/style.css'

const Home = () => {
    const [data, setData] = useState([]);
    const [gSTT, setGSTT] = useState(1);
  
    useEffect(() => {
      const fetchData = async () => {
        try {
          const response = await fetch("http://localhost:8080/api/coinmarketcap?start=1&limit=100&sortBy=market_cap&sortType=desc&convert=USD");
          const json = await response.json();
          const newData = json.data.cryptoCurrencyList;
          setData(newData);
        } catch (error) {
          console.error("Error loading table data:", error);
        }
      };
  
      fetchData();
  
      const interval = setInterval(() => {
        fetchData();
      }, 60000);
  
    
      return () => clearInterval(interval);
    }, []);
  
    const formatNumber = (numericData, fractionDigits = 2) => {
      return "$" + numericData.toLocaleString('en-US', {
        minimumFractionDigits: fractionDigits,
        maximumFractionDigits: fractionDigits,
      });
    };
  
    const renderChange = (numericData) => {
      const formattedNumber = Math.abs(numericData).toLocaleString('en-US', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2,
      });
  
      let textColor = "";
      let icon;
  
      if (numericData >= 0) {
        textColor = "green";
        icon = '↑';
      } else if (numericData < 0) {
        textColor = "red";
        icon = '↓';
      }
  
      return <span style={{ color: textColor }}>{icon} {formattedNumber}%</span>;
    };
  
    return (
      <div>
        <nav className="navbar">
          <a href="/home" className="home">
            <img src='url(../assets/images/330406_home_icon.png)' alt="HOME" title="Trang chủ" />
          </a>
          <div className="login-register-group">
            <button href="/" >Login</button>
            <button href="/register" className="register-text">Register</button>
          </div>
        </nav>
  
        <div className="container mt-5" style={{ width: "100%" }}>
          <table id="coin-table" className="table table-striped table-hover">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col" style={{ width: '200px' }}>Name</th>
                <th scope="col">Price</th>
                <th scope="col">1h %</th>
                <th scope="col">24h %</th>
                <th scope="col">7d %</th>
                <th scope="col">Market Cap</th>
                <th scope="col">Volume(24h)</th>
                <th scope="col">Circulating Supply</th>
              </tr>
            </thead>
            <tbody>
              {data.map((item, index) => (
                <tr key={index}>
                  <td>{gSTT + index}</td>
                  <td>
                    <div className="d-flex align-items-center" style={{ width: '200px' }}>
                      <div>
                        <img src={`https://s2.coinmarketcap.com/static/img/coins/32x32/${item.id}.png`} alt="" className="coin-logo" width={32} height={32} />
                      </div>
                      <div style={{ marginLeft: '10px' }}>
                        <div>{item.name}</div>
                        <div className="text-black-50">{item.symbol}</div>
                      </div>
                    </div>
                  </td>
                  <td>{formatNumber(item.quotes[0].price)}</td>
                  <td>{renderChange(item.quotes[0].percentChange1h)}</td>
                  <td>{renderChange(item.quotes[0].percentChange24h)}</td>
                  <td>{renderChange(item.quotes[0].percentChange7d)}</td>
                  <td>{formatNumber(item.quotes[0].marketCap)}</td>
                  <td>{formatNumber(item.quotes[0].volume24h)}</td>
                  <td>{formatNumber(parseFloat(item.circulatingSupply), 0)} {item.symbol}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  };
  
  export default Home;