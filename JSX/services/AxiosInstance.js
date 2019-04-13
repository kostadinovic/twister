import axios from 'axios';

const AxiosInstance = axios.create({
    baseURL: "/api",
    timeout: 1000
})

export default AxiosInstance;