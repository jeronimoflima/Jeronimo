package br.com.GoTrip.exception;

public class GoTripException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public GoTripException(String msg) {
		super (msg);
	}
	
	public GoTripException(String msg, Throwable t) {
		super (msg, t);
	}
	
	public GoTripException(Throwable t) {
		super ("N�o foi poss�vle executar sua requisi��o, contatar administrador do sistema.", t);
	}

}
