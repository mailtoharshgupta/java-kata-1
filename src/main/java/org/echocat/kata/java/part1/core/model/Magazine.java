package org.echocat.kata.java.part1.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.util.List;

/**
 * @author Harsh Gupta on {2/14/18}
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Magazine {

    String title;
    List<String> authors;
    String publishedAt;
    String isbn;

}
