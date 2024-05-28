import {useLocalState} from "../../util/localStorage";
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import {
    MDBContainer,
    MDBInput,
    MDBBtn,
} from 'mdb-react-ui-kit';


const SignUp = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [role, setRole] = useState('ROLE_USER');
    const [error, setError] = useState('');
    const [jwt, setJwt] = useLocalState("", "jwt");
    const history = useNavigate();

    const handleSignup = async () => {
        try {
            if (!firstName || !lastName || !email || !password || !confirmPassword) {
                setError('Please fill in all fields.');
                return;
            }

            if (password !== confirmPassword) {
                throw new Error("Passwords do not match");
            }

            const response = await axios.post('http://localhost:8080/api/auth/add', {
                "userEmail" : email,
                "userPassword" : password,
                "userFirstName" : firstName,
                "userLastName" : lastName
            });
            console.log(response.data);
            history('/login');
        } catch (error) {
            console.error('Signup failed:', error.response ? error.response.data : error.message);
            setError(error.response ? error.response.data : error.message);
        }
    };

    // function signUp() {
    //     fetch("api/users", {
    //         method: "POST",
    //         headers : {
    //             "Content-Type": "application/json",
    //             "Authorization" : `Bearer ${jwt}`
    //         }
    //     }).then((response) => {
    //         if (response.status === 200) {
    //             return response.json();
    //         }
    //     }).then((data) => {
    //         console.log(data)
    //     })
    // }

    // const [jwt, setJwt] = useLocalState("", "jwt");
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
    //         alert(message)
    //     });
    // }

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{width: '600px', height: 'auto'}}>
                <MDBContainer className="p-3">
                    <h2 className="mb-4 text-center">Sign Up Page</h2>
                    {/* Render error message if exists */}
                    {error && <p className="text-danger">{error}</p>}
                    <MDBInput wrapperClass='mb-3' id='firstName' placeholder={"First Name"} value={firstName} type='text'
                              onChange={(e) => setFirstName(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' id='lastName' placeholder={"Last Name"} value={lastName} type='text'
                              onChange={(e) => setLastName(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Email Address' id='email' value={email} type='email'
                              onChange={(e) => setEmail(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Password' id='password' type='password' value={password}
                              onChange={(e) => setPassword(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Confirm Password' id='confirmPassword' type='password'
                              value={confirmPassword}
                              onChange={(e) => setConfirmPassword(e.target.value)}/>
                    {/*<label className="form-label mb-1">Role:</label>*/}
                    {/*<select className="form-select mb-4" value={role} onChange={(e) => setRole(e.target.value)}>*/}
                    {/*    <option value="ROLE_CUSTOMER">User</option>*/}
                    {/*    <option value="ROLE_ADMIN">Admin</option>*/}
                    {/*</select>*/}
                    <button className="mb-4 d-block mx-auto fixed-action-btn btn-primary"
                            style={{height: '40px', width: '100%'}}
                            onClick={handleSignup}>Sign Up
                    </button>

                    <div className="text-center">
                        <p>Already Registered? <a href="/login">Login</a></p>
                    </div>

                </MDBContainer>
            </div>
        </div>
    );

    // <div>
        //     <div>
        //         <label htmlFor={"username"}>Username</label>
        //         <input type={"email"} id={"username"} value={email}
        //                onChange={(event) => setEmail(event.target.value)}/>
        //     </div>
        //     <div>
        //         <label htmlFor={"password"}>Password</label>
        //         <input type={"password"} id={"password"} value={password}
        //                onChange={(event) => setPassword(event.target.value)}/>
        //     </div>
        //     <div>
        //         <button id={"submit"} type={"button"} onClick={() => sendLogInRequest()}>LogIn</button>
        //     </div>
        // </div>


};

export default SignUp;