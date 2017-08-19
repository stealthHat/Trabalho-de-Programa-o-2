package trabalho01.controller;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabalho01.model.ClimaDoDia;

public class ClimaController {

    private ArrayList<ClimaDoDia> leBinario(String path) throws FileNotFoundException, IOException, ParseException{
        DataInputStream dis = new DataInputStream(new FileInputStream(path));
        
        dis.readByte();
        boolean continua = true;
        ArrayList<ClimaDoDia> listaClima = new ArrayList<ClimaDoDia>();
        
        while(continua){
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
                continua = false;
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
        
        for(ClimaDoDia c : listaClima){
            System.out.println(c);
        }
    }
}
