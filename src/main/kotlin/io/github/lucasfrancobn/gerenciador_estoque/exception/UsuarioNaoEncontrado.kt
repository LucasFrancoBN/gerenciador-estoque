package io.github.lucasfrancobn.gerenciador_estoque.exception

class UsuarioNaoEncontrado(
    override val message: String
): RuntimeException(
    message
)