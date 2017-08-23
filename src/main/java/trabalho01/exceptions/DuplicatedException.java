package trabalho01.exceptions;

import javax.swing.JOptionPane;

public class DuplicatedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6548610719235298413L;

	public DuplicatedException() {
		JOptionPane.showMessageDialog(null, "Existem datas duplicadas");
	}
}