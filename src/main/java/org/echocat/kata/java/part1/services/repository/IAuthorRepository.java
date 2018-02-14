package org.echocat.kata.java.part1.services.repository;

import org.echocat.kata.java.part1.core.model.Author;

/**
 * @author Harsh Gupta on {2/14/18}
 */
public interface IAuthorRepository {

    public Author save(Author author);
    public Author getByEmail(String email);
    public Author delete(Author author);

}
