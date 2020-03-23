package br.com.adrianorodrigues.ControleAcoes.util;

import java.io.File;

public class FileUtil {
    public static void DeleteFiles(String filePath){
        File index = new File(filePath);
        String[]entries = index.list();
        for(String s: entries){
            File currentFile = new File(index.getPath(),s);
            currentFile.delete();
        }
    }
}
