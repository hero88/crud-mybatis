import axios from "axios";



class ApiCoin {
    static getAlls() {
      return axios.get('https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit=100&sortBy=market_cap&sortType=desc&convert=USD&cryptoType=all&tagType=all&audited=false&aux=ath,atl,high24h,low24h,num_market_pairs,cmc_rank,date_added,max_supply,circulating_supply,total_supply,volume_7d,volume_30d,self_reported_circulating_supply,self_reported_market_cap');
    }

    static getAllCoinByUserId(id) {
      return axios.get(`http://localhost:8080/api/coin/user/${id}`);
    }

    static updateUser1(id, obj) {
      return axios.put(`http://localhost:8080/api/user/${id}`, obj);
    }

    static addCoin(obj) {
      return axios.post('http://localhost:8080/api/coin', obj);
    }

  }
  
  export default ApiCoin;