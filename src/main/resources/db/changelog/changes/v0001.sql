drop table if exists acao cascade;

drop table if exists cotacao cascade;

drop sequence if exists hibernate_sequence;

create table acao (
        id int8 not null,
        codigoBdi varchar(100),
        nomeEmpresa varchar(100),
        papel varchar(100),
        quantidade numeric(20),
        tipoMercado int4,
        valor numeric(20,4),
        primary key (id)
    );

    create table cotacao (
        id int8 not null,
        data timestamp,
        papel varchar(100),
        valorFechamento numeric(20,4),
        acao_id int8,
        primary key (id)
    );

    create table provento (
        id int8 not null,
        dataPagamento timestamp,
        valor numeric(19, 2),
        acao_id int8,
        primary key (id)
    );

    create table transacao (
        id int4 not null,
        tipoTransacao int4,
        valor numeric(19, 2),
        acao_id int8,
        primary key (id)
    );

    create table usuario (
        id int8 not null,
        nome varchar(255),
        primary key (id)
    );

    alter table cotacao
        add constraint FK_cpituohljinm5y86j91nn8gxy
        foreign key (acao_id)
        references acao;

    alter table provento
        add constraint FK_b9si3n7rnn4ouud3di1h7vtiq
        foreign key (acao_id)
        references acao;

    alter table transacao
        add constraint FK_781kp1fcnm8rrqo9ylq4f1fwa
        foreign key (acao_id)
        references acao;



create sequence hibernate_sequence;
CREATE OR REPLACE FUNCTION atualizaIdAcaoNaCotacao() RETURNS trigger AS $atualizaIdAcaoNaCotacao$
BEGIN
NEW.acao_id = (select acao.id from acao where acao.papel = NEW.papel);
return NEW;
END
$atualizaIdAcaoNaCotacao$ LANGUAGE plpgsql;
CREATE TRIGGER updateCotacaoSetIdAcao
BEFORE insert ON cotacao
FOR EACH ROW
EXECUTE PROCEDURE atualizaIdAcaoNaCotacao();