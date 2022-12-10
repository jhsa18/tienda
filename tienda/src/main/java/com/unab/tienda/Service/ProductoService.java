package com.unab.tienda.Service;

import com.unab.tienda.Models.Producto;
import com.unab.tienda.Dao.ProductoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {
    @Autowired
    private ProductoDao transaccionDao;

    @Transactional(readOnly = false)
    public Producto save(Producto transaccion) {
        return transaccionDao.save(transaccion);
    }

    @Transactional(readOnly = false)
    public void delete(Integer id) {
        transaccionDao.deleteById(id);
        ;
    }

    @Transactional(readOnly = true)
    public Producto findById(Integer id) {
        return transaccionDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Producto> findByAll() {
        return (List<Producto>) transaccionDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Producto> consulta_transaccion(String idcta) {
        return (List<Producto>) transaccionDao.consulta_transaccion(idcta);
    }

    @Transactional(readOnly = false)
    public void cear_transaccion(String idcta, Double valor_transaccion, String tipo) {
        transaccionDao.crear_transaccion(idcta, valor_transaccion, tipo);
    }

}
