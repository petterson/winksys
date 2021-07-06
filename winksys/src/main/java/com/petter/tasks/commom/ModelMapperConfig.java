package com.petter.tasks.commom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

///////////REPRESENTA BIBLIOTECA RESPONSÁVEL POR CONVERTER CLASSES MODELOS
///////////EM FRONT-END, DE FORMA A CONTROLAR A VISIBILIDADE DOS ATRIBUTOS

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapperConfig modelMapper() {
		return new ModelMapperConfig();
	}

}
