package com.petter.tasks.api.controller;

import java.util.List;

/////////CLASSE ATIVIDADE CONTROLER DEFINE OS ENDPOINTS DOS FUNCIONARIOS

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
import com.petter.tasks.domain.model.Funcionario;
import com.petter.tasks.domain.service.FuncionarioService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	private FuncionarioService fs;
	
/////////BUSCA FUNCIONARIO POR ID
	
	@GetMapping(path =  {"/{id}"})
	public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable("id") Long id) {		
		return fs.findByFuncionarioPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
/////////BUSCA TODOS OS FUNCIONÁRIOS
	
	@GetMapping
	public List<Funcionario> listar() {
		return fs.listar();
	}
	
/////////LISTA NOME DOS FUNCIONÁRIOS UTILIZADOS NO SELECTBOX
	
	@GetMapping("/nomes")
	public List<String> getListNameFuncionarios(){
		System.out.println(fs.getListNameFuncionarios());
		return fs.getListNameFuncionarios();
	}
	
/////////ADICIONA NOVOS FUNCIONARIOS

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario adicionar(@Valid @RequestBody Funcionario f) {
	return this.fs.salvar(f);
	}
	
	//////////ATUALIZA OS FUNCIONARIOS
	
	@PutMapping(path =  {"/{id}"})
	public ResponseEntity<Funcionario> atualizar(@PathVariable("id") Long id, @Valid @RequestBody Funcionario funcionario) {
		if(!fs.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		funcionario.setId(id);
		funcionario = fs.atualizar(funcionario);
		return ResponseEntity.ok(funcionario);
		//return cr.findById(clienteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	/////////////REMOVE OS FUNCIONÁRIOS
	
	@DeleteMapping(path =  {"/{id}"})
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		if(!fs.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		fs.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
}
