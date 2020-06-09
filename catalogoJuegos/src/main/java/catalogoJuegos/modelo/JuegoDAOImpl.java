package catalogoJuegos.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class JuegoDAOImpl implements JuegoDAO {
	private final static String GET_ALL = "SELECT id, nombre FROM juegos ORDER BY id DESC";

	private static final String INSERT = "INSERT INTO juegos (nombre) VALUES(?)";

	private static final String GET_BY_NAME = "SELECT nombre,id FROM juegos WHERE nombre=?";
	
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




	@Override
	public Juego insert(Juego juego) throws Exception {
		Juego juegoR=new Juego();
		
		try (Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);){

			// setear el nombre recogido de la vista
			
			pst.setString(1, juego.getNombre());
			
			pst.execute();
			
			
			try (ResultSet rs= pst.getGeneratedKeys();) {
				if (rs.next()) {
					int key=rs.getInt(1);
					juegoR=juego;
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
		Juego juegoR=new Juego();

		try (Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(GET_BY_NAME);
				){
			pst.setString(1, t.getNombre());
			
			
			try (ResultSet rs=pst.executeQuery()){
				if (rs.next()) {
					juegoR=mapper(rs);
					
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private Juego mapper(ResultSet rs) throws SQLException {
		Juego juego = new Juego(rs.getString("nombre"));
		juego.setId(rs.getInt("id"));

		return juego;
	}
	
	
}
