package trabalho01.controller;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
                System.out.println("O arquivo acabou");
                break;
            }
        }

        dis.close();
        return listaClima;
    }
    
    public ArrayList<String> separaMes(ArrayList<ClimaDoDia> clima) throws IOException {
        AplicationDate aplicationDate = new AplicationDate();
        ArrayList<ClimaDoDia> climaMes = new ArrayList<>();
        ArrayList<String> meses = new ArrayList<>();
        String mesAtual = aplicationDate.formataData(clima.get(0).getData(), "mm");
        int size = clima.size();

        for (ClimaDoDia climaDoDia : clima) {
            if (mesAtual.equals(aplicationDate.formataData(climaDoDia.getData(), "mm"))) {
                climaMes.add(climaDoDia);
            } else {
                criaArquivos(climaMes);
                meses.add(aplicationDate.formataData(climaMes.get(0).getData(), "yyyy-mm"));
                mesAtual = aplicationDate.formataData(climaDoDia.getData(), "mm");
                climaMes.clear();
                climaMes.add(climaDoDia);
                if (clima.get(size - 1).equals(climaDoDia)){
                    criaArquivos(climaMes);
                    meses.add(aplicationDate.formataData(climaMes.get(0).getData(), "yyyy-mm"));
                }
            }
        }
        return meses;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<ClimaDoDia> carregarListaDoMes(String arquivo) throws IOException, ClassNotFoundException {
        Path path = Paths.get(arquivo + ".dat");
        ObjectInputStream file = new ObjectInputStream(new FileInputStream(path.toFile()));
        ArrayList<ClimaDoDia> climas = (ArrayList<ClimaDoDia>) file.readObject();
        return climas;
    }

    private void criaArquivos(ArrayList<ClimaDoDia> clima) throws FileNotFoundException, IOException {
        
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

        return "Mês: " + aplicationDate.formataData(climaDoMes.get(0).getData(), "mm/yyyy")
                + "\n Quantidade de dias considerados: " + climaDoMes.size() + "\n Acumulado de chuva: " + acumuloChuva
                + " mm \n Velocidade média do vento: " + decimal.format(velocidadeMedia)
                + " km/h \n Maior velocidade do vento: " + maiorVelocidade.getVentoVelocidade() + "km/h em "
                + aplicationDate.formataData(maiorVelocidade.getData(), "dd/mm/yyyy") + " na direção "
                + maiorVelocidade.getVentoDirecao() + "\n Menor velocidade do vento: "
                + menorVelocidade.getVentoVelocidade() + "km/h em "
                + aplicationDate.formataData(menorVelocidade.getData(), "dd/mm/yyyy") + " na direção "
                + menorVelocidade.getVentoDirecao() + "\n Temperatura média: " + decimal.format(temperaturaMedia)
                + " ºC" + "\n Maior temperatura: " + decimal.format(maiorTemperatura.getTemperatura()) + "ºC em "
                + aplicationDate.formataData(maiorTemperatura.getData(), "dd/mm/yyyy") + "\n Menor temperatura: "
                + decimal.format(menorTemperatura.getTemperatura()) + "ºC em "
                + aplicationDate.formataData(menorTemperatura.getData(), "dd/mm/yyyy");
    }

    public void checaData(ArrayList<ClimaDoDia> clima) throws DuplicatedException, OrdenDataException {

        Set<Date> set = new HashSet<Date>();
        Date date = clima.get(0).getData();
        for (ClimaDoDia setClima : clima) {

            if (set.contains(setClima.getData()))
                throw new DuplicatedException(setClima);

            //if (setClima.getData().before(date))
            //    throw new OrdenDataException(setClima, date);

            set.add(setClima.getData());
            date = setClima.getData();
        }
    }

    public void escreveEmArquivo(String lines) throws IOException {
        FileWriter fw = new FileWriter("relatorio.txt");
        fw.write(lines);
        fw.close();
    }
}
