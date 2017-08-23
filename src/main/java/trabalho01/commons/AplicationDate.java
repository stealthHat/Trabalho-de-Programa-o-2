package trabalho01.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class AplicationDate {

    public Date formataData(String data) throws ParseException {
        if (StringUtils.isBlank(data)) 
            return null;

        DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        return formatter.parse(data);
    }
    
    public String formataData(Date date, String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
}
