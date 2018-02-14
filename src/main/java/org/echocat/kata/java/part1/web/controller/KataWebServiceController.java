package org.echocat.kata.java.part1.web.controller;

import org.echocat.kata.java.part1.web.annotation.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@RestController
@Document
@RequestMapping(KataWebServiceController.URL)
public class KataWebServiceController {
    protected static final String URL = "/api";

}
