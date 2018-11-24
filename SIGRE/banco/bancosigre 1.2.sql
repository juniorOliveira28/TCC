INSERT INTO `categoria` (`id`, `descricao`, `nome`) VALUES
(1, NULL, 'Bebida'),
(2, NULL, 'Lanche'),
(3, NULL, 'Porção');

-- --------------------------------------------------------

INSERT INTO `fornecedor` (`id`, `email`, `fone`, `nome`, `cnpj`) VALUES
(1, 'antonio@gmail.com', '(44) 98710-5412', 'Antonio', NULL),
(2, 'judite@gmail.com', '(44) 98410-7426', 'Judite', NULL);

-- --------------------------------------------------------

INSERT INTO `itenscompra` (`id`, `quantidadeItens`, `valorTotalItens`, `valorUnitarioItens`, `compra_id`, `produto_id`) VALUES
(1, 50, 100, 2, 1, 1),
(2, 50, 100, 2, 2, 4);

-- --------------------------------------------------------

INSERT INTO `itenspedido` (`id`, `quantidade`, `valorTotal`, `valorUnitario`, `pedido_id`, `produto_id`) VALUES
(1, 1, 5, NULL, 1, 1),
(2, 1, 10, NULL, 1, 2);

-- --------------------------------------------------------

INSERT INTO `mesa` (`id`, `numero`, `qtdLugaress`, `status`) VALUES
(1, 1, 5, 'OCUPADA'),
(2, 2, 5, 'LIVRE'),
(3, 3, 5, 'LIVRE'),
(4, 4, 5, 'LIVRE');

-- --------------------------------------------------------

INSERT INTO `produto` (`id`, `descricao`, `estoque`, `nome`, `quantidadeEstoque`, `valorVenda`, `categoria_id`) VALUES
(1, 'Coca litro', 'SIM', 'Coca Cola 1L', 49, 5, 1),
(2, 'Pão, tomate, alface, ovo', 'NÃO', 'X-Salada', 0, 10, 2),
(3, 'Batata frita', 'NÃO', 'Porção de Batata M', 0, 20, 3),
(4, 'Cerveja lata', 'SIM', 'Cerveja 250ML', 50, 5, 1);

-- --------------------------------------------------------

INSERT INTO `usuario` (`id`, `email`, `fone`, `nome`, `enable`, `perfil`, `senha`) VALUES
(1, 'jose@gmail.com', '(44) 99478-7448', 'José', b'1', 'ADMINISTRADOR', '123'),
(2, 'maria@gmail.com', '(44) 98818-2092', 'Maria', b'1', 'ATENDENTE', '123');
