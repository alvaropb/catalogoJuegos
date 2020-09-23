package com.catalogoJuegos.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.ConnectionManager;

/**
 * Servlet implementation class MigracionBackofficeController
 */
@WebServlet("/views/backoffice/migracion")
public class MigracionBackofficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MigracionBackofficeController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fichero = "/home/java/personas";
		int numLineasLeidas = 0;
		int numInsercionesCorrectas = 0;
		int numLineasErroneas = 0;
		long tiempoInicio = System.currentTimeMillis();
		long tiempo = 0;
		final String sql = "INSERT INTO usuarios (nombre,pass,id_rol) VALUES (?,'e10adc3949ba59abbe56e057f20f883e',1);";
		ArrayList<String>errores=new ArrayList<String>();
		// abrir conexion

		try (Connection conn = ConnectionManager.getConnection()) {
			conn.setAutoCommit(false);

			String[] lineaArray;
			String fila=null;
			File archivo = new File("/home/javaee/personas.txt");
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			// saltamos la primera linea
			br.readLine();

			
			
				while ((fila = br.readLine()) != null) {
					lineaArray=fila.split(";");
					try {
						// si una linea no tiene 6 campos, la linea es erronea
						if (lineaArray.length != 6) {
							// linea incorrecta
							
							throw new Exception("num de campos incorrecto");
						} else {
							// linea correcta
							//Kermit;Eu Ultrices Sit Institute;Feb 19, 2021;07833 704365;urna.suscipit@Nullamvelitdui.ca;16800511 2696
							String fecha=parsearFecha(lineaArray);
							PreparedStatement pst = conn.prepareStatement(sql);
							pst.setString(1, lineaArray[0]);
							int affectedRows = pst.executeUpdate();
							numInsercionesCorrectas++;
						}

					} catch (Exception e) {
						LOG.error(e);
						numLineasErroneas++;
						errores.add(fila);
					}
				} // fin while

		

			conn.commit();

		} catch (Exception e) {
			LOG.error(e);

		} finally {

			request.setAttribute("fichero", fichero);
			request.setAttribute("numLineasLeidas", numLineasLeidas);
			request.setAttribute("numInsercionesCorrectas", numInsercionesCorrectas);
			request.setAttribute("numLineasErroneas", numLineasErroneas);
			tiempo = System.currentTimeMillis();
			request.setAttribute("tiempoEjecucion", (tiempo - tiempoInicio));
			request.setAttribute("errores", errores);
			request.getRequestDispatcher("migracion.jsp").forward(request, response);
			
		}

	}

	private String parsearFecha(String[] lineaArray) {
		String fechaR[];
		
		int mes;		
		int dia;
		int agno;
		//sacar la fecha de la posicion
		//Feb 19, 2021
		fechaR=lineaArray[2].split(" ");
		//formato de la fecha en la bbdd 2020-07-13 
		
		
		return "";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
