package trabalho01.view;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import trabalho01.controller.ClimaController;
import trabalho01.model.ClimaDoDia;

public class main {

    public static void main(String[] args) {
        ClimaController a = new ClimaController();
        try {
            ArrayList<ClimaDoDia> clima = a.leBinario("C:\\Users\\Alequis\\Dropbox\\Furb\\Ci�ncia da Computa��o\\3� Semestre\\Programa��o II\\Trabalhos\\Trabalho 1\\DadosMeteorologicos-Exemplo.dat");
            a.separaMes(clima);

        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
