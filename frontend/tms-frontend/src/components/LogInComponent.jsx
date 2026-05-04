import React from 'react'
import { useState } from 'react';
import { loginUser } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';

const LogInComponent = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const handleLogIn = (e) => {
        e.preventDefault();
        const logInData = {
            email,
            password
        }

        loginUser(logInData).then((response) => {
            console.log(response.data.message);
             const data = response.data.data;
              // STORE DATA
                localStorage.setItem("token", data.token);
                localStorage.setItem("userId", data.id);
                
                console.log("Login Successful");

                // REDIRECT
                navigate("/tasks");
            //navigator
        }).catch((error) => {
            console.log(error.response?.data?.message);
        });

    }
    return (
        <div className="container mt-4" style={{ paddingBottom: "70px" }}>
            <div className="row justify-content-center">
                <div className="col-md-5">
                    <div className="card shadow p-4">

                        <h2 className="text-center mb-4">
                            LogIn
                        </h2>
                        <form onSubmit={handleLogIn}>
                            <div className="mb-3">
                                <label className="form-label">Email: </label>
                                <input
                                    name='email'
                                    type='email'
                                    className="form-control"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)} />
                            </div>
                            <div className="mb-3">
                                <label className="form-label">Password: </label>
                                <input
                                    name='password'
                                    type='password'
                                    className="form-control"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)} />
                            </div>
                            <button
                                type="submit"
                                className="btn btn-primary w-100"
                            >
                                LogIn
                            </button>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    )
}

export default LogInComponent