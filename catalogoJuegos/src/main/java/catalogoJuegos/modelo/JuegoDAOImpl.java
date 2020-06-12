package catalogoJuegos.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JuegoDAOImpl implements JuegoDAO {
	private final static String GET_ALL = "SELECT id, nombre,precio FROM juegos ORDER BY id DESC";

	private static final String INSERT = "INSERT INTO juegos (nombre,precio) VALUES(?,?)";

	private static final String GET_BY_NAME = "SELECT nombre,id, precio FROM juegos WHERE nombre=?";

	private static final String GET_BY_ID = "SELECT nombre,id, precio FROM juegos WHERE id=?";

	private static final String UPDATE = "UPDATE juegos SET nombre=?,precio=? WHERE id=?";

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
	public Juego insert(Juego juego) throws Exception {
		Juego juegoR = new Juego();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);) {

			// setear el nombre recogido de la vista

			pst.setString(1, juego.getNombre());
			pst.setBigDecimal(2, juego.getPrecio());
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
		try (Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(GET_BY_ID)){

				pst.setInt(1, t.getId());
			try (ResultSet rs=pst.executeQuery()){
				if (rs.next()) {
					juegoR=mapper(rs);
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
		Juego juegoR=new Juego();
		try (Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(UPDATE);
				){
			pst.setString(1, t.getNombre());
			pst.setBigDecimal(2, t.getPrecio());
			pst.setInt(3, t.getId());
			
			pst.execute();
			
			juegoR=getById(t);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ocurrio un error a la hora de actualizar");
		}
		
		return juegoR;
	}

	@Override
	public Juego delete(Juego t) throws Exception {
		
		Juego juegoR=new Juego();
		
		try (Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(DELETE)){
			pst.setInt(1, t.getId());
			juegoR=getById(t);
			pst.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return juegoR;
	}
	
	
	
	
	private Juego mapper(ResultSet rs) throws SQLException {
		Juego juego = new Juego(rs.getString("nombre"));
		juego.setId(rs.getInt("id"));
		juego.setPrecio(rs.getBigDecimal("precio"));

		return juego;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
