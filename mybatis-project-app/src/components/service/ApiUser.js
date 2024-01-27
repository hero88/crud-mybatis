import axios from "axios";

class ApiUser {
    static getAlls() {
      return axios.get('http://localhost:8080/api/user');
    }

    static getById(id) {
      return axios.get(`http://localhost:8080/api/user/${id}`);
    }

    static updateUser(id, obj) {
      return axios.put(`http://localhost:8080/api/user/${id}`, obj);
    }

    static createUser(obj) {
      return axios.post('http://localhost:8080/api/user', obj);
    }
  
    static supended(id, obj) {
      return axios.put(`http://localhost:8080/api/user/activeUser/${id}`, obj);
    }
  }
  
  export default ApiUser;