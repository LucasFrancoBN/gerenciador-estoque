package io.github.lucasfrancobn.gerenciador_estoque.controller

import io.github.lucasfrancobn.gerenciador_estoque.dto.AtualizarProdutoRequest
import io.github.lucasfrancobn.gerenciador_estoque.dto.CadastrarProdutoRequest
import io.github.lucasfrancobn.gerenciador_estoque.dto.ProdutoResponse
import io.github.lucasfrancobn.gerenciador_estoque.service.ProdutoService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder
import java.util.UUID

@RestController
@RequestMapping("/api/v1/produtos")
class ProdutoController(
    val service: ProdutoService
) {
    @PostMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'ADMIN')")
    fun cadastrarProduto(@RequestBody request: CadastrarProdutoRequest): ResponseEntity<ProdutoResponse> {
        val produtoCadastrado = service.cadastrarProduto(request)
        val uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(produtoCadastrado.id)
            .toUri()

        return ResponseEntity.created(uri).body(produtoCadastrado)
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'ADMIN')")
    fun obterTodosProdutos(): ResponseEntity<List<ProdutoResponse>> {
        val produtos = service.buscarTodosProdutos()
        return ResponseEntity.ok(produtos)
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'ADMIN')")
    fun obterProdutoPorId(@PathVariable id: UUID): ResponseEntity<ProdutoResponse> {
        val produtoBuscado = service.buscarProdutoPorId(id)
        return ResponseEntity.ok(produtoBuscado)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun  atualizarProduto(@PathVariable id: UUID, @RequestBody request: AtualizarProdutoRequest): ResponseEntity<ProdutoResponse> {
        val produtoBuscado = service.atualizarProduto(id, request)
        return ResponseEntity.ok(produtoBuscado)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deletarProduto(@PathVariable id: UUID): ResponseEntity<Void> {
        service.deletarProduto(id);
        return ResponseEntity.noContent().build()
    }
}