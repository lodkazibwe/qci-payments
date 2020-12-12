package com.qualitychemicals.qcipayments.transaction.rest.v1;

import com.qualitychemicals.qcipayments.transaction.converter.SavingTConverter;
import com.qualitychemicals.qcipayments.transaction.dto.LoanTransactionsDto;
import com.qualitychemicals.qcipayments.transaction.dto.SavingTDto;
import com.qualitychemicals.qcipayments.transaction.dto.SavingsTransactionsDto;
import com.qualitychemicals.qcipayments.transaction.service.SavingTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/transaction/saving")
public class SavingTController {
    @Autowired
    SavingTService savingTService;
    @Autowired SavingTConverter savingTConverter;

    @PostMapping("/save")
    public ResponseEntity<SavingTDto> addSaving(@Valid @RequestBody SavingTDto savingTDto){
        return new ResponseEntity<>(savingTConverter.entityToDto(savingTService.mobileSaving(savingTDto)), HttpStatus.OK);

    }

    @GetMapping("/savingTransactions/{userName}")
    public ResponseEntity<SavingsTransactionsDto> loanTransactions(@PathVariable String userName){

        return  new ResponseEntity<>(new SavingsTransactionsDto(savingTConverter.entityToDto
                (savingTService.loanTransactions(userName))), HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<SavingsTransactionsDto> getAll(){

        return  new ResponseEntity<>(new SavingsTransactionsDto(savingTConverter.entityToDto
                (savingTService.getAll())), HttpStatus.OK);

    }

}
