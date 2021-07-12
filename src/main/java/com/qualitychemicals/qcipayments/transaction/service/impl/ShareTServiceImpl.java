package com.qualitychemicals.qcipayments.transaction.service.impl;

import com.qualitychemicals.qcipayments.transaction.converter.ShareTConverter;
import com.qualitychemicals.qcipayments.transaction.dao.ShareTDao;
import com.qualitychemicals.qcipayments.transaction.dao.TransactionDao;
import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.ShareTDto;
import com.qualitychemicals.qcipayments.transaction.model.ShareT;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import com.qualitychemicals.qcipayments.transaction.service.ShareTService;
import com.qualitychemicals.qcipayments.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShareTServiceImpl implements ShareTService {

    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    ShareTConverter shareTConverter;
    @Autowired
    ShareTDao shareTDao;
    private final Logger logger = LoggerFactory.getLogger(ShareTServiceImpl.class);

    @Override
    @Transactional
    public ShareT mobileShares(ShareTDto shareTDto) {

        logger.info("converting...");
        ShareT shareT = shareTConverter.dtoToEntity(shareTDto);
        //shareT.setCategory(TransactionCat.SHARE);
        logger.info("saving transaction...");
        return transactionDao.save(shareT);
    }


    @Override
    public List<ShareT> shareTransactions(String userName) {
        return shareTDao.findByUserNameOrderByDateDesc(userName);
    }

    @Override
    public List<ShareT> getAll() {
        return shareTDao.findAll();
    }

    @Override
    public double totalShares(Date date) {
        List<ShareT> shareTS = shareTDao.findByStatusAndAmountGreaterThanAndDate
                (TransactionStatus.SUCCESS, 0.0, date);

        double total = 0;
        for (ShareT shareT : shareTS) total += shareT.getAmount();
        return total;
    }

    @Override
    public List<DateSavingDto> dateShares(Date dateFrom, Date dateTo) {

        List<DateSavingDto> dateShares = new ArrayList<>();
        List<ShareT> shareTS = shareTDao
                .findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual
                        (TransactionStatus.SUCCESS, 0.0, dateTo, dateFrom);
        List<ShareT> shareTs = filterTransactions(shareTS);


        for (ShareT shareT : shareTs) {
            DateSavingDto dateShare = new DateSavingDto();
            dateShare.setAmount(shareT.getAmount());
            dateShare.setDate(shareT.getDate());
            dateShares.add(dateShare);
        }
        return dateShares;
    }

    @Override
    public double totalShares(Date dateFrom, Date dateTo) {
        List<ShareT> shareTS = shareTDao
                .findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual
                        (TransactionStatus.SUCCESS, 0.0, dateTo, dateFrom);

        double total = 0;
        for (ShareT shareT : shareTS) total += shareT.getAmount();
        return total;
    }


    private List<ShareT> filterTransactions(List<ShareT> shareTS) {
        return shareTS.stream().collect(Collectors.collectingAndThen(
                Collectors.toMap(ShareT::getDate, Function.identity(), (left, right) -> {
                    left.setAmount(left.getAmount() + right.getAmount());
                    return left;
                }), m -> new ArrayList<>(m.values())));
    }
}

