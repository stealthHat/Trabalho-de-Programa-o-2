package trabalho01.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ClimaController {

	int byteRead;
	private InputStream inputStream;
	private OutputStream outputStream;

	public void lerAquivo(String path1, String Path2) throws IOException {
		inputStream = new FileInputStream(path1);
		outputStream = new FileOutputStream(Path2);

		while ((byteRead = inputStream.read()) != -1) {
			outputStream.write(byteRead);
		}
	}

	public static void main(String[] args) {
		ClimaController a = new ClimaController();
		try {
			a.lerAquivo("//home//bmo//DadosMeteorologicos-Exemplo2.dat",
					"//home//bmo//DadosMeteorologicos-Exemplo22.txt");
		} catch (IOException e) {
			// TODO Auto-geFSFnerated catch block
			e.printStackTrace();
		}
	}
}
