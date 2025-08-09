package io.github.lucasfrancobn.gerenciador_estoque.security

import io.github.lucasfrancobn.gerenciador_estoque.service.ClientService
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings
import org.springframework.stereotype.Component

@Component
class CustomRegisteredClientRepository(
    private val service: ClientService,
    private val tokenSettings: TokenSettings,
    private val clientSettings: ClientSettings
): RegisteredClientRepository {
    override fun save(registeredClient: RegisteredClient?) {
    }

    override fun findById(id: String?): RegisteredClient? {
        return null
    }

    override fun findByClientId(clientId: String?): RegisteredClient? {
        val client = service.obterClientPorClientId(clientId).orElse(null)

        if(client == null) return null

        val scopes = client.scope.split(" ").toSet()

        return RegisteredClient
            .withId(client.id!!.toString())
            .clientId(client.clientId)
            .clientSecret(client.clientSecret)
            .redirectUri(client.redirectURI)
            .scopes { it.addAll(scopes) }
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .tokenSettings(tokenSettings)
            .clientSettings(clientSettings)
            .build()
    }
}