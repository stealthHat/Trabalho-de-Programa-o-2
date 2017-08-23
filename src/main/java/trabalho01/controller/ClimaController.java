package trabalho01.controller;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
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

        for (ClimaDoDia c : listaClima) {
            System.out.println(c);
        }

        dis.close();
        return listaClima;
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

            if (c.getVentoVelocidade() < menorVelocidade.getVentoVelocidade())
                menorVelocidade = c;

            if (c.getTemperatura() > maiorTemperatura.getTemperatura())
                maiorTemperatura = c;

            if (c.getTemperatura() < menorTemperatura.getTemperatura())
                menorTemperatura = c;
        }

        double velocidadeMedia = velocidade / climaDoMes.size();
        double temperaturaMedia = temperatura / climaDoMes.size();

        AplicationDate aplicationDate = new AplicationDate();

        DecimalFormat decimal = new DecimalFormat("0.0");

        return "M�s: " + aplicationDate.formataData(climaDoMes.get(0).getData(), "mm/yyyy")
                + "\n Quantidade de dias considerados: " + climaDoMes.size() + "\n Acumulado de chuva: "
                + acumuloChuva + " mm \n Velocidade m�dia do vento: " + decimal.format(velocidadeMedia)
                + " km/h \n Maior velocidade do vento: " + maiorVelocidade.getVentoVelocidade() + "km/h em "
                + aplicationDate.formataData(maiorVelocidade.getData(), "dd/mm/yyyy") + " na dire��o "
                + maiorVelocidade.getVentoDirecao() + "\n Menor velocidade do vento: "
                + menorVelocidade.getVentoVelocidade() + "km/h em "
                + aplicationDate.formataData(menorVelocidade.getData(), "dd/mm/yyyy") + " na dire��o "
                + menorVelocidade.getVentoDirecao() + "\n Temperatura m�dia: " + decimal.format(temperaturaMedia) + " �C"
                + "\n Maior temperatura: " + decimal.format(maiorTemperatura.getTemperatura()) + "�C em "
                + aplicationDate.formataData(maiorTemperatura.getData(), "dd/mm/yyyy") + "\n Menor temperatura: "
                + decimal.format(menorTemperatura.getTemperatura()) + "�C em "
                + aplicationDate.formataData(menorTemperatura.getData(), "dd/mm/yyyy");
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
    
    public ArrayList<ClimaDoDia> carregarListaDoMes(String arquivo) throws IOException, ClassNotFoundException{
        Path path = Paths.get(arquivo + ".dat");
        ObjectInputStream file = new ObjectInputStream(new FileInputStream(path.toFile()));
        ArrayList<ClimaDoDia> climas = (ArrayList<ClimaDoDia>)file.readObject();
        return climas;
    }
}
