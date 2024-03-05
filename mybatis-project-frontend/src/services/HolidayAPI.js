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

export const doGetAllHoliday = () => {
  try {
    const res = API.get("api/holiday/getAllHoliday");
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doAddNewHoliday = (holidayForm) => {
  try {
    const res = API.post("api/holiday/saveHoliday", holidayForm);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doUpdateHoliday = (holidayForm) => {
  try {
    const res = API.put("api/holiday/updateHoliday", holidayForm);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doDeleteHoliday = (holidayId) => {
  try {
    const res = API.delete(`api/holiday/deleteHolidayById/${holidayId}`);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};
