package trabalho01.controller;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import trabalho01.commons.AplicationDate;
import trabalho01.model.ClimaDoDia;

public class ClimaController {

<<<<<<< HEAD
    private ArrayList<ClimaDoDia> leBinario(String path) throws FileNotFoundException, IOException, ParseException{
        DataInputStream dis = new DataInputStream(new FileInputStream(path));
        
        dis.readByte();
        ArrayList<ClimaDoDia> listaClima = new ArrayList<ClimaDoDia>();
        
        while(true){
            String data = "";
            String direcao = "";
            int velocidade;
            int indicePluviometrico;
            Double temperatura;
            
            try{
                for(int i = 0; i < 11; i++)
                    data += (char)dis.readByte();

                data = data.trim();
                
                for(int i = 0; i < 4; i++)
                    direcao += (char)dis.readByte();       
                
                velocidade = dis.readInt();
                indicePluviometrico = dis.readInt();
                temperatura = dis.readDouble();
                
                Date date = formataData(data);
                
                ClimaDoDia clima = new ClimaDoDia(date, direcao, velocidade, indicePluviometrico, temperatura);
                listaClima.add(clima);
                dis.readByte();
            }catch(EOFException eo){
                break;
            }
        }
        dis.close();
        return listaClima;
    }
    
    public static Date formataData(String data) throws ParseException { 
	if (data == null || data.equals(""))
            return null;
        
        Date date = null;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        date = formatter.parse(data);
        return date;
    }
    
    public static void main(String[] args){
        ClimaController climaController = new ClimaController();
        ArrayList<ClimaDoDia> listaClima = new ArrayList<ClimaDoDia>();
        try {
            listaClima = climaController.leBinario("C:\\Users\\Alequis\\Dropbox\\Furb\\Ciência da Computação\\3º Semestre\\Programação II\\Trabalhos\\Trabalho 1\\DadosMeteorologicos-Exemplo.dat");
        } catch (IOException ex) {
            Logger.getLogger(ClimaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ClimaController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        for(ClimaDoDia c : listaClima)
            System.out.println(c);
    }
=======
	public ArrayList<ClimaDoDia> leBinario(String path) throws FileNotFoundException, IOException, ParseException {
		AplicationDate aplicationDate = new AplicationDate();
		DataInputStream dis = new DataInputStream(new FileInputStream(path));

		dis.readByte();
		boolean continua = true;
		ArrayList<ClimaDoDia> listaClima = new ArrayList<ClimaDoDia>();

		while (continua) {
			String data = "";
			String direcao = "";
			int velocidade;
			int indicePluviometrico;
			Double temperatura;

			try {
				for (int i = 0; i < 11; i++)
					data += (char) dis.readByte();

				data = data.trim();

				for (int i = 0; i < 4; i++)
					direcao += (char) dis.readByte();

				velocidade = dis.readInt();
				indicePluviometrico = dis.readInt();
				temperatura = dis.readDouble();

				Date date = aplicationDate.formataData(data);

				ClimaDoDia clima = new ClimaDoDia(date, direcao, velocidade, indicePluviometrico, temperatura);
				listaClima.add(clima);
				dis.readByte();
			} catch (EOFException eo) {
				continua = false;
			}
		}
		dis.close();
		return listaClima;
	}

	public boolean checaLista(ArrayList<ClimaDoDia> clima) {
		Set<Date> set = new HashSet<Date>();

		for (ClimaDoDia setClima : clima) {
			if (set.contains(setClima.getData()))
				return true;

			set.add(setClima.getData());
		}
		return false;
	}

	public boolean checaListaa(ArrayList<ClimaDoDia> clima) {
		Date date = clima.get(0).getData();
		for (ClimaDoDia climaDoDia : clima) {
			if (climaDoDia.getData().before(date))
				return true;

			date = climaDoDia.getData();
		}
		return false;
	}

	public void criaArquivos(ClimaDoDia clima) {
		AplicationDate aplicationDate = new AplicationDate();
		Calendar calendar = aplicationDate.toCalendar(clima.getData());
		File outFile = new File(calendar.YEAR + calendar.MONTH + ".dat");

	}

>>>>>>> origin/master
}
