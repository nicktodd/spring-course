package com.allstate.quotes.repo;


import com.allstate.quotes.entities.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface QuotationRepository extends JpaRepository<Quotation, Integer> {

    Collection<Quotation> findQuotationByLastName(String lastName);

}
