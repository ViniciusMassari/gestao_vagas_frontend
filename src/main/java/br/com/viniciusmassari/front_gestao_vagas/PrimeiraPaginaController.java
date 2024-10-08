package br.com.viniciusmassari.front_gestao_vagas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PrimeiraPaginaController {
    @GetMapping("/home")
    public String primeiraPaginaHTML() {
        return "primeiraPagina";
    }

    @GetMapping("/login")
    public String loginHTML() {
        return "candidate/login";
    }
}
