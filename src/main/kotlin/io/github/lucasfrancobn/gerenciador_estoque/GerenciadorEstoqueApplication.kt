package io.github.lucasfrancobn.gerenciador_estoque

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GerenciadorEstoqueApplication

fun main(args: Array<String>) {
	runApplication<GerenciadorEstoqueApplication>(*args)
}
