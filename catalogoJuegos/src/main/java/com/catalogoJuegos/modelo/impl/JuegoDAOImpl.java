package com.catalogoJuegos.modelo.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.ConnectionManager;
import com.catalogoJuegos.modelo.dao.JuegoDAO;
import com.catalogoJuegos.modelo.dao.SeguridadException;
import com.catalogoJuegos.modelo.pojo.Categoria;
import com.catalogoJuegos.modelo.pojo.Juego;
import com.catalogoJuegos.modelo.pojo.Resumen;
import com.catalogoJuegos.modelo.pojo.Rol;
import com.catalogoJuegos.modelo.pojo.Usuario;

/**
 * Implementacion para realizar CRUD de los juegos
 * @author javaee
 *
 */
public class JuegoDAOImpl implements JuegoDAO {
	private static final Logger LOG = Logger.getLogger(JuegoDAOImpl.class);
	private final static String camposJuegos=" j.nombre as 'titulo' ,j.id as 'id_juego',j.precio as 'precio',j.fecha_validado as 'is_validado',c.id_categoria,c.nombre as 'nombre_categoria',j.imagen,u.nombre,u.id "; 
	private final static String GET_ALL = "SELECT" +camposJuegos+
								"FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria"
								+ "	INNER JOIN usuarios u ON j.id_usuario =u.id "
								+ " WHERE fecha_validado IS NOT NULL "
								+ "ORDER BY j.id DESC LIMIT 500;";
	private final static String GET_ALL_BY_USUARIO_VALIDADO = "SELECT " +camposJuegos
								+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria "
								+ "	INNER JOIN usuarios u ON j.id_usuario =u.id "
								+ "WHERE fecha_validado IS NOT NULL "
								+ "" + "AND id_usuario=?  ORDER BY j.id DESC LIMIT 500;";
	private final static String GET_ALL_BY_USUARIO_NO_VALIDADO = "SELECT " + camposJuegos
								+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria"
								+ "	INNER JOIN usuarios u ON j.id_usuario =u.id "
								+ " WHERE fecha_validado IS NULL "
								+ "" + "AND id_usuario=?  ORDER BY j.id DESC LIMIT 500;";

	private final static String GET_ULTIMOS = "SELECT"+camposJuegos
											+ "  FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria"
											+ "	 INNER JOIN usuarios u ON j.id_usuario =u.id "
											+ "	 WHERE fecha_validado IS NOT NULL ORDER BY j.id DESC LIMIT ?;";

	private static final String INSERT = "INSERT INTO juegos (nombre,precio,id_categoria,imagen,id_usuario) VALUES(?,?,?,?,?)";

	private static final String GET_BY_NAME = "SELECT nombre,id, precio FROM juegos WHERE nombre=? LIMIT 500";

	private static final String GET_BY_ID = "SELECT "+camposJuegos
								+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria "
								+ "	 INNER JOIN usuarios u ON j.id_usuario =u.id "
								+ "WHERE j.id= ?;";
	private static final String GET_BY_ID_USUARIO = "SELECT " + camposJuegos
								+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria "
								+ "	 INNER JOIN usuarios u ON j.id_usuario =u.id "
								+ "WHERE j.id= ? AND j.id_usuario =?;";
	private static final String GET_BY_ID_CATEGORIA = "SELECT " + camposJuegos
								+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria "
								+ "	 INNER JOIN usuarios u ON j.id_usuario =u.id "
								+ "WHERE c.id_categoria =? AND fecha_validado IS NOT NULL " + "ORDER BY j.id DESC LIMIT ?;";

	private static final String UPDATE = "UPDATE juegos SET nombre=?,precio=?, id_categoria=?, imagen=?,id_usuario=?,fecha_validado=NULL WHERE id=?";

	private static final String DELETE = "DELETE FROM juegos WHERE id=?";
	private static final String DELETE_BY_USER = "DELETE FROM juegos WHERE id=? AND id_usuario=?";

	private static final String VIEW_RESUMEN_ID = "SELECT id_usuario, total,validados,pendientes\n"
			+ "FROM v_usuarios_juegos\n" + "WHERE id_usuario=?";
	private static final String GET_ALL_TODOS = "SELECT" +camposJuegos+
								"FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria"
								+ "	INNER JOIN usuarios u ON j.id_usuario =u.id "
								+ "ORDER BY j.id DESC LIMIT 500;";
	private static final String VALIDATE="UPDATE juegos " + 
									" SET fecha_validado =SYSDATE() " + 
									" WHERE id  IN ";
	
	private static final String GET_NUM_JUEGOS="{CALL pa_juego_num_total(?) }";
	private static final String GET_NUM_JUEGOS_PENDIENTES="{CALL pa_juego_num_totalPendiente(?) }";
	private static final String GET_NUM_USUARIOS = "{CALL pa_usuario_num_total(?)}";
	private static JuegoDAOImpl INSTANCE = null;

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JuegoDAOImpl();
		}
	}

	public static JuegoDAOImpl getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private JuegoDAOImpl() {
		super();

	}
	/**
	 * Método que retornal el numero de usuarios dados de alta 
	 * @return int numero de usuarios dados de alta
	 * @throws Exception
	 */
	public int getNumUsuarios()throws Exception {
		int total=0;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs=conn.prepareCall(GET_NUM_USUARIOS)){
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.executeUpdate();
			total=cs.getInt(1);

			
		}
		
		
		return total;
	}

/**
 * Método que retorna al numero total de juegos
 * @return int  el numero total de juegos
 * @throws Exception
 */
	public int getNumTotales()throws Exception {
		int total=0;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs=conn.prepareCall(GET_NUM_JUEGOS)){
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.executeUpdate();
			total=cs.getInt(1);

			
		}
		
		
		return total;
	};
/**
 * Método que retorna el numero de juegos pendientes de validar
 * @return int numero de juegos pdtes de validar
 * @throws Exception
 */
	public int getNumTotalesPendientes()throws Exception {
		int total=0;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs=conn.prepareCall(GET_NUM_JUEGOS_PENDIENTES)){
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.executeUpdate();
			total=cs.getInt(1);

			
		}
		
		
		return total;
	}
	@Override
	public ArrayList<Juego> getAll() throws Exception {
		ArrayList<Juego> juegos = new ArrayList<Juego>();

		try (Connection conn = ConnectionManager.getConnection(); // crear la conexion con la BBDD
				PreparedStatement pst = conn.prepareStatement(GET_ALL); // preparar el statement
				
				ResultSet rs = pst.executeQuery();) {// recoger el resultado en un result set
			LOG.trace(pst);
			while (rs.next()) {
				juegos.add(mapper(rs));// mapear el resultado en arrayList
			}

		} 

		return juegos;
	}
	@Override
	public ArrayList<Juego> getAll(boolean todos) throws Exception {
		ArrayList<Juego> juegos = new ArrayList<Juego>();

		try (Connection conn = ConnectionManager.getConnection(); // crear la conexion con la BBDD
				PreparedStatement pst = conn.prepareStatement(GET_ALL_TODOS); // preparar el statement
				
				ResultSet rs = pst.executeQuery();) {// recoger el resultado en un result set
			LOG.trace(pst);
			while (rs.next()) {
				juegos.add(mapper(rs));// mapear el resultado en arrayList
			}

		} 

		return juegos;
	} 

	@Override
	public ArrayList<Juego> getAll(int num) throws Exception {
		ArrayList<Juego> juegos = new ArrayList<Juego>();

		try (Connection conn = ConnectionManager.getConnection(); // crear la conexion con la BBDD
				PreparedStatement pst = conn.prepareStatement(GET_ULTIMOS); // preparar el statement
		) {
			pst.setInt(1, num);
			LOG.trace(pst);
			try (ResultSet rs = pst.executeQuery();) {
				// recoger el resultado en un result set
				while (rs.next()) {
					juegos.add(mapper(rs));// mapear el resultado en arrayList
					//TODO añadir al juego el usuario 
				}

			} 

		} 

		return juegos;
	}

	@Override
	public Juego insert(Juego juego) throws Exception {
		Juego juegoR = new Juego();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);) {

			// setear el nombre recogido de la vista

			pst.setString(1, juego.getNombre());
			pst.setBigDecimal(2, juego.getPrecio());
			pst.setInt(3, juego.getCategoria().getId());
			pst.setString(4, juego.getImagen());
			// id_usuario
			pst.setInt(5, juego.getUsuario().getId());
			LOG.trace(pst);
			pst.execute();

			try (ResultSet rs = pst.getGeneratedKeys();) {
				if (rs.next()) {
					int key = rs.getInt(1);
					juegoR = juego;
					juegoR.setId(key);
				}

			}

		} 

		return juegoR;
	}

	@Override
	public Juego getByName(Juego t) throws Exception {
		Juego juegoR = new Juego();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_BY_NAME);) {
			pst.setString(1, t.getNombre());
			LOG.trace(pst);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					juegoR = mapper(rs);

				}

			} 

		}

		return juegoR;
	}

	@Override
	public Juego getById(Juego t) throws Exception {
		Juego juegoR = new Juego();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_BY_ID)) {

			pst.setInt(1, t.getId());
			LOG.trace(pst);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					juegoR = mapper(rs);
				}

			} 

		} 

		return juegoR;
	}

	@Override
	public Juego update(Juego t) throws Exception,SeguridadException {
		return null;
	}
	@Override
	public Juego update(Juego t, Usuario u) throws Exception,SeguridadException {
		Juego juegoR = new Juego();

		// antes de modificar comprobar el ROL del usuario
		// si es ADMIN hacer la update que tenemos abajo


		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(UPDATE);) {//UPDATE juegos SET nombre=?,precio=?, id_categoria=?, imagen=?,id_usuario=?,fecha_validado=NULL WHERE id=?
			//recuperar el id del usuario del juego
			int idUsuario=getById(t).getUsuario().getId();
			
			pst.setString(1, t.getNombre());
			pst.setBigDecimal(2, t.getPrecio());
			pst.setInt(3, t.getCategoria().getId());
			pst.setString(4, t.getImagen());
			pst.setInt(5, idUsuario);
			pst.setInt(6, t.getId());
			LOG.trace(pst);

			if ((u.getRol().getId() == Rol.ADMINISTRADOR) || u.getId()==idUsuario) {

				pst.execute();
			} else {
				throw new SeguridadException();
			}

			juegoR = getById(t);

		} 

		return juegoR;
	}

	@Override
	public Juego delete(Juego t) throws Exception {

		Juego juegoR = new Juego();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(DELETE)) {
			pst.setInt(1, t.getId());
			juegoR = getById(t);
			LOG.trace(pst);
			pst.execute();

		}

		return juegoR;
	}

	private Juego mapper(ResultSet rs) throws SQLException {
		Juego juego = new Juego(rs.getString("titulo"));
		juego.setId(rs.getInt("id_juego"));
		juego.setPrecio(rs.getBigDecimal("precio"));
		juego.setImagen(rs.getString("imagen"));
		juego.setFechaValidacion(rs.getDate("is_validado"));
		
		Categoria c = new Categoria(rs.getString("nombre_categoria"));
		c.setId(rs.getInt("id_categoria"));
		juego.setCategoria(c);
		
		Usuario u=new Usuario();
		u.setId(rs.getInt("u.id"));
		u.setNombre(rs.getString("u.nombre"));
		juego.setUsuario(u);

		return juego;
	}

	@Override // preguntar si rs.next()
	public ArrayList<Juego> getByCategoria(int id, int limite) throws Exception {
		ArrayList<Juego> juegos = new ArrayList<Juego>();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_BY_ID_CATEGORIA)) {
			pst.setInt(1, id);
			pst.setInt(2, limite);
			LOG.trace(pst);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					juegos.add(mapper(rs));
				}
			} 
		} 

		return juegos;
	}

	@Override
	public ArrayList<Juego> getAllByUser(int id, boolean isValidado) throws Exception {
		ArrayList<Juego> juegos = new ArrayList<Juego>();
		String SQL = "";
		if (isValidado) {
			SQL = GET_ALL_BY_USUARIO_VALIDADO;
		} else {
			SQL = GET_ALL_BY_USUARIO_NO_VALIDADO;
		}

		try (Connection conn = ConnectionManager.getConnection(); // crear la conexion con la BBDD
				PreparedStatement pst = conn.prepareStatement(SQL); // preparar el statement
		) {// recoger el resultado en un result set
			pst.setInt(1, id);
			LOG.trace(pst);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					juegos.add(mapper(rs));// mapear el resultado en arrayList
				}

			} 

		}

		return juegos;
	}

	@Override
	public Resumen getResumenById(int id) throws Exception {
		Resumen resumen = new Resumen();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(VIEW_RESUMEN_ID)) {
			pst.setInt(1, id);
			LOG.trace(pst);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					mapperResumen(resumen, rs);
				}

			} 
		}

		return resumen;
	}

	private void mapperResumen(Resumen resumen, ResultSet rs) throws SQLException {
		resumen.setIdUsuario(rs.getInt("id_usuario"));
		resumen.setJuegosTotales(rs.getInt("total"));
		resumen.setJuegosValidados(rs.getInt("validados"));
		resumen.setJuegosPendientes(rs.getInt("pendientes"));
	}

	@Override
	public Juego delete(int idJuego, int idUsuario) throws Exception,SeguridadException {

		Juego juegoR = new Juego();
		int affectedRows = 0;
		juegoR.setId(idJuego);

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(DELETE_BY_USER)) {
			pst.setInt(1, idJuego);
			pst.setInt(2, idUsuario);
			juegoR = getById(juegoR);
			LOG.trace(pst);
			affectedRows = pst.executeUpdate();
			if (affectedRows == 0) {
				throw new SeguridadException();
			}

		}

		return juegoR;
	}

	@Override
	public Juego getById(int idJuego, int idUsuario) throws Exception, SeguridadException {
		Juego juegoR = new Juego();
		// preguntar si rs.next()
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_BY_ID_USUARIO)) {

			pst.setInt(1, idJuego);
			pst.setInt(2, idUsuario);
			LOG.trace(pst);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					juegoR = mapper(rs);

				}else {
					throw new SeguridadException();
				}

			} 

		} 

		return juegoR;
	}

	@Override
	public void validate(ArrayList<Integer> juegosAValidar) throws Exception {
		String cadena="";//(?);
		if (juegosAValidar.size()>0) {
			cadena="(?";
			for (int i = 1; i < juegosAValidar.size(); i++) {
				cadena+=",?";
			}
			cadena+=");";
		}else {
			throw new Exception("No se selecciono ningun juego para validar");
		}
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(VALIDATE+cadena);) {

			LOG.trace(pst);

			for (int i = 0; i <  juegosAValidar.size(); i++) {
				pst.setInt(i+1, juegosAValidar.get(i));
			}
					
			LOG.trace(pst);
			pst.executeUpdate();
			


		} 
		
	}



	
}
