package catalogoJuegos.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAOImpl implements CategoriaDAO {

	private final static String GET_ALL = "SELECT id_categoria, nombre FROM categorias ORDER BY nombre DESC LIMIT 500;";

	private static CategoriaDAOImpl INSTANCE = null;

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CategoriaDAOImpl();
		}
	}

	public static CategoriaDAOImpl getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private CategoriaDAOImpl() {
		super();

	}

	@Override
	public ArrayList<Categoria> getAll() throws Exception {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		try (Connection conn=ConnectionManager.getConnection();
			PreparedStatement pst=conn.prepareStatement(GET_ALL);				
				){
			try (ResultSet rs=pst.executeQuery();){
				
				while (rs.next()) {
					categorias.add(mapper(rs));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return categorias;
	}

	@Override
	public Categoria insert(Categoria t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria getByName(Categoria t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria getById(Categoria t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria update(Categoria t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria delete(Categoria t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private Categoria mapper(ResultSet rs) throws SQLException {
		Categoria c = new Categoria(rs.getString("nombre"));
		c.setId(rs.getInt("id_categoria"));

		return c;
	}

}
