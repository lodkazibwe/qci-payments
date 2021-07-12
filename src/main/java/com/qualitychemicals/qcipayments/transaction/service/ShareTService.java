package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.ShareTDto;
import com.qualitychemicals.qcipayments.transaction.model.ShareT;

import java.util.Date;
import java.util.List;

public interface ShareTService {
    ShareT mobileShares(ShareTDto shareTDto);

    List<ShareT> shareTransactions(String userName);

    List<ShareT> getAll();

    double totalShares(Date date);

    List<DateSavingDto> dateShares(Date dateFrom, Date dateTo);

    double totalShares(Date dateFrom, Date dateTo);

}
