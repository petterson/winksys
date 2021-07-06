package com.petter.tasks.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.petter.tasks.domain.exception.CadastroFuncionarioException;
import com.petter.tasks.domain.model.Atividade;
import com.petter.tasks.domain.repository.AtividadeRepository;
import lombok.AllArgsConstructor;

//////////CLASSE FUNCIONARIO SERVICE SERVE PARA FAZER TRANZAÇẼS COM O BANCO DE DADOS

@AllArgsConstructor
@Service
public class AtividadeService {

	private FuncionarioService fs;
	private AtividadeRepository ar;
	
	///////////////////LISTA TODAS AS ATIVIDADES////////////////////////////////
	
	@Transactional
	public List<Atividade> listar(){	
		return ar.findAll();
	}
	
	/////////////////SALVA AS ATIVIDADES////////////////////////////////////////
	
	@Transactional
	public Atividade salvar(Atividade atividade) {
		boolean descricaoEmUso = ar.findByDescricao(atividade.getDescricao()).stream()
				.anyMatch(funcionarioExistente -> !funcionarioExistente.equals(atividade));
		if(descricaoEmUso) {
			throw new CadastroFuncionarioException("Já existe Atividade com essa Descrição");
		}
		atividade.setDataCadastro(OffsetDateTime.now());
		atividade.setDataConclusao(atividade.getDataPrevista());
		System.out.println(atividade.getFuncionario().getNome());
		System.out.println(atividade.getDescricao());
		System.out.println(atividade.getStatus());
		System.out.println(atividade.getPrioridade());
		System.out.println(atividade.getDataCadastro());
		System.out.println(atividade.getDataPrevista());
		System.out.println(atividade.getDataConclusao());
		atividade.setFuncionario(fs.getFuncionarioParaAtividade(atividade.getFuncionario().getNome()));
		if(!fs.existsById(atividade.getFuncionario().getId())) {
			throw new CadastroFuncionarioException("Não Existe Funcionario com esse ID  " + atividade.getFuncionario().getId());
		}
		return ar.save(atividade);
	}
	
	///////////////////////EXCLUI AS ATIVIDADES/////////////////////////////////
	
	@Transactional
	public void excluir(Long atividadeId) {
		if(!this.existsById(atividadeId)) {
			throw new CadastroFuncionarioException("Não Existe Atividade com esse ID " + atividadeId);
		}
		ar.deleteById(atividadeId);
	}
	
	////////////////////////ATUALIZA A ATIVIDADE E A DATA FINAL/////////////////////////
	
	@Transactional
	public Atividade atualizar(Atividade atividade) {
		 if(atividade.getStatus().equals("CONCLUIDA")) {
			 atividade.setDataConclusao(OffsetDateTime.now());
		 }else if(atividade.getDataPrevista().isBefore(OffsetDateTime.now())){
                 atividade.setDataConclusao(atividade.getDataPrevista());
             }
		return ar.save(atividade);
	}
	
	/////////////BUSCA ATIVIDADE POR ID/////////////////////////////
	
	@Transactional
	public Optional<Atividade> findByAtividadePorId(Long atividadeId) {
		return ar.findById(atividadeId);
	}
	
	///////////////////VERIFICA ID ATIVIDADE/////////////////////////
	
	@Transactional
	public boolean existsById(Long atividadeId) {
		return ar.existsById(atividadeId);
	}
}
