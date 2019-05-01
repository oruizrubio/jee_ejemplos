package modelo;

import java.util.List;

import javax.ejb.Local;

import entidades.Venta;

@Local
public interface PedidosEjbLocal {

	

	void registrarPedido(List<Venta> ventas);

}
