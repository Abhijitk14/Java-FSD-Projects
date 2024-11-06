import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Home.css'; // Ensure this file is correctly linked
import backgroundImage from './Assets/image1.png'; // Import the background image

const Home = () => {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate('/login');
  };

  const handleRegisterClick = () => {
    navigate('/register');
  };

  return (
    <div className="home-container">
      {/* Background Image */}
      <img src={backgroundImage} alt="Background" className="background-image" />
      
      <div className="main-content">
        <button className="btn btn-primary" onClick={handleLoginClick}>Login</button>
        <button className="btn btn-secondary" onClick={handleRegisterClick}>Register</button>
      </div>
    </div>
  );
};

export default Home;
