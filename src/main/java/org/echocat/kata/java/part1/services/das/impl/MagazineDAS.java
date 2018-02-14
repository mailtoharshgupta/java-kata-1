package org.echocat.kata.java.part1.services.das.impl;

import org.echocat.kata.java.part1.core.model.Author;
import org.echocat.kata.java.part1.core.model.Magazine;
import org.echocat.kata.java.part1.services.das.IAuthorDAS;
import org.echocat.kata.java.part1.services.das.IMagazineDAS;
import org.echocat.kata.java.part1.services.repository.IMagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Service("magazineDAS")
public class MagazineDAS implements IMagazineDAS {

    private final IMagazineRepository magazineRepository;

    private final IAuthorDAS authorDAS;

    @Autowired
    public MagazineDAS(IMagazineRepository magazineRepository, IAuthorDAS authorDAS) {
        this.magazineRepository = magazineRepository;
        this.authorDAS = authorDAS;
    }

    @Override
    public Magazine save(Magazine magazine) {
        Magazine persisted = magazineRepository.save(magazine);
        if (null != magazine) {
            persisted.getAuthors().forEach(author -> updateAuthor(author, persisted));
        }
        return persisted;
    }

    private void updateAuthor(String email, Magazine magazine) {
        Author author = authorDAS.getByEmail(email);
        if (null != author) {
            List<String> publications = author.getPublications();
            if (null == publications) {
                publications = new ArrayList<>();
            }
            publications.add(magazine.getIsbn());
            author.setPublications(publications);
            authorDAS.save(author);
        }

    }

    @Override
    public Magazine getByISBN(String isbn) {

        return magazineRepository.getByISBN(isbn);
    }

    @Override
    public List<Magazine> getAllMagazines() {
        return magazineRepository.getAllMagazines();
    }
}
