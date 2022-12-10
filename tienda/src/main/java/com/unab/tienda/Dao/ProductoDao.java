package com.unab.tienda.Dao;

import com.unab.tienda.Models.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductoDao extends CrudRepository<Producto, Integer> {
    // Operación para seleccionar transacciones de una cuenta en particular (SELECT)
    @Transactional(readOnly = true) // No afecta integridad base de datos
    @Query(value = "SELECT * FROM transaccion WHERE id_cuenta= :idcta", nativeQuery = true)
    public List<Producto> consulta_transaccion(@Param("idcta") String idcta);

    // Operación Crear transacción por depósito o retiro
    @Transactional(readOnly = false)
    @Modifying
    @Query(value = "INSERT INTO transaccion(fecha_transaccion,valor_transaccion,tipo_transaccion,id_cuenta) VALUES (current_date(), :valor_transaccion, :tipo, :idcta)", nativeQuery = true)
    public void crear_transaccion(@Param("idcta") String idcta, @Param("valor_transaccion") Double valor_transaccion,
            @Param("tipo") String tipo);
}
