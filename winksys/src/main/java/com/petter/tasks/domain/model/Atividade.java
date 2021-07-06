package com.petter.tasks.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.petter.tasks.api.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

///////CLASSE ENTITY USANDO BIBLIOTECA lombok PARA ENCAPISULA GETTER SETTER E CONSTRUTORS
///////E BEAN VALIDATION PARA VALIDAR DADOS, TAMBÉM USA PROPRIEDADES JSON NOS ATRIBUTOS

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Atividade {
	
	@NotNull(groups = ValidationGroups.AtividadeId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	@Size(max = 250)
	private String descricao;
	
	@NotNull
	@NotBlank
	@Size(max = 20)
	private String status;
	
	@NotNull
	@NotBlank
	@Size(max = 20)
	private String prioridade;
	
	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(pattern="yyyy/MM/dd hh:mm")
	private OffsetDateTime dataCadastro;
	
	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(pattern="yyyy/MM/dd hh:mm")
	private OffsetDateTime dataPrevista;

	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(pattern="yyyy/MM/dd hh:mm")
	private OffsetDateTime dataConclusao;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.FuncionarioId.class)
	@NotNull
	@ManyToOne
	private Funcionario funcionario;
}
