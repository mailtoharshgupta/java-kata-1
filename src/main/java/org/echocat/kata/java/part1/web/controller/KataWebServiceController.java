package org.echocat.kata.java.part1.web.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.echocat.kata.java.part1.base.exception.ResourceNotFoundException;
import org.echocat.kata.java.part1.base.model.PublicationDTO;
import org.echocat.kata.java.part1.services.web.ISearchService;
import org.echocat.kata.java.part1.web.annotation.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@RestController
@Document
@RequestMapping(KataWebServiceController.URL)
public class KataWebServiceController {

    protected static final String URL = "/api";

    private static final Logger LOGGER = LoggerFactory.getLogger(KataWebServiceController.class);

    private final ISearchService searchService;

    @Autowired
    public KataWebServiceController(ISearchService searchService) {
        this.searchService = searchService;
    }

    /**
     *
     * @param isbn
     * @return A book or a magazine identified by ISBN
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Finds the Book or Magazine by ISBN"),
            @ApiResponse(code = 404, message = "Book or magazine identified by  passed ISBN does not exist")
    })
    @GetMapping(value = "/get/isbn/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable String isbn) {
        LOGGER.debug("Request received for book/magazine with ISBN {}", isbn);
        PublicationDTO dto = Optional
                .ofNullable(searchService.findByISBN(isbn))
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Resource with ISBN " + isbn +" not found");
                });

        return ResponseEntity.ok(dto);
    }

    /**
     * Get API for getting a publication by Email
     *
     * @param email
     * @return A book or a magazine identified by Author's Email
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Finds the Book or Magazine by Author email"),
            @ApiResponse(code = 404, message = "Books or magazines identified by  passed author email does not exist")
    })
    @GetMapping(value = "/get/author/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PublicationDTO>> getByEmail(@PathVariable String email) {
        LOGGER.debug("Request received for book/magazine with ISBN {}", email);
        List<PublicationDTO> dtos = Optional
                .ofNullable(searchService.findByAuthorEmail(email))
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Resource with email " + email +" not found");
                });

        return ResponseEntity.ok(dtos);
    }

    /**
     * API to get all sorted list of publications by their title
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns all publications"),
            @ApiResponse(code = 404, message = "Invalid resource")
    })
    @GetMapping(value = "/get/sort/title", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSortedByTitle() {
        LOGGER.debug("Request received getting all publications");
        List<PublicationDTO> dtos = searchService.getAllPublications();
        return ResponseEntity.ok(dtos);
    }

    /**
     * API to get all unsorted list of publications.
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns all publications"),
            @ApiResponse(code = 404, message = "Invalid resource")
    })
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        LOGGER.debug("Request received getting all publications");
        List<PublicationDTO> dtos = searchService.getAllUnsortedPublications();
        return ResponseEntity.ok(dtos);
    }

}
