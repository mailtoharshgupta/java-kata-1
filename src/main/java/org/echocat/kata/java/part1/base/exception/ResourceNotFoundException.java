package org.echocat.kata.java.part1.base.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceNotFoundException extends RuntimeException {

    private String message;
}
