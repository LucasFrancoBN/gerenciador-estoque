package io.github.lucasfrancobn.gerenciador_estoque.model

import io.github.lucasfrancobn.gerenciador_estoque.model.enums.CategoriaEnum
import io.github.lucasfrancobn.gerenciador_estoque.model.enums.StatusProdutoEnum
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "tb_produto")
class Produto(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,

    @Column(nullable = false)
    var nome: String,

    @Column(name = "quantidade_estoque", nullable = false)
    var quantidadeEstoque: Long,

    @Column(nullable = false)
    var descricao: String,

    @Column(nullable = false)
    var preco: BigDecimal,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var categoria: CategoriaEnum,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: StatusProdutoEnum,
) {

    @Column(name = "data_cadastro", nullable = false)
    var dataCadastro: LocalDateTime? = null

    @Column(name = "data_atualizacao", nullable = false)
    var dataAtualizacao: LocalDateTime? = null

    @PrePersist
    fun prePresist() {
        val now = LocalDateTime.now()
        dataCadastro = now
        dataAtualizacao = now
    }

    @PostUpdate
    fun postUpdate() {
        dataAtualizacao = LocalDateTime.now()
    }

    fun atualizarProduto() {

    }
}
