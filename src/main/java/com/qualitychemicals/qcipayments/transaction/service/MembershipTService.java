package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.MembershipTDto;
import com.qualitychemicals.qcipayments.transaction.model.MembershipT;

public interface MembershipTService {
    MembershipT saveMembership(MembershipTDto membershipTDto);
    MembershipT payMembership(MembershipTDto membershipTDto);
}
