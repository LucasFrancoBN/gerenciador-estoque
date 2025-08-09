package io.github.lucasfrancobn.gerenciador_estoque.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "tb_client")
class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,
    @Column(name = "client_id", unique = true, nullable = false)
     val clientId: String,

    @Column(name = "client_secret", nullable = false)
     val clientSecret: String,
    
    @Column(name = "redirect_uri", nullable = false)
     val redirectURI: String,

     val scope: String
)
