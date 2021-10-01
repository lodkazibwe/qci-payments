package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.TransactionDto;
import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import com.qualitychemicals.qcipayments.transaction.model.Transaction;
import com.qualitychemicals.qcipayments.transaction.model.TransactionType;

import java.util.Date;
import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(TransactionDto transactionDto);
    List<Transaction> allByWallet(String wallet);
    List<Transaction> last5ByWallet(String wallet);
    Transaction addTransaction(Transaction transaction);


    List<LoanT> loanTransactions(int loanId);
    List<Transaction> userTransactions(String userName);
    List<Transaction> allTransactions();
    List<Transaction> allTransactions(Date date);
    List<Transaction> allTransactions(Date date1, Date date2);
    List<Transaction> allByType(TransactionType transactionType);
}
