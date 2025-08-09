package io.github.lucasfrancobn.gerenciador_estoque.config

import io.github.lucasfrancobn.gerenciador_estoque.security.JwtCustomAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
class ResourceServerConfiguration {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        filter: JwtCustomAuthenticationFilter
    ): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .formLogin { configurer -> configurer.loginPage("/login").permitAll() }
            .authorizeHttpRequests {
                it.requestMatchers("/login").permitAll()
                it.requestMatchers(HttpMethod.POST, "/api/v1/usuarios").permitAll()
                it.anyRequest().authenticated()
            }
            .oauth2ResourceServer { oauth2Rs -> oauth2Rs.jwt(Customizer.withDefaults()) }
            .addFilterAfter(filter, BearerTokenAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val authoritiesConverter = JwtGrantedAuthoritiesConverter()
        authoritiesConverter.setAuthorityPrefix("")

        val converter = JwtAuthenticationConverter()
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter)

        return converter
    }

    @Bean
    fun grantedAuthorityDefaults(): GrantedAuthorityDefaults = GrantedAuthorityDefaults("")

}