import { useState ,React, useEffect} from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../services/api";


const LoginForm = ({isAuthenticated, setIsAuthenticated }) => {

  // const [isAuthenticated, setIsAuthenticated] = useState(false);

  // Check login status on load
    // useEffect(() => {
    //   const token = localStorage.getItem("token");
    //   setIsAuthenticated(!!token);
    // }, []);

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
            localStorage.setItem("token", response.data);
            alert("Login successful!");
             setIsAuthenticated(true);
            navigate("/prescriptions");
        } catch (error) {
            console.error(error);
            alert("Login failed!");
        }
    }

  return (
    <>
    {!isAuthenticated ? (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <input name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" value={form.password} onChange={handleChange} required />
        <button type="submit">Login</button>
         {/* Option for users who don't have an account */}
              <p style={{ marginTop: "15px", fontSize: "0.9rem" }}>
                Don't have an account?{" "}
                <Link to="/register" style={{ color: "#61dafb", fontWeight: "bold" }}>
                  Register
                </Link>
              </p>
      </form>
    </div>
  ) : (
    <>
      {navigate("/prescriptions")}
    </>
  )}
  </>
  )
}
export default LoginForm;
  