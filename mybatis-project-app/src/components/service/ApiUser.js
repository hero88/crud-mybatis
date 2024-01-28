import axios from "axios";

const apiBaseUrl = process.env.REACT_APP_API_BASE_URL;

class ApiUser {
    static getAlls() {
      return axios.get(`${apiBaseUrl}/user`);
    }

    static getById(id) {
      return axios.get(`${apiBaseUrl}/user/${id}`);
    }

    static updateUser(id, obj) {
      return axios.put(`${apiBaseUrl}/user/${id}`, obj);
    }

    static createUser(obj) {
      return axios.post(`${apiBaseUrl}/user`, obj);
    }
  
    static supended(id, obj) {
      return axios.put(`${apiBaseUrl}/user/activeUser/${id}`, obj);
    }
  }
  
  export default ApiUser;