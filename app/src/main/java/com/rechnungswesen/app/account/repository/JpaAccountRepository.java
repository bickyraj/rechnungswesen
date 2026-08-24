package com.rechnungswesen.app.account.repository;

import java.util.Optional;

import com.rechnungswesen.app.account.model.AccountModel;
import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaAccountRepository extends JpaRepository<AccountModel, Long> {
	boolean existsByCustomerIdAndCurrency(Long customerId, String currency);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("""
            SELECT a
            FROM AccountModel a
            WHERE a.id = :id
            """)
	Optional<AccountModel> findByIdForUpdate(@Param("id") Long id);
}
