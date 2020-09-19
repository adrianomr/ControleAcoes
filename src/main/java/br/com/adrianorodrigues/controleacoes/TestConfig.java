package br.com.adrianorodrigues.controleacoes;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.TransacaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class TestConfig {
    @Bean
    @Profile("test")
    public CommandLineRunner run(UsuarioRepository usuarioRepository, AcaoRepository acaoRepository, TransacaoRepository transacaoRepository) {
        return args -> {
            usuarioRepository.deleteAll();
            transacaoRepository.deleteAll();
            usuarioRepository.deleteAll();
            acaoRepository.deleteAll();
            Usuario usuario = new Usuario();
            usuario.setId(1l);
            usuario.setNome("Adriano");
            usuarioRepository.save(usuario);
            Acao acao = new Acao();
            acao.setCodigoBdi("02");
            acao.setNomeEmpresa("BTG Pactual Fundo de Fundos");
            acao.setPapel("BCFF11");
            acao.setTipoMercado(10);
            acaoRepository.save(acao);
        };
    }
}