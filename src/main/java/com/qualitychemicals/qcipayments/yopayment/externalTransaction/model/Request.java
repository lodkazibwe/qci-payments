package com.qualitychemicals.qcipayments.yopayment.externalTransaction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Request {
    @Id
    @GeneratedValue
    private int id;
    @NotNull(message = "apiUserName cant be null")
    @JsonProperty("APIUsername")
    private String apiUserName;
    @NotNull(message = "apiPassword cant be null")
    @JsonProperty("APIPassword")
    private String apiPassword;
    @NotNull(message = "method cant be null")
    @JsonProperty("Method")
    private String method;//=acdepositfunds
    @JsonProperty("NonBlocking")
    private String nonBlocking; //false //true described in section 0.
    @JsonProperty("Amount")
    private double amount;
    @JsonProperty("Account")
    private String account;//256708252166==number
    @JsonProperty("Narrative")
    private String narrative;// eg. subscription fee
    @JsonProperty("ProviderReferenceText")
    private String providerReferenceText; //sms
    //@JsonProperty("InstantNotificationUrl")
    //private String instantNotificationUrl;
    //@JsonProperty("FailureNotificationUrl")
    //private String failureNotificationUrl;
    @JsonProperty("TransactionReference")
    private String transactionReference;
}
