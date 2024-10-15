package br.com.viniciusmassari.front_gestao_vagas.modules.company.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import br.com.viniciusmassari.front_gestao_vagas.modules.candidate.dto.JobDTO;
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

    public String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getDetails().toString();
    }

    public List<JobDTO> listAllJobsCompany(String token) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<JobDTO>> responseType = new ParameterizedTypeReference<List<JobDTO>>() {
        };

        var result = rt.exchange("http://localhost:8080/company/job/", HttpMethod.GET, httpEntity, responseType);
        return result.getBody();
    }
}
