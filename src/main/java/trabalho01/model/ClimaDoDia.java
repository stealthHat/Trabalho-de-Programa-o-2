package trabalho01.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import trabalho01.commons.AplicationDate;

import trabalho01.commons.AplicationModel;

public class ClimaDoDia extends AplicationModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Date data;
    String ventoDirecao;
    int ventoVelocidade;
    int indicePluviometrico;
    double temperatura;

    public ClimaDoDia() {
    }

    public ClimaDoDia(Date data, String ventoDirecao, int ventoVelocidade, int indicePluciometrico, double temperatura) {
        this.data = data;
        this.ventoDirecao = ventoDirecao;
        this.ventoVelocidade = ventoVelocidade;
        this.indicePluviometrico = indicePluciometrico;
        this.temperatura = temperatura;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getVentoDirecao() {
        return ventoDirecao;
    }

    public void setVentoDirecao(String ventoDirecao) {
        this.ventoDirecao = ventoDirecao;
    }

    public int getVentoVelocidade() {
        return ventoVelocidade;
    }

    public void setVentoVelocidade(int ventoVelocidade) {
        this.ventoVelocidade = ventoVelocidade;
    }

    public int getIndicePluviometrico() {
        return indicePluviometrico;
    }

    public void setIndicePluviometrico(int indicePluviometrico) {
        this.indicePluviometrico = indicePluviometrico;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
    
    @Override
    public String toString(){
        AplicationDate aplicationDate = new AplicationDate();
        String dataString = aplicationDate.formataData(data, "dd/mm/yyyy");
        
        String conteudo = dataString + "-" + ventoDirecao + "-" + 
                ventoVelocidade + "-" + indicePluviometrico + "-" + temperatura;
        return conteudo;
    }
}
