import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './Component/User/Login';
import Register from './Component/User/Register';
import Home from './Home';
import Dashboard from './Component/User/Dashboard';
import Expense from './Component/Expense/Expense';
import Category from './Component/Category/Category';
import Report from './Component/Report/Report';
import Notification from './Component/Notification/Notification';
// Placeholder for registration page

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/user/dashboard" element={<Dashboard />} />
        <Route path="/user/expense" element={<Expense />} />
        <Route path="/user/Category" element={<Category />} />
        <Route path="/user/Report" element={<Report />} />
        <Route path="/user/Notification" element={<Notification />} />
        <Route path="/" element={<Home />} />
      </Routes>
    </Router>
  );
}

export default App;
