import { useState ,React} from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

const RegistrationForm = () => {


    // usestate hook to make the form data reactive or dynamic
    // maintaing state of the form data
    const [form,setForm] = useState({
        username: "",
        email: "",
        password: ""
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await api.post("/auth/register", form);
            console.log(response.data);
            alert("Registration successful!");
            navigate("/login");
        } catch (error) {
            console.error(error);
            alert("Registration failed!");
        }
    }

    return (
        <form onSubmit={handleSubmit}>
         <h2>Register</h2>
            <input
                type="text"
                name="username"
                value={form.username}
                onChange={handleChange}
                placeholder="Username"
                required
            />
            <input
                type="email"
                name="email"
                value={form.email}
                onChange={handleChange}
                placeholder="Email"
                required
            />
            <input
                type="password"
                name="password"
                value={form.password}
                onChange={handleChange}
                placeholder="Password"
                required
            />
            <button type="submit">Register</button>
        </form>
    );
}

export default RegistrationForm;