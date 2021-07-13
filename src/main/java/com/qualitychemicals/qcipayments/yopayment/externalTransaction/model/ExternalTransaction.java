package com.qualitychemicals.qcipayments.yopayment.externalTransaction.model;

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
public class ExternalTransaction {
    @Id
    @GeneratedValue
    private int id;
    private String status;
    private String wallet;
    private String userName;
    @JsonFormat(shape = JsonFormat.Shape.STRING )
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToOne(targetEntity = Request.class, fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn()
    private Request transactionRequest;
    @OneToOne(targetEntity = Response.class, fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn()
    private Response transactionResponse;
}
