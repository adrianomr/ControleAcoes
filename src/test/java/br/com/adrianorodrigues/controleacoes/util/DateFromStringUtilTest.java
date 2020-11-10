package br.com.adrianorodrigues.controleacoes.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateFromStringUtilTest {

    @Test
    void getLocalDate() {
        LocalDateTime dataExpected = LocalDateTime.of(2019, 9, 30, 0, 0);
        LocalDateTime data = DateFromStringUtil.getLocalDate("30/09/19");
        assertEquals(dataExpected, data);
    }
}