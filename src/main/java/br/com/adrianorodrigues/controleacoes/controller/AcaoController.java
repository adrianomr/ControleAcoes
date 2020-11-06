package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.service.acao.AcaoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("acao")
public class AcaoController {
    @Autowired
    AcaoService acaoService;

    @GetMapping()
    public Page<Acao> getAcoes(Pageable pageable, @RequestParam("papel") String papel) {
        return acaoService.findAcaoListByPapelContaining(pageable, papel);
    }

    @PostMapping("/excel")
    public void uploadFile(MultipartFile file) throws IOException, InvalidFormatException {
        String lowerCaseFileName = file.getOriginalFilename().toLowerCase();
        Workbook workbook;
        if (lowerCaseFileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING: data.get(i).add(cell.getStringCellValue()); break;
                    case NUMERIC: data.get(i).add(""+cell.getNumericCellValue()); break;
                    case BOOLEAN: data.get(i).add(""+cell.getBooleanCellValue()); break;
                    case FORMULA: data.get(i).add(""+cell.getCellFormula()); break;
                    default: data.get(new Integer(i)).add(" ");
                }
            }
            i++;
        }
        log.info(data.toString());
    }
}
