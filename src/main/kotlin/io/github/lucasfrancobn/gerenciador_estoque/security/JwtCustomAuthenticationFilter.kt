package io.github.lucasfrancobn.gerenciador_estoque.security

import io.github.lucasfrancobn.gerenciador_estoque.service.UsuarioService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtCustomAuthenticationFilter(
    private val service: UsuarioService
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var authentication = SecurityContextHolder.getContext().authentication

        if(!deveConverter(authentication)) {
            filterChain.doFilter(request, response)
            return
        }

        val email = authentication.name
        val usuarioBuscado = service.buscarUsuarioPorEmail(email)

        if(usuarioBuscado.isEmpty) {
            filterChain.doFilter(request, response)
            return
        }

        val usuario = usuarioBuscado.get()

        authentication = CustomAuthentication(usuario)
        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }

    private fun deveConverter(authentication: Authentication?): Boolean {
        return authentication != null && authentication is JwtAuthenticationToken
    }
}