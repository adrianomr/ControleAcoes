package br.com.adrianorodrigues.ControleAcoes.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFromStringUtil {
    public static Date getDate(String data) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        return sdf1.parse(data);
    }
}
