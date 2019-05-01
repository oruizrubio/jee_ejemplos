package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import modelo.ClientesEjbLocal;
import modelo.TemasEjbLocal;
import modelo.TemporizadorEjbLocal;



@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	@EJB
	TemasEjbLocal gtemas;
	@EJB
	ClientesEjbLocal gestion;
	@EJB
	TemporizadorEjbLocal tmp;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		boolean resultado=false;
		Cliente cl=gestion.autenticar(request.getParameter("user"),request.getParameter("pwd"));
		if(cl!=null){
			request.getSession().setAttribute("idCliente", cl.getIdCliente());
			request.setAttribute("temas", gtemas.obtenerTemas());
			
            resultado=true;
            tmp.iniciarTemporizador(3000);

		}
		else{
			request.setAttribute("mensaje", "Usuario no registrado");
			
		}
       
        request.setAttribute("resultado",resultado);
	}
	

}
