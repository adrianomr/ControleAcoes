package util;

import model.Acao;
import model.Carteira;
import model.Usuario;

import java.math.BigDecimal;

public class MinhaCarteiraUtil {
    private static Usuario usuario;

    public static Usuario getUsuario() {
        if(usuario == null){
             usuario = new Usuario(1, "Adriano", new Carteira());
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("B3SA3"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("ABEV3"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("ITUB4"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("BIDI4"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("VIVT4"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("WIZS3"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("XPLG11"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("HGRE11"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("ITUB3"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"));
             usuario.getCarteira().addAcao(AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"));
        }
        return usuario;
    }
}
