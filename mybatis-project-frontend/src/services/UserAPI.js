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

export const register = (newUser) => {
  try {
    const res = API.post("api/auth/register", newUser);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const login = (userForm) => {
  try {
    const res = API.post("api/auth/authenticate", userForm);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const getAllUsers = () => {
  try {
    const res = API.get("api/users/getAllUsers");
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const updateUser = (updateUser) => {
  try {
    const res = API.put("api/users/updateUser", updateUser);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const deleteUserById = (id) => {
  try {
    const res = API.delete(`api/users/deleteUserById/${id}`);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const getUserById = (id) => {
  try {
    const res = API.get(`api/users/getUser/${id}`);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const changeUserPassword = (passwordForm) => {
  try {
    const res = API.patch(`api/users/changePassword`, passwordForm);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};
