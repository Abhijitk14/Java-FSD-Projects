// src/Service/notificationService.js

import axiosInstance from './axiosInstance';

export const getAllNotifications = async () => {
    try {
        const response = await axiosInstance.get('/user/notifications');
        return response.data;
    } catch (error) {
        console.error('Error fetching notifications:', error);
        throw error;
    }
};

export const updateNotificationStatus = async (notificationId, status) => {
    try {
        const response = await axiosInstance.patch(`/user/notifications/${notificationId}`, { status });
        return response.data;
    } catch (error) {
        console.error('Error updating notification status:', error);
        throw error;
    }
};
