package br.org.serratec.ecommerce.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.ecommerce.api.ApiViaCep;

@Service
public class ApiViaCepService {
	public ApiViaCep consultaCep(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://viacep.com.br/ws/{cep}/json";

		Map<String, String> params = new HashMap<String, String>();

		params.put("cep", cep);

		ApiViaCep dto = restTemplate.getForObject(uri, ApiViaCep.class, params);
		if(dto.getLogradouro() == null) {
			throw new NullPointerException("O cep digitado não retorna um logradouro existente.");
		}
		return dto;
	}
}
