import React, {useState} from 'react';
import {useLocalState} from "../../util/localStorage";

const Login = () => {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const [jwt, setJwt] = useLocalState("", "jwt");
    function sendLogInRequest(){
        const requestBody = {
            "userEmail" : email,
            "userPassword" : password
        }
        fetch('api/auth/login', {
            headers : {
                "Content-Type" : "application/json",
                Authentication : `Bearer ${jwt}`
            },
            method : "POST",
            body : JSON.stringify(requestBody)
        }).then(response => {
            if (response.status === 200) {
                return Promise.all([response.json(), response.headers]);
            } else {
                return Promise.reject("Invalid login attempt");
            }
        })
            .then(([body, headers]) => {
                setJwt(headers.get("authorization"));
                window.location.href = "/configurator";
            }).catch((message) => {
                alert(message)
        });
    }

    return (
        <div>
            <div>
                <label htmlFor={"username"}>Username</label>
                <input type={"email"} id={"username"} value={email}
                       onChange={(event) => setEmail(event.target.value)}/>
            </div>
            <div>
                <label htmlFor={"password"}>Password</label>
                <input type={"password"} id={"password"} value={password}
                       onChange={(event) => setPassword(event.target.value)}/>
            </div>
            <div>
                <button id={"submit"} type={"button"} onClick={() => sendLogInRequest()}>LogIn</button>
            </div>
        </div>
    );
};

export default Login;