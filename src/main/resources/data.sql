-- client
INSERT INTO tb_client (id, client_id, client_secret, redirect_uri, scope)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'meuClient', '$2a$10$/yS0vgwkCm3h6PCMAwMA6eKMeqEuyEuU9hWjjjUjKVwjcYQBOEh.y', 'http://localhost:8080/authorized', 'read write');

-- usuario 1 | jose@123
INSERT INTO tb_usuario (id, email, senha, ativo)
VALUES ('550e8400-e29b-41d4-a716-446655440005', 'jose@email.com', '$2a$10$AljoMmmonnbmKTDrObjqQexBiUpYaoYnBLJ9fdugL9PaaV0unE6Yu', true);

-- usuario 1 | mario@123
INSERT INTO tb_usuario (id, email, senha, ativo)
VALUES ('550e8400-e29b-41d4-a716-446655440006', 'mario@email.com', '$2a$10$ZZ9oaxk5gFtZZO5n7pTU1.iTFrRSM1g62X3PNbHVr4iPTq.qY.qPi', true);

-- role
INSERT INTO tb_role (id, autoridade, nome_autoridade, descricao)
VALUES ('550e8400-e29b-41d4-a716-446655440010', 'ADMIN', 'Administrador', 'Admin com acesso completo ao sistema');

-- role
INSERT INTO tb_role (id, autoridade, nome_autoridade, descricao)
VALUES ('550e8400-e29b-41d4-a716-446655440011', 'OPERADOR', 'Operador', 'Operador com acesso simples ao sistema');


INSERT INTO tb_usuario_role (usuario_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440010');

INSERT INTO tb_usuario_role (usuario_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440011');

INSERT INTO tb_usuario_role (usuario_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440011');

