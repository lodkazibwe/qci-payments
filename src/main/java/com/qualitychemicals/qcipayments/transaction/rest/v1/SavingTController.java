package com.qualitychemicals.qcipayments.transaction.rest.v1;

import com.qualitychemicals.qcipayments.transaction.converter.SavingTConverter;
import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.DateTransactions;
import com.qualitychemicals.qcipayments.transaction.dto.SavingTDto;
import com.qualitychemicals.qcipayments.transaction.dto.SavingsTransactionsDto;
import com.qualitychemicals.qcipayments.transaction.service.SavingTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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
    public ResponseEntity<SavingsTransactionsDto> savingTransactions(@PathVariable String userName){

        return  new ResponseEntity<>(new SavingsTransactionsDto(savingTConverter.entityToDto
                (savingTService.savingTransactions(userName))), HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<SavingsTransactionsDto> getAll(){

        return  new ResponseEntity<>(new SavingsTransactionsDto(savingTConverter.entityToDto
                (savingTService.getAll())), HttpStatus.OK);

    }

    @GetMapping("/withdrawRequests")
    public ResponseEntity<SavingsTransactionsDto> withdrawRequests(){

        return  new ResponseEntity<>(new SavingsTransactionsDto(savingTConverter.entityToDto
                (savingTService.withdrawRequests())), HttpStatus.OK);

    }

    @GetMapping("/totalSaving/{date}")
    public ResponseEntity<Double> totalSaving(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return new ResponseEntity<>(savingTService.totalSaving(date), HttpStatus.OK);
    }

    @GetMapping("/dateSaving/{dateFrom}/{dateTo}")
    public ResponseEntity<DateTransactions> dateSaving
            (@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
             @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo){
        List<DateSavingDto> dateSavingDtos=savingTService.dateSaving(dateFrom, dateTo);

        return new ResponseEntity<>(new DateTransactions(dateSavingDtos), HttpStatus.OK);

    }

    @GetMapping("/totalSaving/{dateFrom}/{dateTo}")
    public ResponseEntity<Double> totalSaving(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
                                              @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo){
        return new ResponseEntity<>(savingTService.totalSaving(dateFrom, dateTo), HttpStatus.OK);
    }

}
