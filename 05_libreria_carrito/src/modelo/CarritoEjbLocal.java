package modelo;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remove;

import javabeans.Libro;

@Local
public interface CarritoEjbLocal {

	List<Libro> recuperarCarrito();

	void eliminarLibro(int pos);

	void agregarCarrito(Libro lib);

	void eliminar();

}
