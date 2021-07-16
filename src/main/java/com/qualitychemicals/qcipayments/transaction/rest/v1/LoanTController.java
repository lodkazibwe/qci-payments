package com.qualitychemicals.qcipayments.transaction.rest.v1;

import com.qualitychemicals.qcipayments.transaction.converter.LoanTConverter;
import com.qualitychemicals.qcipayments.transaction.dto.*;
import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import com.qualitychemicals.qcipayments.transaction.service.LoanTService;
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
@RequestMapping("/transaction/loan")
public class LoanTController {
    @Autowired
    LoanTService loanTService;
    @Autowired
    LoanTConverter loanTConverter;

    @PostMapping("/saveLoanT")//admin
    public ResponseEntity<LoanTDto> saveLoanT(@Valid @RequestBody LoanTDto loanTDto) {
        LoanT loanT = loanTService.saveLoanT(loanTDto);
        return new ResponseEntity<>(loanTConverter.entityToDto(loanT), HttpStatus.OK);
    }


    @GetMapping("/allByWallet/{wallet}/{loanRef}")
    public ResponseEntity<LoanTransactionsDto> allByWallet(@PathVariable String wallet, @PathVariable String loanRef) {

        return new ResponseEntity<>(new LoanTransactionsDto(loanTConverter.entityToDto
                (loanTService.allByWallet(wallet, loanRef))), HttpStatus.OK);

    }
    @GetMapping("/allByLoanRef/{loanRef}")
    public ResponseEntity<LoanTransactionsDto> allByLoanRef(@PathVariable String loanRef) {

        return new ResponseEntity<>(new LoanTransactionsDto(loanTConverter.entityToDto
                (loanTService.allByLoan(loanRef))), HttpStatus.OK);

    }

    @GetMapping("/loanTransactions/{userName}")//admin
    public ResponseEntity<LoanTransactionsDto> loanTransactions(@PathVariable String userName) {

        return new ResponseEntity<>(new LoanTransactionsDto(loanTConverter.entityToDto
                (loanTService.loanTransactions(userName))), HttpStatus.OK);

    }

    @GetMapping("/getAll/{userName}")//admin
    public ResponseEntity<LoanTransactionsDto> getAll(@PathVariable String userName) {

        return new ResponseEntity<>(new LoanTransactionsDto(loanTConverter.entityToDto
                (loanTService.getAll(userName))), HttpStatus.OK);
    }

    @GetMapping("/totalLoanPayments/{date}")
    public ResponseEntity<Double> totalLoanPayments(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return new ResponseEntity<>(loanTService.totalLoanPayment(date), HttpStatus.OK);
    }

    @GetMapping("/dateLoanPayments/{dateFrom}/{dateTo}")
    public ResponseEntity<DateTransactions> dateLoanPayments
            (@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
             @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {

        List<DateSavingDto> dateLoans = loanTService.dateLoanPayment(dateFrom, dateTo);
        return new ResponseEntity<>(new DateTransactions(dateLoans), HttpStatus.OK);

    }

    @GetMapping("/totalLoanPayments/{dateFrom}/{dateTo}")
    public ResponseEntity<Double> totalLoanPayments(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
                                                    @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {
        return new ResponseEntity<>(loanTService.totalLoanPayment(dateFrom, dateTo), HttpStatus.OK);
    }
}
