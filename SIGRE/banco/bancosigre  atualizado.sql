-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 13-Out-2018 às 20:28
-- Versão do servidor: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bancosigre`
--

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`id`, `descricao`, `nome`) VALUES
(1, NULL, 'Bebida'),
(2, NULL, 'Lanche');

--
-- Extraindo dados da tabela `compra`
--

INSERT INTO `compra` (`id`, `dataCompra`, `observacao`, `valorTotalItensCompra`, `fornecedor_id`) VALUES
(1, '2018-10-09', 'Compra A vista', NULL, 1);

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`id`, `email`, `fone`, `nome`, `cnpj`) VALUES
(1, 'marta@gmail.com', '44 888701054', 'Marta', NULL),
(2, 'antonio@gmail.com', '44 985201477', 'Antonio', NULL);

--
-- Extraindo dados da tabela `itenscompra`
--

INSERT INTO `itenscompra` (`id`, `quantidadeItens`, `valorTotalItens`, `valorUnitarioItens`, `compra_id`, `produto_id`) VALUES
(1, 10, 20, 2, 1, 2);

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id`, `descricao`, `nome`, `quantidadeEstoque`, `valorVenda`, `categoria_id`) VALUES
(1, NULL, 'Refrigerante', NULL, NULL, 1),
(2, NULL, 'Cerveja', NULL, NULL, 1),
(3, NULL, 'X - Salada', NULL, NULL, 2);

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `fone`, `nome`, `enable`, `perfil`, `senha`) VALUES
(1, 'jose@gmail.com', '44 999871532', 'José', b'1', 'ADMINISTRADOR', '123'),
(2, 'maria@gmail.com', '44 998741562', 'Maria', b'1', 'ATENDENTE', '123');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
