package br.com.adrianorodrigues.controleacoes.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "empresa_mantenedora")
public class EmpresaMantenedora {
    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "varchar(100)")
    private String descricao;
    @Column(columnDefinition = "varchar(100)")
    private Long cnpj;
    @EqualsAndHashCode.Exclude
    @Column()
    private LocalDateTime dataCadastro;
    @EqualsAndHashCode.Exclude
    @Column()
    private LocalDate dataInicio;
    @EqualsAndHashCode.Exclude
    @Column()
    private LocalDate dataFim;
    @ManyToOne
    @JoinColumn(name = "acao_id")
    private Acao acao;
}
