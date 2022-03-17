-- desabilitando os triggers para não gerar erro ao deleter registros devido as FK

ALTER TABLE cidade DISABLE TRIGGER ALL;
ALTER TABLE cozinha DISABLE TRIGGER ALL;
ALTER TABLE estado DISABLE TRIGGER ALL;
ALTER TABLE forma_pagamento DISABLE TRIGGER ALL;
ALTER TABLE grupo DISABLE TRIGGER ALL;
ALTER TABLE item_pedido DISABLE TRIGGER ALL;
ALTER TABLE pedido DISABLE TRIGGER ALL;
ALTER TABLE permissao DISABLE TRIGGER ALL;
ALTER TABLE produto DISABLE TRIGGER ALL;
ALTER TABLE restaurante DISABLE TRIGGER ALL;
ALTER TABLE usuario DISABLE TRIGGER ALL;

-- resetando as sequences das tabelas

ALTER SEQUENCE cidade_id_seq RESTART WITH 1;
ALTER SEQUENCE cozinha_id_seq RESTART WITH 1;
ALTER SEQUENCE estado_id_seq RESTART WITH 1;
ALTER SEQUENCE forma_pagamento_id_seq RESTART WITH 1;
ALTER SEQUENCE grupo_id_seq RESTART WITH 1;
ALTER SEQUENCE item_pedido_id_seq RESTART WITH 1;
ALTER SEQUENCE pedido_id_seq RESTART WITH 1;
ALTER SEQUENCE permissao_id_seq RESTART WITH 1;
ALTER SEQUENCE produto_id_seq RESTART WITH 1;
ALTER SEQUENCE restaurante_id_seq RESTART WITH 1;
ALTER SEQUENCE usuario_id_seq RESTART WITH 1;

-- deletando dados das tabelas
delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

--reativando os triggers
ALTER TABLE cozinha ENABLE TRIGGER ALL;
ALTER TABLE cidade ENABLE TRIGGER ALL;
ALTER TABLE estado ENABLE TRIGGER ALL;
ALTER TABLE forma_pagamento ENABLE TRIGGER ALL;
ALTER TABLE grupo ENABLE TRIGGER ALL;
ALTER TABLE grupo_permissao ENABLE TRIGGER ALL;
ALTER TABLE permissao ENABLE TRIGGER ALL;
ALTER TABLE produto ENABLE TRIGGER ALL;
ALTER TABLE restaurante ENABLE TRIGGER ALL;
ALTER TABLE usuario ENABLE TRIGGER ALL;
ALTER TABLE usuario_grupo ENABLE TRIGGER ALL;


-- inserção dos dados
insert into cozinha (id, nome) values (default, 'Tailandesa');
insert into cozinha (id, nome) values (default, 'Indiana');
insert into cozinha (id, nome) values (default, 'Argentina');
insert into cozinha (id, nome) values (default, 'Brasileira');

insert into estado (id, nome) values (default, 'Minas Gerais');
insert into estado (id, nome) values (default, 'São Paulo');
insert into estado (id, nome) values (default, 'Ceará');

insert into cidade (id, nome, estado_id) values (default, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (default, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (default, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (default, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (default, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (default, 'Thai Gourmet', 10, 1, current_timestamp, current_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao) values (default, 'Thai Delivery', 9.50, 1, current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao) values (default, 'Tuk Tuk Comida Indiana', 15, 2, current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao) values (default, 'Java Steakhouse', 12, 3, current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao) values (default, 'Lanchonete do Tio Sam', 11, 4, current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao) values (default, 'Bar da Maria', 6, 4, current_timestamp, current_timestamp);

insert into forma_pagamento (id, descricao) values (default, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (default, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (default, 'Dinheiro');

insert into permissao (id, nome, descricao) values (default, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (default, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 6);


