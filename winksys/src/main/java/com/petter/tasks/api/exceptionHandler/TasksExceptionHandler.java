package com.petter.tasks.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.petter.tasks.domain.exception.CadastroFuncionarioException;
import lombok.AllArgsConstructor;

///////////CLASSE PRINCIPAL IMPLEMENTAS OS METODOS QUE CONTROLAN AS EXCEÇÕES

@AllArgsConstructor
@ControllerAdvice
public class TasksExceptionHandler extends ResponseEntityExceptionHandler{
	
	private MessageSource ms;
	
	////////////////METODO BASE UTILIZADO DE FORMA GLOBAL PARA LANÇAR EXCEÇÕES EM CADASTRO

	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Problema.Campo> campos = new ArrayList<>();
		
		for(ObjectError erro : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) erro).getField();
			String msg = ms.getMessage(erro, LocaleContextHolder.getLocale());
			
			campos.add(new Problema.Campo(nome, msg));
		}
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("Campo Inváidos, Preencha Corretamente");
		problema.setCampos(campos);
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(CadastroFuncionarioException.class)
	public ResponseEntity<Object> handleCadastroPessoaException(CadastroFuncionarioException ccx, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ccx.getMessage());
		return handleExceptionInternal(ccx, problema, new HttpHeaders(), status, request);
	}

}
