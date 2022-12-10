package com.unab.tienda.Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "pedido")
public class Pedido implements Serializable {
    @Id
    @NotEmpty(message = "El campo identificador de la cuenta no debe ser vacío")
    @Column(name = "id_pedido")
    private String id_pedido;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Override
    public String toString() {
        return "Cuenta [id_pedido=" + id_pedido + ", fecha_pedido=" + fecha + "cliente=" + cliente + "]";
    }

}
