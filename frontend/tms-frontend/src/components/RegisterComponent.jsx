import React, { useState } from 'react';
import { registerUser } from '../services/AuthService';

const RegisterComponent = () => {

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(false);

    const [showPopup, setShowPopup] = useState(false);
    const [popupMessage, setPopupMessage] = useState('');
    const [popupType, setPopupType] = useState('');

    // Form Validation
    const validateForm = () => {

        let tempErrors = {};
        let valid = true;

        if (name.trim() === '') {
            tempErrors.name = "Name is required";
            valid = false;
        }

        if (email.trim() === '') {
            tempErrors.email = "Email is required";
            valid = false;
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            tempErrors.email = "Enter valid email";
            valid = false;
        }

        if (password.trim() === '') {
            tempErrors.password = "Password is required";
            valid = false;
        } else if (password.length < 5) {
            tempErrors.password = "Minimum 5 characters";
            valid = false;
        }

        setErrors(tempErrors);
        return valid;
    };

    // Save User
    const saveUser = (e) => {
        e.preventDefault();

        if (!validateForm()) {
            return;
        }

        const userData = {
            name,
            email,
            password
        };

        setLoading(true);

        registerUser(userData)

            .then((response) => {

                setPopupType("success");
                setPopupMessage(response.data.message);
                setShowPopup(true);

                // Clear form
                setName('');
                setEmail('');
                setPassword('');
                setErrors({});
            })

            .catch((error) => {

                const msg =
                    error.response?.data?.message ||
                    "Registration Failed";

                setPopupType("error");
                setPopupMessage(msg);
                setShowPopup(true);
            })

            .finally(() => {
                setLoading(false);
            });
    };

    return (
        <div className="container mt-5">

            <div className="row justify-content-center">

                <div className="col-md-5">

                    <div className="card shadow p-4">

                        <h2 className="text-center mb-4">
                            Register
                        </h2>

                        <form onSubmit={saveUser}>

                            {/* Name */}
                            <div className="mb-3">

                                <label className="form-label">
                                    Name
                                </label>

                                <input
                                    type="text"
                                    className="form-control"
                                    value={name}
                                    onChange={(e) =>
                                        setName(e.target.value)
                                    }
                                />

                                <p className="text-danger">
                                    {errors.name}
                                </p>

                            </div>

                            {/* Email */}
                            <div className="mb-3">

                                <label className="form-label">
                                    Email
                                </label>

                                <input
                                    type="email"
                                    className="form-control"
                                    value={email}
                                    onChange={(e) =>
                                        setEmail(e.target.value)
                                    }
                                />

                                <p className="text-danger">
                                    {errors.email}
                                </p>

                            </div>

                            {/* Password */}
                            <div className="mb-3">

                                <label className="form-label">
                                    Password
                                </label>

                                <input
                                    type="password"
                                    className="form-control"
                                    value={password}
                                    onChange={(e) =>
                                        setPassword(e.target.value)
                                    }
                                />

                                <p className="text-danger">
                                    {errors.password}
                                </p>

                            </div>

                            {/* Button */}
                            <button
                                className="btn btn-primary w-100"
                                type="submit"
                                disabled={loading}
                            >
                                {
                                    loading
                                        ? "Registering..."
                                        : "Register"
                                }
                            </button>

                        </form>

                    </div>

                </div>

            </div>

            {/* Popup */}
            {showPopup && (

                <div className="popup-overlay">

                    <div className="popup-box">

                        <h4
                            className={
                                popupType === "success"
                                    ? "text-success mb-3"
                                    : "text-danger mb-3"
                            }
                        >
                            {
                                popupType === "success"
                                    ? "Success"
                                    : "Error"
                            }
                        </h4>

                        <p>{popupMessage}</p>

                        <button
                            className="btn btn-secondary mt-3"
                            onClick={() =>
                                setShowPopup(false)
                            }
                        >
                            Close
                        </button>

                    </div>

                </div>
            )}

        </div>
    );
};

export default RegisterComponent;