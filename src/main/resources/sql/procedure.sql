
	CREATE OR REPLACE FUNCTION emp_stamp() RETURNS trigger AS $emp_stamp$
	BEGIN
	NEW.acao_id = (select acao.id from acao where acao.papel = NEW.papel);
	return NEW;
	END
	$emp_stamp$ LANGUAGE plpgsql;
	DROP TRIGGER updateCotacaoSetIdAcao ON cotacao;
	CREATE TRIGGER updateCotacaoSetIdAcao
    BEFORE insert ON cotacao
    FOR EACH ROW
	EXECUTE PROCEDURE emp_stamp();