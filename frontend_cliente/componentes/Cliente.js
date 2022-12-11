import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { Link,useNavigate} from "react-router-dom";
const URI = "http://localhost:8080/pedido/"

let headers = {
    "usuario" : sessionStorage.getItem("usuario"),
    "clave"   : sessionStorage.getItem("clave")
  };
const pedido = () => {
    const navigate = useNavigate();
    const [pedidos, setpedidos] = useState([])
    useEffect(() =>{
        getpedidos()
    })
    const getpedidos = async () =>{
        try {
            
            const res = await axios({
                method : "GET",
                url : URI + "consulta_pedido?idc="+sessionStorage.getItem("usuario"),
                headers: headers  
            });
                setpedidos(res.data)
        }
        catch (error) {
            swal("No tiene Acceso a esta Opción!", "Presiona el butón!", "error");
            navigate("/");
        }
    }

    return(
        <div className='container'>
        <div className='row'>
            <div className='col'>
                <table className='table'>
                    <thead className='table-primary'>
                        <tr>
                            <th>pedido</th>
                            <th>Fecha Apertura</th>
                            <th>Saldo</th>
                        </tr>
                    </thead>
                    <tbody>
                        { pedidos.map ( (pedido) => (
                           
                            <tr key={ pedido.id_pedido}>
                                <td> { pedido.id_pedido } </td>
                                <td> { pedido.fecha_apertura.substring(0,10) } </td>
                                <td> { pedido.saldo_pedido } </td>
                                <td>
                                    <Link to={`/deposito/${pedido.id_pedido}`} className='btn btn-info'><i className="fas fa-donate"></i>Depósito</Link>
                                    <Link to={`/retiro/${pedido.id_pedido}`} className='btn btn-warning'><i className="fas fa-money-bill-alt"></i>Retiro</Link>
                                    <Link to={`/movimiento/${pedido.id_pedido}`} className='btn btn-success'><i className="fas fa-file-invoice-dollar"></i>Movimientos</Link>
                                </td>
                            </tr>
                        )) }
                    </tbody>
                </table>
            </div>    
        </div>
    </div>
    );
};

export default pedido;