package org.echocat.kata.java.part1.services.web.impl;

import org.echocat.kata.java.part1.base.model.BookDTO;
import org.echocat.kata.java.part1.base.model.MagazineDTO;
import org.echocat.kata.java.part1.base.model.PublicationDTO;
import org.echocat.kata.java.part1.core.model.Author;
import org.echocat.kata.java.part1.core.model.Book;
import org.echocat.kata.java.part1.core.model.Magazine;
import org.echocat.kata.java.part1.core.util.KataUtil;
import org.echocat.kata.java.part1.services.das.IAuthorDAS;
import org.echocat.kata.java.part1.services.das.IBookDAS;
import org.echocat.kata.java.part1.services.das.IMagazineDAS;
import org.echocat.kata.java.part1.services.web.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Service("searchService")
public class SearchService implements ISearchService {


    private final IBookDAS bookDAS;
    private final IMagazineDAS magazineDAS;
    private final IAuthorDAS authorDAS;

    @Autowired
    public SearchService(IBookDAS bookDAS, IMagazineDAS magazineDAS,IAuthorDAS authorDAS) {
        this.bookDAS = bookDAS;
        this.magazineDAS = magazineDAS;
        this.authorDAS = authorDAS;
    }

    @Override
    public PublicationDTO findByISBN(String isbn) {
        Book book = bookDAS.getByISBN(isbn);
        Magazine magazine = magazineDAS.getByISBN(isbn);
        if(null == book && magazine == null){
            return null;
        }

        if(null != book){
            return (BookDTO) KataUtil.convert(book,BookDTO.class);
        } else {
            return (MagazineDTO) KataUtil.convert(magazine,MagazineDTO.class);
        }
    }

    @Override
    public List<PublicationDTO> findByAuthorEmail(String email) {
        Author author = authorDAS.getByEmail(email);
        if(null == author){
            return null;
        }
        List<PublicationDTO> dtos =
                author.getPublications()
                        .stream()
                        .map(isbn -> findByISBN(isbn) )
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<PublicationDTO> getAllPublications(){

        List<Book> allBooks = bookDAS.getAllBooks();
        List<Magazine> magazines = magazineDAS.getAllMagazines();
        List<PublicationDTO> bookList =
                allBooks
                        .stream()
                        .map(book -> (BookDTO) KataUtil.convert(book,BookDTO.class))
                        .collect(Collectors.toList());
        List<PublicationDTO> magazineList =
                magazines
                        .stream()
                        .map(magazine -> (MagazineDTO) KataUtil.convert(magazine,MagazineDTO.class))
                        .collect(Collectors.toList());
        bookList.addAll(magazineList);
       Collections.sort(bookList, new Comparator<PublicationDTO>() {
           @Override
           public int compare(PublicationDTO o1, PublicationDTO o2) {
               return o1.getTitle().compareTo(o2.getTitle());
           }
       });
       return bookList;
    }

}
