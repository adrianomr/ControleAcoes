

    alter table transacao add column usuario_id int8;
    alter table transacao
        add constraint FK_usuario_id
        foreign key (usuario_id)
        references usuario;


