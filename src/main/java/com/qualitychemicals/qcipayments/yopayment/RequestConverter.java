package com.qualitychemicals.qcipayments.yopayment;

import com.qualitychemicals.qcipayments.security.appConfig.AppConfigReader;
import com.qualitychemicals.qcipayments.transaction.dto.TransactionDto;
import com.qualitychemicals.qcipayments.transaction.model.Transaction;
import com.qualitychemicals.qcipayments.yopayment.externalTransaction.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RequestConverter {
    @Autowired
    AppConfigReader appConfigReader;
    public Request transactionToRequest(TransactionDto transactionDto){
        Request request =new Request();
        request.setAccount("256"+transactionDto.getAccount());
        request.setAmount(transactionDto.getAmount());
        request.setApiPassword(appConfigReader.getPassword());
        request.setApiUserName(appConfigReader.getUserName());
        request.setMethod("method");
        request.setNarrative(transactionDto.getNarrative()+transactionDto.getAccount()+getrand());
        request.setNonBlocking("FALSE");
        //request.setProviderReferenceText(transactionDto.getNarrative());
        return request;
    }

    private String getrand() {
        Random random = new Random();
        String rand = String.format("%04d", random.nextInt(100));
        return  "_"+rand;
    }
}
