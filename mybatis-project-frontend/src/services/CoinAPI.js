import axios from "axios";

const apiBaseUrl = "http://localhost:5555/";

console.log(apiBaseUrl);

const API = axios.create({ baseURL: apiBaseUrl });

API.interceptors.request.use((req) => {
  if (localStorage.getItem("token")) {
    req.headers.Authorization = `Bearer ${JSON.parse(
      localStorage.getItem("token")
    )}`;
  }
  return req;
});

export const getAllCoins = () => API.get("api/coin/getAllCoins");

export const getMarketCapCoins = () => {
  try {
    const res = API.get(
      "api/crypto/coinmarketcap?start=1&limit=100&sortBy=market_cap&sortType=desc&convert=USD&cryptoType=all&tagType=all&audited=false&aux=ath,atl,high24h,low24h,num_market_pairs,cmc_rank,date_added,max_supply,circulating_supply,total_supply,volume_7d,volume_30d,self_reported_circulating_supply,self_reported_market_cap"
    );
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const getCoinsByUserId = (id) => API.get(`api/coin/getCoinById/${id}`);

export const addCoin = (newCoin) => API.post("api/coin/saveCoin", newCoin);

export const updateCoin = (updatedCoin) =>
  API.put("api/coin/updateCoin", updatedCoin);

export const deleteCoinById = (id) =>
  API.delete(`api/coin/deleteCoinById/${id}`, {
    headers: {
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
  });
