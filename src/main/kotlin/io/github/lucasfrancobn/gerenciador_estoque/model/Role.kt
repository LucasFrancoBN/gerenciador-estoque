package io.github.lucasfrancobn.gerenciador_estoque.model

import jakarta.persistence.*
import org.springframework.context.annotation.Description
import org.springframework.security.core.GrantedAuthority
import java.util.UUID

@Entity
@Table(name = "tb_role")
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,

    @Column(nullable = false)
    val autoridade: String,

    @Column(name = "nome_autoridade", nullable = false)
    val nomeAutoridade: String,

    @Column(nullable = false)
    val descricao: String

): GrantedAuthority {
    override fun getAuthority() = autoridade

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Role

        if (id != other.id) return false
        if (autoridade != other.autoridade) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + autoridade.hashCode()
        return result
    }
}
