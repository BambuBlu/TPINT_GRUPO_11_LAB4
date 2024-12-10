package integrador.excepciones;

public class ExisteDNIUsuarioException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExisteDNIUsuarioException()
	{
		
	}

	@Override
	public String getMessage() {

		return "Ya existe el DNI para el usuario que intenta registrar";
	}
	
	
		
	
}
