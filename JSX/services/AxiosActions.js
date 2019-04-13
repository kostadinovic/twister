import AxiosInstance from './AxiosInstance';
import Axios from 'axios';

const AxiosActions = {
    getUsers() {
        return AxiosInstance.get('/users');
    }

}

export default AxiosActions;
