package io.github.lucasfrancobn.gerenciador_estoque.security

import io.github.lucasfrancobn.gerenciador_estoque.model.Usuario
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class CustomAuthentication(
    val usuario: Usuario
): Authentication {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return usuario.roles
    }

    override fun getCredentials(): Any? {
        return null
    }

    override fun getDetails(): Any? {
        return usuario
    }

    override fun getPrincipal(): Any? {
        return usuario
    }

    override fun isAuthenticated(): Boolean {
        return true
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
    }

    override fun getName(): String? {
        return usuario.email
    }
}