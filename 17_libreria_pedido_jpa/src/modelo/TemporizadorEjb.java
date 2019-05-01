package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.sql.DataSource;

/**
 * Session Bean implementation class TemporizadorEjb
 */
@Singleton
@LocalBean
public class TemporizadorEjb implements TemporizadorEjbLocal {

	@Resource
	SessionContext sc;
	@Resource(mappedName="java:comp/env/aliaslibros",type=javax.sql.DataSource.class)
	DataSource ds;
	
	
	Timer tm;
	
		@Timeout
		void actualizaResumen(Timer t) {
			try(Connection cn=ds.getConnection()) {                       
				  
	            String sql="Update resumen set totalUnidades=(select count(*) from ventas)";
	            
	            //creamos consulta preparada:
	            PreparedStatement ps=cn.prepareStatement(sql);
	            ps.execute();
	            
	        }  catch (SQLException ex) {
	            ex.printStackTrace();
	        }  
		}
	
		@Override
		public void iniciarTemporizador(long periodo) {
			if(tm==null) {
				tm=sc.getTimerService().createTimer(0, periodo, "mensaje de temporizador");
				System.out.println("inicio de temporizador!!!");
			}
		}
		@Override
		public void detenerTemporizador() {
			if(tm!=null) {
				tm.cancel();
				tm=null;
				System.out.println("cancela temporizador!!!");
			}
			
		}

}
