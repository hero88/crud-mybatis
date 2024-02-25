import { HOST_URL } from "./env.js";
import { HOST_PORT } from "./env.js";

export const instance = axios.create({
    baseURL: `http://localhost:8080/api/`,
    timeout: 1000,
});