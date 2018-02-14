package org.echocat.kata.java.part1.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Harsh Gupta on {2/14/18}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagazineDTO extends PublicationDTO{

    String publishedAt;
}
