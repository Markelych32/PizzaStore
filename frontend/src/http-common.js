import axios, { Axios } from "axios";

const AXIOS = axios.create({
  baseURL: `http://localhost:8081`,
  headers: {
    "Access-Control-Allow-Origin": "http://localhost:9090",
  },
});
export default AXIOS;
