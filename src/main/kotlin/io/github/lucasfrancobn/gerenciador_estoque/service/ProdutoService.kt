package io.github.lucasfrancobn.gerenciador_estoque.service

import io.github.lucasfrancobn.gerenciador_estoque.dto.AtualizarProdutoRequest
import io.github.lucasfrancobn.gerenciador_estoque.dto.CadastrarProdutoRequest
import io.github.lucasfrancobn.gerenciador_estoque.dto.ProdutoResponse
import io.github.lucasfrancobn.gerenciador_estoque.dto.mapper.ProdutoMapper.toProduto
import io.github.lucasfrancobn.gerenciador_estoque.dto.mapper.ProdutoMapper.toProdutoResponse
import io.github.lucasfrancobn.gerenciador_estoque.model.Produto
import io.github.lucasfrancobn.gerenciador_estoque.repository.ProdutoRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProdutoService(
    val repository: ProdutoRepository
) {
    fun cadastrarProduto(request: CadastrarProdutoRequest): ProdutoResponse {
        val produto = toProduto(request)

        val produtoSalvo = repository.save(produto)

        return toProdutoResponse(produtoSalvo)
    }

    fun buscarTodosProdutos(): List<ProdutoResponse> {
        return repository.findAll().map { toProdutoResponse(it) }
    }

    fun buscarProdutoPorId(id: UUID): ProdutoResponse {
        return repository.findById(id).map { toProdutoResponse(it) }
            .orElseThrow { RuntimeException("Produto não encontrado") }
    }

    fun atualizarProduto(id: UUID, request: AtualizarProdutoRequest): ProdutoResponse {
        val produto = repository.findById(id).orElseThrow { RuntimeException("Produto não encontrado") }
        produto.apply {
            nome = request.nome ?: produto.nome
            quantidadeEstoque = request.quantidadeEstoque ?: produto.quantidadeEstoque
            descricao = request.descricao ?: produto.descricao
            preco = request.preco ?: produto.preco
            categoria = request.categoria ?: produto.categoria
            status = request.status ?: produto.status
        }

        val produtoAtualizado = repository.save(produto)

        return toProdutoResponse(produtoAtualizado)
    }

    fun deletarProduto(id: UUID) {
        val produto = repository.findById(id).orElseThrow { RuntimeException("Produto não encontrado") }
        repository.delete(produto)
    }
}