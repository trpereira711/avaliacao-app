INSERT INTO USUARIO (nome, senha)
VALUES ('admin', '$2a$10$sNov4BGjDqNcKiywoV5e1O3p7/fUr3NjWyUjSTZTeEj4MJhlEYkLG');

INSERT INTO USUARIO (nome, senha)
VALUES ('comum', '$2a$10$sNov4BGjDqNcKiywoV5e1O3p7/fUr3NjWyUjSTZTeEj4MJhlEYkLG');

INSERT INTO PERFIL (tipo) VALUES ('ADMIN');

INSERT INTO PERFIL (tipo) VALUES ('COMUM');

INSERT INTO USUARIO_PERFIS (usuario_id, perfis_id) VALUES (1, 1);

INSERT INTO USUARIO_PERFIS (usuario_id, perfis_id) VALUES (2, 2);

INSERT INTO ENDERECO (bairro, cep, cidade, complemento, logradouro, uf)
VALUES('Bairro', '77777777', 'Cidade', 'Complemento', 'Logradouro', 'DF');

INSERT INTO CLIENTE (cpf, nome, endereco_id)
VALUES('00000000000', 'Thiago Roberto Pereira', '1');

INSERT INTO TELEFONE (ddd, numero, tipo_telefone, cliente_id)
VALUES('99', '999999999', 'CELULAR', 1);

INSERT INTO EMAIL_CLIENTE (email, cliente_id)
VALUES('email@gmail.com', 1);