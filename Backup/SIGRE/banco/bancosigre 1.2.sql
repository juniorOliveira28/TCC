-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 03-Nov-2018 às 13:28
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

-- --------------------------------------------------------

--
-- Estrutura da tabela `caixa`
--

CREATE TABLE `caixa` (
  `id` bigint(20) NOT NULL,
  `troco` double DEFAULT NULL,
  `valorPago` double DEFAULT NULL,
  `valorTotal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`id`, `descricao`, `nome`) VALUES
(1, NULL, 'Bebida');

-- --------------------------------------------------------

--
-- Estrutura da tabela `compra`
--

CREATE TABLE `compra` (
  `id` bigint(20) NOT NULL,
  `dataCompra` date DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `valorTotalCompra` double DEFAULT NULL,
  `caixa_id` bigint(20) DEFAULT NULL,
  `fornecedor_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fone` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `cnpj` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`id`, `email`, `fone`, `nome`, `cnpj`) VALUES
(1, 'antonio@gmail.com', '(44) 98785-1059', 'Antonio', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `itenscompra`
--

CREATE TABLE `itenscompra` (
  `id` bigint(20) NOT NULL,
  `quantidadeItens` double DEFAULT NULL,
  `valorTotalItens` double DEFAULT NULL,
  `valorUnitarioItens` double DEFAULT NULL,
  `compra_id` bigint(20) DEFAULT NULL,
  `produto_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `itenspedido`
--

CREATE TABLE `itenspedido` (
  `id` bigint(20) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valorTotal` double DEFAULT NULL,
  `valorUnitario` double DEFAULT NULL,
  `pedido_id` bigint(20) DEFAULT NULL,
  `produto_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `itensprato`
--

CREATE TABLE `itensprato` (
  `id` bigint(20) NOT NULL,
  `quantidadeItens` double DEFAULT NULL,
  `valorTotal` double DEFAULT NULL,
  `valorUnitario` double DEFAULT NULL,
  `pedido_id` bigint(20) DEFAULT NULL,
  `prato_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `mesa`
--

CREATE TABLE `mesa` (
  `id` bigint(20) NOT NULL,
  `numero` int(11) NOT NULL,
  `qtdLugaress` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `caixa_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `mesa`
--

INSERT INTO `mesa` (`id`, `numero`, `qtdLugaress`, `status`, `caixa_id`) VALUES
(1, 1, 5, 'LIVRE', NULL),
(2, 2, 5, 'LIVRE', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

CREATE TABLE `pedido` (
  `id` bigint(20) NOT NULL,
  `dataPedido` date DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `valorTotal` double DEFAULT NULL,
  `valorUnitario` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `prato`
--

CREATE TABLE `prato` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `valorVenda` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `quantidadeEstoque` double DEFAULT NULL,
  `valorVenda` double DEFAULT NULL,
  `categoria_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id`, `descricao`, `nome`, `quantidadeEstoque`, `valorVenda`, `categoria_id`) VALUES
(1, 'Coca Cola litro', 'Coca Cola 1L', 0, 6, 1),
(2, 'Cerveja lata', 'Cerveja 350ML', 0, 4, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fone` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `perfil` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `fone`, `nome`, `enable`, `perfil`, `senha`) VALUES
(1, 'jose@gmail.com', '(44) 98988-8898', 'José', b'1', 'ADMINISTRADOR', '123'),
(2, 'maria@gmail.com', '(44) 99820-5656', 'Maria', b'1', 'ATENDENTE', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `caixa`
--
ALTER TABLE `caixa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_bf5dl9yx7w5ky1ad5kl7a5w4g` (`caixa_id`),
  ADD KEY `FK_c8evtl84j502sr0f0aop8s8ul` (`fornecedor_id`);

--
-- Indexes for table `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `itenscompra`
--
ALTER TABLE `itenscompra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ppy73s9fn4ntl77ouv8qj8cs` (`compra_id`),
  ADD KEY `FK_8h32sofoayw68qeykhmv82cdd` (`produto_id`);

--
-- Indexes for table `itenspedido`
--
ALTER TABLE `itenspedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_e8j9rv6qfb8jjitg8d3d8gg2o` (`pedido_id`),
  ADD KEY `FK_oxo5mhwro6ao7tstihf1igxf6` (`produto_id`);

--
-- Indexes for table `itensprato`
--
ALTER TABLE `itensprato`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ev9mmd1my6dgvv38lxesjk41s` (`pedido_id`),
  ADD KEY `FK_7wbr0ag3kbvc0dh41xec9lcwi` (`prato_id`);

--
-- Indexes for table `mesa`
--
ALTER TABLE `mesa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_35lx5gwtqnbl1hc7n4l4ls30c` (`caixa_id`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prato`
--
ALTER TABLE `prato`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_7roeds87qp6pp2g07rv86t8cb` (`categoria_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `caixa`
--
ALTER TABLE `caixa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `compra`
--
ALTER TABLE `compra`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `itenscompra`
--
ALTER TABLE `itenscompra`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `itenspedido`
--
ALTER TABLE `itenspedido`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `itensprato`
--
ALTER TABLE `itensprato`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mesa`
--
ALTER TABLE `mesa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `prato`
--
ALTER TABLE `prato`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `FK_bf5dl9yx7w5ky1ad5kl7a5w4g` FOREIGN KEY (`caixa_id`) REFERENCES `caixa` (`id`),
  ADD CONSTRAINT `FK_c8evtl84j502sr0f0aop8s8ul` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`);

--
-- Limitadores para a tabela `itenscompra`
--
ALTER TABLE `itenscompra`
  ADD CONSTRAINT `FK_8h32sofoayw68qeykhmv82cdd` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`),
  ADD CONSTRAINT `FK_ppy73s9fn4ntl77ouv8qj8cs` FOREIGN KEY (`compra_id`) REFERENCES `compra` (`id`);

--
-- Limitadores para a tabela `itenspedido`
--
ALTER TABLE `itenspedido`
  ADD CONSTRAINT `FK_e8j9rv6qfb8jjitg8d3d8gg2o` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `FK_oxo5mhwro6ao7tstihf1igxf6` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`);

--
-- Limitadores para a tabela `itensprato`
--
ALTER TABLE `itensprato`
  ADD CONSTRAINT `FK_7wbr0ag3kbvc0dh41xec9lcwi` FOREIGN KEY (`prato_id`) REFERENCES `prato` (`id`),
  ADD CONSTRAINT `FK_ev9mmd1my6dgvv38lxesjk41s` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`);

--
-- Limitadores para a tabela `mesa`
--
ALTER TABLE `mesa`
  ADD CONSTRAINT `FK_35lx5gwtqnbl1hc7n4l4ls30c` FOREIGN KEY (`caixa_id`) REFERENCES `caixa` (`id`);

--
-- Limitadores para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `FK_7roeds87qp6pp2g07rv86t8cb` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
