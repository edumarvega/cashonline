package com.broker.cashonline.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.broker.cashonline.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Loan> {

	Page<Loan> findAll(Pageable pageable);

	Page<Loan> findByUserId(long userId, Pageable pageable);

}
