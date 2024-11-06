import axiosInstance from './axiosInstance'; // Assuming you are using axiosInstance for authenticated requests

// Fetch all reports
export const getAllReports = async () => {
    try {
        const response = await axiosInstance.get('/user/reports'); // Adjust the API endpoint if necessary
        return response.data;
    } catch (error) {
        console.error('Error fetching reports:', error);
        throw error;
    }
};

// Generate a new report
export const generateReport = async (reportData) => {
    try {
        const response = await axiosInstance.post('/user/reports/generate', reportData); // Send the report data
        return response.data;
    } catch (error) {
        console.error('Error generating report:', error);
        throw error;
    }
};
