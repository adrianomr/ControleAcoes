package br.com.adrianorodrigues.controleacoes.util;

import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.interfaces.ICallback;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ExcelUtil {

    private ExcelUtil() {
    }

    public static Sheet getSheet(MultipartFile file) throws IOException {
        return getWorkbook(file).getSheetAt(0);
    }

    public static Workbook getWorkbook(MultipartFile file) throws IOException {
        String originalName = file.getOriginalFilename();
        if (originalName == null) {
            throw new ResourceNotFoundException("Problema ao ler arquivo");
        }
        Workbook workbook;
        String lowerCaseFileName = originalName.toLowerCase();
        if (lowerCaseFileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        return workbook;
    }

    public static void processRows(Sheet sheet, ICallback<Row> callback, int skipLines) {
        int i = 0;
        for (Row row : sheet) {
            if (i >= skipLines)
                callback.callback(row);
            i++;
        }
    }
}
