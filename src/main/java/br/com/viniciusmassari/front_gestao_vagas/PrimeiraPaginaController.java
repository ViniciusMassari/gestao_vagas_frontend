package br.com.viniciusmassari.front_gestao_vagas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PrimeiraPaginaController {
    @GetMapping("/home")
    public String primeiraPaginaHTML(Model model) {
        model.addAttribute("mensagemController", "Olá mundo");
        return "primeiraPagina";
    }

    @GetMapping("/login")
    public String loginHTML() {
        return "candidate/login";
    }

    @PostMapping("/create")
    public void cadastroCandidato(String nome_candidato) {
        System.out.println(nome_candidato);
    }
}
