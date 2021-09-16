package com.qualitychemicals.qcipayments.transaction.converter;

import com.qualitychemicals.qcipayments.transaction.dto.TransactionDto;
import com.qualitychemicals.qcipayments.transaction.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {
    public TransactionDto entityToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setId(transaction.getId());
        transactionDto.setStatus(transaction.getStatus());
        transactionDto.setUserName(transaction.getUserName());
        transactionDto.setAccount(transaction.getAccount());
        transactionDto.setWallet(transaction.getWallet());
        transactionDto.setNarrative(transaction.getNarrative());
        transactionDto.setCreationDateTime(transaction.getCreationDateTime());
        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setClassification(transaction.getClassification());
        return transactionDto;

    }

    public Transaction dtoToEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDate(new Date());
        transaction.setStatus(transactionDto.getStatus());
        transaction.setAccount(transactionDto.getAccount());
        transaction.setNarrative(transactionDto.getNarrative());
        transaction.setUserName(transactionDto.getUserName());
        transaction.setCreationDateTime(new Date());
        transaction.setWallet(transactionDto.getWallet());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setClassification(transactionDto.getClassification());
        return transaction;

    }

    public List<TransactionDto> entityToDto(List<Transaction> transactions) {
        return transactions.stream().map(this::entityToDto).collect(Collectors.toList());

    }

    public List<Transaction> dtoToEntity(List<TransactionDto> transactions) {
        return transactions.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
