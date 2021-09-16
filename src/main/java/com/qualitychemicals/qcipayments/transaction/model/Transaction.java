package com.qualitychemicals.qcipayments.transaction.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", locale = "pt-BR", timezone = "EAT")
    private Date date;
    private double amount;
    private String account;
    private String wallet;
    private String userName;
    private String narrative;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "EAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    private TransactionType transactionType;
    private String classification;
    //private int externalId;

   // @Enumerated(EnumType.STRING)
   // private TransactionType transactionType;


}
