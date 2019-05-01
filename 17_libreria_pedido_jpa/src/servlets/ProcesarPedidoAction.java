package servlets;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Libro;
import entidades.Venta;
import modelo.CarritoEjbLocal;
import modelo.ClientesEjbLocal;

/**
 * Servlet implementation class ProcesarPedidoAction
 */
@WebServlet("/ProcesarPedidoAction")
public class ProcesarPedidoAction extends HttpServlet {
	@Resource(mappedName="jms/pedidosfactory",type=ConnectionFactory.class)
	ConnectionFactory cf;
	@Resource(mappedName="jms/colapedidos",type=Queue.class)
	Queue cola;
	@EJB
	ClientesEjbLocal clientes;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		CarritoEjbLocal carrito=(CarritoEjbLocal)s.getAttribute("carrito");
		if(carrito!=null) {
			List<Libro> libros=carrito.recuperarCarrito();
			int idCliente=(Integer)s.getAttribute("idCliente");
			List<Venta> ventas=new ArrayList<>();
			for(Libro lib:libros) {
				ventas.add(new Venta(0,new Date(),clientes.buscarCliente(idCliente),lib));
			}
			enviarPedido(ventas);
			s.invalidate();
		}
	}
	
	private void enviarPedido(List<Venta> ventas) {
		try(Connection cn=cf.createConnection()){
			//establecemos una sesión
			Session s=cn.createSession();
			//creamos productor de mensajes asociado a la cola
			MessageProducer mp=s.createProducer(cola);
			//creamos mensaje y lo enviamos
			ObjectMessage ob=s.createObjectMessage((Serializable)ventas);
			mp.send(ob);
			
		}catch(JMSException ex) {
			ex.printStackTrace();
		}
	}

}
