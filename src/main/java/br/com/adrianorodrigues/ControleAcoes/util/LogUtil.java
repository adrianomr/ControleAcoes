package br.com.adrianorodrigues.ControleAcoes.util;

import br.com.adrianorodrigues.ControleAcoes.ControleAcoesApplication;
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
