package com.qualitychemicals.qcipayments.transaction.service.impl;

import com.qualitychemicals.qcipayments.transaction.converter.SavingTConverter;
import com.qualitychemicals.qcipayments.transaction.dao.SavingTDao;
import com.qualitychemicals.qcipayments.transaction.dao.TransactionDao;
import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.SavingTDto;
import com.qualitychemicals.qcipayments.transaction.model.*;
import com.qualitychemicals.qcipayments.transaction.service.SavingTService;
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
public class SavingTServiceImpl implements SavingTService {
    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    SavingTConverter savingTConverter;
    @Autowired
    SavingTDao savingTDao;
    private final Logger logger = LoggerFactory.getLogger(SavingTServiceImpl.class);

    @Override
    @Transactional
    public SavingT mobileSaving(SavingTDto savingTDto) {
        logger.info("converting...");
        SavingT savingT = savingTConverter.dtoToEntity(savingTDto);
        savingT.setStatus(TransactionStatus.SUCCESS);
        logger.info("saving transaction...");
        return transactionDao.save(savingT);
    }


    @Override
    public List<SavingT> savingTransactions(String userName) {
        return savingTDao.findByUserNameOrderByDateDesc(userName);
    }

    @Override
    public List<SavingT> getAll() {
        return savingTDao.findAll();
    }

    @Override
    public List<SavingT> withdrawRequests() {
        return savingTDao.findByStatusAndAmountLessThan(TransactionStatus.PENDING, 0.0);
    }

    @Override
    public double totalSaving(Date date) {

        List<SavingT> savingTS = savingTDao.findByStatusAndAmountGreaterThanAndDate
                (TransactionStatus.SUCCESS, 0.0, date);

        double total = 0;
        for (SavingT savingT : savingTS) total += savingT.getAmount();
        return total;

    }

    @Override
    public List<DateSavingDto> dateSaving(Date dateFrom, Date dateTo) {
        List<DateSavingDto> dateSavings = new ArrayList<>();
        List<SavingT> savingTS = savingTDao
                .findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual
                        (TransactionStatus.SUCCESS, 0.0, dateTo, dateFrom);
        List<SavingT> savingTs = filterTransactions(savingTS);
        for (SavingT savingT : savingTs) {
            DateSavingDto dateSaving = new DateSavingDto();
            dateSaving.setAmount(savingT.getAmount());
            dateSaving.setDate(savingT.getDate());
            dateSavings.add(dateSaving);
        }
        return dateSavings;
    }

    @Override
    public double totalSaving(Date dateFrom, Date dateTo) {
        List<SavingT> savingTS = savingTDao
                .findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual
                        (TransactionStatus.SUCCESS, 0.0, dateTo, dateFrom);

        double total = 0;
        for (SavingT savingT : savingTS) total += savingT.getAmount();
        return total;
    }

    private List<SavingT> filterTransactions(List<SavingT> savingTS) {
        return savingTS.stream().collect(Collectors.collectingAndThen(
                Collectors.toMap(SavingT::getDate, Function.identity(), (left, right) -> {
                    left.setAmount(left.getAmount() + right.getAmount());
                    return left;
                }), m -> new ArrayList<>(m.values())));
    }
}
