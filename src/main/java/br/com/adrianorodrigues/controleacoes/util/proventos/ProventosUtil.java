package br.com.adrianorodrigues.controleacoes.util.proventos;


import br.com.adrianorodrigues.controleacoes.model.Provento;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProventosUtil {
    public static Map<Long, List<Provento>> proventos;

    public static Map<Long, List<Provento>> getProventos() throws ParseException {
        if (proventos == null) {
            proventos = new HashMap<>();
            ProventosBcff11Util.getProventos(proventos);
        }
        return proventos;
    }
}
