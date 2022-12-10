package com.unab.tienda.Controller;

import com.unab.tienda.Models.Producto;
import com.unab.tienda.Security.Hash;
import com.unab.tienda.Models.Cliente;
import com.unab.tienda.Dao.ProductoDao;
import com.unab.tienda.Dao.ClienteDao;
import com.unab.tienda.Service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private ClienteDao clienteDao;
    @Autowired
    private ProductoService transaccionService;

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<Producto> agregar(@RequestBody Producto) {
        Producto obj = transaccionService.save(transaccion);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping(value = "/crear_producto")
    public void crear_transaccion(@RequestParam("idcta") String idcta,
            @RequestParam("precio") Double valor_transaccion, @RequestParam("tipo") String tipo,
            @RequestHeader("clave") String clave, @RequestHeader("usuario") String usuario) {
        Cliente cliente1 = new Cliente();
        cliente1 = clienteDao.login(usuario, Hash.sha1(clave));
        if (cliente1 != null) {
            transaccionService.cear_transaccion(idcta, valor_transaccion, tipo);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> eliminar(@PathVariable Integer id) {
        Producto obj = transaccionService.findById(id);
        if (obj != null)
            transaccionService.delete(id);
        else
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Producto> editar(@RequestBody Producto transaccion) {
        Producto obj = transaccionService.findById(transaccion.getId_transaccion());
        if (obj != null) {
            obj.setValor_transaccion(transaccion.getValor_transaccion());
            obj.setFecha_transaccion(transaccion.getFecha_transaccion());
            obj.setCuenta(transaccion.getCuenta());
            transaccionService.save(obj);
        } else
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Producto> consultarTodo() {
        return transaccionService.findByAll();
    }

    @GetMapping("/list/{id}")
    public Producto consultaPorId(@PathVariable Integer id) {
        return transaccionService.findById(id);
    }

    @GetMapping("/consulta_transaccion")
    @ResponseBody
    public ResponseEntity<List<Producto>> consulta_transaccion(@RequestParam("idcta") String idcta,
            @RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave) {
        Cliente cliente = new Cliente();
        cliente = clienteDao.login(usuario, Hash.sha1(clave));
        if (cliente != null) {
            return new ResponseEntity<>(transaccionService.consulta_transaccion(idcta), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
