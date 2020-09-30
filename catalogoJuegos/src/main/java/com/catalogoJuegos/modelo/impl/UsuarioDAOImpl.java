package com.catalogoJuegos.modelo.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.ConnectionManager;
import com.catalogoJuegos.modelo.dao.UsuarioDAO;
import com.catalogoJuegos.modelo.pojo.Usuario;

/**
 * Implementacion para realizar un CRUD de usuarios
 * @author javaee
 *
 */
public class UsuarioDAOImpl implements UsuarioDAO {
	private static final Logger LOG=Logger.getLogger(UsuarioDAOImpl.class);
	
	private final static String USUARIO_EXISTE = "SELECT u.nombre, u.id ,u.pass,u.imagen, r.id_rol,r.nombre_rol FROM usuarios u INNER JOIN roles r ON u.id_rol=r.id_rol  WHERE nombre=? AND pass=?";
	
	private static final String SQL_PA_GET_USUARIOS="{CALL pa_usuario_getAll()}";
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
		ArrayList<Usuario>usuarios=new ArrayList<Usuario>();
		
		try(Connection conn=ConnectionManager.getConnection();
				CallableStatement cs=conn.prepareCall(SQL_PA_GET_USUARIOS);
						ResultSet rs=cs.executeQuery();) {
				while (rs.next()) {
					
					usuarios.add(mapper(rs));
				}
			
		}
		
		
		
		return usuarios;
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
			LOG.trace(pst);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					usuarioR = mapper(rs);
				}
			}

		} 

		return usuarioR;
	}

	private Usuario mapper(ResultSet rs) throws SQLException {
		Usuario u = new Usuario();

		u.setId(rs.getInt("id"));
		u.setImagen(rs.getString("imagen"));
		u.setNombre(rs.getString("nombre"));
		u.setPass(rs.getString("pass"));
		u.getRol().setId(rs.getInt("id_rol"));
		u.getRol().setNombre(rs.getString("nombre_rol"));

		return u;
	}

}
