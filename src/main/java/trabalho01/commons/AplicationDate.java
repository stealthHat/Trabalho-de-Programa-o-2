package trabalho01.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class AplicationDate {
	public Date formataData(String data) throws ParseException {
		if (StringUtils.isBlank(data))
			return null;

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.parse(data);
	}

	public Calendar toCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
}