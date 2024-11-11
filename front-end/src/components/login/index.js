import React, {useState} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {
    MDBContainer,
    MDBInput,
    MDBBtn,
} from 'mdb-react-ui-kit';

const Login = () => {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [error, setError] = useState('');
    const history = useNavigate();

    // function sendLogInRequest(){
    //     const requestBody = {
    //         "userEmail" : email,
    //         "userPassword" : password
    //     }
    //     fetch('api/auth/login', {
    //         headers : {
    //             "Content-Type" : "application/json",
    //             Authentication : `Bearer ${jwt}`
    //         },
    //         method : "POST",
    //         body : JSON.stringify(requestBody)
    //     }).then(response => {
    //         if (response.status === 200) {
    //             return Promise.all([response.json(), response.headers]);
    //         } else {
    //             return Promise.reject("Invalid login attempt");
    //         }
    //     })
    //         .then(([body, headers]) => {
    //             setJwt(headers.get("authorization"));
    //             window.location.href = "/configurator";
    //         }).catch((message) => {
    //             alert(message)
    //     });
    // }

    const handleLogin = async () => {
        try {
            if (!email || !password) {
                setError('Please enter both username and password.');
                return;
            }

            const response =  await axios.post('http://localhost:8080/api/auth/login', {
                "userEmail" : email, "userPassword" : password
            });

            localStorage.setItem('jwt', JSON.stringify(response.headers.get("authorization")));
            console.log('Login successful');
            history('/configurator');
        } catch (error) {
            console.error('Login failed:', error.response ? error.response.data : error.message);
            setError('Invalid username or password.');
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{ width: '500px', height: 'auto' }}>
                <MDBContainer className="p-3">
                    <h2 className="mb-4 text-center">Login Page</h2>
                    <MDBInput wrapperClass='mb-4' placeholder='Email address' id='email' value={email} type='email' onChange={(e) => setEmail(e.target.value)} />
                    <MDBInput wrapperClass='mb-4' placeholder='Password' id='password' type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
                    {error && <p className="text-danger">{error}</p>} {/* Render error message if exists */}
                    <button className="mb-4 d-block btn-primary" style={{ height:'50px',width: '100%' }} onClick={handleLogin}>Sign in</button>
                    <div className="text-center">
                        <p>Not a member? <a href="/signup" >Register</a></p>
                    </div>
                </MDBContainer>
            </div>
        </div>
    );

    // return (
    //     <div>
    //         <div>
    //             <label htmlFor={"username"}>Username</label>
    //             <input type={"email"} id={"username"} value={email}
    //                    onChange={(event) => setEmail(event.target.value)}/>
    //         </div>
    //         <div>
    //             <label htmlFor={"password"}>Password</label>
    //             <input type={"password"} id={"password"} value={password}
    //                    onChange={(event) => setPassword(event.target.value)}/>
    //         </div>
    //         <div>
    //             <button id={"submit"} type={"button"} onClick={() => sendLogInRequest()}>LogIn</button>
    //         </div>
    //     </div>
    // );
};

export default Login;