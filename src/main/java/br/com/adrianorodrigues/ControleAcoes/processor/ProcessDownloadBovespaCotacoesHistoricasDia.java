package br.com.adrianorodrigues.ControleAcoes.processor;

import br.com.adrianorodrigues.ControleAcoes.client.CotacaoCliente;

public class ProcessDownloadBovespaCotacoesHistoricasDia {
    public static void execute() {
        CotacaoCliente.getCotacaoDia();
    }
}
