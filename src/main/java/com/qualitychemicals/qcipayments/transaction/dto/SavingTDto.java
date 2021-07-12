package com.qualitychemicals.qcipayments.transaction.dto;

import com.qualitychemicals.qcipayments.transaction.model.SavingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingTDto extends TransactionDto {
    @NotNull
    private int accountId;
}
