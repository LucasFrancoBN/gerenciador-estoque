package io.github.lucasfrancobn.gerenciador_estoque.service

import io.github.lucasfrancobn.gerenciador_estoque.model.Client
import io.github.lucasfrancobn.gerenciador_estoque.repository.ClientRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ClientService(
    private val repository: ClientRepository
) {
    fun obterClientPorClientId(clientId: String?): Optional<Client> {
        return repository.findByClientId(clientId)
    }
}