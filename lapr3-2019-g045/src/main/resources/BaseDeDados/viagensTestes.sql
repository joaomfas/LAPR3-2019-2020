delimiter /

ALTER SESSION SET NLS_DATE_FORMAT = 'YY.MM.DD HH24:MI:SS'/
DECLARE

    id_utilizador_A     "Utilizador".id_utilizador%type       := 1;

    id_utilizador_B     "Utilizador".id_utilizador%type       := 2;

    id_utilizador_C     "Utilizador".id_utilizador%type       := 3;

    id_utilizador_D     "Utilizador".id_utilizador%type       := 4;

    id_utilizador_E     "Utilizador".id_utilizador%type       := 5;

   

    scooterA      "Veiculo".descricao%type                    := 'scooterA';

    scooterB      "Veiculo".descricao%type                    := 'scooterB';

    scooterC      "Veiculo".descricao%type                    := 'scooterC';

    scooterD      "Veiculo".descricao%type                    := 'scooterD';

    scooterE      "Veiculo".descricao%type                    := 'scooterE';

    scooterF      "Veiculo".descricao%type                    := 'scooterF';

   

    bikeA      "Veiculo".descricao%type                       := 'bikeA';

    bikeB      "Veiculo".descricao%type                       := 'bikeB';

    bikeC      "Veiculo".descricao%type                       := 'bikeC';

    bikeD      "Veiculo".descricao%type                       := 'bikeD';

    bikeE      "Veiculo".descricao%type                       := 'bikeE';

    bikeF      "Veiculo".descricao%type                       := 'bikeF';

   

    id_parque_A         "Parque".id_parque%type               := 'Trindade';

    id_parque_B         "Parque".id_parque%type               := 'Ribeira';

    id_parque_C         "Parque".id_parque%type               := 'CasteloQueijo';

    id_parque_D         "Parque".id_parque%type               := 'Dummy';

   

    

    hora_inicio_viagem  "Viagem".data_hora_inicio%type        := sysdate;

    hora_fim_viagem     "Viagem".data_hora_fim%type           := sysdate;

   

BEGIN

    -- Novembro

 

    hora_inicio_viagem := TO_DATE('19.11.05 10:34:31');

    hora_fim_viagem    := TO_DATE('19.11.05 10:43:01');

    addViagem(id_utilizador_D, scooterC, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterC, id_parque_B, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.11.05 10:45:31');

    hora_fim_viagem    := TO_DATE('19.11.05 12:50:01');

    addViagem(id_utilizador_D, scooterC, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterC, id_parque_B, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.11.05 12:41:31');

    hora_fim_viagem    := TO_DATE('19.11.05 13:14:01');

    addViagem(id_utilizador_D, scooterC, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterC, id_parque_B, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.11.17 14:34:31');

    hora_fim_viagem    := TO_DATE('19.11.17 14:53:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.11.17 17:45:31');

    hora_fim_viagem    := TO_DATE('19.11.17 21:50:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.11.17 12:41:31');

    hora_fim_viagem    := TO_DATE('19.11.17 15:14:01');

    addViagem(id_utilizador_D, scooterA, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_B, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.11.23 14:01:31');

    hora_fim_viagem    := TO_DATE('19.11.23 14:53:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.11.23 17:15:31');

    hora_fim_viagem    := TO_DATE('19.11.23 21:50:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.11.23 12:01:31');

    hora_fim_viagem    := TO_DATE('19.11.23 15:14:01');

    addViagem(id_utilizador_D, scooterA, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_B, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    

    -- Dezembro

 

    hora_inicio_viagem := TO_DATE('19.12.01 10:31:31');

    hora_fim_viagem    := TO_DATE('19.12.01 10:40:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.01 10:41:31');

    hora_fim_viagem    := TO_DATE('19.12.01 12:50:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.01 12:41:31');

    hora_fim_viagem    := TO_DATE('19.12.01 13:10:01');

    addViagem(id_utilizador_D, scooterA, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_B, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.02 10:31:31');

    hora_fim_viagem    := TO_DATE('19.12.02 10:40:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.02 10:41:31');

    hora_fim_viagem    := TO_DATE('19.12.02 12:50:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.02 12:41:31');

    hora_fim_viagem    := TO_DATE('19.12.02 13:10:01');

    addViagem(id_utilizador_D, scooterA, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_B, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

 

    hora_inicio_viagem := TO_DATE('19.12.03 10:34:31');

    hora_fim_viagem    := TO_DATE('19.12.03 10:43:01');

    addViagem(id_utilizador_D, scooterC, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterC, id_parque_B, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.03 10:45:31');

    hora_fim_viagem    := TO_DATE('19.12.03 12:50:01');

    addViagem(id_utilizador_D, scooterC, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterC, id_parque_B, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.03 12:41:31');

    hora_fim_viagem    := TO_DATE('19.12.03 13:14:01');

    addViagem(id_utilizador_D, scooterC, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterC, id_parque_B, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.05 10:34:31');

    hora_fim_viagem    := TO_DATE('19.12.05 10:43:01');

    addViagem(id_utilizador_D, scooterC, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterC, id_parque_B, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.05 10:45:31');

    hora_fim_viagem    := TO_DATE('19.12.05 12:50:01');

    addViagem(id_utilizador_D, scooterC, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterC, id_parque_B, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.05 12:41:31');

    hora_fim_viagem    := TO_DATE('19.12.05 13:14:01');

    addViagem(id_utilizador_D, scooterC, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterC, id_parque_B, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.17 14:34:31');

    hora_fim_viagem    := TO_DATE('19.12.17 14:53:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.17 17:45:31');

    hora_fim_viagem    := TO_DATE('19.12.17 21:50:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.17 12:41:31');

    hora_fim_viagem    := TO_DATE('19.12.17 15:14:01');

    addViagem(id_utilizador_D, scooterA, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_B, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.23 14:01:31');

    hora_fim_viagem    := TO_DATE('19.12.23 14:53:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.23 17:15:31');

    hora_fim_viagem    := TO_DATE('19.12.23 21:50:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('19.12.23 12:01:31');

    hora_fim_viagem    := TO_DATE('19.12.23 15:14:01');

    addViagem(id_utilizador_D, scooterA, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_B, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

  

 

    -- Janeiro

   

    hora_inicio_viagem := TO_DATE('20.01.01 10:31:31');

    hora_fim_viagem    := TO_DATE('20.01.01 10:40:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('20.01.01 10:41:31');

    hora_fim_viagem    := TO_DATE('20.01.01 12:50:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('20.01.01 12:41:31');

    hora_fim_viagem    := TO_DATE('20.01.01 13:10:01');

    addViagem(id_utilizador_D, scooterA, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_B, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('20.01.02 10:31:31');

    hora_fim_viagem    := TO_DATE('20.01.02 10:40:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('20.01.02 10:41:31');

    hora_fim_viagem    := TO_DATE('20.01.02 12:50:01');

    addViagem(id_utilizador_D, scooterA, id_parque_A, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_A, id_parque_B, hora_inicio_viagem, hora_fim_viagem);

   

    hora_inicio_viagem := TO_DATE('20.01.02 12:41:31');

    hora_fim_viagem    := TO_DATE('20.01.02 13:10:01');

    addViagem(id_utilizador_D, scooterA, id_parque_B, hora_inicio_viagem);

    atualizaViagem(id_utilizador_D, scooterA, id_parque_B, id_parque_A, hora_inicio_viagem, hora_fim_viagem);

END;
/
