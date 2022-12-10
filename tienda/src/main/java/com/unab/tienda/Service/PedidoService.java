package com.unab.tienda.Service;

import com.unab.tienda.Models.Pedido;
import com.unab.tienda.Dao.PedidoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
    @Autowired
    private PedidoDao cuentaDao;

    @Transactional(readOnly = false)
    public Pedido save(Pedido pedido) {
        return cuentaDao.save(pedido);
    }

    @Transactional(readOnly = false)
    public void delete(String id) {
        cuentaDao.deleteById(id);
        ;
    }

    @Transactional(readOnly = true)
    public Pedido findById(String id) {
        return cuentaDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Pedido> findByAll() {
        return (List<Pedido>) cuentaDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Pedido> consulta_pedido(String idc) {
        return (List<Pedido>) cuentaDao.consulta_cuenta(idc);
    }

    @Transactional(readOnly = false)
    public void deposito(String idcta, Double valor_deposito) {
        cuentaDao.deposito(idcta, valor_deposito);
    }

    @Transactional(readOnly = false)
    public void retiro(String idcta, Double valor_retiro) {
        pedidoDao.retiro(idcta, valor_retiro);
    }
}
