package Bravit.libraryapi.controller.common;

import Bravit.libraryapi.controller.dto.ErroCampo;
import Bravit.libraryapi.controller.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErroResposta handleRegistroDuplicadoException(MethodArgumentNotValidException e) {
//        System.out.println("Exception: " + e.getMessage());
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrors
                .stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
//        return ErroResposta.respostaPadrao("Erro validação");
        return new ErroResposta(HttpStatus.UNPROCESSABLE_CONTENT.value(),
                "Erro validação", listaErros);
    }
}
