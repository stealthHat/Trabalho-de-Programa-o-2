package trabalho01.view;

import java.io.IOException;
import java.text.ParseException;

import trabalho01.controller.ClimaController;
import trabalho01.exceptions.DuplicatedException;
import trabalho01.exceptions.NullDataExeption;
import trabalho01.exceptions.OrdenDataException;

public class main {
	public static void main(String[] args) {
		ClimaController a = new ClimaController();
		try {
			a.chacaDataa(a.leBinario("/home/bmo/templates/Trabalho 1/DadosMeteorologicos-Exemplo.dat"));
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullDataExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrdenDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
