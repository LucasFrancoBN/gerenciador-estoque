package io.github.lucasfrancobn.gerenciador_estoque.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginViewController {
    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}