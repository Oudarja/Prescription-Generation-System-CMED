import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import "../App.css";

const PrescriptionList = () => {
  const [prescriptions, setPrescriptions] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPrescriptions = async () => {
      try {
        const res = await api.get("/prescriptions/fetch-all-prescriptions");
        setPrescriptions(res.data);
      } catch (err) {
        alert("Failed to fetch prescriptions");
      }
    };
    fetchPrescriptions();
  }, []);

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete?")) {
      await api.delete(`/prescriptions/delete/${id}`);
      setPrescriptions(prescriptions.filter((p) => p.id !== id));
    }
  };

  return (
    <div className="prescription-page">
      {/* Top container for heading */}
      <div className="prescription-heading-container">
        <h1>Prescription List</h1>
      </div>

      {/* Bottom container for table */}
      <div className="prescription-table-container">
        <table className="prescription-table">
          <thead>
            <tr>
              <th>Patient Name</th>
              <th>Age</th>
              <th>Gender</th>
              <th>Diagnosis</th>
              <th>Medicines</th>
              <th>Prescribed Date</th>
              <th>Next Visit Date</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {prescriptions.map((p) => (
              <tr key={p.id}>
                <td>{p.patientName}</td>
                <td>{p.patientAge}</td>
                <td>{p.patientGender}</td>
                <td>{p.diagnosis}</td>
                <td>{p.medicines}</td>
                <td>{p.prescriptionDate}</td>
                <td>{p.nextVisitDate}</td>
                <td className="actions">
                  <button
                    className="edit-btn"
                    onClick={() => navigate(`/prescription/new/${p.id}`)}
                  >
                    Edit
                  </button>
                  <button
                    className="delete-btn"
                    onClick={() => handleDelete(p.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default PrescriptionList;
