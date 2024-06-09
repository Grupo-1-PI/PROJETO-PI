insert into sistema (id, nome)
values
(1, 'Sistema de Gerenciamento de Projetos'), (2, 'Sistema de Vendas'), (3, 'Sistema de Controle de Estoque');

insert into tarefa (id, titulo, descricao, data_vencimento, status, sistema_id)
values
(1, 'Tarefa 1', 'Descrição da Tarefa 1', '2024-07-01', 'PENDENTE', 1),
(2, 'Tarefa 2', 'Descrição da Tarefa 2', '2024-07-15', 'EM_ANDAMENTO', 1),
(3, 'Tarefa 3', 'Descrição da Tarefa 3', '2024-06-20', 'CONCLUIDA', 2),
(4, 'Tarefa 4', 'Descrição da Tarefa 4', '2024-08-05', 'PENDENTE', 3);