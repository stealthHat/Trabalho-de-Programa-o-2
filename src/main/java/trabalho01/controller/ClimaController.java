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
import trabalho01.exceptions.DuplicatedException;
import trabalho01.exceptions.NullDataExeption;
import trabalho01.exceptions.OrdenDataException;
import trabalho01.model.ClimaDoDia;

public class ClimaController {

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
	public ArrayList<ClimaDoDia> leBinarasdio(String path) {
		
		return null;
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
	public void separaMes(ArrayList<ClimaDoDia> clima) {
		ArrayList<ClimaDoDia> climaMes = new ArrayList<ClimaDoDia>();
		Date mesAtual = clima.get(0).getData();

		for (ClimaDoDia climaDoDia : clima) {
			if (climaDoDia.getData().getMonth() == mesAtual.getMonth()) {
				climaMes.add(climaDoDia);
			} else {
				criaArquivos(climaMes);
				mesAtual = climaDoDia.getData();
				climaMes.clear();
			}
		}
	}

	private void criaArquivos(ArrayList<ClimaDoDia> clima) {
		AplicationDate aplicationDate = new AplicationDate();
		File outFile = new File(
				String.valueOf(clima.get(0).getData().getYear() + clima.get(0).getData().getMonth()) + ".dat");

	}
}