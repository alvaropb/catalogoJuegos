package catalogoJuegos.modelo;

public interface UsuarioDAO extends ICrudable<Usuario>{

	
	public Usuario usuarioExiste(Usuario usuario)throws Exception;
}
