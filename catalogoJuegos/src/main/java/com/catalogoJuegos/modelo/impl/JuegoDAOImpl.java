package com.catalogoJuegos.modelo.impl;

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

public class JuegoDAOImpl implements JuegoDAO {
	private static final Logger LOG = Logger.getLogger(JuegoDAOImpl.class);
	private final static String GET_ALL = "SELECT " + "j.nombre as 'titulo' ," + "j.id as 'id_juego',"
			+ "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria'," + "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria WHERE fecha_validado IS NOT NULL "
			+ "" + "ORDER BY j.id DESC LIMIT 500;";
	private final static String GET_ALL_BY_USUARIO_VALIDADO = "SELECT " + "j.nombre as 'titulo' ,"
			+ "j.id as 'id_juego'," + "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria',"
			+ "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria WHERE fecha_validado IS NOT NULL "
			+ "" + "AND id_usuario=?  ORDER BY j.id DESC LIMIT 500;";
	private final static String GET_ALL_BY_USUARIO_NO_VALIDADO = "SELECT " + "j.nombre as 'titulo' ,"
			+ "j.id as 'id_juego'," + "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria',"
			+ "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria WHERE fecha_validado IS NULL "
			+ "" + "AND id_usuario=?  ORDER BY j.id DESC LIMIT 500;";

	private final static String GET_ULTIMOS = "SELECT " + "j.nombre as 'titulo' ," + "j.id as 'id_juego',"
			+ "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria'," + "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria WHERE fecha_validado IS NOT NULL "
			+ "" + "ORDER BY j.id DESC LIMIT ?;";

	private static final String INSERT = "INSERT INTO juegos (nombre,precio,id_categoria,imagen,id_usuario) VALUES(?,?,?,?,?)";

	private static final String GET_BY_NAME = "SELECT nombre,id, precio FROM juegos WHERE nombre=? LIMIT 500";
	
	private static final String GET_BY_ID = "SELECT " + "j.nombre as 'titulo' ," + "j.id as 'id_juego',"
			+ "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria'," + "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria " + "WHERE j.id= ?;";
	private static final String GET_BY_ID_USUARIO = "SELECT " + "j.nombre as 'titulo' ," + "j.id as 'id_juego',"
			+ "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria'," + "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria " + "WHERE j.id= ? AND j.id_usuario =?;";
	private static final String GET_BY_ID_CATEGORIA = "SELECT " + "j.nombre as 'titulo' ," + "j.id as 'id_juego',"
			+ "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria'," + "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria "
			+ "WHERE c.id_categoria =? AND fecha_validado IS NOT NULL " + "ORDER BY j.id DESC LIMIT ?;";

	private static final String UPDATE = "UPDATE juegos SET nombre=?,precio=?, id_categoria=?, imagen=?,id_usuario=? WHERE id=?";

	private static final String DELETE = "DELETE FROM juegos WHERE id=?";
	private static final String DELETE_BY_USER = "DELETE FROM juegos WHERE id=? AND id_usuario=?";

	private static final String VIEW_RESUMEN_ID = "SELECT id_usuario, total,validados,pendientes\n"
			+ "FROM v_usuarios_juegos\n" + "WHERE id_usuario=?";

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

	@Override
	public ArrayList<Juego> getAll() throws Exception {
		ArrayList<Juego> juegos = new ArrayList<Juego>();

		try (Connection conn = ConnectionManager.getConnection(); // crear la conexion con la BBDD
				PreparedStatement pst = conn.prepareStatement(GET_ALL); // preparar el statement
				ResultSet rs = pst.executeQuery();) {// recoger el resultado en un result set
			while (rs.next()) {
				juegos.add(mapper(rs));// mapear el resultado en arrayList
			}

		} catch (Exception e) {
			LOG.error(e);
			throw new Exception("Error de lectura en la bbdd");
		}

		return juegos;
	}		// preguntar si rs.next()

	@Override
	public ArrayList<Juego> getAll(int num) throws Exception {
		ArrayList<Juego> juegos = new ArrayList<Juego>();

		try (Connection conn = ConnectionManager.getConnection(); // crear la conexion con la BBDD
				PreparedStatement pst = conn.prepareStatement(GET_ULTIMOS); // preparar el statement
		) {
			pst.setInt(1, num);
			try (ResultSet rs = pst.executeQuery();) {
				// recoger el resultado en un result set
				while (rs.next()) {
					juegos.add(mapper(rs));// mapear el resultado en arrayList
				}

			} catch (Exception e) {
				LOG.error(e);

			}

		} catch (Exception e) {
			e.printStackTrace();		// preguntar si rs.next()
			throw new Exception("Error de lectura en la bbdd");
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
			pst.execute();

			try (ResultSet rs = pst.getGeneratedKeys();) {
				if (rs.next()) {
					int key = rs.getInt(1);
					juegoR = juego;
					juegoR.setId(key);
				}

			} catch (Exception e) {
				LOG.error(e);
				throw e;
			}

		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}

		return juegoR;
	}

	@Override
	public Juego getByName(Juego t) throws Exception {
		Juego juegoR = new Juego();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_BY_NAME);) {
			pst.setString(1, t.getNombre());

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					juegoR = mapper(rs);

				}

			} catch (Exception e) {
				LOG.error(e);
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return juegoR;
	}

	@Override
	public Juego getById(Juego t) throws Exception {
		Juego juegoR = new Juego();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_BY_ID)) {

			pst.setInt(1, t.getId());
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					juegoR = mapper(rs);
				}

			} catch (Exception e) {
				LOG.error(e);
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return juegoR;
	}

	@Override
	public Juego update(Juego t) throws Exception {
		Juego juegoR = new Juego();
		
		//TODO antes de modificar comprobar el ROL del usuario
		// si es ADMIN hacer la update que tenemos abajo
		// si es USER comprobar que le pertenezca ??
		
		
		// throw new SeguridadException( SeguridadException.MENSAJE_1 );
		// throw new SeguridadException();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(UPDATE);) {
			pst.setString(1, t.getNombre());		// preguntar si rs.next()
			pst.setBigDecimal(2, t.getPrecio());
			pst.setInt(3, t.getCategoria().getId());
			pst.setString(4, t.getImagen());
			pst.setInt(5, t.getUsuario().getId());
			pst.setInt(6, t.getId());
			
			juegoR=getById(t.getId(), t.getUsuario().getId());
			if ((t.getUsuario().getRol().getId()==Rol.ADMINISTRADOR)||juegoR.getId()!=0) {
				
				pst.execute();
			}else {
				throw new SeguridadException();
			}

			juegoR = getById(t);

		} catch (Exception e) {
			LOG.error(e);
			throw e;
			// throw new Exception("Ocurrio un error a la hora de actualizar");
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
			pst.execute();

		} catch (Exception e) {
			LOG.error(e);

		}

		return juegoR;
	}

	private Juego mapper(ResultSet rs) throws SQLException {
		Juego juego = new Juego(rs.getString("titulo"));
		juego.setId(rs.getInt("id_juego"));
		juego.setPrecio(rs.getBigDecimal("precio"));
		juego.setImagen(rs.getString("imagen"));
		Categoria c = new Categoria(rs.getString("nombre_categoria"));
		c.setId(rs.getInt("id_categoria"));
		juego.setCategoria(c);

		return juego;
	}

	@Override		// preguntar si rs.next()
	public ArrayList<Juego> getByCategoria(int id, int limite) throws Exception {
		ArrayList<Juego> juegos = new ArrayList<Juego>();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_BY_ID_CATEGORIA)) {
			pst.setInt(1, id);
			pst.setInt(2, limite);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					juegos.add(mapper(rs));
				}
			} catch (Exception e) {
				LOG.error(e);
			}
		} catch (Exception e) {
			LOG.error(e);
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

			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					juegos.add(mapper(rs));// mapear el resultado en arrayList
				}

			} catch (Exception e) {
				LOG.error(e);
				throw e;
			}

		} catch (Exception e) {
			LOG.error(e);
			throw new Exception("Error de lectura en la bbdd");
		}

		return juegos;
	}

	@Override
	public Resumen getResumenById(int id) throws Exception {
		Resumen resumen = new Resumen();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(VIEW_RESUMEN_ID)) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					mapperResumen(resumen, rs);
				}
					
			} catch (Exception e) {
				LOG.error(e);
				throw e;
			}
		} catch (Exception e) {
			LOG.error(e);
			throw e;
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
	public Juego delete(int idJuego, int idUsuario) throws Exception {

		Juego juegoR = new Juego();
		int affectedRows=0;
		juegoR.setId(idJuego);

		try (Connection conn = ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(DELETE_BY_USER)) {
			pst.setInt(1, idJuego);
			pst.setInt(2, idUsuario);
			juegoR = getById(juegoR);
			
			affectedRows=pst.executeUpdate();
			if (affectedRows==0) {
				throw new SeguridadException();
			}

		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}

		return juegoR;
	}

	@Override
	public Juego getById(int idJuego, int idUsuario) throws SeguridadException {
		Juego juegoR = new Juego();
		// preguntar si rs.next()
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_BY_ID_USUARIO)) {

			pst.setInt(1, idJuego);
			pst.setInt(2, idUsuario);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					juegoR = mapper(rs);
				}

			} catch (Exception e) {
				LOG.error(e);
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return juegoR;
	}

}
