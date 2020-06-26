package catalogoJuegos.modelo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import catalogoJuegos.modelo.ConnectionManager;
import catalogoJuegos.modelo.dao.CategoriaDAO;
import catalogoJuegos.modelo.pojo.Categoria;
import catalogoJuegos.modelo.pojo.Juego;

public class CategoriaDAOImpl implements CategoriaDAO {

	private final static String GET_ALL = "SELECT id_categoria, nombre FROM categorias ORDER BY nombre DESC LIMIT 500;";
	private final static String GET_BY_ID = "SELECT id_categoria, nombre FROM categorias WHERE id_categoria=? ";
	private final static String GET_ALL_WITH_PRODUCTS = "SELECT j.nombre as 'titulo'," + 
									"		j.id as 'id_juego'," + 
									"		precio," + 
									"		imagen," + 
									"		c.id_categoria," + 
									"		c.nombre " + 
			"FROM juegos j INNER JOIN categorias c ON j.id_categoria= c.id_categoria; ";

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
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_ALL);) {
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					categorias.add(mapper(rs));
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return categorias;
	}
	
	@Override
	public ArrayList<Categoria> getAllWithProducts() throws Exception {
		
		ArrayList<Categoria> categoriasReturn=new ArrayList<Categoria>();
		
		HashMap<Integer, Categoria>hashMapCategorias=new HashMap<Integer, Categoria>();
		
		try (Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(GET_ALL_WITH_PRODUCTS);){
			try (ResultSet rs=pst.executeQuery();){
				
				// j.nombre as 'titulo', j.id as 'id_juego', precio, imagen, c.id_categoria, c.nombre
				while (rs.next()) {
					
					Categoria c=new Categoria();
					c.setId(rs.getInt("c.id_categoria"));
					c.setNombre(rs.getString("c.nombre"));
					
					Juego j=new Juego();
					j.setCategoria(c);
					j.setId(rs.getInt("id_juego"));
					j.setImagen(rs.getString("imagen"));
					j.setNombre(rs.getString("titulo"));
					j.setPrecio(rs.getBigDecimal("precio"));
					
									
					if (hashMapCategorias.get(c.getId())!=null   ) {
						// categoria ya esta en el hashmap, añadir juego al array de categoria
						hashMapCategorias.get(c.getId()).getJuegos().add(j);
						
					}else {
						// la categoria no está , añadir al hashmap 
						c.getJuegos().add(j);
						hashMapCategorias.put(c.getId(), c);
					}
					
				}// while (rs.next()) 
				
				categoriasReturn=new ArrayList<Categoria>(hashMapCategorias.values()) ;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return categoriasReturn;
	}
	

	@Override
	public Categoria insert(Categoria t) throws Exception {
		// Sin implementar
		return null;
	}

	@Override
	public Categoria getByName(Categoria t) throws Exception {
		// Sin implementar
		return null;
	}

	@Override
	public Categoria getById(Categoria t) throws Exception {
		Categoria cReturn = new Categoria();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_BY_ID)) {
				pst.setInt(1, t.getId());
			try (ResultSet rs=pst.executeQuery()){
				if (rs.next()) {
					cReturn=mapper(rs);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return cReturn;
	}

	@Override
	public Categoria update(Categoria t) throws Exception {
		// Sin implementar
		return null;
	}

	@Override
	public Categoria delete(Categoria t) throws Exception {
		// 	sin implementar		
		return null;
	}

	private Categoria mapper(ResultSet rs) throws SQLException {
		Categoria c = new Categoria(rs.getString("nombre"));
		c.setId(rs.getInt("id_categoria"));

		return c;
	}


}
