package com.petter.tasks.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.petter.tasks.domain.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	/////////BUSCA CPF DO FUNCIONARIO PARA VALIDAR CADASTRO DE FUNCIONARIO
	List<Funcionario> findByCpf(String cpf);
	
	///////////BUSCA FUNCIONARIO POR NOME
	Funcionario findByNome(String nome);
	
	////////BUSCA O NOME DO FUNCONARIO POR ID
	@Query("select f.nome from Funcionario f where f.id = :funcionarioId")
	String findByNomePorId(Long funcionarioId);
	
	/////////BUSCA TODOS OS NOME DOS FUNCIONARIOS
	@Query("select f.nome from Funcionario f")
	List<String> findByFuncionarioNomes();
}
