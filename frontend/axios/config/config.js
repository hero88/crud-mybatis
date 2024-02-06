import { HOST_URL } from "./env.js";
import { HOST_PORT } from "./env.js";

export const instance = axios.create({
    baseURL: `https://${HOST_URL}/api/`,
    timeout: 1000,
});
