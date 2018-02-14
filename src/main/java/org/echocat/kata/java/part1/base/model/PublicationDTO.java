package org.echocat.kata.java.part1.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDTO{

    String title;
    String isbn;
    List<String> authors;
}
