package com.catalogoJuegos.modelo;

import java.sql.Connection;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Clase que obtiene una conexion de un pool de conexiones a la bbdd
 * @author javaee
 *
 */
public class ConnectionManager {

	
	public static Connection getConnection() throws SQLException, ClassNotFoundException, NamingException {
		
		InitialContext initCtx=new InitialContext();;
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource dataSource = (DataSource)envCtx.lookup("jdbc/poolConnection");
		Connection con = dataSource.getConnection();

		
		return con;
	};
	

}
