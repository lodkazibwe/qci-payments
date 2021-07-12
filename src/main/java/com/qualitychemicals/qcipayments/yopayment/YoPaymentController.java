package com.qualitychemicals.qcipayments.yopayment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qualitychemicals.qcipayments.transaction.converter.TransactionConverter;
import com.qualitychemicals.qcipayments.transaction.dto.AllTransactions;
import com.qualitychemicals.qcipayments.transaction.dto.TransactionDto;
import com.qualitychemicals.qcipayments.yopayment.externalTransaction.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/yo")
public class YoPaymentController {
    @Autowired YoPaymentService yoPaymentService;
    @Autowired
    TransactionConverter transactionConverter;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@Valid @RequestBody TransactionDto transactionDto) throws IOException {
        yoPaymentService.deposit(transactionDto);
        return new ResponseEntity<>("transaction initiated", HttpStatus.OK);
    }

    @GetMapping("/refresh/{walletRef}")
    public ResponseEntity<AllTransactions> refresh(@PathVariable String walletRef) throws IOException {
        AllTransactions deposits =new AllTransactions();
        deposits.setTransactions(transactionConverter.entityToDto(yoPaymentService.refreshDeposit(walletRef)));
        return new ResponseEntity<>(deposits, HttpStatus.OK);
    }


    @GetMapping("/get")
    public ResponseEntity<Request> getTransaction() throws JsonProcessingException {
        return new ResponseEntity<>(yoPaymentService.getTransaction(), HttpStatus.OK);
    }

}
