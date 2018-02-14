package org.echocat.kata.java.part1.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.util.List;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO extends PublicationDTO{

    String description;

}
