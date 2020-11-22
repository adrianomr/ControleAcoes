create table subscricao (
        id int8 not null,
        valor numeric(19, 2) not null,
        acao_id int8 not null,
        usuario_id int8 not null,
        data date not null,
        quantidade int8 not null,
        carteira_id int8 not null,
        transacao_id int8 not null,
        primary key (id)
    );

alter table subscricao
        add constraint fk_subscricao_acao
        foreign key (acao_id)
        references acao;

alter table subscricao
    add constraint FK_subscricao_usuario
    foreign key (usuario_id)
    references usuario;

alter table subscricao
        add constraint FK_subscricao_carteira
        foreign key (carteira_id)
        references carteira;

alter table subscricao
        add constraint FK_subscricao_transacao
        foreign key (transacao_id)
        references transacao;