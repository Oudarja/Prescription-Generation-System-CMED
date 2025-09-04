import { useState ,React} from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";


const LoginForm = () => {

    const [form,setForm]=useState({
        email: "",
        password: ""
    });
    const navigate=useNavigate();

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    }

    const handleSubmit = async (e) => {
        // prevents the browser from reloading the page that is 
        // the default behavior of form submission
        e.preventDefault();
        try {
            const response = await api.post("/auth/login", form);
            console.log(response.data);
            // store the token in local storage
            // so that it can be used to authenticate the user in future requests
            //token is stored in the local storage with the key "token"
            localStorage.setItem("token", response.data.token);
            alert("Login successful!");
            navigate("/prescriptions");
        } catch (error) {
            console.error(error);
            alert("Login failed!");
        }
    }

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <input name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" value={form.password} onChange={handleChange} required />
        <button type="submit">Login</button>
      </form>
    </div>
  )
}

export default LoginForm