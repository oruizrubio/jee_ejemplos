package servlets;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.CarritoEjbLocal;

/**
 * Servlet implementation class EliminarCarritoAction
 */
@WebServlet("/EliminarCarritoAction")
public class EliminarCarritoAction extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession s=request.getSession();
		CarritoEjbLocal carrito=(CarritoEjbLocal)s.getAttribute("carrito");
		if(carrito==null) {
			try {
				Context ct=new InitialContext();
				
				carrito=(CarritoEjbLocal)ct.lookup("java:comp/env/carritoRef");
			}catch(NamingException ex) {
				ex.printStackTrace();
			}
		}
		
		carrito.eliminarLibro(Integer.parseInt(request.getParameter("pos")));
		request.setAttribute("carrito", carrito.recuperarCarrito());
		request.getRequestDispatcher("LibrosAction").include(request, response);
	}

}
