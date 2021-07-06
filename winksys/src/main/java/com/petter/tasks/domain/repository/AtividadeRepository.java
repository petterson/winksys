package com.petter.tasks.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.petter.tasks.domain.model.Atividade;

//////CLASSE ATIVIDADEREPOSITÓRIO RESPONSÁVEL POR UTILIZAR SERVIÇOS DA INTERFACE JPAREPOSITORY

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{

	//////BUSCA ATIVIDADE PELA  DESCRIÇÃO 
	
	List<Atividade> findByDescricao(String descricao);
	
	//////////BUSCA O ID DO FUNCIONARIO EM RELAÇÃO COM ATIVIDADE

	@Query("select f.id from Atividade a inner join a.funcionario f where f.id = :funcionarioId")
    Long findByIdDoFuncionario(Long funcionarioId);
}

