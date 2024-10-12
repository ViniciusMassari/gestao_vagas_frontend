package br.com.viniciusmassari.front_gestao_vagas.modules.candidate.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.viniciusmassari.front_gestao_vagas.modules.candidate.dto.Token;

@Service
public class CandidateService {
    public Token login(String username, String password) {
        RestTemplate rt = new RestTemplate();

        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);

        // tipo do conteúdo Content-Type
        // HttpHeaders precisa vir de springframework
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // representação dos dados que iremos enviar
        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);

        // requisição
        var result = rt.postForObject("http://localhost:8080/candidate/auth", request, Token.class);

        return result;
    }
}
