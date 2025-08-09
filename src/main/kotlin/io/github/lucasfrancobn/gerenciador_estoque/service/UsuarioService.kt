package io.github.lucasfrancobn.gerenciador_estoque.service

import io.github.lucasfrancobn.gerenciador_estoque.model.Usuario
import io.github.lucasfrancobn.gerenciador_estoque.repository.UsuarioRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class UsuarioService(
    private val repository: UsuarioRepository
) {

    @Transactional(readOnly = true)
    fun buscarUsuarioPorEmail(email: String): Optional<Usuario> {
        return repository.findByEmail(email)
    }
}