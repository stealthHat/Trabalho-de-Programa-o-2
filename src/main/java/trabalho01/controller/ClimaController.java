package trabalho01.controller;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
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

        for (ClimaDoDia setClima : clima) {
            if (setClima.getData() == null) {
                throw new NullDataExeption();
            }

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

    @SuppressWarnings("deprecation")
    public void separaMes(ArrayList<ClimaDoDia> clima) throws IOException {
        ArrayList<ClimaDoDia> climaMes = new ArrayList<>();
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

    @SuppressWarnings("deprecation")
    public void criaArquivos(ArrayList<ClimaDoDia> clima) throws FileNotFoundException, IOException {
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm");
        String data = df.format(clima.get(0).getData());

        Path path = Paths.get(data + ".dat");

        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(path.toFile()));
        file.writeObject(clima);
        file.close();
    }
}
