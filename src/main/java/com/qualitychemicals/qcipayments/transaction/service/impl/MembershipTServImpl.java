package com.qualitychemicals.qcipayments.transaction.service.impl;

import com.qualitychemicals.qcipayments.transaction.converter.MembershipTConverter;
import com.qualitychemicals.qcipayments.transaction.dao.MembershipTDao;
import com.qualitychemicals.qcipayments.transaction.dao.TransactionDao;
import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.MembershipTDto;
import com.qualitychemicals.qcipayments.transaction.model.MembershipT;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import com.qualitychemicals.qcipayments.transaction.service.MembershipTService;
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
public class MembershipTServImpl implements MembershipTService {
    @Autowired
    TransactionService transactionService;
    @Autowired
    MembershipTConverter membershipTConverter;
    @Autowired
    MembershipTDao membershipTDao;
    @Autowired
    TransactionDao transactionDao;
    private final Logger logger = LoggerFactory.getLogger(MembershipTServImpl.class);


    @Override
    @Transactional
    public MembershipT saveMembership(MembershipTDto membershipTDto) {

        logger.info("converting Transaction...");
        MembershipT membershipT = membershipTConverter.dtoToEntity(membershipTDto);
        membershipT.setStatus(TransactionStatus.SUCCESS);

        return transactionDao.save(membershipT);

    }

    @Override
    public List<MembershipT> membershipTrans(String userName) {
        return membershipTDao.findByUserName(userName);

    }

    @Override
    public List<MembershipT> getAll() {
        return membershipTDao.findAll();
    }

    @Override
    public double totalMembership(Date date) {
        List<MembershipT> membershipTS = membershipTDao.findByStatusAndAmountGreaterThanAndDate
                (TransactionStatus.SUCCESS, 0.0, date);

        double total = 0;
        for (MembershipT membershipT : membershipTS) total += membershipT.getAmount();
        return total;
    }

    @Override
    public List<DateSavingDto> dateMembership(Date dateFrom, Date dateTo) {
        List<DateSavingDto> dateMemberships = new ArrayList<>();
        List<MembershipT> membershipTS = membershipTDao
                .findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual
                        (TransactionStatus.SUCCESS, 0.0, dateTo, dateFrom);
        List<MembershipT> membershipTs = filterTransactions(membershipTS);


        for (MembershipT membershipT : membershipTs) {
            DateSavingDto dateMembership = new DateSavingDto();
            dateMembership.setAmount(membershipT.getAmount());
            dateMembership.setDate(membershipT.getDate());
            dateMemberships.add(dateMembership);
        }
        return dateMemberships;
    }

    @Override
    public double totalMembership(Date dateFrom, Date dateTo) {
        List<MembershipT> membershipTS = membershipTDao
                .findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual
                        (TransactionStatus.SUCCESS, 0.0, dateTo, dateFrom);
        double total = 0;
        for (MembershipT membershipT : membershipTS) total += membershipT.getAmount();
        return total;
    }

    private List<MembershipT> filterTransactions(List<MembershipT> membershipTS) {
        return membershipTS.stream().collect(Collectors.collectingAndThen(
                Collectors.toMap(MembershipT::getDate, Function.identity(), (left, right) -> {
                    left.setAmount(left.getAmount() + right.getAmount());
                    return left;
                }), m -> new ArrayList<>(m.values())));
    }
}
