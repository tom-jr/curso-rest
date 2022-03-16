CREATE  SEQUENCE  IF NOT EXISTS cozinha_id_seq
    AS bigint 
    INCREMENT BY 1
    MINVALUE 1 
    START  WITH 1;
	

CREATE TABLE cozinha (
id bigint DEFAULT nextval('cozinha_id_seq') PRIMARY KEY,
nome varchar(60) not null
);