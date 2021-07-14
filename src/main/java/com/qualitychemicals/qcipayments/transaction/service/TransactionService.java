package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.TransactionDto;
import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import com.qualitychemicals.qcipayments.transaction.model.Transaction;
import com.qualitychemicals.qcipayments.transaction.model.TransactionType;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(TransactionDto transactionDto);

    List<Transaction> successfulDeposits(String wallet);

    List<LoanT> loanTransactions(int loanId);

    List<Transaction> userTransactions(String mobile);

    List<Transaction> allTransactions();

    List<Transaction> allByType(String transactionType);

    Transaction addTransaction(Transaction transaction);
}
