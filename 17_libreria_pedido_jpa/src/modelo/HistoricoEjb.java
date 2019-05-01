package modelo;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Cliente;
import entidades.Historico;
import entidades.Venta;


@Stateless
@LocalBean
public class HistoricoEjb implements HistoricoEjbLocal {

	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;
	@Override
	@Asynchronous
	public void guardarHistorico(List<Venta> ventas) {
		Cliente cl=ventas.get(0).getCliente();
		double cant=0;
        for(Venta v:ventas) {       	
        	cant+=v.getLibro().getPrecio();
        }
		em.persist(new Historico(0,cant,cl));
	}
}
