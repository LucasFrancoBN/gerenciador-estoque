package io.github.lucasfrancobn.gerenciador_estoque.security

import com.nimbusds.jose.proc.SecurityContext
import io.github.lucasfrancobn.gerenciador_estoque.model.Usuario
import org.springframework.security.core.context.SecurityContextHolder

object SecurityService {
    fun obterUsuarioLogado(): Usuario? {
        val auth = SecurityContextHolder.getContext().authentication

        if(auth is CustomAuthentication) {
            return auth.usuario
        }

        return null
    }
}