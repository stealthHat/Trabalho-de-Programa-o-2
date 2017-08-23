package trabalho01.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import trabalho01.controller.ClimaController;
import trabalho01.exceptions.DuplicatedException;
import trabalho01.exceptions.NullDataExeption;
import trabalho01.exceptions.OrdenDataException;
import trabalho01.model.ClimaDoDia;

public class main {

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
