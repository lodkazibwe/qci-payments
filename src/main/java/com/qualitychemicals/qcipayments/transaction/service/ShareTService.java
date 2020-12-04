package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.ShareTDto;
import com.qualitychemicals.qcipayments.transaction.model.ShareT;

public interface ShareTService {
    ShareT mobileShares(ShareTDto shareTDto);
}
