package com.qualitychemicals.qcipayments.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateSavingDto {
    private Date date;
    private double amount;
}
