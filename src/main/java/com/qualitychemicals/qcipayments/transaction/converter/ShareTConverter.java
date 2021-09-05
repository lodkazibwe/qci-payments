package com.qualitychemicals.qcipayments.transaction.converter;

import com.qualitychemicals.qcipayments.transaction.dto.ShareTDto;
import com.qualitychemicals.qcipayments.transaction.model.ShareT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShareTConverter {

    public ShareTDto entityToDto(ShareT shareT) {
        ShareTDto shareTDto = new ShareTDto();

        shareTDto.setDate(shareT.getDate());
        shareTDto.setShares(shareT.getShares());
        shareTDto.setShareValue(shareT.getShareValue());
        shareTDto.setAmount(shareT.getAmount());
        shareTDto.setStatus(shareT.getStatus());
        shareTDto.setUserName(shareT.getUserName());
        shareTDto.setId(shareT.getId());
        shareTDto.setCreationDateTime(shareT.getCreationDateTime());
        shareTDto.setAccount(shareT.getAccount());
        shareTDto.setWallet(shareT.getWallet());
        shareTDto.setNarrative(shareT.getNarrative());
        shareTDto.setTransactionType(shareT.getTransactionType());
        return shareTDto;

    }

    public ShareT dtoToEntity(ShareTDto shareTDto) {
        ShareT shareT = new ShareT();
        shareT.setDate(new Date());
        shareT.setAmount(shareTDto.getAmount());
        shareT.setShares(shareTDto.getShares());
        shareT.setNarrative(shareTDto.getNarrative());
        shareT.setStatus(shareTDto.getStatus());
        shareT.setWallet(shareTDto.getWallet());
        shareT.setUserName(shareTDto.getUserName());
        shareT.setAccount(shareTDto.getAccount());
        shareT.setShareValue(shareTDto.getShareValue());
        shareT.setTransactionType(shareTDto.getTransactionType());
        shareT.setCreationDateTime(new Date());
        return shareT;
    }

    public List<ShareTDto> entityToDto(List<ShareT> shareTS) {
        return shareTS.stream().map(this::entityToDto).collect(Collectors.toList());

    }

    public List<ShareT> dtoToEntity(List<ShareTDto> shareTDtos) {
        return shareTDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());

    }
}
