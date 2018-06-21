package exception;

public class CadastroNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CadastroNaoEncontradoException() {
		super();		
	}

	public CadastroNaoEncontradoException(String message) {
		super(message);		
	}

}
