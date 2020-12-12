package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.SavingTDto;
import com.qualitychemicals.qcipayments.transaction.model.SavingT;

import java.util.List;

public interface SavingTService {
    SavingT mobileSaving(SavingTDto savingTDto);
    List<SavingT> loanTransactions(String userName);

    List<SavingT> getAll();
}
