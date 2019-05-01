package modelo;

import javax.ejb.Local;

import javabeans.Cliente;

@Local
public interface ClientesEjbLocal {

	void registrar(Cliente c);

	boolean autenticar(String user, String pass);

}
