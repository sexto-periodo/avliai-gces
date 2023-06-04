package com.ti.avaliai.global.domain;

import com.ti.avaliai.global.response.success.BaseSucessResponse;
import com.ti.avaliai.global.response.success.NoPayloadSuccessResponse201;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;


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
                        .sucsses(true)
                        .status(status.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath())
                        .reason(status.getReasonPhrase())
                        .timestamp(LocalDateTime.now())
                        .build(), status);
    }

    private static <T> ResponseEntity<T> buildSucessResponseWithoutPayload201(HttpStatus status){
        return new ResponseEntity(
                NoPayloadSuccessResponse201.builder()
                        .sucsses(true)
                        .path(ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath())
                        .reason(status.getReasonPhrase())
                        .timestamp(LocalDateTime.now())
                        .build(), status
        );
    }

}
