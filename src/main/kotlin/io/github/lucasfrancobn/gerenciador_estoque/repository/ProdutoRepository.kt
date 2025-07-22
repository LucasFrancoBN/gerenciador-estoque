package io.github.lucasfrancobn.gerenciador_estoque.repository

import io.github.lucasfrancobn.gerenciador_estoque.model.Produto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProdutoRepository: JpaRepository<Produto, UUID> {
}