import React, { useEffect, useState } from 'react';
import { getAllReports, generateReport } from '../../Service/reportService'; // Import your service functions
import Navbar from '../../Navbar';
import './Report.css';

const Report = () => {
    const [reports, setReports] = useState([]);
    const [showForm, setShowForm] = useState(false); // To toggle form visibility
    const [formData, setFormData] = useState({
        userId: '',
        period: '',
        totalAmount: ''
    }); // State to capture form input

    // Fetch all reports when the component mounts
    useEffect(() => {
        const fetchReports = async () => {
            try {
                const data = await getAllReports();
                setReports(data);
            } catch (error) {
                console.error('Error fetching reports:', error);
            }
        };

        fetchReports();
    }, []);

    const handleGenerateReport = async (event) => {
        event.preventDefault(); // Prevent default form submission behavior
        try {
            // Structure the report data as required
            const reportData = {
                user: { userId: formData.userId },
                period: formData.period,
                totalAmount: parseFloat(formData.totalAmount) // Convert amount to a number
            };
            await generateReport(reportData); // Pass reportData to the API
            alert('Report generated successfully!');
            // Refresh the report list after generating
            const updatedReports = await getAllReports();
            setReports(updatedReports);
            setShowForm(false); // Hide form after successful report generation
        } catch (error) {
            console.error('Error generating report:', error);
        }
    };
    // Handle form input changes
    const handleInputChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    return (
        <div className="report-container">
            <Navbar /> {/* Navbar is included here */}
            <div className="report-content">
                <button
                    className="generate-report-btn, btn btn-primary"
                    onClick={() => setShowForm(!showForm)} // Toggle form visibility
                >
                    {showForm ? 'Close Report Form' : 'Generate Report'}
                </button>

                {/* Conditionally render the report generation form */}
                {showForm && (
                    <form className="report-form" onSubmit={handleGenerateReport}>
                        <div className="form-group">
                            <label htmlFor="userId">User ID:</label>
                            <input
                                type="number"
                                id="userId"
                                name="userId"
                                value={formData.userId}
                                onChange={handleInputChange}
                                placeholder="Enter User ID"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="period">Report Period:</label>
                            <input
                                type="text"
                                id="period"
                                name="period"
                                value={formData.period}
                                onChange={handleInputChange}
                                placeholder="Enter report period (e.g., January 2024)"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="totalAmount">Total Amount:</label>
                            <input
                                type="number"
                                id="totalAmount"
                                name="totalAmount"
                                value={formData.totalAmount}
                                onChange={handleInputChange}
                                placeholder="Enter total amount"
                                step="0.01"
                                required
                            />
                        </div>
                        <button type="submit" className="submit-btn">
                            Submit
                        </button>
                    </form>
                )}

                <table className="report-table">
                    <thead>
                        <tr>
                            <th>Report ID</th>
                            <th>Generate Date</th>
                            <th>Period</th>
                            <th>Total Amount</th>
                            <th>User ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        {reports.length > 0 ? (
                            reports.map((report) => (
                                <tr key={report.reportId}>
                                    <td>{report.reportId}</td>
                                    <td>{report.generatedDate}</td>
                                    <td>{report.period}</td>
                                    <td>${report.totalAmount}</td>
                                    <td>{report.user.userId}</td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="5">No reports found.</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Report;
