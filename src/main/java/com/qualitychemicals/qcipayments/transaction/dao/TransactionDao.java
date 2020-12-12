package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import com.qualitychemicals.qcipayments.transaction.model.MembershipT;
import com.qualitychemicals.qcipayments.transaction.model.Transaction;
import com.qualitychemicals.qcipayments.transaction.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {
    List<LoanT> findByLoanId(int loanId);

    List<Transaction> findByAcctFrom(String acctFrom);
    List<Transaction> findByUserNameOrderByDateDesc(String userName);

    List<Transaction> fingByTransactionType(TransactionType transactionType);
}
