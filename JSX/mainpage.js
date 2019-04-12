import React, { Component } from 'react';

class MainPage extends Component{
    constructor(props){
        super(props);
        this.state = {actualpage:"acceuil", connected:false, login:"", id:"", key:""};
        this.setConnected = this.setConnected.bind(this);
        this.changeActualPage = this.changeActualPage.bind(this);
        this.setLogin = this.setLogin.bind(this);
        this.setSessionKey = this.setKey.bind(this);
        this.setLogout = this.setLogout.bind(this);
    }

    changeactualpage(name){
        this.setState({actualpage:name});
    }

    setSessionKey(newkey){
        this.setState({key:newkey});
    }

    setConnected(){
        this.setState({connected:!this.state.connected});
    }

    setUser(id, log){
        this.setState({login:log,id:id, });
    }

    setLogout(){
        this.setState({login: "",id:"",key:""});
        this.setConnected();
    }

    render(){
        let pagetosee;
        var {connected} = this.state;
        var {actualpage} = this.state;

        if(connected){
            if(actualpage === "personalpage"){
                pagetosee=<AcceuilPerso changeActualPage = {this.changeActualPage} setLogout = {this.setLogout} userKey={this.state.key} setKey = {this.setKey} login={this.state.login} userId = {this.state.id} /> 
            }
            if(actualpage === "mypage"){
                pagetosee=<Pageperso changeActualPage = {this.changeActualPage} setconnected = {this.setConnected}/>
            }
        }else{
            if(actualpage === "registration"){
                pagetosee=<Inscription changeActualPage = {this.changepage} setconnected = {this.setConnected}/>;
            }
            if(actualpage === "login" ){
                pagetosee=<Connexion changeActualPage = {this.changeActualPage} setconnected = {this.setConnected} setKey = {this.setKey} setUser= {this.setUser}/>;
            }
            else {
                pagetosee=<Acceuil changeActualPage = {this.changeActualPage}/>
            }
        }
        return( <div>{actualpage}</div>);
    }
}

export default MainPage;