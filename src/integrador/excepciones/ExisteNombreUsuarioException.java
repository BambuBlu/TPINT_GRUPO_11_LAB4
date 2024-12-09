package integrador.excepciones;

public class ExisteNombreUsuarioException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExisteNombreUsuarioException()
	{
		
	}

	@Override
	public String getMessage() {

		return "Ya existe el nombre de usuario que intenta registrar";
	}
	
	
		
	
}
