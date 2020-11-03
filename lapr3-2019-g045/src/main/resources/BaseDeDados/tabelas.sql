DROP TABLE "Utilizador" CASCADE CONSTRAINTS;
DROP TABLE "Cliente" CASCADE CONSTRAINTS;
DROP TABLE "Administrador" CASCADE CONSTRAINTS;
DROP TABLE "Parque" CASCADE CONSTRAINTS;
DROP TABLE "TipoVeiculo" CASCADE CONSTRAINTS;
DROP TABLE "Veiculo" CASCADE CONSTRAINTS;
DROP TABLE "Viagem" CASCADE CONSTRAINTS;
DROP TABLE "PontoInteresse" CASCADE CONSTRAINTS;
DROP TABLE "Caminho" CASCADE CONSTRAINTS;
DROP TABLE "FaturaViagem" CASCADE CONSTRAINTS;
DROP TABLE "Fatura" CASCADE CONSTRAINTS;


CREATE TABLE "Utilizador" 
(id_utilizador number(10) GENERATED AS IDENTITY, 
username varchar2(20) CONSTRAINT nn_utilizador_user  NOT NULL 
                        CONSTRAINT uk_utilizador_user UNIQUE, 
password varchar2(50) CONSTRAINT nn_utilizador_pass NOT NULL, 
email varchar2(100) CONSTRAINT nn_utilizador_email NOT NULL
                    CONSTRAINT ck_utilizador_email CHECK(REGEXP_LIKE(email, '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$')), 
nome varchar2(30), 
PRIMARY KEY (id_utilizador));

CREATE TABLE "Cliente" 
(id_utilizador number(10) CONSTRAINT nn_cliente_id NOT NULL, 
cartao_credito varchar2(20) CONSTRAINT nn_cliente_cartao NOT NULL,
peso number(10, 2) CONSTRAINT nn_cliente_peso NOT NULL,
altura number(10, 2) CONSTRAINT nn_cliente_altura NOT NULL, 
velocidade_media number(10, 2) DEFAULT 0,
genero char(1), 
pontos number Default 0 CONSTRAINT nn_cliente_pontos NOT NULL,
PRIMARY KEY (id_utilizador));

CREATE TABLE"Administrador"
(id_utilizador number(10) CONSTRAINT nn_administrador_id NOT NULL,
PRIMARY KEY (id_utilizador));

CREATE TABLE "Parque" 
(id_parque varchar2(20), 
latitude number(10, 6) CONSTRAINT nn_parque_latitude NOT NULL,
longitude number(10, 6) CONSTRAINT nn_parque_longitude NOT NULL,
elevacao number(10) DEFAULT 0, 
descricao varchar2(255)  CONSTRAINT nn_parque_descricao NOT NULL, 
lot_bike number(10) CONSTRAINT nn_parque_lot_bike NOT NULL, 
lot_scooter number(10) CONSTRAINT nn_parque_lot_scooter NOT NULL,
voltagem number(10) CONSTRAINT nn_parque_voltagem NOT NULL, 
corrente number(10) CONSTRAINT nn_parque_corrente NOT NULL,
ativo char(1) DEFAULT 1, PRIMARY KEY (id_parque));

CREATE TABLE "TipoVeiculo" 
(id_tipo_veiculo number(10) CONSTRAINT nn_tipoVeiculo_id_tipo_veiculo NOT NULL,
designacao varchar2(255) CONSTRAINT nn_tipoVeiculo_desig NOT NULL,
PRIMARY KEY (id_tipo_veiculo));

CREATE TABLE "Veiculo"
(descricao varchar2(50),
id_tipo_veiculo number(10) CONSTRAINT nn_veiculo_id_tipo NOT NULL,
id_parque varchar2(20),
peso number(10) CONSTRAINT nn_veiculo_peso NOT NULL,
area_frontal number DEFAULT 0, 
coeficiente_aero number(10, 2) DEFAULT 0,
estado char(1) DEFAULT 0,
removido char(1) DEFAULT 0, 
tamanho number(10),
capacidade_max number,
carga_atual number(10), 
tipo varchar2(255), 
pot_motor number,
PRIMARY KEY (descricao));

CREATE TABLE "Viagem" 
(id_viagem number(10) GENERATED AS IDENTITY, 
id_utilizador number(10) CONSTRAINT nn_viagem_id_utilizador NOT NULL, 
descricao_veiculo varchar2(20) CONSTRAINT nn_viagem_id_veiculo NOT NULL, 
id_parque_inicio varchar2(20) CONSTRAINT nn_viagem_id_parque NOT NULL, 
id_parque_fim varchar2(20),
data_hora_inicio timestamp(0) CONSTRAINT nn_viagem_data_hora_inicio NOT NULL,
data_hora_fim timestamp(0), PRIMARY KEY (id_viagem));

CREATE TABLE "PontoInteresse" 
(latitude number(10, 6),
longitude number(10, 6), 
elevacao number(10) DEFAULT 0,
descricao varchar2(100), PRIMARY KEY (latitude, longitude));

CREATE TABLE "Caminho" 
(latitude_inicial number(10, 6),
longitude_inicial number(10, 6),
latitude_final number(10, 6), 
longitude_final number(10, 6),
coeficiente_cinetico number DEFAULT 0, 
direcao_vento number(10) DEFAULT 0,
velocidade_vento number(10) DEFAULT 0, PRIMARY KEY (latitude_inicial, longitude_inicial, latitude_final, longitude_final));

CREATE TABLE "FaturaViagem" 
(id_fatura number,
id_viagem number,
--data_emissao timestamp,
tempo_viagem_segundos number,
valor number(10,2),
pontos number,
PRIMARY KEY (id_viagem));

CREATE TABLE "Fatura"
(id_fatura number GENERATED AS IDENTITY,
username varchar2(20),
mes number,
pontos_anteriores number,
pontos_ganhos number,
pontos_descontados number,
pontos_atuais number,
valor_cobrado number,
valor_pago number,
PRIMARY KEY (id_fatura));

ALTER TABLE "Cliente" ADD CONSTRAINT FKCliente FOREIGN KEY (id_utilizador) REFERENCES "Utilizador" (id_utilizador);
ALTER TABLE "Administrador" ADD CONSTRAINT FK_Administrador FOREIGN KEY (id_utilizador) REFERENCES "Utilizador" (id_utilizador);
ALTER TABLE "Viagem" ADD CONSTRAINT FK_Viagem_Utilizador FOREIGN KEY (id_utilizador) REFERENCES "Cliente" (id_utilizador);
ALTER TABLE "Viagem" ADD CONSTRAINT FK_Viagem_Parque FOREIGN KEY (id_parque_inicio) REFERENCES "Parque" (id_parque);
ALTER TABLE "Viagem" ADD CONSTRAINT FK_Viagem_ParqueFim FOREIGN KEY (id_parque_fim) REFERENCES "Parque" (id_parque);
ALTER TABLE "Viagem" ADD CONSTRAINT FK_Viagem_Veiculo FOREIGN KEY (descricao_veiculo) REFERENCES "Veiculo" (descricao);
ALTER TABLE "Veiculo" ADD CONSTRAINT FK_Veiculo_Parque FOREIGN KEY (id_parque) REFERENCES "Parque" (id_parque);
ALTER TABLE "Veiculo" ADD CONSTRAINT FK_Veiculo_Tipo_Veiculo FOREIGN KEY (id_tipo_veiculo) REFERENCES "TipoVeiculo" (id_tipo_veiculo);
ALTER TABLE "Fatura" ADD CONSTRAINT FK_Fatura_Utilizador FOREIGN KEY (username) REFERENCES "Utilizador" (username);
ALTER TABLE "FaturaViagem" ADD CONSTRAINT FK_FaturaViagem_Viagem FOREIGN KEY (id_viagem) REFERENCES "Viagem" (id_viagem);
ALTER TABLE "FaturaViagem" ADD CONSTRAINT FK_FaturaViagem_Fatura FOREIGN KEY (id_fatura) REFERENCES "Fatura" (id_fatura);