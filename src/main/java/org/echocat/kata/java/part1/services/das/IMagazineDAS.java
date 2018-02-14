package org.echocat.kata.java.part1.services.das;

import org.echocat.kata.java.part1.core.model.Magazine;

import java.util.List;

/**
 * @author Harsh Gupta on {2/14/18}
 */
public interface IMagazineDAS {

    Magazine save(Magazine magazine);
    Magazine getByISBN(String isbn);
    List<Magazine> getAllMagazines();

}
