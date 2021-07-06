package com.petter.tasks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petter.tasks.domain.exception.CadastroFuncionarioException;
import com.petter.tasks.domain.model.Atividade;
import com.petter.tasks.domain.model.Funcionario;
import com.petter.tasks.domain.service.AtividadeService;
import com.petter.tasks.domain.service.FuncionarioService;

import lombok.AllArgsConstructor;

/////////CLASSE ATIVIDADE CONTROLER DEFINE OS ENDPOINTS DA  ATIVIDADE


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/atividades")
public class AtividadeController {

	private AtividadeService as;
	
	//////////////////LISTA TODAS AS ATIVIDADES
	
	@GetMapping
	public List<Atividade> listar() {
		return as.listar();
	}
	
	
	
	/////////////////BUSCA ATIVIDADE POR ID///////////////////////////////////
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Atividade> buscarAtividadePorId(@PathVariable("id") Long id) {		
		return as.findByAtividadePorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
   
	////////////////////ADICIONA UMA ATIVIDADE////////////////////////
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Atividade adicionar(@Valid @RequestBody Atividade atividade) {
	return this.as.salvar(atividade);
	}
	
	///////////////////////////ATUALIZA AS ATIVIDADES/////////////////////////////
	
	@PutMapping(path =  {"/{id}"})
	public ResponseEntity<Atividade> atualizar(@PathVariable("id") Long id, @Valid @RequestBody Atividade atividade) {
		if(!as.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		atividade.setId(id);
		atividade = as.atualizar(atividade);
		return ResponseEntity.ok(atividade);
	}
	
	///////////////EXCLUI AS ATIVIDADES////////////////////////////////////
	
	@DeleteMapping(path =  {"/{id}"})
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		if(!as.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		System.out.println("vish");
		as.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
