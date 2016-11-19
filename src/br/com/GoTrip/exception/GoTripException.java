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
		super ("Não foi possívle executar sua requisição, contatar administrador do sistema.", t);
	}

}
