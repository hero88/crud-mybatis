import axios from "axios";

const apiBaseUrl = import.meta.env.VITE_API_BASE_URL;

const API = axios.create({ baseURL: apiBaseUrl });

API.interceptors.request.use((req) => {
  if (localStorage.getItem("token")) {
    req.headers.Authorization = `Bearer ${JSON.parse(
      localStorage.getItem("token")
    )}`;
  }
  return req;
});

export const doGetAllDepartments = () => {
  try {
    const res = API.get(`api/Department/getAllDepartment`);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};
