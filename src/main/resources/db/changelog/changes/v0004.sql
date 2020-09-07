
create table rebalanceamento_acao (
        id int8 not null,
        acao_id int8 not null,
        usuario_id int8 not null,
        percentual float,
        primary key (id)
    );

alter table rebalanceamento_acao
        add constraint FK_rebalanceamento_acao_acao
        foreign key (acao_id)
        references acao;

alter table rebalanceamento_acao
            add constraint FK_rebalanceamento_acao_usuario
            foreign key (usuario_id)
            references usuario;