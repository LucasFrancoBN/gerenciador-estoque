package io.github.lucasfrancobn.gerenciador_estoque.model

import jakarta.persistence.*
import java.util.Arrays
import java.util.UUID

@Entity
@Table(name = "tb_usuario")
class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val senha: String,

    @Column(nullable = false)
    val ativo: Boolean,

    @ManyToMany
    @JoinTable(
        name = "tb_usuario_role",
        joinColumns = [JoinColumn(name = "usuario_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: Set<Role>

)
