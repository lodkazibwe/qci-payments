package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.MembershipTDto;
import com.qualitychemicals.qcipayments.transaction.model.MembershipT;

import java.util.List;

public interface MembershipTService1 {
    MembershipT saveMembership(MembershipTDto membershipTDto);
    MembershipT payMembership(MembershipTDto membershipTDto);
    List<MembershipT> membershipTrans(String userName);

}
