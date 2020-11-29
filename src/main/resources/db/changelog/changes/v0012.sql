create table grupo_acao (
        id int8 not null,
        nome varchar not null,
        percentual float8,
        nota float8 not null,
        usuario_id int8 not null,
        primary key (id)
    );
create table grupo_acao_acao (
    grupo_acao_id int8 not null,
    acao_id int8 not null,
    primary key (grupo_acao_id, acao_id)
);

alter table grupo_acao_acao
        add constraint fk_grupo_acao_acao_grupo_acao
        foreign key (grupo_acao_id)
        references grupo_acao;

alter table grupo_acao_acao
        add constraint fk_grupo_acao_acao_acao
        foreign key (acao_id)
        references acao;

create table grupo_acao_grupo_acao (
    grupo_acao_id int8 not null,
    subgrupo_id int8 not null,
    primary key (grupo_acao_id, subgrupo_id)
);

alter table grupo_acao_grupo_acao
        add constraint fk_grupo_acao_grupo_acao_grupo_acao
        foreign key (grupo_acao_id)
        references grupo_acao;

alter table grupo_acao_grupo_acao
        add constraint fk_grupo_acao_grupo_acao_subgrupo
        foreign key (subgrupo_id)
        references grupo_acao;

