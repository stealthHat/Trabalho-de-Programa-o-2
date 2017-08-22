package trabalho01.view;

import java.text.ParseException;
import java.util.Date;

import trabalho01.commons.AplicationDate;

public class main {
	public static void main(String[] args) {

		Date a = null;
		try {
			a = new AplicationDate().formataData("20/01/2222");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a);
	}
}
