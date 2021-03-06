package org.generation.blogPessoal.repository;

import org.generation.blogPessoal.model.EnderecoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "https://viacep.com.br/ws/" , name = "viacep")
public interface ServicoInterface {
	@GetMapping("{cep}/json")
    EnderecoModel buscaEnderecoPorCep(@PathVariable("cep") String cep);

}
