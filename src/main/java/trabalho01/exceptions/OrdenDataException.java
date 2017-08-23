package trabalho01.exceptions;

import trabalho01.model.ClimaDoDia;

public class OrdenDataException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -6089427444442698025L;

	public OrdenDataException(ClimaDoDia setClima) {
		super(setClima.getData().toString() + "esta data esta fora de ordem");
	}
}
