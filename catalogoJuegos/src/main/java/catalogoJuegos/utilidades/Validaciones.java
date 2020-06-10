package catalogoJuegos.utilidades;

public class Validaciones {

	/**
	 * Método que comprueba que la longitud de una cadena esta entre 2 valores
	 * 
	 * @param cadena cadena a comprobar
	 * @param min    tamaño minimo
	 * @param max    tamaño maximo
	 * @return boolean true si cadena esta entre min y max(ambos incluidos)
	 */
	public static boolean longitud(String cadena, int min, int max) {
		
		boolean correcto = false;

		if (cadena != null && cadena.length() >= min && cadena.length() <= 100) {
			correcto = true;
		}

		return correcto;
	}
}
