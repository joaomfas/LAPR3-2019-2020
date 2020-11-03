delimiter /

--------------------------------------------
-- PROCEDURES DA BD
--------------------------------------------

CREATE OR REPLACE PROCEDURE doCommit IS
BEGIN
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE doRollback IS
BEGIN
    ROLLBACK;
END;
/
--------------------------------------------
-- FUNÇÕES PARA VEÍCULOS
--------------------------------------------
-- Função para adicionar Veiculo
CREATE OR REPLACE PROCEDURE addVeiculo(
    id_tipo number,
    id_parque varchar,
    descricao varchar,
    peso number,
    area_frontal number,
    coef_aero number,
    estado number,
    tamanho number,
    cap_max number,
    cap_atual number,
    tipo_scooter varchar,
    pot_motor number
) IS
BEGIN
INSERT INTO "Veiculo"(
        id_tipo_veiculo,
        id_parque,
        descricao,
        peso,
        area_frontal,
        coeficiente_aero,
        tamanho,
        capacidade_max,
        carga_atual,
        tipo,
        pot_motor
    )VALUES(
        id_tipo,
        id_parque,
        descricao,
        peso,
        area_frontal,
        coef_aero,
        tamanho,
        cap_max,
        cap_atual,
        tipo_scooter,
        pot_motor
    );
END;
/

-- Procedure para atualizar veiculo
CREATE OR REPLACE PROCEDURE updateVeiculo(
    p_id_parque varchar,
    p_descricao varchar,
    p_peso number,
    p_area_frontal number,
    p_coef_aero number,
    p_tamanho number,
    p_cap_max number,
    p_cap_atual number,
    p_tipo_scooter varchar,
    p_pot_motor number,
    p_removido number
)
IS
BEGIN
  UPDATE "Veiculo" v
  SET v.id_parque = p_id_parque,
  v.peso = p_peso,
  v.area_frontal = p_area_frontal,
  v.coeficiente_aero = p_coef_aero,
  v.tamanho = p_tamanho,
  v.capacidade_max = p_cap_max,
  v.carga_atual = p_cap_atual,
  v.tipo = p_tipo_scooter,
  v.pot_motor = p_pot_motor,
  v.removido = p_removido
  WHERE v.descricao = p_descricao;
  COMMIT;
END;
/

-- Função que retorna as bicicletas na BD

create or replace function getBicicletas (removido number)
RETURN SYS_REFCURSOR as curBicicletas SYS_REFCURSOR;
begin
    -- Se a flag ativos for verdadeira, retorna somente os parques ativos
    IF removido = 0 THEN
        open curBicicletas for select * from "Veiculo" v where v.removido = 0 and id_tipo_veiculo = 2;
        return curBicicletas;
    -- Caso contrário, retorna todos os parques, incluindo inativos.
    ELSE
        open curBicicletas for select * from "Veiculo" v where id_tipo_veiculo = 2;
        return curBicicletas;
    END IF;
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;  
END;
/

-- Função que retornas as scooters na BD

create or replace function getScooters (removido number)
RETURN SYS_REFCURSOR as curScooters SYS_REFCURSOR;
begin
    -- Se a flag ativos for verdadeira, retorna somente os parques ativos
    IF removido = 0 THEN
        open curScooters for select * from "Veiculo" v where v.removido = 0 and id_tipo_veiculo = 1;
        return curScooters;
    -- Caso contrário, retorna todos os parques, incluindo inativos.
    ELSE
        open curScooters for select * from "Veiculo" v where id_tipo_veiculo = 1;
        return curScooters;
    END IF;
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;  
END;
/

-- Função para obter veiculo com descricao

CREATE OR REPLACE FUNCTION getVeiculoComDescricao(vdesc_veic "Veiculo".descricao%type) RETURN SYS_REFCURSOR AS 
 resultado                                  SYS_REFCURSOR;
BEGIN
  OPEN resultado FOR 
    SELECT * FROM "Veiculo" v 
    WHERE v.descricao = vdesc_veic; 
  RETURN resultado; 
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;
END;
/

-- Função que obtem todas as bicicletas num parque
create or replace function getBicicletasNoParque(
    p_id_parque varchar2
) 
RETURN SYS_REFCURSOR as curParque SYS_REFCURSOR;
begin
  open curParque for select * from "Veiculo" v where v.id_parque = p_id_parque and id_tipo_veiculo = 2 and v.removido = 0 and v.estado = 0; 
  return curParque; 
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;
END;
/

-- Função para obter uma scooter com carga
create or replace function getScooterComCarga(
    carga_necessaria number,
    pid_parque varchar2
) 
RETURN SYS_REFCURSOR as curScooter SYS_REFCURSOR;
begin
  open curScooter for select * from "Veiculo" v 
  where v.id_parque = pid_parque and 
  id_tipo_veiculo = 1 and 
  v.removido = 0 and 
  v.estado = 0 and 
  (v.carga_atual/100*v.capacidade_max) >= carga_necessaria
  Order by v.carga_atual ASC; 
  return curScooter; 
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;
END;
/

-- Função que obtem todas as scooters num parque
create or replace function getScootersNoParque(
    p_id_parque varchar
) 
RETURN SYS_REFCURSOR as curParque SYS_REFCURSOR;
begin
  open curParque for select * from "Veiculo" v where v.id_parque = p_id_parque and id_tipo_veiculo = 1 and v.removido = 0 and v.estado = 0; 
  return curParque; 
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;
END;
/

CREATE OR REPLACE FUNCTION getVeiculosEmUtilizacao (data_atual "Viagem".data_hora_inicio%type)
RETURN SYS_REFCURSOR as curVeiculos SYS_REFCURSOR;
BEGIN
 OPEN curVeiculos FOR SELECT v.id_parque_inicio, v.id_utilizador, v.descricao_veiculo 
  FROM "Viagem" v WHERE  
  v.data_hora_inicio <= data_atual  AND v.data_hora_fim IS NULL ; 
  RETURN curVeiculos; 
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;
END;
/

--------------------------------------------
-- FUNÇÕES PARA PARQUES
--------------------------------------------

-- Função para devolver um parque com determinadas coordenadas
create or replace function getParqueComCoordenadas(
    v1 NUMBER, 
    v2 NUMBER
) return SYS_REFCURSOR as curParque SYS_REFCURSOR;
begin
  open curParque for 
	select * 
	from "Parque" p 
	where p.latitude = v1 and 
	      p.longitude = v2 and
		  p.ativo = 1; 
  return curParque; 
  
  Exception when no_data_found then
  return NULL;
end;
/

-- Função para devolver um parque com determinadas coordenadas
create or replace function getParque(
    id_parque varchar2
 ) RETURN SYS_REFCURSOR as curParque SYS_REFCURSOR;
begin
  open curParque for 
	select * 
	from "Parque" p 
	where p.id_parque = getParque.id_parque AND 
		  p.ativo = 1; 

  return curParque; 
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;
END;
/


-- Função Adicionar Parque 
CREATE OR REPLACE PROCEDURE addParque (
parque_id varchar2,
parque_latitude number, 
parque_longitude number, 
parque_elevacao number, 
parque_descricao varchar, 
parque_lot_bike number, 
parque_lot_scooter number,
parque_voltagem number, 
parque_corrente number)
IS
BEGIN
INSERT INTO "Parque"(
id_parque,
latitude,
longitude, 
elevacao,
descricao, 
lot_bike,
lot_scooter, 
voltagem,
corrente)
values (
parque_id,
parque_latitude,
parque_longitude, 
parque_elevacao, 
parque_descricao,
parque_lot_bike, 
parque_lot_scooter, 
parque_voltagem, 
parque_corrente);
END;
/

-- Procedimento atualizar Parque
CREATE OR REPLACE PROCEDURE atualizaParque(
    p_id_parque varchar2,
    p_latitude number,
    p_longitude number,
    p_elevacao number,
    p_descricao varchar,
    p_lot_bike number,
    p_lot_scooter number,
    p_voltagem number,
    p_corrente number,
    p_ativo char
 )
IS
BEGIN
 UPDATE "Parque" p
  SET p.latitude = p_latitude,
    p.longitude=p_longitude,
    p.elevacao =p_elevacao,
    p.descricao = p_descricao,
    p.lot_bike = p_lot_bike,
    p.lot_scooter = p_lot_scooter,
    p.voltagem =p_voltagem,
    p.corrente = p_corrente,
    p.ativo = p_ativo
  WHERE p.id_parque = p_id_parque;
  COMMIT;
END;
/

-- Função que retorna todos os parques
-- cria uma flag para ativos
create or replace function getParques (ativos number)
RETURN SYS_REFCURSOR as curParque SYS_REFCURSOR;
begin
    -- Se a flag ativos for verdadeira, retorna somente os parques ativos
    IF ativos = 1 THEN
        open curParque for select * from "Parque" p where p.Ativo = 1;
        return curParque;
    -- Caso contrário, retorna todos os parques, incluindo inativos.
    ELSE
        open curParque for select * from "Parque";
        return curParque;
    END IF;
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;  
END;
/

-- Funcao para verificar lugares disponiveis no parque de acordo com o tipo do veÃ­culo
CREATE OR REPLACE FUNCTION verificaLotacaoVeiculoParque(
	pusername "Utilizador".username%type,
	pid_parque "Parque".id_parque%type) 
RETURN NUMBER AS lotacao NUMBER;
	tipo_veiculo         "TipoVeiculo".designacao%type;
	pid_utilizador       NUMBER;
BEGIN
	-- 1. verifica o tipo de veiculo do usuario
	SELECT u.id_utilizador INTO pid_utilizador
	FROM "Utilizador" u 
	WHERE u.username = pusername;

    SELECT tv.designacao INTO tipo_veiculo
	FROM "Viagem" vi, "Veiculo" ve, "TipoVeiculo" tv
	WHERE vi.id_utilizador = pid_utilizador AND
		  vi.data_hora_fim IS NULL AND
		  vi.descricao_veiculo = ve.descricao AND
          ve.id_tipo_veiculo = tv.id_tipo_veiculo;

	-- 2. retornar a lotacao
	-- tipo == 1 -> bike, tipo == 2 -> scooter
	IF (tipo_veiculo = 'escooter') THEN
        lotacao := verificaLotacaoScootersParque(pid_parque);
	ELSE 
        lotacao := verificaLotacaoBicicletasParque(pid_parque);
	END IF;
	RETURN lotacao;
    END;
/



CREATE OR REPLACE FUNCTION verificaLotacaoScootersParque(pid "Parque".id_parque%type) RETURN NUMBER AS
    veiculos_estacionados                   NUMBER;
    espaco_maximo                           NUMBER;
    lotacao                                 NUMBER;
BEGIN
    SELECT Count(*) into veiculos_estacionados FROM "Veiculo" v 
    JOIN "TipoVeiculo" tv ON v.id_tipo_veiculo = tv.id_tipo_veiculo
    JOIN "Parque" p ON p.id_parque = v.id_parque
    WHERE p.id_parque = pid
    AND tv.designacao = 'escooter';
    SELECT p.lot_scooter INTO espaco_maximo FROM "Parque" p
    WHERE p.id_parque = pid;
    lotacao := espaco_maximo - veiculos_estacionados;
    return lotacao;
END;
/


CREATE OR REPLACE FUNCTION verificaLotacaoBicicletasParque(pid "Parque".id_parque%type) RETURN NUMBER AS
    veiculos_estacionados                   NUMBER;
    espaco_maximo                           NUMBER;
    lotacao                                 NUMBER;
BEGIN
    SELECT Count(*) into veiculos_estacionados FROM "Veiculo" v 
    JOIN "TipoVeiculo" tv ON v.id_tipo_veiculo = tv.id_tipo_veiculo
    JOIN "Parque" p ON p.id_parque = v.id_parque
    WHERE p.id_parque = pid
    AND tv.designacao = 'bicicleta';
    SELECT p.lot_bike INTO espaco_maximo FROM "Parque" p
    WHERE p.id_parque = pid;
    lotacao := espaco_maximo - veiculos_estacionados;
    return lotacao;
END;
/

CREATE OR REPLACE FUNCTION getTipoVeiculoPorUser(pusername "Utilizador".username%type) 
RETURN "TipoVeiculo".designacao%type AS tipo "TipoVeiculo".designacao%type;
          pid_utilizador       NUMBER;
BEGIN
	-- 1. verifica o tipo de veiculo do usuario
	SELECT u.id_utilizador INTO pid_utilizador
	FROM "Utilizador" u 
	WHERE u.username = pusername;

    SELECT tv.designacao INTO tipo
	FROM "Viagem" vi, "Veiculo" ve, "TipoVeiculo" tv
    WHERE vi.id_utilizador = pid_utilizador AND
		  vi.data_hora_fim IS NULL AND
		  vi.descricao_veiculo = ve.descricao AND ve.id_tipo_veiculo = tv.id_tipo_veiculo;

   RETURN tipo;
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;  
END;
/

--------------------------------------------
-- FUNÇÕES PARA POIs
--------------------------------------------
CREATE OR REPLACE PROCEDURE addPOI(
    latitude number,
    longitude number,
    elevacao number,
    descricao varchar
) IS
BEGIN 
INSERT INTO "PontoInteresse"(
        latitude,
        longitude,
        elevacao,
        descricao
    )VALUES(
        latitude,
        longitude,
        elevacao,
        descricao
    );
END;
/

-- Função para retornar todos os POIs
create or replace function getPOIS(dummyvar char)
return SYS_REFCURSOR as curPOI SYS_REFCURSOR;
begin
  open curPOI for select * from "PontoInteresse" poi; 
  return curPOI; 
  
  Exception when no_data_found then
  return NULL;
end;
/

-- Função para retornar POI com coordenadas
create or replace function getPOIComCoordenadas(
    v1 NUMBER, 
    v2 NUMBER
) return SYS_REFCURSOR as curPOI SYS_REFCURSOR;
begin
  open curPOI for select * from "PontoInteresse" p where p.latitude = v1 and p.longitude = v2; 
  return curPOI; 
  
  Exception when no_data_found then
  return NULL;
end;
/

-- Função para remover POI
CREATE OR REPLACE PROCEDURE removePOI(
    latitude number,
    longitude number
) IS
BEGIN 
DELETE FROM "PontoInteresse" poi WHERE poi.Latitude = latitude AND poi.Longitude = longitude;
END;
/

--------------------------------------------
-- FUNÇÕES PARA USERS
--------------------------------------------

CREATE OR REPLACE FUNCTION getUtilizador(pid_utilizador "Utilizador".id_utilizador%type) 
RETURN SYS_REFCURSOR IS 
 resultado                                  SYS_REFCURSOR;
BEGIN
    OPEN resultado FOR 
        SELECT u.id_utilizador, u.username, u.password, u.email, u.nome, c.cartao_credito, c.peso, c.altura, c.velocidade_media, c.genero, c.pontos FROM "Utilizador" u
        INNER JOIN "Cliente" c ON u.id_utilizador = c.id_utilizador
        WHERE u.id_utilizador = pid_utilizador;
    RETURN resultado;
    EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        RETURN NULL;
END;
/



CREATE OR REPLACE FUNCTION getUserByEmail(pemail "Utilizador".email%type)
RETURN SYS_REFCURSOR IS
    resultado                                     SYS_REFCURSOR;
    ex_not_found                                  exception;
BEGIN
    
    OPEN resultado FOR 
        SELECT u.id_utilizador, u.username, u.password, u.email, u.nome, c.cartao_credito, c.peso, c.altura, c.velocidade_media, c.genero, c.pontos FROM "Utilizador" u
        INNER JOIN "Cliente" c ON u.id_utilizador = c.id_utilizador
        WHERE u.email = pemail;
    RETURN resultado;

    EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        RETURN NULL;
END;
/

CREATE OR REPLACE FUNCTION getUtilizadorByUserName(pusername "Utilizador".username%type)
RETURN SYS_REFCURSOR IS
    resultado                                     SYS_REFCURSOR;
BEGIN
    
    OPEN resultado FOR 
        SELECT u.id_utilizador, u.username, u.password, u.email, u.nome, c.cartao_credito, c.peso, c.altura, c.velocidade_media, c.genero, c.pontos FROM "Utilizador" u
        INNER JOIN "Cliente" c ON u.id_utilizador = c.id_utilizador
        WHERE u.username = pusername;
    RETURN resultado;
    
    EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        RETURN NULL;
END;
/



create or replace FUNCTION createUtilizador(
    username "Utilizador".username%type,
    password "Utilizador".password%type,
    email "Utilizador".email%type,
    nome "Utilizador".nome%type
) 
RETURN NUMBER AS 
    ID_U                                         Number;
    ex_password_not_found                        exception;
BEGIN
    IF (password IS NOT NULL) THEN
    INSERT INTO "Utilizador" (id_utilizador, username, password, email, nome)
    VALUES (default, username, password, email, nome) 
    returning id_utilizador INTO ID_U;
    COMMIT;
    return ID_U;
    END IF;
    raise ex_password_not_found;
    EXCEPTION
    WHEN ex_password_not_found THEN
        RAISE_APPLICATION_ERROR(-20105,'Erro a criar utilizador, password não encontrada');

END;
/

create or replace PROCEDURE atualizaUtilizador(
    pid_utilizador          "Utilizador".id_utilizador%type,
    --pusername               "Utilizador".username%type,
    ppassword               "Utilizador".password%type,
    pemail                  "Utilizador".email%type,
    pnome                   "Utilizador".nome%type,
    pcartao_credito         "Cliente".cartao_credito%type,
    ppeso                   "Cliente".peso%type,
    paltura                 "Cliente".altura%type,
    pvelocidade_media       "Cliente".velocidade_media%type,
    pgenero                 "Cliente".genero%type
) 
IS
    id_utilizador "Utilizador".id_utilizador%type;
BEGIN
    UPDATE "Utilizador" u SET u.password = ppassword, u.email = pemail, u.nome = pnome
    WHERE u.id_utilizador = pid_utilizador;
    
    UPDATE "Cliente" c SET c.cartao_credito = pcartao_credito, 
            c.peso = ppeso, c.altura = paltura, 
            c.velocidade_media = pvelocidade_media,
            c.genero = pgenero
    WHERE c.id_utilizador = pid_utilizador;
END;
/


CREATE OR REPLACE PROCEDURE addUtilizador(
    username "Utilizador".username%type,
    password "Utilizador".password%type,
    email "Utilizador".email%type,
    nome "Utilizador".nome%type,
    cartao_credito "Cliente".id_utilizador%type,
    peso "Cliente".peso%type,
    altura "Cliente".altura%type,
    velocidade_media "Cliente".velocidade_media%type,
    genero "Cliente".genero%type
)
IS
    id_utilizador "Utilizador".id_utilizador%type;
BEGIN
    id_utilizador:=createUtilizador(username, password, email, nome);
    INSERT INTO "Cliente" VALUES (id_utilizador, cartao_credito, peso, altura, velocidade_media, genero, default);
    DBMS_OUTPUT.PUT_LINE('ID: utilizador criado : ' || id_utilizador);
END;
/

--------------------------------------------
-- FUNÇÕES PARA VIAGENS
--------------------------------------------

CREATE OR REPLACE FUNCTION getViagemUtilizador(pid_utilizador "Viagem".id_utilizador%type)
RETURN SYS_REFCURSOR IS
    resultado                                     SYS_REFCURSOR;
    ex_not_found                                  exception;
BEGIN
    OPEN resultado FOR 
        SELECT * FROM "Viagem" v
        WHERE v.id_utilizador = pid_utilizador
        AND v.data_hora_fim is NULL;
    RETURN resultado;
    raise ex_not_found;
    EXCEPTION
    WHEN ex_not_found THEN
        RAISE_APPLICATION_ERROR(-20104,'viagem nao encontrada');
END;
/

CREATE OR REPLACE FUNCTION getViagemVeiculo(pveic_desc "Viagem".descricao_veiculo%type)
RETURN SYS_REFCURSOR IS
    resultado                                     SYS_REFCURSOR;
    ex_not_found                                  exception;
BEGIN
    OPEN resultado FOR 
        SELECT * FROM "Viagem" v
        WHERE v.descricao_veiculo = pveic_desc
        AND v.data_hora_fim is NULL;
    RETURN resultado;
    raise ex_not_found;
    EXCEPTION
    WHEN ex_not_found THEN
        RAISE_APPLICATION_ERROR(-20104,'viagem nao encontrada');
END;
/

CREATE OR REPLACE PROCEDURE addViagem (
    id_utilizador "Viagem".id_utilizador%type,
    descricao_veiculo "Viagem".descricao_veiculo%type,
    id_parque_inicio "Viagem".id_parque_inicio%type,
    data_hora_inicio "Viagem".data_hora_inicio%type
) IS    
BEGIN
    INSERT INTO "Viagem"(
        id_utilizador,
        descricao_veiculo,
        id_parque_inicio,
        data_hora_inicio
    )
    VALUES (id_utilizador,
    descricao_veiculo, 
    id_parque_inicio, 
    data_hora_inicio);
    COMMIT;
END;
/


CREATE OR REPLACE PROCEDURE atualizaViagem (
    pid_utilizador "Viagem".id_utilizador%type,
    pdescricao_veiculo "Viagem".descricao_veiculo%type,
    pid_parque_inicio "Viagem".id_parque_inicio%type,
    pid_parque_fim "Viagem".id_parque_fim%type,
    pdata_hora_inicio "Viagem".data_hora_inicio%type,
    pdata_hora_fim "Viagem".data_hora_fim%type
) IS 
    ex_updatefail       exception;
BEGIN
    UPDATE "Viagem" v 
    SET v.id_parque_fim = pid_parque_fim, v.data_hora_fim = pdata_hora_fim
    WHERE v.id_utilizador = pid_utilizador
    AND v.descricao_veiculo = pdescricao_veiculo
    AND v.id_parque_inicio = pid_parque_inicio
    AND v.data_hora_inicio = pdata_hora_inicio;
    if sql%rowcount = 0 then
        raise ex_updatefail;
    end if;
    EXCEPTION
    WHEN ex_updatefail THEN
        RAISE_APPLICATION_ERROR(-20104, 'Não foi possivel atualizar a viagem.');
END;
/




--------------------------------------------
-- FUNÇÕES PARA CAMINHOS
--------------------------------------------

-- Função para adicionar caminho
CREATE OR REPLACE PROCEDURE addCaminho(
    latA number,
    lonA number,
    latB number,
    lonB number,
    coefCinetico number,
    dirVento number,
    velVento number
)
IS
BEGIN 
INSERT INTO "Caminho"(
        latitude_inicial,
        longitude_inicial,
        latitude_final,
        longitude_final,
        coeficiente_cinetico,
        direcao_vento,
        velocidade_vento
    )VALUES(
        latA,
        lonA,
        latB,
        lonB,
        coefCinetico,
        dirVento,
        velVento
    );
END;
/

-- Função para retornar caminho com inicio e fim
create or replace function getCaminhoComCoordenadas(
    latA NUMBER,
    lonA number,
    latB number,
    lonB number
) return SYS_REFCURSOR as curCaminho SYS_REFCURSOR;
begin
  open curCaminho for select * from "Caminho" c 
  where c.latitude_inicial = latA and
        c.longitude_inicial = lonA and
        c.latitude_final = latB and
        c.longitude_final = lonB; 
  return curCaminho; 
  
  Exception when no_data_found then
  return NULL;
end;
/

-- Procedure que retorna todos os caminhos
create or replace function getCaminhos(dummyvar char)
return SYS_REFCURSOR as curCaminho SYS_REFCURSOR;
begin
  open curCaminho for select * from "Caminho" c; 
  return curCaminho; 
  
  Exception when no_data_found then
  return NULL;
end;
/

-- Função para atualizar um caminho
CREATE OR REPLACE PROCEDURE atualizaCaminho(
    latA number,
    lonA number,
    latB number,
    lonB number,
    coefCinetico number,
    dirVento number,
    velVento number
)
IS
BEGIN
  UPDATE "Caminho" c
  SET 
        c.coeficiente_cinetico = coefCinetico,
        c.direcao_vento = dirVento,
        c.velocidade_vento = velVento
  WHERE c.latitude_inicial = latA and
        c.longitude_inicial = lonA and
        c.latitude_final = latB and
        c.longitude_final = lonB;
  COMMIT;
END;
/

--------------------------------------------
-- FUNÇÕES PARA FATURA
--------------------------------------------

create or replace NONEDITIONABLE PROCEDURE addFatura(
    pusername                       "Fatura".username%type,
    pmes                            number
)
IS
    ptotal_pontos_ganhos_ant        number;
    ptotal_pontos_gastos_ant        number;

    ppontos_anteriores              number;--
    ppontos_ganhos                  number;
    ppontos_descontados             number;-- default 0 no inicio?
    ppontos_atuais                  number;-- ppontos_anteriores + ppontos_ganhos
    pvalor_cobrado                  number;
    pvalor_pago                     number;-- default 0
    ID_F                            number;
    
    uExiste                         number;
    utilizador_ex                   exception;
    
BEGIN
    SELECT COUNT(*) into uExiste FROM "Utilizador" u Where u.username = pusername;
    IF(uExiste = 0) THEN
        raise utilizador_ex;
    END IF;
    
    -- total pontos descontados até ao momento
    select NVL(SUM(f.pontos_descontados),0) into ptotal_pontos_gastos_ant 
    FROM "Fatura" f
    where username = pusername;
    
    -- total pontos ganhos até ao momento
    SELECT NVL(SUM(fv.pontos),0) into ptotal_pontos_ganhos_ant 
    FROM "FaturaViagem" fv 
    INNER JOIN "Viagem" v ON fv.id_viagem = v.id_viagem
    INNER JOIN "Utilizador" u ON v.id_utilizador = u.id_utilizador
    WHERE u.username = pusername
    AND fv.id_fatura IS NOT NULL;-- já tem ligada a fatura
    
    ppontos_anteriores := ptotal_pontos_ganhos_ant - ptotal_pontos_gastos_ant;
    
    SELECT NVL(SUM(fv.pontos),0) into ppontos_ganhos 
    FROM "FaturaViagem" fv 
    INNER JOIN "Viagem" v ON fv.id_viagem = v.id_viagem
    INNER JOIN "Utilizador" u ON v.id_utilizador = u.id_utilizador
    WHERE u.username = pusername
    AND fv.id_fatura IS NULL
    AND EXTRACT(MONTH FROM (v.data_hora_fim)) = pmes;
    
    ppontos_atuais := ppontos_anteriores + ppontos_ganhos;
    
    SELECT NVL(SUM(fv.valor),0) into pvalor_cobrado 
    FROM "FaturaViagem" fv 
    INNER JOIN "Viagem" v ON fv.id_viagem = v.id_viagem
    INNER JOIN "Utilizador" u ON v.id_utilizador = u.id_utilizador
    WHERE u.username = pusername
    AND fv.id_fatura IS NULL
    AND EXTRACT(MONTH FROM (v.data_hora_fim)) = pmes;
    
    -- cria fatura
    INSERT INTO 
        "Fatura" (id_fatura, username, mes, pontos_anteriores, pontos_ganhos, pontos_descontados, pontos_atuais, valor_cobrado, valor_pago)
        VALUES (default, pusername, pmes, ppontos_anteriores, ppontos_ganhos, 0, ppontos_atuais, pvalor_cobrado, 0)
        RETURNING id_fatura INTO ID_F;
    
    -- atualiza faturaviagem
    UPDATE "FaturaViagem" fv
        SET fv.id_fatura = ID_F
        WHERE fv.id_viagem IN (
            SELECT v.id_viagem
            FROM "FaturaViagem" fv 
            INNER JOIN "Viagem" v ON fv.id_viagem = v.id_viagem
            INNER JOIN "Utilizador" u ON v.id_utilizador = u.id_utilizador
            WHERE u.username = pusername
            AND fv.id_fatura IS NULL
            AND EXTRACT(MONTH FROM (v.data_hora_fim)) = pmes
        );
        
    EXCEPTION
    WHEN utilizador_ex THEN
        RAISE_APPLICATION_ERROR(-20103,'Utilizador nao encontrado');
END;
/

CREATE OR REPLACE NONEDITIONABLE PROCEDURE atualizaFatura (
    pid_fatura              "Fatura".id_fatura%type,
    pusername               "Fatura".username%type,
    pmes                    "Fatura".mes%type,
    ppontos_anteriores      "Fatura".pontos_anteriores%type,
    ppontos_ganhos          "Fatura".pontos_ganhos%type,
    ppontos_descontados     "Fatura".pontos_descontados%type,
    ppontos_atuais          "Fatura".pontos_atuais%type,
    pvalor_cobrado          "Fatura".valor_cobrado%type,
    pvalor_pago             "Fatura".valor_pago%type
) IS 
BEGIN
    UPDATE "Fatura" f
    SET f.pontos_anteriores = ppontos_anteriores,
        f.pontos_ganhos = ppontos_ganhos,
        f.pontos_descontados = ppontos_descontados,
        f.pontos_atuais = ppontos_atuais,
        f.valor_cobrado = pvalor_cobrado,
        f.valor_pago = pvalor_pago
    WHERE f.id_fatura = pid_fatura
    AND f.username = pusername
    AND f.mes = pmes;
END;
/

create or replace NONEDITIONABLE function getFatura(
    pusername                       "Fatura".username%type,
    pmes                            number
) 
RETURN SYS_REFCURSOR AS 
    curFatura               SYS_REFCURSOR;
BEGIN
    OPEN curFatura FOR 
        SELECT * FROM "Fatura" f 
        WHERE f.username = pusername AND 
        f.mes = pmes; 
    RETURN curFatura;     
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;
END;
/

create or replace NONEDITIONABLE function getFaturas(
    pusername                       "Fatura".username%type
) 
RETURN SYS_REFCURSOR AS 
    curFatura               SYS_REFCURSOR;
BEGIN
    OPEN curFatura FOR 
        SELECT * FROM "Fatura" f 
        WHERE f.username = pusername
        ORDER BY f.id_fatura ASC;
    RETURN curFatura;     
EXCEPTION 
WHEN NO_DATA_FOUND THEN 
RETURN NULL;
END;
/

-- Função para retornar detalhe da fatura de um Utilizador Username
CREATE or REPLACE FUNCTION getDetalheFatura(
    pid_fatura       "Fatura".id_fatura%type
) RETURN SYS_REFCURSOR as 
    curFaturaDetalhe SYS_REFCURSOR;
BEGIN
  OPEN curFaturaDetalhe FOR 
      SELECT
        v.descricao_veiculo, v.data_hora_inicio, v.data_hora_fim,
        po.latitude, po.longitude, pd.latitude, pd.longitude,
        fv.tempo_viagem_segundos, fv.valor
        FROM "Viagem" v
        INNER JOIN "Parque" po ON v.id_parque_inicio = po.id_parque
        INNER JOIN "Parque" pd ON v.id_parque_fim = pd.id_parque
        INNER JOIN "FaturaViagem" fv ON v.id_viagem = fv.id_viagem
        INNER JOIN "Utilizador" u ON v.id_utilizador = u.id_utilizador
        WHERE fv.id_fatura = pid_fatura;
  RETURN curFaturaDetalhe; 
  EXCEPTION 
    WHEN no_data_found THEN
        RETURN NULL;
END;
/

CREATE or REPLACE FUNCTION getDetalheFaturaAtual(
    pusername       "Utilizador".username%type
) RETURN SYS_REFCURSOR as 
    curFaturaDetalhe SYS_REFCURSOR;
BEGIN
  OPEN curFaturaDetalhe FOR 
      SELECT
        v.descricao_veiculo, v.data_hora_inicio, v.data_hora_fim,
        po.latitude, po.longitude, pd.latitude, pd.longitude,
        fv.tempo_viagem_segundos, fv.valor
        FROM "Viagem" v
        INNER JOIN "Parque" po ON v.id_parque_inicio = po.id_parque
        INNER JOIN "Parque" pd ON v.id_parque_fim = pd.id_parque
        INNER JOIN "FaturaViagem" fv ON v.id_viagem = fv.id_viagem
        INNER JOIN "Utilizador" u ON v.id_utilizador = u.id_utilizador
        WHERE fv.id_fatura IS NULL
        AND u.username = pusername;
  RETURN curFaturaDetalhe; 
  EXCEPTION 
    WHEN no_data_found THEN
        RETURN NULL;
END;
/

CREATE or REPLACE FUNCTION getDetalhePontos(
    pusername       "Utilizador".username%type
) RETURN SYS_REFCURSOR as 
    curFaturaDetalhe SYS_REFCURSOR;
BEGIN
  OPEN curFaturaDetalhe FOR 
      SELECT
        v.descricao_veiculo, v.data_hora_inicio, v.data_hora_fim,
        po.latitude, po.longitude, po.elevacao, 
        pd.latitude, pd.longitude, pd.elevacao,
        pd.elevacao-po.elevacao "dif_elevacao", fv.pontos
        FROM "Viagem" v
        INNER JOIN "Parque" po ON v.id_parque_inicio = po.id_parque
        INNER JOIN "Parque" pd ON v.id_parque_fim = pd.id_parque
        INNER JOIN "FaturaViagem" fv ON v.id_viagem = fv.id_viagem
        INNER JOIN "Utilizador" u ON v.id_utilizador = u.id_utilizador
        --WHERE fv.id_fatura IS NULL
        AND u.username = pusername
        ORDER BY v.data_hora_inicio ASC;
  RETURN curFaturaDetalhe; 
  EXCEPTION 
    WHEN no_data_found THEN
        RETURN NULL;
END;
/

-- Função que retornas uma lista de veículos de um tipo com dada característica

CREATE OR REPLACE FUNCTION veiculoComTipoESpecs (
	tipo VARCHAR,
	specs VARCHAR
)
RETURN SYS_REFCURSOR AS 
    curVeics SYS_REFCURSOR;
BEGIN
	IF tipo = 'escooter' THEN
		OPEN curVeics FOR 
			SELECT * 
			FROM "Veiculo" v
			WHERE v.id_tipo_veiculo = 1 AND
				  v.tipo = specs;
	ELSIF tipo = 'bicycle' THEN
			OPEN curVeics FOR 
			SELECT * 
			FROM "Veiculo" v
			WHERE v.id_tipo_veiculo = 2 AND
				  v.tamanho = specs;
	END IF;
	RETURN curVeics;
EXCEPTION 
	WHEN NO_DATA_FOUND THEN 
		RETURN NULL;
END;
/
