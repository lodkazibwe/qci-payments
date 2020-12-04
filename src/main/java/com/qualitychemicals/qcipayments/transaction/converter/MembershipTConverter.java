package com.qualitychemicals.qcipayments.transaction.converter;

import com.qualitychemicals.qcipayments.transaction.dto.MembershipTDto;
import com.qualitychemicals.qcipayments.transaction.model.MembershipT;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MembershipTConverter {
    public MembershipTDto entityToDto(MembershipT membershipT){
        MembershipTDto membershipTDto =new MembershipTDto();
        membershipTDto.setDate(membershipT.getDate());
        membershipTDto.setYear(membershipT.getYear());
        membershipTDto.setAmount(membershipT.getAmount());
        membershipTDto.setStatus(membershipT.getStatus());
        membershipTDto.setId(membershipT.getId());
        membershipTDto.setUserName(membershipT.getUserName());
        membershipTDto.setTransactionType(membershipT.getTransactionType());
        membershipTDto.setAcctFrom(membershipT.getAcctFrom());
        membershipTDto.setAcctTo(membershipT.getAcctTo());
        return membershipTDto;

    }
    public MembershipT dtoToEntity(MembershipTDto membershipTDto){
        MembershipT membershipT =new MembershipT();
        membershipT.setStatus(membershipTDto.getStatus());
        membershipT.setYear(membershipTDto.getYear());
        membershipT.setAmount(membershipTDto.getAmount());
        membershipT.setDate(membershipTDto.getDate());
        membershipT.setUserName(membershipTDto.getUserName());
        membershipT.setTransactionType(membershipTDto.getTransactionType());
        membershipT.setAcctFrom(membershipTDto.getAcctFrom());
        membershipT.setAcctTo(membershipTDto.getAcctTo());
        return membershipT;

    }
    public List<MembershipTDto> entityToDto(List<MembershipT> membershipTS){
        return membershipTS.stream().map(this::entityToDto).collect(Collectors.toList());

    }
    public List<MembershipT> dtoToEntity(List<MembershipTDto> memberships){
        return memberships.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
