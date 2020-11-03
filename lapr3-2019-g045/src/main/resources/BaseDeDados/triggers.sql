delimiter /

--------------------------------------------
-- TRIGGERS PARA PARQUES
--------------------------------------------

CREATE OR REPLACE TRIGGER removerParque AFTER UPDATE ON "Parque" 
FOR EACH ROW
BEGIN
	IF (:old.ativo = 1 AND :new.ativo = 0)
	THEN
		UPDATE "Veiculo" v
		SET v.id_parque = NULL
		WHERE v.id_parque = :old.id_parque;
	END IF;
END;
/



CREATE OR REPLACE TRIGGER trgInicioViagem BEFORE INSERT ON "Viagem" FOR EACH ROW
DECLARE
    CURSOR c1 IS 
        SELECT tv.designacao, veic.descricao, veic.estado, veic.removido, veic.id_parque
        FROM "Parque" pi
        INNER JOIN "Veiculo" veic ON veic.id_parque = pi.id_parque
        INNER JOIN "TipoVeiculo" tv ON tv.id_tipo_veiculo = veic.id_tipo_veiculo;

    vc            c1%rowtype;

    utilizadorEmViagem                                             number;
    ex_utilizador_em_viagem                                        exception;
    ex_data_invalida                                               exception;
BEGIN
    SELECT tv.designacao, veic.descricao, veic.estado, veic.removido, veic.id_parque INTO vc
        FROM "Parque" pi
        INNER JOIN "Veiculo" veic ON veic.id_parque = pi.id_parque
        INNER JOIN "TipoVeiculo" tv ON tv.id_tipo_veiculo = veic.id_tipo_veiculo
        WHERE veic.descricao = :new.descricao_veiculo
        AND veic.id_parque = :new.id_parque_inicio;
    
    DBMS_OUTPUT.PUT_LINE('Viagem data : ' || vc.designacao || ' ESTADO : ' || vc.estado || ' VEICULO REMOVIDO ? ' || vc.removido);
    -- Validação : Utilizador existe, a viagem ainda não acabou, o veiculo está "parado", e n foi removido. 
    IF (:new.id_utilizador IS NOT NULL AND :new.data_hora_fim IS NULL AND vc.estado = 0 AND vc.removido = 0 AND :new.id_parque_inicio = vc.id_parque) THEN
        
        SELECT COUNT(*) INTO utilizadorEmViagem FROM "Viagem" v
        WHERE v.id_utilizador = :new.id_utilizador
        AND v.data_hora_fim is NULL;
        
        -- Validação : o utilizador não se encontra em viagem
        IF (utilizadorEmViagem = 0) THEN
        
            UPDATE "Veiculo" v SET v.estado = 1 , v.id_parque = NULL WHERE v.descricao = :new.descricao_veiculo;            -- coloca o veiculo com id parque a null; e em estado = 1 "em utilização"
            
            
            DBMS_OUTPUT.PUT_LINE('Viagem Iniciada com Sucesso');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Utilizador em viagem : ' || utilizadorEmViagem);
            raise ex_utilizador_em_viagem;
        END IF;
        
    ELSE
        DBMS_OUTPUT.PUT_LINE('Utilizador em viagem ou dados incorretos ');
        raise ex_data_invalida;
    END IF;
    
    EXCEPTION
    WHEN ex_utilizador_em_viagem THEN
        RAISE_APPLICATION_ERROR(-20105,'Erro, este utilizador já se encontra em viagem.');
    WHEN ex_data_invalida THEN
        RAISE_APPLICATION_ERROR(-20110,'Erro, dados invalidos, veiculo ou parque indisponiveis.');
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20110,'Erro, dados invalidos, veiculo ou parque indisponiveis.');
END;
/

create or replace NONEDITIONABLE TRIGGER trgFimViagem BEFORE UPDATE OF data_hora_fim ON "Viagem" FOR EACH ROW
DECLARE

    tempo_viagem_segundos                   NUMBER;
    custo_viagem                            NUMBER;
    diferenca_altura                        NUMBER;

    ptipo_veiculo                           "TipoVeiculo".designacao%type;

    espaco_disponivel                       NUMBER;

    total_pontos                            NUMBER;

    ex_parque_destino_cheio                 exception;
    ex_viagem_ja_tinha_terminado            exception;
    ex_faltam_dados_para_terminar_viagem    exception;
    
BEGIN
    IF(:old.data_hora_fim IS NOT NULL) THEN
        raise ex_viagem_ja_tinha_terminado;
    END IF;

    DBMS_OUTPUT.PUT_LINE('data_hora_fim : ' || :new.data_hora_fim);
    DBMS_OUTPUT.PUT_LINE('id_parque_fim : ' || :new.id_parque_fim);

    if(:new.data_hora_fim IS NULL OR :new.id_parque_fim IS NULL) THEN
        raise ex_faltam_dados_para_terminar_viagem;
    END IF;

    SELECT tv.designacao INTO ptipo_veiculo from "Veiculo" v
    JOIN "TipoVeiculo" tv ON v.id_tipo_veiculo = tv.id_tipo_veiculo
    WHERE v.descricao = :new.descricao_veiculo;

    IF (ptipo_veiculo = 'Scooter') THEN
        espaco_disponivel := verificaLotacaoScootersParque(:new.id_parque_fim);
        IF ( espaco_disponivel <= 0 ) THEN
            raise ex_parque_destino_cheio;
        END IF;
    END IF;
    --verifica se o parque destino tem espaço disponivel para guardar Bicicletas.
    IF (ptipo_veiculo = 'Bicicleta') THEN
        espaco_disponivel := verificaLotacaoBicicletasParque(:new.id_parque_fim);
        IF ( espaco_disponivel <= 0 ) THEN
            raise ex_parque_destino_cheio;
        END IF;
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('viagem termina');

    UPDATE "Veiculo" v SET v.estado = 0 , v.id_parque = :new.id_parque_fim WHERE v.descricao = :new.descricao_veiculo;            -- coloca o veiculo com id parque a null; e em estado = 1 "em utilização"

    -- calcular diferenca altura entre parques.
    select (p1.elevacao - p2.elevacao) into diferenca_altura from "Parque" p1, "Parque" p2
    where p1.id_parque=:new.id_parque_fim AND p2.id_parque = :new.id_parque_inicio;

    DBMS_OUTPUT.PUT_LINE('diferenca altura.. : ' || diferenca_altura);
    total_pontos:=0;
    IF(diferenca_altura >= 25 AND diferenca_altura <50) THEN
        total_pontos:=10;
    END IF;

    IF(diferenca_altura >= 50) THEN
        total_pontos:=15;
    END IF;
    

    tempo_viagem_segundos := extract( day from(:new.data_hora_fim - :old.data_hora_inicio)*24*60*60); --TO_TIMESTAMP(:old.data_hora_inicio, 'YYYY/MM/DD HH:MI:SS') - TO_TIMESTAMP(:new.data_hora_fim, 'YYYY/MM/DD HH:MI:SS');
    DBMS_OUTPUT.PUT_LINE('Viagem terminou, duração : ' || tempo_viagem_segundos);
    -- https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=24655
    -- "For example, at the moment, when a user picks-up an electric scooter and leaves it in less that 15 minutes, points are credited to the user"
    -- R : devem ser creditados 5 pontos.
    IF (tempo_viagem_segundos/60 <= 15) THEN
        total_pontos:=total_pontos+5;
    END IF;
    
    --IF (total_pontos <> 0) THEN
    --    UPDATE "Cliente" c SET c.pontos = c.pontos + total_pontos WHERE c.id_utilizador = :new.id_utilizador;
    --    DBMS_OUTPUT.PUT_LINE('Credita ' || total_pontos || ' pts ao clt');
    --END IF;

    custo_viagem:=0;
    IF (tempo_viagem_segundos/60 > 60) THEN
        custo_viagem:= (tempo_viagem_segundos/60 - 60) * (1.5/60);
    END IF;


    INSERT INTO "FaturaViagem" VALUES (null, :new.id_viagem, tempo_viagem_segundos, custo_viagem, total_pontos);


    EXCEPTION
    WHEN ex_viagem_ja_tinha_terminado THEN
        RAISE_APPLICATION_ERROR(-20111,'Erro, esta viagem ja tinha terminado.');
    WHEN ex_faltam_dados_para_terminar_viagem THEN
        RAISE_APPLICATION_ERROR(-20112,'Erro, id_parque_fim ou data_hora_fim nao atualizados.');
    WHEN ex_parque_destino_cheio THEN
        RAISE_APPLICATION_ERROR(-20114,'Erro, parque destino cheio.');
END;
/



--------------------------------------------
-- TRIGGERS PARA VEICULOS
--------------------------------------------
create or replace TRIGGER removerVeiculo AFTER UPDATE ON "Veiculo" 
FOR EACH ROW
BEGIN
    IF (:old.id_parque != null AND :old.estado = 0 AND :old.removido = 0 AND :new.removido = 1)
    THEN
        UPDATE "Veiculo" v
        SET v.id_parque = NULL
        WHERE v.descricao = :old.descricao;
    END IF;
END;
/