package com.unab.tienda.Controller;

import com.unab.tienda.Models.Pedido;
import com.unab.tienda.Security.Hash;
import com.unab.tienda.Dao.PedidoDao;
import com.unab.tienda.Models.Cliente;
import com.unab.tienda.Dao.ClienteDao;
import com.unab.tienda.Service.PedidoService;

import java.util.List;

import javax.validation.Valid;

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
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoDao pedidoDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping(value = "/")
    public ResponseEntity<Pedido> agregar(@RequestHeader("clave") String clave,
            @RequestHeader("usuario") String usuario, @Valid @RequestBody Pedido cuenta) {
        Cliente cliente1 = new Cliente();
        cliente1 = clienteDao.login(usuario, Hash.sha1(clave));
        if (cliente1 != null) {
            return new ResponseEntity<>(pedidoService.save(cuenta), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Pedido> eliminar(@PathVariable String id) {
        Pedido obj = pedidoService.findById(id);
        if (obj != null)
            pedidoService.delete(id);
        else
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Pedido> editar(@RequestBody Pedido pedido) {
        Pedido obj = pedidoService.findById(pedido.getId_cuenta());
        if (obj != null) {

            obj.setFecha_apertura(pedido.getFecha_apertura());
            obj.setSaldo_pedido(pedido.getSaldo_pedido());
            obj.setCliente(pedido.getCliente());

            pedidoService.save(obj);
        } else
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping(value = "/deposito")
    public void deposito(@RequestParam("idcta") String idcta, @RequestParam("valor_deposito") Double valor_deposito,
            @RequestHeader("clave") String clave, @RequestHeader("usuario") String usuario) {
        Cliente cliente1 = new Cliente();
        cliente1 = clienteDao.login(usuario, Hash.sha1(clave));
        if (cliente1 != null) {
            pedidoService.deposito(idcta, valor_deposito);
        }

    }

    @PutMapping(value = "/retiro")
    public void retiro(@RequestParam("idcta") String idcta, @RequestParam("valor_retiro") Double valor_retiro,
            @RequestHeader("clave") String clave, @RequestHeader("usuario") String usuario) {
        Cliente cliente1 = new Cliente();
        cliente1 = clienteDao.login(usuario, Hash.sha1(clave));
        if (cliente1 != null) {
            pedidoService.retiro(idcta, valor_retiro);
        }

    }

    @GetMapping("/list")
    public List<Pedido> consultarTodo() {
        return cuentaService.findByAll();
    }

    @GetMapping("/list/{id}")
    public Pedido consultaPorId(@PathVariable String id) {
        return cuentaService.findById(id);
    }

    @GetMapping("/consulta_cuenta")
    @ResponseBody
    public ResponseEntity<List<Pedido>> consulta_cuenta(@RequestParam("idc") String idc,
            @RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave) {
        Cliente cliente = new Cliente();
        cliente = clienteDao.login(usuario, Hash.sha1(clave));
        if (cliente != null) {
            return new ResponseEntity<>(cuentaService.consulta_cuenta(idc), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
