package org.echocat.kata.java.part1.services.das.impl;

import org.echocat.kata.java.part1.core.model.Author;
import org.echocat.kata.java.part1.services.das.IAuthorDAS;
import org.echocat.kata.java.part1.services.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Service("authorDAS")
public class AuthorDASImpl implements IAuthorDAS{

    private final IAuthorRepository authorRepository;

    @Autowired
    public AuthorDASImpl(IAuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author getByEmail(String email) {
        return authorRepository.getByEmail(email);
    }

    @Override
    public Author delete(Author author) {
        return authorRepository.delete(author);
    }
}
