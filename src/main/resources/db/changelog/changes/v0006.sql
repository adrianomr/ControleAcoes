
create table empresa_mantenedora (
        id int8 not null,
        acao_id int8 not null,
        cnpj int8,
        descricao varchar(100),
        data_cadastro timestamp,
        primary key (id)
    );

alter table rebalanceamento_acao
        add constraint FK_empresa_mantenedora_acao
        foreign key (acao_id)
        references acao;

