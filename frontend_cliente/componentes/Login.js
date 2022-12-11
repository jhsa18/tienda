import swal from "sweetalert";
import axios from "axios";
import { useState }  from "react";
import { useNavigate } from "react-router-dom";
const URI = "http://localhost:8080/cliente/"
const Login = () => {
    const navigate = useNavigate();
    const [clientes, setClientes] = useState([])
    const [id_cliente, setId_cliente] = useState("");
    const [clave_cliente, setClave_cliente] = useState("");
    const guardar = async (e) => {
        e.preventDefault();
        
        try {          
            const res = await axios({
                method : "GET",
                url: URI + "login?usuario="+id_cliente+"&clave="+clave_cliente
            });          
            setClientes(res.data)
            if (res.data.id_cliente==null) {
                
                swal("Cliente NO Autorizado!", "Presiona el butón!", "error");
                navigate("/");
                
            } else {
               sessionStorage.setItem("usuario",id_cliente);
               sessionStorage.setItem("clave",clave_cliente);
               swal("Bienvenido "+res.data.nombre_cliente+"!", "Presiona el butón!", "success");
               navigate("/");
            }
        }
        catch (error) {
            swal("Operación NO realizada")
        }
      };

    return (

        <div className="container" >
        <div className="row" id="centrado">
            <div className="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3" >
                <form onSubmit={guardar} >
                    <fieldset >
                        <h2>Ingreso al sistema</h2>
                        <hr className="colorgraph"/>
                        <div className="form-group">
                            <input type="text" name="id" id="id" value={id_cliente} maxLength={15} required
                            onInvalid={e => e.target.setCustomValidity('El campo Id cliente es obligatorio')}
                            onInput={e => e.target.setCustomValidity('')} class="form-control input-lg" placeholder="Digite el ID" onChange={(e) => setId_cliente(e.target.value)}/>
                        </div><br></br>
                        <div className="form-group">
                            <input type="password" name="password" id="password" 
                            value={clave_cliente}
                            onChange={(e) => setClave_cliente(e.target.value)}
                            maxLength={50}
                            required
                            onInvalid={e => e.target.setCustomValidity('El campo Contraseña  es obligatorio')}
                            onInput={e => e.target.setCustomValidity('')}
                            class="form-control input-lg" placeholder="Contraseña"/>
                        </div><br></br>
                        
                        
                        <div className="row">
                            <div className="col-xs-12 col-sm-12 col-md-12">
                                <input type="submit" className="btn  btn-success btn-block" value="Ingresar"/>
                            </div>
                            
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
        
    );
  };
  
  export default Login;
