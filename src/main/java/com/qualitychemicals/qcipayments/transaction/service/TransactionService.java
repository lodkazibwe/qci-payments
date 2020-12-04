package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.LoanTransactionsDto;
import com.qualitychemicals.qcipayments.transaction.dto.MobilePayment;
import com.qualitychemicals.qcipayments.transaction.dto.TransactionDto;
import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import com.qualitychemicals.qcipayments.transaction.model.Transaction;

import java.util.List;

public interface TransactionService {
    MobilePayment transactMobile(MobilePayment mobilePayment);
    Transaction saveTransaction(TransactionDto transactionDto);
    List<LoanT> loanTransactions(int loanId);
    List<Transaction> userTransactions(String mobile);
}
