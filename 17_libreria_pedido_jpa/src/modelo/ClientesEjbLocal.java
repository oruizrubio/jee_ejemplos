package modelo;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Cliente;

@Local
public interface ClientesEjbLocal {

	

	void registrar(Cliente c);

	Cliente buscarCliente(int idCliente);

	Cliente autenticar(String user, String pass);

}
