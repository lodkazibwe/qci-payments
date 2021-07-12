package com.qualitychemicals.qcipayments.transaction.converter;

import com.qualitychemicals.qcipayments.transaction.dto.SavingTDto;
import com.qualitychemicals.qcipayments.transaction.model.SavingT;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SavingTConverter {
    public SavingTDto entityToDto(SavingT savingT) {
        SavingTDto savingTDto = new SavingTDto();
        savingTDto.setDate(savingT.getDate());
        savingTDto.setAccountId(savingT.getAccountId());
        savingTDto.setAmount(savingT.getAmount());
        savingTDto.setId(savingT.getId());
        savingTDto.setUserName(savingT.getUserName());
        savingTDto.setStatus(savingT.getStatus());
        savingTDto.setAccount(savingT.getAccount());
        savingTDto.setNarrative(savingT.getNarrative());
        savingTDto.setWallet(savingT.getWallet());
        savingTDto.setCreationDateTime(savingT.getCreationDateTime());
        savingTDto.setTransactionType(savingT.getTransactionType());
        return savingTDto;

    }

    public SavingT dtoToEntity(SavingTDto savingTDto) {
        SavingT savingT = new SavingT();
        savingT.setDate(savingTDto.getDate());
        savingT.setAccountId(savingTDto.getAccountId());
        savingT.setAmount(savingTDto.getAmount());
        savingT.setUserName(savingTDto.getUserName());
        savingT.setStatus(savingTDto.getStatus());
        savingT.setNarrative(savingTDto.getNarrative());
        savingT.setAccount(savingTDto.getAccount());
        savingT.setWallet(savingTDto.getWallet());
        savingT.setTransactionType(savingTDto.getTransactionType());
        return savingT;

    }

    public List<SavingTDto> entityToDto(List<SavingT> savingTS) {
        return savingTS.stream().map(this::entityToDto).collect(Collectors.toList());

    }

    public List<SavingT> dtoToEntity(List<SavingTDto> savingTDtos) {
        return savingTDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
