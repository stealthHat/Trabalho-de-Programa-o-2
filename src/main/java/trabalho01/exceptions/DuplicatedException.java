package trabalho01.exceptions;

public class DuplicatedException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 6548610719235298413L;

    public DuplicatedException() {
        super("Dia dd/mm/yyyy est� repetido.");
    }
}
