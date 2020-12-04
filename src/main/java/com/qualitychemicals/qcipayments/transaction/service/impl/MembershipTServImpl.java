package com.qualitychemicals.qcipayments.transaction.service.impl;

import com.qualitychemicals.qcipayments.transaction.converter.MembershipTConverter;
import com.qualitychemicals.qcipayments.transaction.dao.TransactionDao;
import com.qualitychemicals.qcipayments.transaction.dto.MembershipTDto;
import com.qualitychemicals.qcipayments.transaction.model.MembershipT;
import com.qualitychemicals.qcipayments.transaction.service.MembershipTService;
import com.qualitychemicals.qcipayments.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembershipTServImpl implements MembershipTService {
    @Autowired
    TransactionService transactionService;
    @Autowired MembershipTConverter membershipTConverter;

    @Autowired
    TransactionDao transactionDao;
    private final Logger logger= LoggerFactory.getLogger(MembershipTServImpl.class);
    @Override
    public MembershipT payMembership(MembershipTDto membershipTDto) {

       return null;  }

    @Override
    @Transactional
    public MembershipT saveMembership(MembershipTDto membershipTDto) {

        logger.info("converting Transaction...");
        MembershipT membershipT=membershipTConverter.dtoToEntity(membershipTDto);
        return transactionDao.save(membershipT);

    }
}
