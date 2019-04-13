import React, { Component } from 'react';
import AxiosActions from '../services/AxiosActions';

class SignUpPage extends Component {

    constructor(props) {
        super(props);
    }

    ct() {
        AxiosActions.setUsers(this.onSubmit.nom, this.onSubmit.prenom, this.onSubmit.login, this.onSubmit.mail, this.onSubmit.age, this.onSubmit.password).then((results) => {
            console.log(results.data)
            this.setState({
                users: results.data
            });
        })
    }

    render() {
        return(
        <div>
        <h1>Inscription</h1>
            <form onSubmit={this.onSubmit}>
                <input type="text" name="nom" placeholder="nom" /><br></br>
                <input type="text" name="prenom" placeholder="prenom" /><br></br>
                <input type="text" name="login" placeholder="login" /><br></br>
                <input type="text" name="mail" placeholder="mail" /><br></br>
                <input type="text" name="age" placeholder="age" /><br></br>
                <input type="password" name="password" placeholder="password" /><br></br>
                <input type="submit" value="Sign up" />

            </form>
            </div>
        );
    }

}


export default SignUpPage;