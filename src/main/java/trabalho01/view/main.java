package trabalho01.view;

import java.io.IOException;
import java.text.ParseException;

import trabalho01.controller.ClimaController;

public class main {
<<<<<<< HEAD

    public static void main(String[] args) {
        ClimaController a = new ClimaController();
        try {
            ArrayList<ClimaDoDia> climaDoDia = a.leBinario("C:\\Users\\Alequis\\Dropbox\\Furb\\Ciência da Computação\\3º Semestre\\Programação II\\Trabalhos\\Trabalho 1\\DadosMeteorologicos-Exemplo.dat");
            a.chacaDataa(climaDoDia);
            a.separaMes(climaDoDia);
            System.out.println(a.geraRelatorio(climaDoDia));
        } catch (DuplicatedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullDataExeption e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OrdenDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
=======
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
>>>>>>> origin/master
