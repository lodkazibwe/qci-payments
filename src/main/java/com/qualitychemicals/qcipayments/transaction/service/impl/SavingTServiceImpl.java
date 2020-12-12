package com.qualitychemicals.qcipayments.transaction.service.impl;

import com.qualitychemicals.qcipayments.transaction.converter.SavingTConverter;
import com.qualitychemicals.qcipayments.transaction.dao.SavingTDao;
import com.qualitychemicals.qcipayments.transaction.dao.TransactionDao;
import com.qualitychemicals.qcipayments.transaction.dto.MembershipTDto;
import com.qualitychemicals.qcipayments.transaction.dto.SavingTDto;
import com.qualitychemicals.qcipayments.transaction.model.*;
import com.qualitychemicals.qcipayments.transaction.service.SavingTService;
import com.qualitychemicals.qcipayments.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SavingTServiceImpl implements SavingTService {
    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionDao transactionDao;
    @Autowired SavingTConverter savingTConverter;
    @Autowired SavingTDao savingTDao;
    private final Logger logger = LoggerFactory.getLogger(SavingTServiceImpl.class);
    @Override
    @Transactional
    public SavingT mobileSaving(SavingTDto savingTDto) {
        logger.info("converting...");
        SavingT savingT=savingTConverter.dtoToEntity(savingTDto);
        logger.info("saving transaction...");
        return transactionDao.save(savingT);
    }

    @Override
    public List<SavingT> loanTransactions(String userName) {
        return savingTDao.findByUserNameOrderByDateDesc(userName);
    }

    @Override
    public List<SavingT> getAll() {
        return savingTDao.findAll();
    }
}
