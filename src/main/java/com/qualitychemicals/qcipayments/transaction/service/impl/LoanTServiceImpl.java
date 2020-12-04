package com.qualitychemicals.qcipayments.transaction.service.impl;

import com.qualitychemicals.qcipayments.exceptions.ResourceNotFoundException;
import com.qualitychemicals.qcipayments.transaction.converter.LoanTConverter;
import com.qualitychemicals.qcipayments.transaction.dao.TransactionDao;
import com.qualitychemicals.qcipayments.transaction.dto.LoanPayDto;
import com.qualitychemicals.qcipayments.transaction.dto.LoanTDto;
import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import com.qualitychemicals.qcipayments.transaction.service.LoanTService;
import com.qualitychemicals.qcipayments.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanTServiceImpl implements LoanTService {

    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    LoanTConverter loanTConverter;
    private final Logger logger = LoggerFactory.getLogger(LoanTServiceImpl.class);

    @Transactional
    @Override
    public LoanT saveLoanT(LoanTDto loanTDto) {
        logger.info("setting transaction...");
        LoanT loanT=loanTConverter.dtoToEntity(loanTDto);
        loanT.setStatus(TransactionStatus.SUCCESS);
        return transactionDao.save(loanT);

    }

    @Override
    public LoanT repay(LoanTDto loanTDto) {
        throw new ResourceNotFoundException("repay");
    }

    @Override
    public LoanT payPenalty(LoanTDto loanTDto) {
        return null;
    }

    @Override
    public LoanT repayMobile(LoanPayDto loanPayDto) {
        return null;
    }
}
