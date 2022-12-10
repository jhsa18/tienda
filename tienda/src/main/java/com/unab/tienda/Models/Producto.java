package com.unab.tienda.Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int id_producto;
    // @NotEmpty(message = "EL campo Fecha transacción no debe ser vacío")
    @Column(name = "producto")
    private String producto;
    // @NotEmpty(message = "EL campo valor transacción no debe ser vacío")
    @Column(name = "precio")
    private double valor_transaccion;
    // @NotEmpty(message = "EL campo Tipo transacción no debe ser vacío")
    @Column(name = "cantidad")
    private String tipo_transaccion;

    // @NotEmpty(message = "EL campo identificador cuenta que realizó la transacción
    // no debe ser vacío")
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Pedido;

    @Override
    public String toString() {
        return "Producto [id_producto=" + id_p + ", producto="producto+ ", precio=" + precio + ", cantidad=" + cantidad + ", pedido="
                + pedido]";
    }
}
