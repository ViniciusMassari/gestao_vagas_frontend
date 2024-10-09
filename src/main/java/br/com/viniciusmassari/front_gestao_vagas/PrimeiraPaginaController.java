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
    public String cadastroCandidate(Model model, Pessoa pessoa) {
        System.out.println("Nome do candidato: " + pessoa.nome);
        System.out.println("Email do candidato: " + pessoa.email);
        System.out.println("Usuario do candidato: " + pessoa.usuario);

        model.addAttribute("pessoa", pessoa);

        return "candidate/info";
    }

    public record Pessoa(String usuario, String email, String nome) {
    }
}
