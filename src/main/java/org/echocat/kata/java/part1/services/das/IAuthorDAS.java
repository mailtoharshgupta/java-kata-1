package org.echocat.kata.java.part1.services.das;

import org.echocat.kata.java.part1.core.model.Author;

/**
 * @author Harsh Gupta on {2/14/18}
 */
public interface IAuthorDAS {

    public Author save(Author author);
    public Author getByEmail(String email);
    public Author delete(Author author);

}
