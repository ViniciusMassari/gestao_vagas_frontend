package br.com.viniciusmassari.front_gestao_vagas.modules.company.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import br.com.viniciusmassari.front_gestao_vagas.modules.candidate.dto.Token;
import br.com.viniciusmassari.front_gestao_vagas.modules.company.dto.CreateCompanyDTO;

@Service
public class CompanyService {
    public String createCompany(CreateCompanyDTO createCompanyDTO) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateCompanyDTO> request = new HttpEntity<>(createCompanyDTO, headers);

        return rt.postForObject("http://localhost:8080/company/", request, String.class);
    }

    public Token loginCompany(String username, String password) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);

        var result = rt.postForObject("http://localhost:8080/company/auth", request, Token.class);

        System.out.println(result);

        return result;
    }
}
