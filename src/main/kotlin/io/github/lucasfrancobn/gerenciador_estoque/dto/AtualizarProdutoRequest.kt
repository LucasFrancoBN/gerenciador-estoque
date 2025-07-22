package io.github.lucasfrancobn.gerenciador_estoque.dto

import io.github.lucasfrancobn.gerenciador_estoque.model.enums.CategoriaEnum
import io.github.lucasfrancobn.gerenciador_estoque.model.enums.StatusProdutoEnum
import java.math.BigDecimal

data class AtualizarProdutoRequest(
    var nome: String?,
    var quantidadeEstoque: Long?,
    var descricao: String?,
    var preco: BigDecimal?,
    var categoria: CategoriaEnum?,
    var status: StatusProdutoEnum?
)
