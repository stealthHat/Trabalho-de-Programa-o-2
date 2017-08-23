package trabalho01.view;

import java.io.IOException;
import java.text.ParseException;

import trabalho01.controller.ClimaController;

public class main {
	public static void main(String[] args) {
		ClimaController a = new ClimaController();
		try {
			a.separaMes(a.leBinario("/home/bmo/templates/Trabalho 1/DadosMeteorologicos-Exemplo2.dat"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
