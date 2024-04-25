import axios from "axios";

const AXIOS = axios.create({
  baseURL: `http://localhost:8081`,
  headers: {
    "Access-Control-Allow-Origin": "http://localhost:9090",
    "Access-Control-Allow-Methods": "DELETE, POST, GET, OPTIONS",
    "Access-Control-Allow-Headers":
      "Content-Type, Authorization, X-Requested-With",
  },
  methods: "*",
  crossDomain: true,
});
export default AXIOS;
