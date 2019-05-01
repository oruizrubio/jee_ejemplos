package com.java2s.common;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


@ManagedBean(name="customer")
@SessionScoped
public class CustomerBean implements Serializable{
	//resource injection
	@Resource(name="jdbc/java2s")
	private DataSource ds;
	//if resource inject is not support, you still can get it manually.
	public CustomerBean(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/java2s");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//connect to DB and get customer list
	public List<Customer> getCustomerList() throws SQLException{
		if(ds==null)
			throw new SQLException("Can't get data source");
		//get database connection
		Connection con = ds.getConnection();
		
		if(con==null)
			throw new SQLException("Can't get database connection");

//        con.createStatement().executeUpdate("create table customer(customer_id varchar(45),"
  //          +" name varchar(45),"
    //        +"address varchar(45),"+
      //      "created_date date)");

         con.createStatement().executeUpdate("insert into customer(customer_id,"
            +" name ,"
            +"address,"+
            "created_date)values('1','java2s','Main Street',null)");
		
		PreparedStatement ps 
			= con.prepareStatement(
				"select customer_id, name, address, created_date from customer"); 
		ResultSet result =  ps.executeQuery();
		List<Customer> list = new ArrayList<Customer>();
		while(result.next()){
			Customer cust = new Customer();
			cust.setCustomerID(result.getLong("customer_id"));
			cust.setName(result.getString("name"));
			cust.setAddress(result.getString("address"));
			cust.setCreated_date(result.getDate("created_date"));
			list.add(cust);
		}
		return list;
	}
}
