package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFromString {
    public static Date getDate(String data) throws ParseException {
        SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); //você pode usar outras máscaras
        return sdf1.parse(data);
    }
}
