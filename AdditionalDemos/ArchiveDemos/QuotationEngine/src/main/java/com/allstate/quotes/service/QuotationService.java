package com.allstate.quotes.service;

import com.allstate.quotes.entities.Quotation;

import java.util.Collection;

public interface QuotationService {

    Quotation getQuotation(Quotation quotation);

    Collection<Quotation> getAllQuotes();

    Collection<Quotation> getQuotesByLastName(String lastName);


}
