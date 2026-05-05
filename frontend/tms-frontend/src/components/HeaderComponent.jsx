import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';

const HeaderComponent = () => {

  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const location = useLocation(); //THIS FIXES YOUR ISSUE

  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsAuthenticated(!!token);
  }, [location]); // re-runs on route change

  const logout = () => {
    localStorage.clear();
    window.location.href = "/login"; // simple & fine
  };

  return (
    <header>
      <nav className='navbar navbar-dark bg-dark px-3'>

        {/* Left */}
        <Link to="/tasks" className="navbar-brand">
          Task Management System
        </Link>

        {/* Right */}
        <div>
          {isAuthenticated ? (
            <button onClick={logout} className="btn btn-danger">
              Logout
            </button>
          ) : (
            <>
              <Link to="/login" className="btn btn-primary me-2">
                Login
              </Link>
              <Link to="/register" className="btn btn-success">
                Register
              </Link>
            </>
          )}
        </div>

      </nav>
    </header>
  );
};

export default HeaderComponent;