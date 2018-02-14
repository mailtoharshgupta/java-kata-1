package org.echocat.kata.java.part1.services.repository.impl;

import org.echocat.kata.java.part1.core.model.Author;
import org.echocat.kata.java.part1.services.repository.IAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Service("authorRepository")
public class AuthorRepository implements IAuthorRepository{

    Map<String,Author> authorMap = new HashMap<>();

    @Override
    public Author save(Author author) {
        String email = (author == null) ? null : author.getEmail();
        authorMap.put(email,author);
        return authorMap.get(email);
    }

    @Override
    public Author getByEmail(String email) {
        return authorMap.get(email);

    }

    @Override
    public Author delete(Author author) {
        String email = (author == null) ? null : author.getEmail();
        authorMap.remove(email);
        return author;
    }
}
