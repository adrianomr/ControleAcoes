package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.client.CotacaoCliente;

public class ProcessDownloadBovespaCotacoesHistoricasDia {
    public static void execute() {
        CotacaoCliente.getCotacaoDia();
    }
}
