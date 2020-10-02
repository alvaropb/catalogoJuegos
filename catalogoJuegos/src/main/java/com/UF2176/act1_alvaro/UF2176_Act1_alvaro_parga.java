package com.UF2176.act1_alvaro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UF2176_Act1_alvaro_parga {
	private final static String URL = "jdbc:mysql://localhost/UF2176_act1_alvaro";
	private final static String USUARIO = "debian-sys-maint";
	private final static String PASS = "o8lAkaNtX91xMUcV";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		/*
		 * -- 1.- Lista el nombre de todos los productos que hay en la tabla producto
		 * 
		 * SELECT nombre FROM productos ;
		 */
		// Abrir la conexion

		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement("SELECT nombre FROM productos");

		ResultSet rs = pst.executeQuery();
		System.out.println("-- 1.- Lista el nombre de todos los productos que hay en la tabla producto");
		while (rs.next()) {
			System.out.println(rs.getString("nombre"));
		}

		/*
		 * -- 2.- Lista todas las columnas de la tabla producto
				SELECT * FROM productos ;
		 */
		
		pst = conn.prepareStatement("SELECT * FROM productos");

		rs = pst.executeQuery();
		System.out.println("-- 2.- Lista todas las columnas de la tabla producto");
		while (rs.next()) {
			System.out.println(rs.getInt("id")+ " "+ rs.getString("nombre")+" "+rs.getDouble("precio")+ " "+rs.getInt("codigo_fabricante"));
		}		
		
		/*-- 3.- Lista los nombres y los precios de todos los productos de la tabla producto, convirtiendo los nombres a mayúscula

			SELECT UPPER(nombre) as 'nombre', precio FROM productos;
		 * 
		 */
		pst = conn.prepareStatement("SELECT UPPER(nombre) as 'nombre', precio FROM productos;");

		rs = pst.executeQuery();
		System.out.println("-- 3.- Lista los nombres y los precios de todos los productos de la tabla producto, convirtiendo los nombres a mayúscula");
		while (rs.next()) {
			System.out.println(rs.getString("nombre")+" "+rs.getDouble("precio"));
		}
		
		/*
		 * -- 4.- Lista los nombres y los precios de todos los productos de la tabla producto, redondeando el valor del precio

			SELECT nombre, ROUND(precio) as 'precio' FROM productos;
		 */
		
		pst = conn.prepareStatement("SELECT nombre, ROUND(precio) as 'precio' FROM productos;");

		rs = pst.executeQuery();
		System.out.println("-- 4.- Lista los nombres y los precios de todos los productos de la tabla producto, redondeando el valor del precio");
		while (rs.next()) {
			System.out.println(rs.getString("nombre")+" "+rs.getDouble("precio"));
		}
		
		/*
			-- 5.- Lista el código de los fabricantes que tienen productos en la tabla producto

			SELECT DISTINCT(f.id) FROM productos p2 INNER JOIN Fabricantes f ON p2.codigo_fabricante =f.id;
		 */
		
		pst = conn.prepareStatement("SELECT DISTINCT(f.id) FROM productos p2 INNER JOIN Fabricantes f ON p2.codigo_fabricante =f.id;");

		rs = pst.executeQuery();
		System.out.println("-- 5.- Lista el código de los fabricantes que tienen productos en la tabla producto");
		while (rs.next()) {
			System.out.println(rs.getInt("id"));
		}
		
		
		/*
		 * -- 6.- Lista los nombres de los fabricantes ordenados de forma descendente

				SELECT nombre FROM Fabricantes ORDER BY nombre DESC ;
		 */
		
		
		pst = conn.prepareStatement("SELECT nombre FROM Fabricantes ORDER BY nombre DESC ;");

		rs = pst.executeQuery();
		System.out.println("-- 6.- Lista los nombres de los fabricantes ordenados de forma descendente");
		while (rs.next()) {
			System.out.println(rs.getString("nombre"));
		}
		
		
		/*
		 * 

			-- 7.- Lista el nombre y el precio del producto más barato

			SELECT nombre, precio FROM productos ORDER BY precio ASC LIMIT 1;
		 */
		
		pst = conn.prepareStatement("SELECT nombre, precio FROM productos ORDER BY precio ASC LIMIT 1;");

		rs = pst.executeQuery();
		System.out.println("-- 7.- Lista el nombre y el precio del producto más barato");
		while (rs.next()) {
			System.out.println(rs.getString("nombre")+" "+rs.getDouble("precio") );
		}
		
		
		/*
		 * -- 8.-Lista el nombre de los productos que no tienen un precio mayor o igual a 400€

			SELECT nombre FROM productos WHERE precio <=400;
		 */
		
		pst = conn.prepareStatement("SELECT nombre FROM productos WHERE precio <=400;");

		rs = pst.executeQuery();
		System.out.println("-- 8.-Lista el nombre de los productos que no tienen un precio mayor o igual a 400€");
		while (rs.next()) {
			System.out.println(rs.getString("nombre") );
		}
		
		/*
		 * -- 9.- Devuelve una lista con el nombre de todos los productos que contienen la cadena Portátil en el nombre

			SELECT nombre FROM productos WHERE nombre LIKE '%Portátil%';
		 */
		
		pst = conn.prepareStatement("SELECT nombre FROM productos WHERE nombre LIKE '%Portátil%';");

		rs = pst.executeQuery();
		System.out.println("-- 9.- Devuelve una lista con el nombre de todos los productos que contienen la cadena Portátil en el nombre");
		while (rs.next()) {
			System.out.println(rs.getString("nombre") );
		}
		
		/*
		 * 

		-- 10.- Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.

		SELECT nombre FROM productos WHERE nombre LIKE '%monitor%' AND precio<215;		
		 */
		
		pst = conn.prepareStatement("SELECT nombre FROM productos WHERE nombre LIKE '%monitor%' AND precio<215;	");

		rs = pst.executeQuery();
		System.out.println("-- 10.- Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.");
		while (rs.next()) {
			System.out.println(rs.getString("nombre") );
		}
		
		
		
		//  cerrar recursos que ya se han usado
		rs.close();
		pst.close();
		conn.close();
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		Connection con = null;

		// comprobar que tengamos el .jar de MySQL
		Class.forName("com.mysql.jdbc.Driver");

		// establecer conexion
		con = DriverManager.getConnection(URL, USUARIO, PASS);

		return con;
	};

}
