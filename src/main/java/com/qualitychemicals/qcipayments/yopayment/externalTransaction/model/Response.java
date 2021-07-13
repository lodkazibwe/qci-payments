package com.qualitychemicals.qcipayments.yopayment.externalTransaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    @Id
    @GeneratedValue
    private int id;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("StatusCode")
    private int statusCode;
    @JsonProperty("TransactionStatus")
    private String transactionStatus;
    @JsonProperty("TransactionReference")
    private String transactionReference;
    @JsonProperty("MNOTransactionReferenceId")
    private String  mnoTransactionReferenceId;
    @JsonProperty("IssuedReceiptNumber")
    private String issuedReceiptNumber;
    @JsonProperty("StatusMessage")
    @Column(length = 900)
    private String statusMessage;
    @JsonProperty("ErrorMessageCode")
    private String errorMessageCode;
    @JsonProperty("ErrorMessage")
    @Column(length = 900)
    private String errorMessage;

}
