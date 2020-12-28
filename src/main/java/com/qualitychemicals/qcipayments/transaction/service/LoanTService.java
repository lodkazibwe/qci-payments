package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.LoanPayDto;
import com.qualitychemicals.qcipayments.transaction.dto.LoanTDto;
import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import com.qualitychemicals.qcipayments.transaction.model.TransactionType;

import java.util.Date;
import java.util.List;

public interface LoanTService {
    LoanT saveLoanT(LoanTDto loanTDto);
    LoanT repay(LoanTDto loanTDto);
    LoanT payPenalty(LoanTDto loanTDto);
    LoanT repayMobile(LoanPayDto loanPayDto);
    List<LoanT> loanTransactions(String userName);
    List<LoanT> getAll();

    double totalLoanPayment(Date date);
    List<DateSavingDto> dateLoanPayment(Date dateFrom, Date dateTo);
    double totalLoanPayment(Date dateFrom, Date dateTo);
}
