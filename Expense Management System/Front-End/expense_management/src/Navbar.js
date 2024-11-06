import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Navbar.css'; // Ensure this file exists and is properly linked

const Navbar = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        if (window.confirm('Are you sure you want to logout?')) {
            localStorage.removeItem('token'); // Remove token from localStorage
            navigate('/login'); // Redirect to login page
        }
    };

    return (
        <nav className="navbar">
            <ul className="nav-list">
                <div className="nav-left">
                    <li><a href="/user/dashboard">Home</a></li>
                    <li><a href="/user/expense">Add Expense</a></li>
                    <li><a href="/user/category">Category</a></li>
                    <li><a href="/user/report">Generate Report</a></li>
                    <li><a href="/user/notification">Notification</a></li>
                </div>
                <div className="nav-right">
                    <button onClick={handleLogout} className="logout-button">Logout</button>
                </div>
            </ul>
        </nav>
    );
};

export default Navbar;
