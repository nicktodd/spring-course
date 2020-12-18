package com.allstate.quotes.service;

import com.allstate.quotes.entities.Quotation;
import com.allstate.quotes.repo.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class QuotationServiceImpl implements QuotationService{

    @Autowired
    private QuotationRepository repository;

    @Override
    public Quotation getQuotation(Quotation quotation) {
        // got the price
        Quotation newQuote = calculateQuote(quotation);
        // put the quote into the database
        Quotation savedQuotation = repository.save(newQuote);
        // return the saved quotation
        return savedQuotation;
    }

    @Override
    public Collection<Quotation> getAllQuotes() {
        return repository.findAll();
    }

    @Override
    public Collection<Quotation> getQuotesByLastName(String lastName) {
        return repository.findQuotationByLastName(lastName);
    }

    private Quotation calculateQuote(Quotation quotation) {
        // applying all your factors
        quotation.setQuote(200.0);
        return quotation;
    }

}
