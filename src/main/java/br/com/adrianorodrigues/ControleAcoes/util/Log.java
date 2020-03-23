package br.com.adrianorodrigues.ControleAcoes.util;

import br.com.adrianorodrigues.ControleAcoes.ControleAcoesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private static final Logger logger = LoggerFactory.getLogger(ControleAcoesApplication.class);
    private Log() {

    }
    public static Logger getLogger() {
        return logger;
    }
}
