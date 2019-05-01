package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.Libro;

/**
 * Servlet implementation class EliminarCarritoAction
 */
@WebServlet("/EliminarCarritoAction")
public class EliminarCarritoAction extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		//recuperamos carrito que tenemos en un atributo de sesión
		List<Libro> carrito=(List<Libro>)s.getAttribute("carrito");
		//recogemos el parametro con la posición del libro a eliminar
		carrito.remove(Integer.parseInt(request.getParameter("pos")));
		
		request.getRequestDispatcher("LibrosAction").include(request, response);
	}

}
