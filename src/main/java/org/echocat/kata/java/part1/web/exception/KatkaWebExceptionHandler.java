package org.echocat.kata.java.part1.web.exception;

import org.echocat.kata.java.part1.base.exception.ResourceNotFoundException;
import org.echocat.kata.java.part1.base.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@ControllerAdvice
public class KatkaWebExceptionHandler {


    /**
     * Handler for requests that fail due to invalid resource access.
     *
     * @param ex
     * @return {@link ErrorResponse}
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(StringUtils.isEmpty(ex.getMessage()) ? HttpStatus.NOT_FOUND.getReasonPhrase() : ex.getMessage())
                        .build());
    }


}
