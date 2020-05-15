package br.com.adrianorodrigues.controleacoes.client;

import br.com.adrianorodrigues.controleacoes.dto.alphavantage.AlphavantageGlobalQuoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class AlphavantageClient {
    String url = "https://www.alphavantage.co/query?function={function}&symbol={symbol}&apikey={apikey}";
    @Autowired
    RestTemplate restTemplate;

    public AlphavantageGlobalQuoteDTO getCotacao(String papel) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("function", "GLOBAL_QUOTE");
        queryParams.put("symbol", papel + ".SAO");
        queryParams.put("apikey", "P3Z3O1ZMZJV5BNX4");
        return restTemplate.getForObject(url, AlphavantageGlobalQuoteDTO.class, queryParams);
    }
}
