package trabalho01.exceptions;

import java.util.Date;
import trabalho01.commons.AplicationDate;
import trabalho01.model.ClimaDoDia;

public class OrdenDataException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -6089427444442698025L;
    
    public OrdenDataException(ClimaDoDia climaAtual, Date climaproximo) {
        super("Dia " + new AplicationDate().formataData(climaAtual.getData(), "dd/mm/yyyy") + " foi encontrado antes de " + new AplicationDate().formataData(climaproximo, "dd/mm/yyyy"));
    }
}
