package com.petter.tasks.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.petter.tasks.api.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

///////CLASSE ENTITY USANDO BIBLIOTECA lombok PARA ENCAPISULA GETTER SETTER E CONSTRUTORS
///////E BEAN VALIDATION PARA VALIDAR DADOS

@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Funcionario {
	
	@NotNull(groups = ValidationGroups.FuncionarioId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	@Size(max = 50)
	private String nome;
	
	@NotNull
	@NotBlank
	@Size(max = 20)
	private String cpf;
	
	@NotNull
	@NotBlank
	@Size(max = 50)
	private String cargo;

}
