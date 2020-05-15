package br.com.adrianorodrigues.controleacoes.util;

import br.com.adrianorodrigues.controleacoes.ControleAcoesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static final Logger logger = LoggerFactory.getLogger(ControleAcoesApplication.class);
    private LogUtil() {

    }
    public static Logger getLogger() {
        return logger;
    }
}
