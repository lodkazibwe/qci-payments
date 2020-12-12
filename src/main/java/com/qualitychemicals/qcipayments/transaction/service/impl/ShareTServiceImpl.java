package com.qualitychemicals.qcipayments.transaction.service.impl;

import com.qualitychemicals.qcipayments.transaction.converter.ShareTConverter;
import com.qualitychemicals.qcipayments.transaction.dao.ShareTDao;
import com.qualitychemicals.qcipayments.transaction.dao.TransactionDao;
import com.qualitychemicals.qcipayments.transaction.dto.ShareTDto;
import com.qualitychemicals.qcipayments.transaction.model.ShareT;
import com.qualitychemicals.qcipayments.transaction.service.ShareTService;
import com.qualitychemicals.qcipayments.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShareTServiceImpl implements ShareTService {

    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionDao transactionDao;
    @Autowired ShareTConverter shareTConverter;
    @Autowired ShareTDao shareTDao;
    private final Logger logger = LoggerFactory.getLogger(ShareTServiceImpl.class);

    @Override
    @Transactional
    public ShareT mobileShares(ShareTDto shareTDto) {

        logger.info("converting...");
        ShareT shareT=shareTConverter.dtoToEntity(shareTDto);
        logger.info("saving transaction...");
        return transactionDao.save(shareT);
    }

    @Override
    public List<ShareT> loanTransactions(String userName) {
        return shareTDao.findByUserNameOrderByDateDesc(userName);
    }

    @Override
    public List<ShareT> getAll() {
        return shareTDao.findAll();
    }
}

