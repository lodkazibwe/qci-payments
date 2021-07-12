package com.qualitychemicals.qcipayments.transaction.service.impl;

import com.qualitychemicals.qcipayments.transaction.converter.TransactionConverter;
import com.qualitychemicals.qcipayments.transaction.dao.LoanTDao;
import com.qualitychemicals.qcipayments.transaction.dao.TransactionDao;
import com.qualitychemicals.qcipayments.transaction.dto.TransactionDto;
import com.qualitychemicals.qcipayments.transaction.model.*;
import com.qualitychemicals.qcipayments.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionDao transactionDao;
    @Autowired LoanTDao loanTDao;
    @Autowired
    TransactionConverter transactionConverter;
    @Autowired
    RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);


    @Override
    public Transaction saveTransaction(TransactionDto transactionDto) {
        logger.info("converting...");
        Transaction transaction = transactionConverter.dtoToEntity(transactionDto);
        logger.info("saving transaction...");
        return transactionDao.save(transaction);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    @Override
    public List<LoanT> loanTransactions(int loanId) {
        logger.info("getting transactions...");
        return loanTDao.findByLoanId(loanId);

    }

    @Override
    public List<Transaction> userTransactions(String userName) {

        return transactionDao.findByUserNameOrderByDateDesc(userName);
    }

    @Override
    public List<Transaction> allTransactions() {
        return transactionDao.findAll();
    }

    @Override
    public List<Transaction> allByType(String transactionType) {
        return transactionDao.findByTransactionType(transactionType);
    }



}
