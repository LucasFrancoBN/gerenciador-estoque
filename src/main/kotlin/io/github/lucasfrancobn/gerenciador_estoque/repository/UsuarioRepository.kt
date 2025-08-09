package io.github.lucasfrancobn.gerenciador_estoque.repository

import io.github.lucasfrancobn.gerenciador_estoque.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional
import java.util.UUID

interface UsuarioRepository: JpaRepository<Usuario, UUID> {
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.roles WHERE u.email = :email")
    fun findByEmail(@Param("email") email: String): Optional<Usuario>
}