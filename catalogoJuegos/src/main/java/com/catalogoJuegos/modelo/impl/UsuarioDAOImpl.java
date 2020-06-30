package com.catalogoJuegos.modelo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.ConnectionManager;
import com.catalogoJuegos.modelo.dao.UsuarioDAO;
import com.catalogoJuegos.modelo.pojo.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	private static final Logger LOG=Logger.getLogger(UsuarioDAOImpl.class);
	
	private final static String USUARIO_EXISTE = "SELECT nombre, id ,pass,imagen FROM usuarios WHERE nombre=? AND pass=?";
	
	private static UsuarioDAOImpl INSTANCE = null;

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAOImpl();
		}
	}

	public static UsuarioDAOImpl getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private UsuarioDAOImpl() {
		super();

	}
	
	

	@Override
	public ArrayList<Usuario> getAll() throws Exception {
		// Sin implementar
		return null;
	}

	@Override
	public Usuario insert(Usuario t) throws Exception {
		// Sin implementar
		return null;
	}

	@Override
	public Usuario getByName(Usuario t) throws Exception {
		// Sin implementar
		return null;
	}

	@Override
	public Usuario getById(Usuario t) throws Exception {
		// Sin implementar
		return null;
	}

	@Override
	public Usuario update(Usuario t) throws Exception {
		// Sin implementar
		return null;
	}

	@Override
	public Usuario delete(Usuario t) throws Exception {
		// Sin implementar
		return null;
	}

	@Override
	public Usuario usuarioExiste(Usuario usuario) throws Exception {
		Usuario usuarioR = new Usuario();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(USUARIO_EXISTE)) {
			

			pst.setString(1, usuario.getNombre());
			pst.setString(2, usuario.getPass());

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					usuarioR = mapper(rs);
				}
			} catch (Exception e) {
				LOG.error(e);
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return usuarioR;
	}

	private Usuario mapper(ResultSet rs) throws SQLException {
		Usuario u = new Usuario();

		u.setId(rs.getInt("id"));
		u.setImagen(rs.getString("imagen"));
		u.setNombre(rs.getString("nombre"));
		u.setPass(rs.getString("pass"));

		return u;
	}

}
