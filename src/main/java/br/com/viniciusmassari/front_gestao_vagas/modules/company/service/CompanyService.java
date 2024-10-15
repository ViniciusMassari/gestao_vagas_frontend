package br.com.viniciusmassari.front_gestao_vagas.modules.company.service;

import org.springframework.beans.factory.annotation.Value;
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
import br.com.viniciusmassari.front_gestao_vagas.modules.company.dto.CreateJobsDTO;

@Service
public class CompanyService {

    @Value("${host.api.gestao.vagas}")
    private String hostAPIGestaoVagas;

    public String createCompany(CreateCompanyDTO createCompanyDTO) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateCompanyDTO> request = new HttpEntity<>(createCompanyDTO, headers);
        var url = hostAPIGestaoVagas.concat("/company/");

        return rt.postForObject(url, request, String.class);
    }

    public Token loginCompany(String username, String password) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);
        var url = hostAPIGestaoVagas.concat("/company/auth");

        var result = rt.postForObject(url, request, Token.class);

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

        var url = hostAPIGestaoVagas.concat("/company/job/");

        var result = rt.exchange(url, HttpMethod.GET, httpEntity, responseType);
        return result.getBody();
    }

    public String createJob(CreateJobsDTO jobs, String token) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<CreateJobsDTO> request = new HttpEntity<>(jobs, headers);

        var url = hostAPIGestaoVagas.concat("/company/job/");

        var result = rt.postForObject(url, request, String.class);

        return result;
    }
}
