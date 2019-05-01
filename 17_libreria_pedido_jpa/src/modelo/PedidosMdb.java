package modelo;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import entidades.Venta;

/**
 * Message-Driven Bean implementation class for: PedidosMdb
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationLookup", propertyValue = "jms/colapedidos"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "jms/colapedidos")
public class PedidosMdb implements MessageListener {

	@EJB
	PedidosEjbLocal pedidos;
    
    public void onMessage(Message message) {
        ObjectMessage ob=(ObjectMessage)message;
        try {
        	List<Venta> ventas=(List<Venta>)ob.getObject();
        	pedidos.registrarPedido(ventas);
        	/* opción para tipo Pedido
        	 * Pedido pedido=(Pedido)ob.getObject();
        		pedidos.registrarPedido(pedido);
        	 */
        }
        catch(JMSException ex) {
        	ex.printStackTrace();
        }
        
    }

}
