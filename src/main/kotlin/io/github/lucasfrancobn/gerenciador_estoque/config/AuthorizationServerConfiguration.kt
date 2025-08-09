package io.github.lucasfrancobn.gerenciador_estoque.config

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import io.github.lucasfrancobn.gerenciador_estoque.security.CustomAuthentication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.time.Duration
import java.util.*


@Configuration
@EnableWebSecurity
class AuthorizationServerConfiguration {
    companion object {
        private const val STRENGTH_BCRYPT = 10
        private const val ACCESS_TOKEN_TIME_TO_LIVE = 60L
        private const val REFRESH_TOKEN_TIME_TO_LIVE = 60L
        private const val REQUIRE_CONSENT = false
        private const val ALGORITHM = "RSA"
        private const val KEY_INITIALIZE = 2048

    }

    @Bean
    @Order(1)
    fun authSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer.authorizationServer()

        http
            .securityMatcher(authorizationServerConfigurer.endpointsMatcher)
            .with(authorizationServerConfigurer) { it.oidc(Customizer.withDefaults()) }
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .formLogin { it.loginPage("/login") }
            .exceptionHandling { exceptions ->
                exceptions.defaultAuthenticationEntryPointFor(
                    LoginUrlAuthenticationEntryPoint("/login"),
                    MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                )
            }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(STRENGTH_BCRYPT)

    @Bean
    fun tokenSettings(): TokenSettings =
        TokenSettings.builder()
            .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
            .accessTokenTimeToLive(Duration.ofMinutes(ACCESS_TOKEN_TIME_TO_LIVE))
            .refreshTokenTimeToLive(Duration.ofMinutes(REFRESH_TOKEN_TIME_TO_LIVE))
            .build()

    @Bean
    fun clientSettings(): ClientSettings =
        ClientSettings.builder()
            .requireAuthorizationConsent(REQUIRE_CONSENT)
            .build()


    @Bean
    fun jwkSource(): JWKSource<SecurityContext> {
        val rsaKey = gerarChaveRsa()
        val jwkSet = JWKSet(rsaKey)
        return ImmutableJWKSet(jwkSet)
    }

    private fun gerarChaveRsa(): RSAKey {
        val keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM)
        keyPairGenerator.initialize(KEY_INITIALIZE)
        val keyPair = keyPairGenerator.generateKeyPair()

        val chavePublica = keyPair.public as RSAPublicKey
        val chavePrivada = keyPair.private as RSAPrivateKey

        return RSAKey
            .Builder(chavePublica)
            .privateKey(chavePrivada)
            .keyID(UUID.randomUUID().toString())
            .build()
    }

    @Bean
    fun jwtDecoder(jwkSource: JWKSource<SecurityContext>): JwtDecoder =
        OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource)


    @Bean
    fun authorizationServerSettings(): AuthorizationServerSettings =
        AuthorizationServerSettings.builder()
            .tokenEndpoint("/oauth2/token")
            .tokenIntrospectionEndpoint("/oauth2/introspect")
            .tokenRevocationEndpoint("oauth2/revoke")
            .authorizationEndpoint("/oauth2/authorize")
            .oidcUserInfoEndpoint("/oauth2/userinfo")
            .jwkSetEndpoint("/oauth2/jwks")
            .oidcLogoutEndpoint("/oauth2/logout")
            .build()


    @Bean
    fun tokenCustomizer(): OAuth2TokenCustomizer<JwtEncodingContext> =
        OAuth2TokenCustomizer { context ->
            val principal = context.getPrincipal<Authentication>()
            if (principal !is CustomAuthentication) return@OAuth2TokenCustomizer

            val tipoToken = context.tokenType
            if (OAuth2TokenType.ACCESS_TOKEN != tipoToken) return@OAuth2TokenCustomizer

            val authoritiesList: List<String> = principal.authorities!!.map{ it!!.authority }
            context.claims.claim("authorities", authoritiesList)
    }
}