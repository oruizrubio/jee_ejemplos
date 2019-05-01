package modelo;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Libro;
import entidades.Tema;

/**
 * Session Bean implementation class LibrosEjb
 */
@Stateless
@LocalBean
public class LibrosEjb implements LibrosEjbLocal {

	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;	

	public List<Libro> recuperarLibros(int idTema){
		Tema t=em.find(Tema.class, idTema);
		return t.getLibros();
		
	}
	public List<Libro> recuperarLibros(String tema){
		String jpql="Select l From Libro l Where l.tema.tema=?1";
		TypedQuery<Libro> tq=em.createQuery(jpql,Libro.class);
		tq.setParameter(1, tema);
		return tq.getResultList();
	}
	
	public Tema buscarTema(int idTema) {
		Tema t=em.find(Tema.class, idTema);
		em.detach(t); //desvincula la instancia de la capa de persistencia
		return t;
	}
	
	public void eliminarTema(int idTema) {
		Tema t=em.find(Tema.class, idTema);
		em.remove(t);
	}
}
