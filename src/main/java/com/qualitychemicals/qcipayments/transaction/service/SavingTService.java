package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.SavingTDto;
import com.qualitychemicals.qcipayments.transaction.model.SavingT;

public interface SavingTService {
    SavingT mobileSaving(SavingTDto savingTDto);
}
