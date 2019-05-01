package modelo;

import java.util.List;

import javax.ejb.Local;

import entidades.Venta;

@Local
public interface HistoricoEjbLocal {

	void guardarHistorico(List<Venta> ventas);

}
