package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.ShareTDto;
import com.qualitychemicals.qcipayments.transaction.model.ShareT;

import java.util.List;

public interface ShareTService {
    ShareT mobileShares(ShareTDto shareTDto);

    List<ShareT> loanTransactions(String userName);

    List<ShareT> getAll();
}
