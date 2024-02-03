import { HOST_URL } from "./env.example.js";
import { HOST_PORT } from "./env.example.js";

export const instance = axios.create({
    baseURL: `http://${HOST_URL}:${HOST_PORT}/api/`,
    timeout: 1000,
});