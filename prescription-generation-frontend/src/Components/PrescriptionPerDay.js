import React, { useEffect, useState } from "react";
import api from "../services/api";
import "../App.css";

const PrescriptionPerDay = () => {
  const [prescriptions, setPrescriptions] = useState([]);

  useEffect(() => {
    const fetchPrescriptions = async () => {
      try {
        const response = await api.get("/prescriptions/report/daywise");
        setPrescriptions(response.data);
      } catch (error) {
        console.error("Error fetching prescriptions:", error);
      }
    };

    fetchPrescriptions();
  }, []);

  return (
    <div className="prescription-per-day" style={{ maxWidth: "400px", margin: "auto" }}>
      <h2 style={{ textAlign: "center" }}>Prescriptions Per Day</h2>
      <table style={{ width: "100%", borderCollapse: "collapse" }}>
        <thead>
          <tr>
            <th style={{ border: "1px solid black", padding: "10px", textAlign: "center" }}>Date</th>
            <th style={{ border: "1px solid black", padding: "10px", textAlign: "center" }}>Count</th>
          </tr>
        </thead>
        <tbody>
          {prescriptions.map((prescription) => (
            <tr key={prescription[0]}>
              <td style={{ border: "1px solid black", padding: "8px", textAlign: "center" }}>{prescription[0]}</td>
              <td style={{ border: "1px solid black", padding: "8px", textAlign: "center" }}>{prescription[1]}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PrescriptionPerDay;
