package com.qualitychemicals.qcipayments.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanTransactionsDto {
    private List<LoanTDto> loanTransactions;
}
