package trabalho01.model;

import java.io.Serializable;
import java.util.Date;

import trabalho01.commons.AplicationModel;

public class Clima extends AplicationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Date data;
	String ventoDirecao;
	int ventoVelicidade;
	int indicePluciometrico;
	double temperatura;

	public Clima() {
	}

	public Clima(Date data, String ventoDirecao, int ventoVelocidade, int indicePluciometrico, double temperatura) {
		this.data = data;
		this.ventoDirecao = ventoDirecao;
		this.ventoVelicidade = ventoVelocidade;
		this.indicePluciometrico = indicePluciometrico;
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

	public int getVentoVelicidade() {
		return ventoVelicidade;
	}

	public void setVentoVelicidade(int ventoVelicidade) {
		this.ventoVelicidade = ventoVelicidade;
	}

	public int getIndicePluciometrico() {
		return indicePluciometrico;
	}

	public void setIndicePluciometrico(int indicePluciometrico) {
		this.indicePluciometrico = indicePluciometrico;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
}