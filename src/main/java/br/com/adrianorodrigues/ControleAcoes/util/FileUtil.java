package br.com.adrianorodrigues.ControleAcoes.util;

import java.io.*;

public class FileUtil {
    public static void DeleteFiles(String filePath){
        File index = new File(filePath);
        String[]entries = index.list();
        for(String s: entries){
            File currentFile = new File(index.getPath(),s);
            currentFile.delete();
        }
    }

    public static String readFile(String filePath) throws IOException {
        Class clazz = FileUtil.class;
        InputStream inputStream = new FileInputStream(filePath);
        String data = readFromInputStream(inputStream);
        return data;
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}