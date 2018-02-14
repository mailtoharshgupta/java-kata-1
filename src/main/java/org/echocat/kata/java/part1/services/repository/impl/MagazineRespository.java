package org.echocat.kata.java.part1.services.repository.impl;

import org.echocat.kata.java.part1.core.model.Magazine;
import org.echocat.kata.java.part1.services.repository.IMagazineRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Harsh Gupta on {2/14/18}
 */

@Service("magazineRepository")
public class MagazineRespository implements IMagazineRepository {

    Map<String, Magazine> magazineMap = new HashMap<>();

    @Override
    public Magazine save(Magazine magazine) {
        String isbn = (magazine == null) ? null : magazine.getIsbn();
        magazineMap.put(isbn,magazine);
        return magazineMap.get(isbn);
    }

    @Override
    public Magazine getByISBN(String isbn) {
        return magazineMap.get(isbn);
    }

    @Override
    public List<Magazine> getAllMagazines() {
        return magazineMap.values().stream().collect(Collectors.toList());
    }
}
