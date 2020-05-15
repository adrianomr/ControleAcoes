package br.com.adrianorodrigues.controleacoes.util.proventos;


import br.com.adrianorodrigues.controleacoes.model.Provento;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProventosUtil {
    public static HashMap<Integer, ArrayList<Provento>> proventos;

    public static HashMap<Integer, ArrayList<Provento>>getProventos() throws ParseException {
        if(proventos == null){
            proventos = new HashMap<Integer, ArrayList<Provento>>();
            ProventosBcff11Util.getProventos(proventos);
        }
        return proventos;
    }
}
