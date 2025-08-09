package io.github.lucasfrancobn.gerenciador_estoque.repository

import io.github.lucasfrancobn.gerenciador_estoque.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface ClientRepository: JpaRepository<Client, UUID> {
    fun findByClientId(clientId: String?): Optional<Client>
}