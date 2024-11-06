import React, { useEffect, useState } from 'react';
import { getAllNotifications, updateNotificationStatus } from '../../Service/notificationService'; // Import your service functions
import Navbar from '../../Navbar';
import './Notification.css'; // Import your CSS for styling

const Notification = () => {
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    const fetchNotifications = async () => {
      try {
        const data = await getAllNotifications();
        setNotifications(data);
      } catch (error) {
        console.error('Error fetching notifications:', error);
      }
    };

    fetchNotifications();
  }, []);

  const handleStatusToggle = async (notificationId, currentStatus) => {
    try {
      const newStatus = currentStatus === 'unread' ? 'read' : 'unread';

      // Update notification status in the backend
      await updateNotificationStatus(notificationId, newStatus);

      // Efficiently update local state with only status change
      setNotifications((prevNotifications) =>
        prevNotifications.map((notification) =>
          notification.notificationId === notificationId
            ? { ...notification, status: newStatus } // Update only status
            : notification
        )
      );
    } catch (error) {
      console.error('Error updating notification status:', error);
    }
  };

  return (
    <div className="dashboard-container">
      <Navbar />
      <div className="dashboard-content">
        <h1>Your Notifications</h1>
        <table className="notification-table">
          <thead>
            <tr>
              <th>Notification ID</th>
              <th>Date</th>
              <th>Message</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {notifications.length > 0 ? (
              notifications.map((notification) => (
                <tr key={notification.notificationId}>
                  <td>{notification.notificationId}</td>
                  <td>{new Date(notification.date).toLocaleDateString()}</td>
                  <td>{notification.message}</td>
                  <td>
                    <button
                      className={`status-button ${notification.status}`}
                      onClick={() => handleStatusToggle(notification.notificationId, notification.status)}
                    >
                      {notification.status}
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4">No notifications found.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Notification;