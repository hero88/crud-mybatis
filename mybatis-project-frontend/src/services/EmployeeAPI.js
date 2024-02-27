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

export const doGetAllEmployees = () => {
  try {
    const res = API.get("api/employee/getAllEmployees");
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doAddNewEmployee = (employeeForm) => {
  try {
    const res = API.post("api/employee/saveEmployee", employeeForm);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doUpdateEmployee = (employeeForm) => {
  try {
    const res = API.put("api/employee/updateEmployee", employeeForm);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doDeleteEmployee = (employeeId) => {
  try {
    const res = API.delete(`api/employee/deleteEmployeeById/${employeeId}`);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};
