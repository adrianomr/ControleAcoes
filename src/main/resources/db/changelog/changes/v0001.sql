drop table if exists acao cascade;

drop table if exists cotacao cascade;

drop sequence if exists hibernate_sequence;

create table acao (
    id int8 not null,
    codigo_bdi varchar(100),
    nome_empresa varchar(100),
    papel varchar(100),
    quantidade numeric(20),
    tipo_mercado int4,
    valor numeric(20,4),
    primary key (id)
);

create table cotacao (
    id int8 not null,
    data timestamp,
    papel varchar(100),
    valor_fechamento numeric(20,4),
    acao_id int8,
    primary key (id)
);

alter table cotacao
    add constraint FK_cpituohljinm5y86j91nn8gxy
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