import swal from "sweetalert";
import { useNavigate } from "react-router-dom";


const Logout = () => {
    
    sessionStorage.removeItem("usuario")
    sessionStorage.removeItem("clave")
    const navigate = useNavigate();
    swal("Sesión Finalizada!", "Presiona el butón!", "success");
    navigate("/");



    return (
        

<       div className='container'>
            <br>
            </br>
            <p>Sesión Cerrada</p>
            
        </div>




    );
  };
  
  export default Logout;
