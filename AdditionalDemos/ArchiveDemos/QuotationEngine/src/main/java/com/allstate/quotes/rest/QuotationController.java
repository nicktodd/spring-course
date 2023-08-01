package com.allstate.quotes.rest;


import com.allstate.quotes.entities.Quotation;
import com.allstate.quotes.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/quotes")
public class QuotationController {

    @Autowired
    private QuotationService quoteService;


    @GetMapping("/{lastName}")
    public Collection<Quotation> getQuotesByLastName(@PathVariable String lastName) {
        return quoteService.getQuotesByLastName(lastName);
    }


    @GetMapping
    public Collection<Quotation> getAllQuotes() {
        return quoteService.getAllQuotes();
    }


    @PostMapping
    public Quotation getQuote(@RequestBody Quotation quotation) {
        Quotation newQuote = quoteService.getQuotation(quotation);
        return newQuote;
        /*try {
            Quotation newQuote = quoteService.getQuotation(quotation);
            return new ResponseEntity<Quotation>(newQuote, HttpStatus.CREATED);
        }
        catch (Exception ex) {
            return new ResponseEntity<Quotation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
    }
}
