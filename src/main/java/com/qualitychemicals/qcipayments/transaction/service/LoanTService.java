package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.LoanTDto;
import com.qualitychemicals.qcipayments.transaction.model.LoanT;

import java.util.Date;
import java.util.List;

public interface LoanTService {
    LoanT saveLoanT(LoanTDto loanTDto);
    List<LoanT> allByWallet(String wallet, String loan);
    List<LoanT> allByLoan(String loan);


    List<LoanT> loanTransactions(String userName);

    List<LoanT> getAll(String userName);

    double totalLoanPayment(Date date);

    List<DateSavingDto> dateLoanPayment(Date dateFrom, Date dateTo);

    double totalLoanPayment(Date dateFrom, Date dateTo);
}
