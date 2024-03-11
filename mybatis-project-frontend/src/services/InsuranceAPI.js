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

export const doGetAllInsurances = () => {
  try {
    const res = API.get("api/InsuranceType/getAllInsuranceType");
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doAddNewInsurance = (insuranceForm) => {
  try {
    const res = API.post("api/InsuranceType/saveInsuranceType", insuranceForm);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doUpdateInsurance = (insuranceForm) => {
  try {
    const res = API.put("api/InsuranceType/updateInsuranceType", insuranceForm);
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const doDeleteInsurance = (insuranceId) => {
  try {
    const res = API.delete(
      `api/InsuranceType/deleteInsuranceTypeId/${insuranceId}`
    );
    return res;
  } catch (error) {
    console.log(error);
    return null;
  }
};
