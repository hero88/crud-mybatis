import axios from "axios";

const coinMarketCapApi = process.env.REACT_APP_COINMARKETCAP_API;
const apiBaseUrl = process.env.REACT_APP_API_BASE_URL;

class ApiCoin {
  static getAlls() {
    return axios.get(coinMarketCapApi);
  }

  static getCoinById(id) {
    return axios.get(`${apiBaseUrl}/coin/${id}`);
  }

  static delCoinById(id) {
    return axios.delete(`${apiBaseUrl}/coin/${id}`);
  }

  static updateCoin(id, obj) {
    return axios.put(`${apiBaseUrl}/coin/${id}`, obj);
  }

  static getAllCoinByUserId(id) {
    return axios.get(`${apiBaseUrl}/coin/user/${id}`);
  }

  static updateUser1(id, obj) {
    return axios.put(`${apiBaseUrl}/user/${id}`, obj);
  }

  static addCoin(obj) {
    return axios.post(`${apiBaseUrl}/coin`, obj);
  }

  static getAllCoins() {
    return axios.get(`${apiBaseUrl}/coinmarketcap?start=1&limit=100&sortBy=market_cap&sortType=desc&convert=USD`);
  }
}
  
  export default ApiCoin;