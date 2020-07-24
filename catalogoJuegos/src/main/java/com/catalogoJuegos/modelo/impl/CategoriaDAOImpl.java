package com.catalogoJuegos.modelo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.ConnectionManager;
import com.catalogoJuegos.modelo.dao.CategoriaDAO;
import com.catalogoJuegos.modelo.pojo.Categoria;
import com.catalogoJuegos.modelo.pojo.Juego;
import com.catalogoJuegos.modelo.pojo.Usuario;

public class CategoriaDAOImpl implements CategoriaDAO {

	private static final Logger LOG = Logger.getLogger(CategoriaDAOImpl.class);

	private final static String GET_ALL = "SELECT id_categoria, nombre FROM categorias ORDER BY nombre DESC LIMIT 500;";
	private final static String GET_BY_ID = "SELECT id_categoria, nombre FROM categorias WHERE id_categoria=? ";
	private final static String GET_ALL_WITH_PRODUCTS = "SELECT j.nombre as 'titulo',j.id as 'id_juego'," + 
			"			precio,j.imagen,c.id_categoria,c.nombre,u.nombre,u.id " + 
			"			FROM juegos j INNER JOIN categorias c ON j.id_categoria= c.id_categoria\n" + 
			"			INNER JOIN usuarios u ON u.id =j.id_usuario ; ";

	private final static String INSERT = "INSERT INTO categorias (nombre) VALUES (?);";
	private final static String UPDATE = "UPDATE categorias SET nombre=? WHERE id_categoria=? ;";

	private static final String DELETE = "DELETE FROM categorias WHERE id_categoria=? ;";

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
				LOG.trace(pst);
				while (rs.next()) {
					categorias.add(mapper(rs));
				}

			}

		} 

		return categorias;
	}

	@Override
	public ArrayList<Categoria> getAllWithProducts() throws Exception {

		ArrayList<Categoria> categoriasReturn = new ArrayList<Categoria>();

		HashMap<Integer, Categoria> hashMapCategorias = new HashMap<Integer, Categoria>();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(GET_ALL_WITH_PRODUCTS);) {
			
			LOG.trace(pst);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {

					Categoria c = new Categoria();
					c.setId(rs.getInt("c.id_categoria"));
					c.setNombre(rs.getString("c.nombre"));

					Juego j = new Juego();
					j.setCategoria(c);
					j.setId(rs.getInt("id_juego"));
					j.setImagen(rs.getString("imagen"));
					j.setNombre(rs.getString("titulo"));
					j.setPrecio(rs.getBigDecimal("precio"));
					
					//usuario
					//u.nombre,u.id
					Usuario u=new Usuario();
					u.setId(rs.getInt("u.id"));
					u.setNombre(rs.getString("u.nombre"));
					j.setUsuario(u);
					
					

					if (hashMapCategorias.get(c.getId()) != null) {
						// categoria ya esta en el hashmap, añadir juego al array de categoria
						hashMapCategorias.get(c.getId()).getJuegos().add(j);

					} else {
						// la categoria no está , añadir al hashmap
						c.getJuegos().add(j);
						hashMapCategorias.put(c.getId(), c);
					}

				} // while (rs.next())

				categoriasReturn = new ArrayList<Categoria>(hashMapCategorias.values());

			} 

		}

		return categoriasReturn;
	}

	@Override
	public Categoria insert(Categoria t) throws Exception {
		Categoria categoria = new Categoria();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, t.getNombre());
			LOG.trace(pst);

			pst.executeUpdate();

			try (ResultSet rs = pst.getGeneratedKeys()) {
				if (rs.next()) {

					categoria.setId(rs.getInt(1));
					categoria = getById(categoria);
				}
			} 
			

		} 

		return categoria;
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
			LOG.trace(pst);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					cReturn = mapper(rs);
				}

			} 
		} 

		return cReturn;
	}

	@Override
	public Categoria update(Categoria t) throws Exception {
		Categoria cReturn = new Categoria();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(UPDATE);) {
			pst.setString(1, t.getNombre());
			pst.setInt(2, t.getId());
			LOG.trace(pst);
			pst.executeUpdate();
			cReturn = getById(t);

		} 

		return cReturn;
	}

	@Override
	public Categoria delete(Categoria t) throws Exception {
		Categoria cReturn = new Categoria();
		cReturn = getById(t);

		try (Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(DELETE)){
			pst.setInt(1, t.getId());
			LOG.trace(pst);
			pst.executeUpdate();
			
			
		} 

		return cReturn;
	}

	private Categoria mapper(ResultSet rs) throws SQLException {
		Categoria c = new Categoria(rs.getString("nombre"));
		c.setId(rs.getInt("id_categoria"));

		return c;
	}

}
