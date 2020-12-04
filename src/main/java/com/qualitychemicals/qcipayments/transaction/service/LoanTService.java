package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.LoanPayDto;
import com.qualitychemicals.qcipayments.transaction.dto.LoanTDto;
import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import com.qualitychemicals.qcipayments.transaction.model.TransactionType;

public interface LoanTService {
    LoanT saveLoanT(LoanTDto loanTDto);
    LoanT repay(LoanTDto loanTDto);
    LoanT payPenalty(LoanTDto loanTDto);
    LoanT repayMobile(LoanPayDto loanPayDto);
}
