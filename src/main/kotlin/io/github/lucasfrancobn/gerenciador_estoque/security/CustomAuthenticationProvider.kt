package io.github.lucasfrancobn.gerenciador_estoque.security

import io.github.lucasfrancobn.gerenciador_estoque.exception.UsuarioNaoEncontrado
import io.github.lucasfrancobn.gerenciador_estoque.service.UsuarioService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    private val passwordEncoder: PasswordEncoder,
    private val service: UsuarioService
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication? {
        if (authentication == null) throw RuntimeException("Ocorreu um erro ao logar no sistema")

        val email = authentication.name
        val senha = authentication.credentials.toString()

        val usuario = service.buscarUsuarioPorEmail(email).orElseThrow { UsuarioNaoEncontrado("Usuário e/ou senha incorretos") }
        val senhasValidas = passwordEncoder.matches(senha, usuario.senha)

        if(senhasValidas) return CustomAuthentication(usuario)

        throw UsuarioNaoEncontrado("Usuário e/ou senha incorretos")
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.isAssignableFrom(UsernamePasswordAuthenticationToken::class.java) ?: false
    }
}