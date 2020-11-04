package br.com.adrianorodrigues.controleacoes.conf;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.EmpresaMantenedora;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.EmpresaMantenedoraRepository;
import br.com.adrianorodrigues.controleacoes.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
public class DatabaseTestConfig {
    @Bean
    @Profile("test")
    public CommandLineRunner run(UsuarioRepository usuarioRepository, AcaoRepository acaoRepository, EmpresaMantenedoraRepository empresaMantenedoraRepository) {
        return args -> {
            usuarioRepository.deleteAll();
            acaoRepository.deleteAll();
            Usuario usuario = new Usuario();
            usuario.setNome("Adriano");
            usuarioRepository.saveAndFlush(usuario);
            Acao acao = new Acao();
            acao.setCodigoBdi("02");
            acao.setNomeEmpresa("BTG Pactual Fundo de Fundos");
            acao.setPapel("BCFF11");
            acao.setTipoMercado(10);
            Acao acaoDB = acaoRepository.saveAndFlush(acao);
            EmpresaMantenedora empresaMantenedora = EmpresaMantenedora
                    .builder()
                    .dataCadastro(LocalDateTime.now())
                    .descricao("Empresa teste")
                    .acao(acaoDB)
                    .build();
            empresaMantenedoraRepository.saveAndFlush(empresaMantenedora);
        };
    }
}