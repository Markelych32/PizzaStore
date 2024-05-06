import axios from "axios";

const AXIOS = axios.create({
  baseURL: `https://pizza-store-frontend-ghcr-v1.onrender.com/:8081`,
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "DELETE, POST, GET, OPTIONS",
    "Access-Control-Allow-Headers":
      "Content-Type, Authorization, X-Requested-With",
  },
  methods: "*",
  crossDomain: true,
});
export default AXIOS;
