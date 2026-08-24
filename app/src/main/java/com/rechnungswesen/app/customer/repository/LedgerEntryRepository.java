package com.rechnungswesen.app.customer.repository;

import com.rechnungswesen.app.payment.entity.LedgerEntry;

public interface LedgerEntryRepository {
	void createLedgerEntry(LedgerEntry ledgerEntry);
}
