package com.ti.avaliai.global.domain;

import com.ti.avaliai.global.domain.exceptions.*;
import com.ti.avaliai.global.response.error.*;
import com.ti.avaliai.global.response.success.BaseSucessResponse;
import com.ti.avaliai.global.response.success.NoPayloadSuccessResponse201;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

@ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse404.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse400.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse401.class))),
        @ApiResponse(responseCode = "422", description = "Not Processable Entity", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse422.class))),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse409.class))),
})
@RestControllerAdvice
public abstract class BasicController {

    protected static ResponseEntity created(){
        return buildSucessResponseWithoutPayload201(HttpStatus.CREATED);
    }

    protected static ResponseEntity ok(){
        return buildSucessResponseWithoutPayload200();
    }

    private static ResponseEntity buildSucessResponseWithoutPayload200() {
        return buildSucessResponseWithoutPayload(HttpStatus.OK);
    }

    protected static ResponseEntity deleted(){
        return buildSucessResponseWithoutPayload(HttpStatus.OK);
    }

    protected static <T> ResponseEntity ok(T entity){
        return  buildSucessResponse(entity, HttpStatus.OK);
    }


    private static <T> ResponseEntity<T> buildSucessResponse(T entity, HttpStatus status){
        return new ResponseEntity<>(
                entity,
                status
        );
    }
    private static <T> ResponseEntity<T> buildSucessResponseWithoutPayload(HttpStatus status) {

        return new ResponseEntity(
                BaseSucessResponse.builder()
                        .success(true)
                        .status(status.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath())
                        .reason(status.getReasonPhrase())
                        .timestamp(LocalDateTime.now())
                        .build(), status);
    }

    private static <T> ResponseEntity<T> buildSucessResponseWithoutPayload201(HttpStatus status){
        return new ResponseEntity(
                NoPayloadSuccessResponse201.builder()
                        .success(true)
                        .path(ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath())
                        .reason(status.getReasonPhrase())
                        .timestamp(LocalDateTime.now())
                        .build(), status
        );
    }

    private static <T> ResponseEntity<T> buildErrorResponse(String message, HttpStatus httpStatus) {
        return new ResponseEntity(
                BaseErrorResponse.builder()
                .success(false)
                .path(ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath())
                .reason(httpStatus.getReasonPhrase())
                .error(message)
                .build(),
                httpStatus);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<BaseErrorResponse<HttpException>> handleException(Exception ex) {

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof BadRequestException ||
                ex instanceof MissingRequestHeaderException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        if (ex instanceof EntityNotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        if (ex instanceof UnauthorizedException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }

        if (ex instanceof ForbbidenException) {
            httpStatus = HttpStatus.FORBIDDEN;
        }

        if (ex instanceof HttpRequestMethodNotSupportedException) {
            httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        }

        if (ex instanceof MultipartException || ex instanceof InvalidEmailException) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        }

        if (ex instanceof AlreadyReviewedByUserException ||
                ex instanceof AlreadyExistingEmailException) {
            httpStatus = HttpStatus.CONFLICT;
        }
        return buildErrorResponse(ex.getMessage(), httpStatus);
    }
}
