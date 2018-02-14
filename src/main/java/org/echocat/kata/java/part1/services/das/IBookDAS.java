package org.echocat.kata.java.part1.services.das;

import org.echocat.kata.java.part1.core.model.Book;

import java.util.List;

/**
 * @author Harsh Gupta on {2/14/18}
 */
public interface IBookDAS {

    Book save(Book magazine);
    Book getByISBN(String isbn);
    List<Book> getAllBooks();
}
