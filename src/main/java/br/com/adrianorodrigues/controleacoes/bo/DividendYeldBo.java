package br.com.adrianorodrigues.controleacoes.bo;


import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Provento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DividendYeldBo {
    static BigDecimal getDividendYeld(Acao acao, int ultimosNMeses) throws ParseException {
        final BigDecimal[] dividendYeld = {new BigDecimal(0)};
        Date dataFinal = new Date();
        Date dataInicial = Date.from(LocalDate.now().minusMonths(ultimosNMeses).atStartOfDay(ZoneId.systemDefault()).toInstant());
        ArrayList<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(dataInicial, dataFinal, acao.getId());
        HashMap<String, BigDecimal> proventosPorAno = new HashMap<>();
        for (Provento provento : proventos){
            Calendar cal = Calendar.getInstance();
            cal.setTime(provento.getDataPagamento());
            String ano = cal.get(Calendar.YEAR)+"";
            BigDecimal valor = proventosPorAno.get(ano) == null ? new BigDecimal(0) : proventosPorAno.get(ano);
            proventosPorAno.put(ano, provento.getValor().add(valor));
        }
        proventosPorAno.forEach((chave, totalProventos) ->{
            System.out.println(totalProventos);
            System.out.println(chave);
            BigDecimal dyAno = totalProventos.divide(acao.getValor(), 2, RoundingMode.HALF_UP);
            dividendYeld[0] = dividendYeld[0].add(dyAno.divide(new BigDecimal(proventosPorAno.size()), 2,
                    RoundingMode.HALF_UP));
        });
        return dividendYeld[0];
    }
}
