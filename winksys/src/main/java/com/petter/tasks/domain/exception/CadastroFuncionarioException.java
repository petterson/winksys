package com.petter.tasks.domain.exception;

///////////CLASSE UTILIZADA PARA MANDAR EXCEÇÕES EM TODOS OS CADASTROS

public class CadastroFuncionarioException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public CadastroFuncionarioException(String mensagem) {
		super(mensagem);
		
	}

}
