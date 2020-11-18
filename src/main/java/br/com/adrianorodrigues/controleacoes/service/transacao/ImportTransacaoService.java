package br.com.adrianorodrigues.controleacoes.service.transacao;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.interfaces.ICallback;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.util.DateFromStringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;

import java.time.LocalDateTime;
@AllArgsConstructor
@Slf4j
public class ImportTransacaoService implements ICallback<Row> {

    private TransacaoService transacaoService;
    private Long idUsuario;
    private Long idCarteira;
    private boolean endFile = false;

    public ImportTransacaoService(TransacaoService transacaoService, Long idUsuario, Long idCarteira) {
        this.transacaoService = transacaoService;
        this.idUsuario = idUsuario;
        this.idCarteira = idCarteira;
    }

    @Override
    public void callback(Row row) {
        String papel = row.getCell(6).getStringCellValue().trim().toUpperCase();
        if(!papel.isEmpty() && !endFile) {
            String tipoTransacaoString = row.getCell(3).getStringCellValue().trim();
            int quantidade = (int) row.getCell(8).getNumericCellValue();
            Double valor = row.getCell(9).getNumericCellValue();
            LocalDateTime data = DateFromStringUtil.getLocalDate(row.getCell(1).getStringCellValue().trim());
            if (papel.endsWith("F"))
                papel = papel.substring(0, papel.length() - 1);
            TransacaoDTO transacaoDTO = TransacaoDTO
                    .builder()
                    .papel(papel)
                    .quantidade(quantidade)
                    .valor(valor)
                    .data(data)
                    .idUsuario(idUsuario)
                    .idCarteira(idCarteira)
                    .build();
            TipoTransacao tipoTransacao = tipoTransacaoString.equalsIgnoreCase("C") ?
                    TipoTransacao.COMPRA : TipoTransacao.VENDA;
            log.info(tipoTransacao.getNome());
            log.info(transacaoDTO.toString());
            transacaoService.saveTrasacao(transacaoDTO, tipoTransacao);
        }else {
            endFile = true;
        }
    }
}