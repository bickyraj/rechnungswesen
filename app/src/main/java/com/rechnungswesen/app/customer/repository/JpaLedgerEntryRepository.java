package com.rechnungswesen.app.customer.repository;

import java.util.UUID;

import com.rechnungswesen.app.payment.model.LedgerEntryModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLedgerEntryRepository extends JpaRepository<LedgerEntryModel, UUID> {
}
