package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFromString {
    public static Date getDate(String data) throws ParseException {
        SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); //voc� pode usar outras m�scaras
        return sdf1.parse(data);
    }
}
