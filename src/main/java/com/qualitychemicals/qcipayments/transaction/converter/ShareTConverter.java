package com.qualitychemicals.qcipayments.transaction.converter;

import com.qualitychemicals.qcipayments.transaction.dto.ShareTDto;
import com.qualitychemicals.qcipayments.transaction.model.ShareT;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShareTConverter {

    public ShareTDto entityToDto(ShareT shareT){
        ShareTDto shareTDto=new ShareTDto();

        shareTDto.setDate(shareT.getDate());
        shareTDto.setShares(shareT.getShares());
        shareTDto.setUnitCost(shareT.getUnitCost());
        shareTDto.setAmount(shareT.getAmount());
        shareTDto.setStatus(shareT.getStatus());
        shareTDto.setUserName(shareT.getUserName());
        shareTDto.setId(shareT.getId());
        shareTDto.setTransactionType(shareT.getTransactionType());
        shareTDto.setAcctFrom(shareT.getAcctFrom());
        shareTDto.setAcctTo(shareT.getAcctTo());
        return shareTDto;

    }
    public ShareT dtoToEntity(ShareTDto shareTDto){
        ShareT shareT =new ShareT();
        shareT.setDate(shareTDto.getDate());
        shareT.setAmount(shareTDto.getAmount());
        shareT.setShares(shareTDto.getShares());
        shareT.setUnitCost(shareTDto.getUnitCost());
        shareT.setStatus(shareTDto.getStatus());
        shareT.setUserName(shareTDto.getUserName());
        shareT.setAcctFrom(shareTDto.getAcctFrom());
        shareT.setAcctTo(shareTDto.getAcctTo());
        shareT.setTransactionType(shareTDto.getTransactionType());
        return shareT;
    }

    public List<ShareTDto> entityToDto(List<ShareT> shareTS){
        return shareTS.stream().map(this::entityToDto).collect(Collectors.toList());

    }
    public List<ShareT> dtoToEntity(List<ShareTDto> shareTDtos){
        return shareTDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());

    }
}
