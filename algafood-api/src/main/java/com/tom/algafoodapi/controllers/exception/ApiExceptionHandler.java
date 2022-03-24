package com.tom.algafoodapi.controllers.exception;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.tom.algafoodapi.controllers.enums.EnumErrorType;
import com.tom.algafoodapi.domain.exception.EntityInUseException;
import com.tom.algafoodapi.domain.exception.EntityNotFoundException;
import com.tom.algafoodapi.domain.exception.GeneralException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundHandlerMethod(EntityNotFoundException e, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        EnumErrorType errorType = EnumErrorType.ENTITY_NOT_FOUND;
        String detail = e.getMessage();

        StandardError error = this.factoryErrorStandard(status, errorType, detail).build();

        return this.handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> entityLinkedHandlerMethod(EntityInUseException e, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        EnumErrorType errorType = EnumErrorType.ENTITY_LINKED;
        String detail = e.getMessage();

        StandardError error = this.factoryErrorStandard(status, errorType, detail).build();

        return this.handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> generalExceptionHandlerMethod(GeneralException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        EnumErrorType errorType = EnumErrorType.GENERAL_ERROR;
        String detail = e.getMessage();

        StandardError error = this.factoryErrorStandard(status, errorType, detail).build();
        return this.handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return this.handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        EnumErrorType erroType = EnumErrorType.INVALIDE_FIELD;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        StandardError error = this.factoryErrorStandard(status, erroType, detail).build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        EnumErrorType errorType = EnumErrorType.INVALIDE_FIELD;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
                + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        StandardError error = this.factoryErrorStandard(status, errorType, detail).build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        if (body == null) {
            body = StandardError.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = StandardError.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private StandardError.StandardErrorBuilder factoryErrorStandard(HttpStatus status, EnumErrorType errorType,
            String detail) {
        return StandardError.builder()
                .status(status.value())
                .type(errorType.getUri())
                .title(errorType.getTitle())
                .detail(detail);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        EnumErrorType errorType = EnumErrorType.INVALIDE_DATA;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        BindingResult bindingResult = ex.getBindingResult();
        List<StandardError.Field> errorFields = bindingResult.getFieldErrors().stream()
                .map(fieldError -> StandardError.Field.builder()
                        .name(fieldError.getField())
                        .userMessage(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        StandardError error = this.factoryErrorStandard(status, errorType, detail).userMenssage(detail)
                .fields(errorFields)
                .build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }
}
