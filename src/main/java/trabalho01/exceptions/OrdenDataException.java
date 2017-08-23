package trabalho01.exceptions;

public class OrdenDataException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -6089427444442698025L;

    public OrdenDataException() {
        super("Dia dd/mm/yyyy foi encontrado antes de dd/mm/yyyy.");
    }
}
