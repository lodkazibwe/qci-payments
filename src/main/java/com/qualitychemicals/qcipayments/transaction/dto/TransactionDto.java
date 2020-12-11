package com.qualitychemicals.qcipayments.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qualitychemicals.qcipayments.transaction.model.TransactionCat;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import com.qualitychemicals.qcipayments.transaction.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date date;
    @NotNull(message ="amount cannot be Blank")
    private double amount;
    @NotNull(message ="acctFrom cannot be Blank")
    private String acctFrom;
    @NotNull (message ="acctTo cannot be Blank")
    private String acctTo;
    @NotNull (message ="userName cannot be Blank")
    private String userName;
    private TransactionCat category;
    private TransactionType transactionType;
    private TransactionStatus status;


}
