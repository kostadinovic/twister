import React, {Component} from 'react';
import axios from "axios";
import { send } from 'q';

class Login extends Component{
    constructor(props){
        super(props);
        this.state={login:"",password:""}
    }
}

axios_login(){
    var data == {login: this.refs.login.value,password:this.refs.password.value}
    axios.get("http://localhost:8080/Twister/User/Login/?login="+data.login+"&password="+data.password);
}

render(){
    return(
        <div className="Login">
        
        
        </div>
    )
}

