package trabalho01.exceptions;

import trabalho01.model.ClimaDoDia;

public class DuplicatedException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 6548610719235298413L;

	public DuplicatedException(ClimaDoDia setClima) {
		super(setClima.getData().toString() + "esta data esta duplicada");
	}
}
