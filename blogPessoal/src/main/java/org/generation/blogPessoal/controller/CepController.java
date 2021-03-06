package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.model.EnderecoModel;
import org.generation.blogPessoal.repository.ServicoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepController {

	    @Autowired
	    private ServicoInterface cepService;

	    @GetMapping("/{cep}")
	    public ResponseEntity<EnderecoModel> getCep(@PathVariable String cep) {

	        EnderecoModel endereco = cepService.buscaEnderecoPorCep(cep);

	        return endereco != null ? ResponseEntity.ok().body(endereco) : ResponseEntity.notFound().build(); 
	    }

}

