package io.github.lucasfrancobn.gerenciador_estoque.dto.mapper

import io.github.lucasfrancobn.gerenciador_estoque.dto.CadastrarProdutoRequest
import io.github.lucasfrancobn.gerenciador_estoque.dto.ProdutoResponse
import io.github.lucasfrancobn.gerenciador_estoque.model.Produto

object ProdutoMapper {
    fun toProdutoResponse(produto: Produto): ProdutoResponse {
        return ProdutoResponse(
            produto.id,
            produto.nome,
            produto.quantidadeEstoque,
            produto.descricao,
            produto.preco,
            produto.categoria,
            produto.status,
            produto.dataCadastro,
            produto.dataAtualizacao
        )
    }

    fun toProduto(request: CadastrarProdutoRequest): Produto {
        return Produto(
            null,
            request.nome,
            request.quantidadeEstoque,
            request.descricao,
            request.preco,
            request.categoria,
            request.status
        )
    }
}