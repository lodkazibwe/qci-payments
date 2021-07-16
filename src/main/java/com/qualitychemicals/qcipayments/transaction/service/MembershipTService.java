package com.qualitychemicals.qcipayments.transaction.service;

import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.MembershipTDto;
import com.qualitychemicals.qcipayments.transaction.model.MembershipT;

import java.util.Date;
import java.util.List;

public interface MembershipTService {
    MembershipT saveMembership(MembershipTDto membershipTDto);//wallet
    List<MembershipT> allByWallet(String wallet);
    List<MembershipT> last5ByWallet(String wallet);


    List<MembershipT> membershipTrans(String userName);//admin
    List<MembershipT> getAll();



    double totalMembership(Date date);

    List<DateSavingDto> dateMembership(Date dateFrom, Date dateTo);

    double totalMembership(Date dateFrom, Date dateTo);
}
