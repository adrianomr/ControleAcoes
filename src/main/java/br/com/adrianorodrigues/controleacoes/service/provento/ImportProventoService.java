package br.com.adrianorodrigues.controleacoes.service.provento;

import br.com.adrianorodrigues.controleacoes.dto.ProventoDTO;
import br.com.adrianorodrigues.controleacoes.interfaces.ICallback;
import br.com.adrianorodrigues.controleacoes.util.DateFromStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;

import java.time.LocalDate;

@Slf4j
public class ImportProventoService implements ICallback<Row> {

    private final ProventoService proventoService;

    public ImportProventoService(ProventoService proventoService) {
        this.proventoService = proventoService;
    }

    @Override
    public void callback(Row row) {
        String papel = row.getCell(7).getStringCellValue();
        Double valor = row.getCell(1).getNumericCellValue();
        LocalDate data = DateFromStringUtil.getLocalDate(row.getCell(6).getStringCellValue()).toLocalDate();
        LocalDate dataPosicao = DateFromStringUtil.getLocalDate(row.getCell(5).getStringCellValue()).toLocalDate();
        ProventoDTO proventoDTO = ProventoDTO
                .builder()
                .papel(papel)
                .data(data)
                .dataPosicao(dataPosicao)
                .valor(valor)
                .build();
        log.info(proventoDTO.toString());
        proventoService.save(proventoDTO);
    }
}
