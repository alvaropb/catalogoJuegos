package catalogoJuegos.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class JuegoDAOImpl implements JuegoDAO {
	private final static String GET_ALL = "SELECT id, nombre FROM juegos";
	
	private  static JuegoDAOImpl INSTANCE=null;
	
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

		try (Connection conn = ConnectionManager.getConnection();// crear la conexion con la BBDD
				PreparedStatement pst = conn.prepareStatement(GET_ALL);// preparar el statement
				ResultSet rs = pst.executeQuery();) {// recoger el resultado en un result set
			while (rs.next()) {
				juegos.add(mapper(rs));//mapear el resultado en arrayList
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error de lectura en la bbdd");
		}

		return juegos;
	}

	private Juego mapper(ResultSet rs) throws SQLException {
		Juego juego = new Juego(rs.getString("nombre"));
		juego.setId(rs.getInt("id"));

		return juego;
	}

}
