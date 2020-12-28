package com.qualitychemicals.qcipayments.transaction.service.impl;

import com.qualitychemicals.qcipayments.exceptions.ResourceNotFoundException;
import com.qualitychemicals.qcipayments.transaction.converter.LoanTConverter;
import com.qualitychemicals.qcipayments.transaction.dao.LoanTDao;
import com.qualitychemicals.qcipayments.transaction.dao.TransactionDao;
import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LoanTServiceImpl implements LoanTService {

    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    LoanTConverter loanTConverter;
    @Autowired LoanTDao loanTDao;
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

    @Override
    public List<LoanT> loanTransactions(String userName) {
        return loanTDao.findByUserNameOrderByDateDesc(userName);
    }

    @Override
    public List<LoanT> getAll() {
        return loanTDao.findAll();
    }

    @Override
    public double totalLoanPayment(Date date) {
        List<LoanT> loanTS =loanTDao.findByStatusAndAmountGreaterThanAndDate
                (TransactionStatus.SUCCESS,0.0,date);

        double total = 0;
        for(LoanT loanT:loanTS) total += loanT.getAmount();
        return total;
    }

    @Override
    public List<DateSavingDto> dateLoanPayment(Date dateFrom, Date dateTo) {
        List<DateSavingDto> dateLoanPayments=new ArrayList<>();
        List<LoanT> loanTS =loanTDao
                .findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual
                        (TransactionStatus.SUCCESS,0.0,dateTo,dateFrom);
        List<LoanT> loanTs = filterTransactions(loanTS);
        for(LoanT loanT:loanTs){
            DateSavingDto loanPayment =new DateSavingDto();
            loanPayment.setAmount(loanT.getAmount());
            loanPayment.setDate(loanT.getDate());
            dateLoanPayments.add(loanPayment);
        }
        return dateLoanPayments;
    }

    @Override
    public double totalLoanPayment(Date dateFrom, Date dateTo) {
        List<LoanT> loanTS =loanTDao
                .findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual
                        (TransactionStatus.SUCCESS,0.0,dateTo,dateFrom);

        double total = 0;
        for(LoanT loanT:loanTS) total += loanT.getAmount();
        return total;
    }

    private List<LoanT> filterTransactions(List<LoanT> loanTS){
        return loanTS.stream().collect(Collectors.collectingAndThen(
                Collectors.toMap(LoanT::getDate, Function.identity(), (left, right) -> {
                    left.setAmount(left.getAmount()+right.getAmount());
                    return left;
                }), m -> new ArrayList<>(m.values())));
    }
}
