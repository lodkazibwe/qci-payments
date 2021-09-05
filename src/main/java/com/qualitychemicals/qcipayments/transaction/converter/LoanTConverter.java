package com.qualitychemicals.qcipayments.transaction.converter;

import com.qualitychemicals.qcipayments.transaction.dto.LoanTDto;
import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanTConverter {

    public LoanTDto entityToDto(LoanT loanT) {
        LoanTDto loanTDto = new LoanTDto();
        loanTDto.setAccount(loanT.getAccount());
        loanTDto.setAmount(loanT.getAmount());
        loanTDto.setUserName(loanT.getUserName());
        loanTDto.setCreationDateTime(loanT.getCreationDateTime());
        loanTDto.setNarrative(loanT.getNarrative());
        loanTDto.setDate(loanT.getDate());
        loanTDto.setId(loanT.getId());
        loanTDto.setWallet(loanT.getWallet());
        loanTDto.setStatus(loanT.getStatus());
        loanTDto.setLoanId(loanT.getLoanId());
        loanTDto.setTransactionType(loanT.getTransactionType());
        //loanTDto.setLoanRef(loanT.getLoanRef());
        loanTDto.setCreationDateTime(loanT.getCreationDateTime());
        return loanTDto;

    }

    public LoanT dtoToEntity(LoanTDto loanTDto) {
        LoanT loanT = new LoanT();
        loanT.setAccount(loanTDto.getAccount());
        loanT.setAmount(loanTDto.getAmount());
        loanT.setDate(new Date());
        loanT.setStatus(loanTDto.getStatus());
        loanT.setUserName(loanTDto.getUserName());
        loanT.setLoanId(loanTDto.getLoanId());
        loanT.setWallet(loanTDto.getWallet());
        loanT.setNarrative(loanTDto.getNarrative());
        loanT.setTransactionType(loanTDto.getTransactionType());
        //loanT.setLoanRef(loanTDto.getLoanRef());
        loanT.setCreationDateTime(new Date());
        return loanT;

    }

    public List<LoanTDto> entityToDto(List<LoanT> loanTS) {
        return loanTS.stream().map(this::entityToDto).collect(Collectors.toList());

    }

    public List<LoanT> dtoToEntity(List<LoanTDto> loanTDtos) {
        return loanTDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
