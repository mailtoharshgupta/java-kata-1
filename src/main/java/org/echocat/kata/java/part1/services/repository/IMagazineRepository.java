package org.echocat.kata.java.part1.services.repository;

import org.echocat.kata.java.part1.core.model.Magazine;

import java.util.List;

/**
 * @author Harsh Gupta on {2/14/18}
 */
public interface IMagazineRepository {

    Magazine save(Magazine magazine);
    Magazine getByISBN(String isbn);
    List<Magazine> getAllMagazines();

}
