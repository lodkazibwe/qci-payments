package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.SavingTDto;
import com.qualitychemicals.qcipayments.transaction.model.SavingT;

import java.util.Date;
import java.util.List;

public interface SavingTService {
    SavingT mobileSaving(SavingTDto savingTDto);
    List<SavingT> savingTransactions(String userName);

    List<SavingT> getAll();
    List<SavingT> withdrawRequests();
    double totalSaving(Date date);
    List<DateSavingDto> dateSaving(Date dateFrom, Date dateTo);
    double totalSaving(Date dateFrom, Date dateTo);
}
