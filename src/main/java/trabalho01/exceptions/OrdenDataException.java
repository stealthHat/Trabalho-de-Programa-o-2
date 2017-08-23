package trabalho01.exceptions;

import javax.swing.JOptionPane;

public class OrdenDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6089427444442698025L;

	public OrdenDataException() {
		JOptionPane.showMessageDialog(null, "A ordem das datas estao erradas");
	}
}
