package catalogoJuegos.modelo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import catalogoJuegos.modelo.ConnectionManager;
import catalogoJuegos.modelo.dao.JuegoDAO;
import catalogoJuegos.modelo.pojo.Categoria;
import catalogoJuegos.modelo.pojo.Juego;

public class JuegoDAOImpl implements JuegoDAO {
	private final static String GET_ALL = "SELECT " + "j.nombre as 'titulo' ," + "j.id as 'id_juego',"
			+ "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria'," + "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria " + ""
			+ "ORDER BY j.id DESC LIMIT 500;";

	private final static String GET_ULTIMOS = "SELECT " + "j.nombre as 'titulo' ," + "j.id as 'id_juego',"
			+ "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria'," + "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria " + ""
			+ "ORDER BY j.id DESC LIMIT ?;";

	private static final String INSERT = "INSERT INTO juegos (nombre,precio,id_categoria,imagen) VALUES(?,?,?,?)";

	private static final String GET_BY_NAME = "SELECT nombre,id, precio FROM juegos WHERE nombre=? LIMIT 500";

	private static final String GET_BY_ID = "SELECT " + "j.nombre as 'titulo' ," + "j.id as 'id_juego',"
			+ "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria'," + "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria " + "WHERE j.id= ?;";
	private static final String GET_BY_ID_CATEGORIA = "SELECT " + "j.nombre as 'titulo' ," + "j.id as 'id_juego',"
			+ "j.precio as 'precio'," + "c.id_categoria," + "c.nombre as 'nombre_categoria'," + "j.imagen "
			+ "FROM juegos j INNER JOIN categorias c ON j.id_categoria =c.id_categoria " + "WHERE c.id_categoria =? "
			+ "ORDER BY j.id DESC LIMIT ?;";

	private static final String UPDATE = "UPDATE juegos SET nombre=?,precio=?, id_categoria=?, imagen=? WHERE id=?";

	private static final String DELETE = "DELETE FROM juegos WHERE id=?";

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
			e.printStackTrace();
			throw new Exception("Error de lectura en la bbdd");
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
			try (ResultSet rs = pst.executeQuery();) {
				// recoger el resultado en un result set
				while (rs.next()) {
					juegos.add(mapper(rs));// mapear el resultado en arrayList
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

		} catch (Exception e) {
			e.printStackTrace();
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
			pst.execute();

			try (ResultSet rs = pst.getGeneratedKeys();) {
				if (rs.next()) {
					int key = rs.getInt(1);
					juegoR = juego;
					juegoR.setId(key);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		} catch (Exception e) {
			e.printStackTrace();
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
				e.printStackTrace();
				throw e;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
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
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return juegoR;
	}

	@Override
	public Juego update(Juego t) throws Exception {
		Juego juegoR = new Juego();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(UPDATE);) {
			pst.setString(1, t.getNombre());
			pst.setBigDecimal(2, t.getPrecio());
			pst.setInt(3, t.getCategoria().getId());
			pst.setString(4, t.getImagen());
			pst.setInt(5, t.getId());

			pst.execute();

			juegoR = getById(t);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ocurrio un error a la hora de actualizar");
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
			e.printStackTrace();
			throw e;
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
	@Override
	public ArrayList<Juego> getByCategoria(int id, int limite) throws Exception {
		ArrayList<Juego> juegos = new ArrayList<Juego>();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_BY_ID_CATEGORIA)) {
			pst.setInt(1, id);
			pst.setInt(2, limite);
			try (ResultSet rs=pst.executeQuery()){
				while (rs.next()) {
					juegos.add(mapper(rs));
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return juegos;
	}

}
