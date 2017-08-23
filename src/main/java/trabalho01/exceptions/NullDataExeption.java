package trabalho01.exceptions;

import javax.swing.JOptionPane;

public class NullDataExeption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullDataExeption() {
		JOptionPane.showMessageDialog(null, "existem campo de data vazios");
	}
}