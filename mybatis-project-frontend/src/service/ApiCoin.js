import axios from "axios";

const coinMarketCapApi = process.env.REACT_APP_COINMARKETCAP_API;
const apiBaseUrl = process.env.REACT_APP_API_BASE_URL;

const API = axios.create({ baseURL: apiBaseUrl });

API.interceptors.request.use((req) => {
  if (sessionStorage.getItem("profile")) {
    req.headers.Authorization = `Bearer ${
      JSON.parse(sessionStorage.getItem("profile")).token
    }`;
  }
  return req;
});

export const getAllCoins = (formData) => API.get("api/coin", formData);

export const getCoinById = () => API.get("/");

export const delCoinById = () => API.delete("/");

export const updateCoin = () => API.put("/");

export const getAllCoinByUserId = () => API.get("/");

export const updateUser1 = () => API.get("/");

export const addCoin = () => API.post("/");
