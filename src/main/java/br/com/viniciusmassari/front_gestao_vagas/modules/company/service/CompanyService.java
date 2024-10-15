package br.com.viniciusmassari.front_gestao_vagas.modules.company.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
