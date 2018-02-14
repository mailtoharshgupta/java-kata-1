package org.echocat.kata.java.part1.services.web;

import org.echocat.kata.java.part1.base.model.BookDTO;
import org.echocat.kata.java.part1.base.model.MagazineDTO;
import org.echocat.kata.java.part1.base.model.PublicationDTO;

import java.util.List;

/**
 * @author Harsh Gupta on {2/14/18}
 */
public interface ISearchService {

    PublicationDTO findByISBN(String isbn);

    List<PublicationDTO> findByAuthorEmail(String email);

}
