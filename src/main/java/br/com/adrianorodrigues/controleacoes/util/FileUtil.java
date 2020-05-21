package br.com.adrianorodrigues.controleacoes.util;

import br.com.adrianorodrigues.controleacoes.interfaces.ICallback;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtil {

    public final String PATH = System.getProperty("user.dir") + "/tmp";

    public void createFolderIfNotExists(File folder) {
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public File getFolderCreateIfNotExists(String path) {
        File file = new File(PATH + path);
        createFolderIfNotExists(file);
        return file;
    }

    public void writeToFIle(String fileName, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + fileName));
        writer.write(text);
        writer.close();
    }

    public File getFileCreateIfNotExists(String path) {
        File file = new File(PATH + path);
        getFolderCreateIfNotExists(file.getPath().replace(file.getName(), ""));
        return file;
    }

    public void DeleteFiles(String filePath) {
        File index = new File(PATH + filePath);
        String[] entries = index.list();
        for (String s : entries) {
            File currentFile = new File(index.getPath(), s);
            currentFile.delete();
        }
    }

    public String readFile(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(PATH + filePath);
        String data = readFromInputStream(inputStream);
        return data;
    }

    public String readFile(String filePath, ICallback callback) throws IOException {
        InputStream inputStream = new FileInputStream(PATH + filePath);
        String data = readFromInputStream(inputStream, callback);
        return data;
    }

    private String readFromInputStream(InputStream inputStream, ICallback callback)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                callback.callback(line);
            }
        }
        return resultStringBuilder.toString();
    }

    private String readFromInputStream(InputStream inputStream)
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

    public List<String> listFilesForFolder(final String folderName) {
        File folder = getFolderCreateIfNotExists(folderName);
        return listFilesForFolder(folder);
    }

    private List<String> listFilesForFolder(final File folder) {
        List<String> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                files.add(fileEntry.getName());
            }
        }
        return files;
    }
}
