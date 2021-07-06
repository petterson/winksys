package com.petter.tasks.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.petter.tasks.domain.exception.CadastroFuncionarioException;
import com.petter.tasks.domain.model.Funcionario;
import com.petter.tasks.domain.repository.AtividadeRepository;
import com.petter.tasks.domain.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;

////CLASSE FUNCIONARIO SERVICE SERE PARA EFETUAR TRANZAÇÕES COM BANCO DE DADOS

@AllArgsConstructor
@Service
public class FuncionarioService {

	private AtividadeRepository ar;
	private FuncionarioRepository fr;
	
	////////////////LISTA TODOS OS FUNCIONARIOS//////////////
	
	@Transactional
	public List<Funcionario> listar(){	
		return fr.findAll();
	}
	
	////////////////////SALVA O FUNCIONARIO///////////////////////
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		boolean cpfEmUso = fr.findByCpf(funcionario.getCpf()).stream()
				.anyMatch(funcionarioExistente -> !funcionarioExistente.equals(funcionario));
		if(cpfEmUso) {
			throw new CadastroFuncionarioException("Já existe Funcionário com esse CPF");
		}
		return fr.save(funcionario);
	}
	
	@Transactional
	public Funcionario atualizar(Funcionario funcionario) {
		return fr.save(funcionario);
	}
	
	////////////////////EXCLUI O FUNCIONARIO PELO ID/////////////////////
	
	@Transactional
	public void excluir(Long funcionarioId) {
		if(this.verificaFuncionarioEmUso(funcionarioId)) {
			throw new CadastroFuncionarioException("Existe Funcionário Na tabela Atividade com esse ID " + funcionarioId);
		}
		fr.deleteById(funcionarioId);
	}
	
	//////////VERIFICA SE O FUNCIONARIO ESTÁ NA TABELA ATIVIDADE///////////////
	
	@Transactional
	public Boolean verificaFuncionarioEmUso(Long funcionarioId){
		Integer id = 0;
		try {
		   id = Integer.valueOf(ar.findByIdDoFuncionario(funcionarioId).toString());
		}catch (Exception e) {
			id=0;
		}
		   System.out.println(id);
		   if(id == 0)
			   return false;
			   
		return true;
	}
	
	/////////////////////////BUSCA O FUNCIONARIO POR ID///////////////////////
	
	@Transactional
	public Optional<Funcionario> findByFuncionarioPorId(Long funcionarioId) {
		return fr.findById(funcionarioId);
	}
	
	//////////////VERIFICA SE ID DO FUNCIONARIO EXISTE/////////////////
	
	@Transactional
	public boolean existsById(Long funcionarioId) {
		return fr.existsById(funcionarioId);
	}
	
	///////////////////BUSCA O FUNCIONARIO PARA CADASTRAR COM A ATIVIDADE/////////////////
	
	@Transactional
	public Funcionario getFuncionarioParaAtividade(String nomeFuncionario) {
		return fr.findByNome(nomeFuncionario);
	}
	
///////////////////BUSCA O NOME DO FUNCIONARIO PARA LISTAR NA ////////////////
	
	@Transactional
	public String nomeFuncionario(Long funcionarioId) {
	return fr.findByNomePorId(funcionarioId);
	}
	
///////////////////RETORNA ALISTA DE NOMES DOS FUNCIONÁRIOS ////////////////
	
	public List<String> getListNameFuncionarios(){
		return fr.findByFuncionarioNomes();
	}
	
	
}
