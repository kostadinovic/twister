import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import AxiosActions from '../services/AxiosActions';

class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: [],
            redirect: false
        };
    }
    componentDidMount() {
        AxiosActions.getUsers().then((results) => {
            console.log(results.data)
            this.setState({
                users: results.data
            });
        })
    }
    onSubmit = (event) => {
        event.preventDefault();
        let data = new FormData(event.target);
        let login = data.get('login')
        let password = data.get('password')
        let isCorresponding = (user) => (user.password === password) && (user.login === login)
        let correspondingPeople = this.state.users.find(isCorresponding);
        if (typeof (correspondingPeople) === "undefined") {
            console.log("login failed");
        } else {
            this.setState({
                redirect: true
            })
            console.log(correspondingPeople.mail + "is welcome on board");
        }
    }
    render() {

        if (this.state.redirect)
            return <Redirect to="/feed" />

        return <div>

            <h1>Login</h1>
            <form onSubmit={this.onSubmit}>
                <input type="text" name="login" placeholder="login" />
                <input type="password" name="password" placeholder="password" />
                <input type="submit" value="Connect" />



            </form>
            {
                this.state.users.map((user, index) => {
                    return <div key={index}>{user.login} {user.password}</div>

                })
            }

        </div>

    }
}
export default LoginPage;