package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import javabeans.Cliente;

/**
 * Session Bean implementation class ClientesEjb
 */
@Stateless
@LocalBean
public class ClientesEjb implements ClientesEjbLocal {
	
	@Resource(mappedName="java:comp/env/aliaslibros",type=javax.sql.DataSource.class)
	DataSource ds;
	
	@Override
	public boolean autenticar(String user, String pass) {
		boolean res=false;
		try(Connection con=ds.getConnection()){
			String sql="select * from clientes where usuario=? and password=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				res=true;
			}			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return res;
	}

	@Override
	public void registrar(Cliente c) {
		try(Connection cn=ds.getConnection()) {                       
	           
            String sql="insert into clientes (usuario,password,email,telefono) ";
            sql+="values(?,?,?,?)";
            //creamos consulta preparada:
            PreparedStatement ps=cn.prepareStatement(sql);
               //Sustituimos parametros por valores
               ps.setString(1, c.getUsuario());
               ps.setString(2, c.getPassword());
               ps.setString(3, c.getEmail());
               ps.setInt(4, c.getTelefono());
               //ejecutamos
             ps.execute();
            
        }  catch (SQLException ex) {
            ex.printStackTrace();
        }  
		
	}

}
