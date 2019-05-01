package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.Libro;
import modelo.CarritoEjbLocal;
import modelo.LibrosEjbLocal;
@EJB(name="carritoRef",beanInterface=modelo.CarritoEjbLocal.class)
@WebServlet("/AgregarCarritoAction")
public class AgregarCarritoAction extends HttpServlet {
	
	@EJB
	LibrosEjbLocal glibros;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		CarritoEjbLocal carrito=(CarritoEjbLocal)s.getAttribute("carrito");
		if(carrito==null) {
			try {
				Context ct=new InitialContext();
				
				carrito=(CarritoEjbLocal)ct.lookup("java:comp/env/carritoRef");
				s.setAttribute("carrito", carrito);
			}catch(NamingException ex) {
				ex.printStackTrace();
			}
		}
		
		//recogemos el parametro con el isbn del libro a añadir
		int isbn=Integer.parseInt(request.getParameter("isbn"));
		
		//Añadimos libro obtenido al carrito
		carrito.agregarCarrito(glibros.recuperarLibroPorIsbn(isbn));
		
		request.setAttribute("carrito", carrito.recuperarCarrito());
		request.getRequestDispatcher("LibrosAction").include(request, response);
	}

}
