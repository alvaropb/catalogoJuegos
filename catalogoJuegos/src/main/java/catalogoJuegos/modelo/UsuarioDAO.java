package catalogoJuegos.modelo;

public interface UsuarioDAO extends ICrudable<Usuario>{

	/**
	 * Metodo que comprueba que un usuario existe y su contraseña es correcta
	 * @param usuario
	 * @return el usuario buscado
	 * @throws Exception
	 */
	public Usuario usuarioExiste(Usuario usuario)throws Exception;
}
