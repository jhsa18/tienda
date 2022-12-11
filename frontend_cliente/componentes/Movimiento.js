import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { useNavigate, useParams} from "react-router-dom";
const URI = "http://localhost:8080/producto/"

let headers = {
    "usuario" : sessionStorage.getItem("usuario"),
    "clave"   : sessionStorage.getItem("clave")
  };

const Mostrarproducto = () => {
    const navigate = useNavigate();
    const [producto, setproducto] = useState([])
    useEffect(() =>{
        getproductoes()
    })
    const {id} = useParams()
    const getproductoes = async () =>{
        try {
            const res = await axios({
                method : "GET",
                url : URI + "consulta_producto?idcta="+id,
                headers: headers 
               
            });
            
            setproducto(res.data)
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
                        <th>Id</th>
                        <th>Fecha</th>
                        <th>Valor</th>
                        <th>Tipo</th>
                    </tr>
                </thead>
                <tbody>
                    { productoes.map ( (producto) => (
                        <tr key={ producto.id_producto}>
                            <td> { producto.id_producto } </td>
                            <td> { producto.precio_producto } </td>
                            <td> { producto.cantidad_producto } </td>
                            
                        </tr>
                    )) }
                </tbody>
            </table>
        </div>    
    </div>
</div>
    );
};

export default Mostrarproducto;