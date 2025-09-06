import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../services/api";
import "../App.css"; // ensure styles are imported

const PrescriptionForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState({
    patientName: "",
    patientAge: "",
    patientGender: "",
    diagnosis: "",
    medicines: "",
    prescriptionDate: "",
    nextVisitDate: ""
  });

  useEffect(() => {
    if (id) {
      const fetchPrescription = async () => {
        // api has to be built in such a way that it fetches one prescription by id
        const res = await api.get(`/prescriptions/fetch-one/${id}`);
        setForm(res.data);
      };
      fetchPrescription();
    }
  }, [id]);

  

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (id) {
        await api.put(`/prescriptions/edit/${id}`, form);
      } else {
        await api.post("/prescriptions/create-prescription", form);
      }
      navigate("/prescriptions");
    } catch (err) {
      alert("Failed to save prescription");
    }
  };

  return (
   <div className="prescription-form-container">
  <h1>Prescription Form</h1>
  <h2>{id ? "Edit" : "New"} Prescription</h2>
  <form onSubmit={handleSubmit}>
    <input name="patientName" placeholder="Patient Name" value={form.patientName} onChange={handleChange} required />
    <input name="patientAge" placeholder="Age" value={form.patientAge} onChange={handleChange} required />
    <select name="patientGender" value={form.patientGender} onChange={handleChange} required>
      <option value="">Select Gender</option>
      <option value="Male">Male</option>
      <option value="Female">Female</option>
    </select>
    <input name="diagnosis" placeholder="Diagnosis" value={form.diagnosis} onChange={handleChange} required />
    <input name="medicines" placeholder="Medicines" value={form.medicines} onChange={handleChange} required />

    <div className="date-row">
      <div className="date-cell">
        <label className="date-label">Prescription Date</label>
        <input name="prescriptionDate" type="date" value={form.prescriptionDate} onChange={handleChange} required />
      </div>
      <div className="date-cell">
        <label className="date-label">Next Visit Date</label>
        <input name="nextVisitDate" type="date" value={form.nextVisitDate} onChange={handleChange} />
      </div>
    </div>

    <button type="submit">Save</button>
  </form>
</div>


  );
};

export default PrescriptionForm;
