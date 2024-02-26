import axios from "axios";

const apiBaseUrl = "http://localhost:5555/";

const API = axios.create({ baseURL: apiBaseUrl });

API.interceptors.request.use((req) => {
  if (localStorage.getItem("token")) {
    req.headers.Authorization = `Bearer ${JSON.parse(
      localStorage.getItem("token")
    )}`;
  }
  return req;
});

export const doGetAllTimeTracking = () => {
  try {
    const res = API.get("api/timeTracking/getAllTimeTracking");
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doAddNewTimeTracking = (timeTrackingForm) => {
  try {
    const res = API.post("api/timeTracking/saveTimeTracking", timeTrackingForm);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doUpdateTimeTracking = (timeTrackingForm) => {
  try {
    const res = API.put(
      "api/timeTracking/updateTimeTracking",
      timeTrackingForm
    );
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};
