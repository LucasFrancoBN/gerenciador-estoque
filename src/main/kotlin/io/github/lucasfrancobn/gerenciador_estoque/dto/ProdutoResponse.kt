package io.github.lucasfrancobn.gerenciador_estoque.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.github.lucasfrancobn.gerenciador_estoque.model.enums.CategoriaEnum
import io.github.lucasfrancobn.gerenciador_estoque.model.enums.StatusProdutoEnum
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ProdutoResponse(
    var id: UUID?,
    var nome: String,
    var quantidadeEstoque: Long,
    var descricao: String,
    var preco: BigDecimal,
    var categoria: CategoriaEnum,
    var status: StatusProdutoEnum,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    var dataCriacao: LocalDateTime?,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    var dataAtualizacao: LocalDateTime?,

)
