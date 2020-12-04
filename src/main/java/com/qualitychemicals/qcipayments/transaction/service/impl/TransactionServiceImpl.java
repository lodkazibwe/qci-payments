package com.qualitychemicals.qcipayments.transaction.service.impl;

import com.qualitychemicals.qcipayments.transaction.converter.TransactionConverter;
import com.qualitychemicals.qcipayments.transaction.dao.TransactionDao;
import com.qualitychemicals.qcipayments.transaction.dto.MobilePayment;
import com.qualitychemicals.qcipayments.transaction.dto.TransactionDto;
import com.qualitychemicals.qcipayments.transaction.model.*;
import com.qualitychemicals.qcipayments.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionDao transactionDao;
    @Autowired TransactionConverter transactionConverter;

    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);


    @Override
    public Transaction saveTransaction(TransactionDto transactionDto) {
        logger.info("converting...");
        Transaction transaction =transactionConverter.dtoToEntity(transactionDto);
        logger.info("saving transaction...");
        return transactionDao.save(transaction);
    }

    @Override
    public List<LoanT> loanTransactions(int loanId) {
        logger.info("getting transactions...");
        return transactionDao.findByLoanId(loanId);

    }

    @Override
    public List<Transaction> userTransactions(String userName) {
       return transactionDao.findByUserName(userName);
    }

    @Override
    public MobilePayment transactMobile(MobilePayment mobilePayment) {
        logger.info("yo payments api...");
        return mobilePayment;
    }

}
