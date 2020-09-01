package br.com.adrianorodrigues.controleacoes.util;

import br.com.adrianorodrigues.controleacoes.interfaces.ICallback;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class FileUtil {

    public static final String PATH = System.getProperty("user.dir") + "/tmp";

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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + fileName))) {
            writer.write(text);
        } catch (IOException e) {
            Logger.getGlobal().throwing(this.getClass().getName(), "writeToFIle", e);
        }
    }

    public File getFileCreateIfNotExists(String path) {
        File file = new File(PATH + path);
        getFolderCreateIfNotExists(file.getPath().replace(file.getName(), ""));
        return file;
    }

    public void deleteFiles(String filePath) {
        File index = new File(PATH + filePath);
        String[] entries = index.list();
        if (entries != null && entries.length > 0)
            for (String s : entries) {
                File currentFile = new File(index.getPath(), s);
                try {
                    Files.delete(currentFile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public String readFile(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(PATH + filePath);
        return readFromInputStream(inputStream);
    }

    public String readFile(String filePath, ICallback callback) throws IOException {
        InputStream inputStream = new FileInputStream(PATH + filePath);
        return readFromInputStream(inputStream, callback);
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
