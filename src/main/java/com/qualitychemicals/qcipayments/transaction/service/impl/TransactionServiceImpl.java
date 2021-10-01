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

import java.util.Date;
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
        transaction.setStatus(TransactionStatus.SUCCESS);
        return transactionDao.save(transaction);
    }

    @Override
    public List<Transaction> allByWallet(String wallet) {
        return transactionDao.findByWalletOrderByIdDesc(wallet);
    }

    @Override
    public List<Transaction> last5ByWallet(String wallet) {
        return transactionDao.findFirst5ByWalletOrderByIdDesc(wallet);
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
    public List<Transaction> allByType(TransactionType transactionType) {
        return transactionDao.findByTransactionType(transactionType);
    }

    @Override
    public List<Transaction> allTransactions(Date date) {
        return transactionDao.findByDate(date);
    }

    @Override
    public List<Transaction> allTransactions(Date date1, Date date2) {
        return transactionDao.findByDateGreaterThanEqualAndDateLessThanEqual(date1, date2);
    }
}
