
-------------- ADD USERNAME INTO USUARIO ------------------
alter table usuario
        add column username varchar(100);
update usuario set username = 'adriano';
alter table usuario
        alter column username set not null;

-------------- CREATE CORRETORA ------------------
create table corretora (
        id int8 not null,
        nome varchar(100),
        primary key (id)
    );
-------------- CREATE CARTEIRA ------------------
create table carteira (
        id int8 not null,
        usuario_id int8 not null,
        corretora_id int8 not null,
        primary key (id)
    );

alter table carteira
        add constraint FK_carteira_usuario
        foreign key (usuario_id)
        references usuario;

alter table carteira
        add constraint FK_carteira_corretora
        foreign key (corretora_id)
        references corretora;

-------------- ADD CARTEIRA INTO TANSACAO ------------------
alter table transacao
        add column carteira_id int8 not null;

alter table transacao
        add constraint FK_transacao_carteira
        foreign key (carteira_id)
        references carteira;
