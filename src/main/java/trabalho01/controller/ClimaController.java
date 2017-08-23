package trabalho01.controller;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import trabalho01.commons.AplicationDate;
import trabalho01.exceptions.DuplicatedException;
import trabalho01.exceptions.NullDataExeption;
import trabalho01.exceptions.OrdenDataException;
import trabalho01.model.ClimaDoDia;

public class ClimaController {

<<<<<<< HEAD
    public ArrayList<ClimaDoDia> leBinario(String path) throws FileNotFoundException, IOException, ParseException {
        AplicationDate aplicationDate = new AplicationDate();
        DataInputStream dis = new DataInputStream(new FileInputStream(path));
        ArrayList<ClimaDoDia> listaClima = new ArrayList<ClimaDoDia>();

        while (true) {
            String data = "";
            String direcao = "";
            int velocidade;
            int indicePluviometrico;
            Double temperatura;

            try {
                data = dis.readUTF();

                direcao += dis.readChar();
                direcao += dis.readChar();

                velocidade = dis.readInt();
                indicePluviometrico = dis.readInt();
                temperatura = dis.readDouble();

                Date date = aplicationDate.formataData(data);

                ClimaDoDia clima = new ClimaDoDia(date, direcao, velocidade, indicePluviometrico, temperatura);
                listaClima.add(clima);
            } catch (EOFException eo) {
                eo.printStackTrace();
                break;
            }
        }

        for (ClimaDoDia c : listaClima) {
            System.out.println(c);
        }

        dis.close();
        return listaClima;
    }

    public void chacaDataa(ArrayList<ClimaDoDia> clima)
            throws DuplicatedException, NullDataExeption, OrdenDataException {

        Set<Date> set = new HashSet<Date>();
        Date date = clima.get(0).getData();

        for (ClimaDoDia setClima : clima) {
            if (setClima.getData() == null)
                throw new NullDataExeption();

            if (set.contains(setClima.getData()))
                throw new DuplicatedException();

            if (setClima.getData().before(date))
                throw new OrdenDataException();

            set.add(setClima.getData());
            date = setClima.getData();
        }
    }

    @SuppressWarnings("deprecation")
    public void separaMes(ArrayList<ClimaDoDia> clima) throws IOException {
        AplicationDate aplicationDate = new AplicationDate();
        ArrayList<ClimaDoDia> climaMes = new ArrayList<>();

        String mesAtual = aplicationDate.formataData(clima.get(0).getData(), "mm");
        System.out.println(mesAtual);

        for (ClimaDoDia climaDoDia : clima) {
            if (mesAtual.equals(aplicationDate.formataData(climaDoDia.getData(), "mm"))) {
                climaMes.add(climaDoDia);
            } else {
                criaArquivos(climaMes);
                mesAtual = aplicationDate.formataData(climaDoDia.getData(), "mm");
                climaMes.clear();
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void criaArquivos(ArrayList<ClimaDoDia> clima) throws FileNotFoundException, IOException {

        AplicationDate aplicationDate = new AplicationDate();
        String data = aplicationDate.formataData(clima.get(0).getData(), "yyyy-mm");

        Path path = Paths.get(data + ".dat");

        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(path.toFile()));
        file.writeObject(clima);
        file.close();
    }

    public String geraRelatorio(ArrayList<ClimaDoDia> climaDoMes) {
        int acumuloChuva = 0;
        int velocidade = 0;
        ClimaDoDia maiorVelocidade = new ClimaDoDia();
        ClimaDoDia menorVelocidade = climaDoMes.get(0);
        double temperatura = 0;
        ClimaDoDia maiorTemperatura = new ClimaDoDia();
        ClimaDoDia menorTemperatura = climaDoMes.get(0);
        
        for (ClimaDoDia c : climaDoMes) {
            acumuloChuva += c.getIndicePluviometrico();
            velocidade += c.getVentoVelocidade();
            temperatura += c.getTemperatura();
            
            if (c.getVentoVelocidade() > maiorVelocidade.getVentoVelocidade())
                maiorVelocidade = c;
            
            if(c.getVentoVelocidade() < menorVelocidade.getVentoVelocidade())
                menorVelocidade = c;
                
            if(c.getTemperatura() > maiorTemperatura.getTemperatura())
                maiorTemperatura = c;
            
            if(c.getTemperatura() < menorTemperatura.getTemperatura())
                menorTemperatura = c;
        }
        
        double velocidadeMedia = velocidade / climaDoMes.size();
        double temperaturaMedia = temperatura / climaDoMes.size();
        
        AplicationDate aplicationDate = new AplicationDate();
        String data = aplicationDate.formataData(climaDoMes.get(0).getData(), "mm/yyyy");
        
        return "Mês: " + data + 
                "\n Quantidade de dias considerados: " + climaDoMes.size() + 
                "\n Acumulado de chuva: " + acumuloChuva + 
                " mm \n Velocidade média do vento: " + velocidadeMedia + 
                " km/h \n Maior velocidade do vento: " + maiorVelocidade.getVentoVelocidade() + 
                "km/h em " + aplicationDate.formataData(maiorVelocidade.getData(), "dd/mm/yyyy") + 
                " na direção " + maiorVelocidade.getVentoDirecao() + 
                "\n Maior velocidade do vento: " + maiorVelocidade.getVentoVelocidade() + 
                "km/h em " + aplicationDate.formataData(menorVelocidade.getData(), "dd/mm/yyyy") + 
                " na direção " + menorVelocidade.getVentoDirecao() + 
                "\n Temperatura média: " + temperaturaMedia + " ºC" +
                "\n Maior temperatura: " + maiorTemperatura.getTemperatura() + 
                "ºC em " + aplicationDate.formataData(maiorTemperatura.getData(), "dd/mm/yyyy") + 
                "\n Menor temperatura: " + menorTemperatura.getTemperatura() + 
                "ºC em " + aplicationDate.formataData(menorTemperatura.getData(), "dd/mm/yyyy");
    }
=======
	public ArrayList<ClimaDoDia> leBinario(String path) throws FileNotFoundException, IOException, ParseException {
		AplicationDate aplicationDate = new AplicationDate();
		DataInputStream dis = new DataInputStream(new FileInputStream(path));
		ArrayList<ClimaDoDia> listaClima = new ArrayList<ClimaDoDia>();

		while (true) {
			String data = "";
			String direcao = "";
			int velocidade;
			int indicePluviometrico;
			Double temperatura;

			try {
				data = dis.readUTF();

				direcao += dis.readChar();
				direcao += dis.readChar();

				velocidade = dis.readInt();
				indicePluviometrico = dis.readInt();
				temperatura = dis.readDouble();

				Date date = aplicationDate.formataData(data);

				ClimaDoDia clima = new ClimaDoDia(date, direcao, velocidade, indicePluviometrico, temperatura);
				listaClima.add(clima);
			} catch (EOFException eo) {
				eo.printStackTrace();
				break;
			}
		}

		for (ClimaDoDia c : listaClima)
			System.out.println(c);

		dis.close();
		return listaClima;
	}

	public void chacaDataa(ArrayList<ClimaDoDia> clima)
			throws DuplicatedException, NullDataExeption, OrdenDataException {

		Set<Date> set = new HashSet<Date>();
		Date date = clima.get(0).getData();
		// tirar null e mostrar a data que deu erro e validar o dia tbm
		for (ClimaDoDia setClima : clima) {

			if (set.contains(setClima.getData())) {
				throw new DuplicatedException();
			}

			if (setClima.getData().before(date)) {
				throw new OrdenDataException();
			}

			set.add(setClima.getData());
			date = setClima.getData();
		}
	}

	public void separaMes(ArrayList<ClimaDoDia> clima) throws IOException {
		AplicationDate aplicationDate = new AplicationDate();
		ArrayList<ClimaDoDia> climaMes = new ArrayList<>();
		String mesAtual = aplicationDate.formataData(clima.get(0).getData(), "mm");
		int size = clima.size();

		for (ClimaDoDia climaDoDia : clima) {
			if (mesAtual.equals(aplicationDate.formataData(climaDoDia.getData(), "mm"))
					&& clima.get(size - 1).equals(climaDoDia)) {
				climaMes.add(climaDoDia);
			} else {
				climaMes.add(climaDoDia);
				criaArquivos(climaMes);
				mesAtual = aplicationDate.formataData(climaDoDia.getData(), "mm");
				climaMes.clear();
			}
		}
	}

	public void criaArquivos(ArrayList<ClimaDoDia> clima) throws FileNotFoundException, IOException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm");
		String data = df.format(clima.get(0).getData());

		Path path = Paths.get(data + ".dat");

		ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(path.toFile()));
		file.writeObject(clima);
		file.close();
	}
>>>>>>> origin/master
}
