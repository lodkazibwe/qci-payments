package com.qualitychemicals.qcipayments.transaction.rest.v1;

import com.qualitychemicals.qcipayments.transaction.converter.LoanTConverter;
import com.qualitychemicals.qcipayments.transaction.converter.TransactionConverter;
import com.qualitychemicals.qcipayments.transaction.dto.*;
import com.qualitychemicals.qcipayments.transaction.model.TransactionType;
import com.qualitychemicals.qcipayments.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("transaction/")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    LoanTConverter loanTConverter;
    @Autowired
    TransactionConverter transactionConverter;

    @GetMapping("/loanTransactions/{loanId}")
    public ResponseEntity<LoanTransactionsDto> loanTransactions(@PathVariable int loanId){
              return  new ResponseEntity<>(new LoanTransactionsDto(loanTConverter.entityToDto
                (transactionService.loanTransactions(loanId))), HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<AllTransactions> allTransactions(){
        return  new ResponseEntity<>(new AllTransactions(transactionConverter.entityToDto
                (transactionService.allTransactions())), HttpStatus.OK);

    }
    @GetMapping("/getAll/{transactionType}")
    public ResponseEntity<AllTransactions> allByType(@PathVariable TransactionType transactionType){
        return  new ResponseEntity<>(new AllTransactions(transactionConverter.entityToDto
                (transactionService.allByType(transactionType))), HttpStatus.OK);

    }

    @GetMapping("/userTransactions/{userName}")
    public ResponseEntity<UserTransactionsDto> userTransactions(@PathVariable String userName){

        return  new ResponseEntity<>(new UserTransactionsDto(transactionConverter.entityToDto
                (transactionService.userTransactions(userName))), HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<TransactionDto> saveTransaction(@Valid @RequestBody TransactionDto transactionDto){
        return new ResponseEntity<>(transactionConverter.entityToDto(transactionService.saveTransaction(transactionDto)), HttpStatus.OK);
    }

    @PostMapping("/mobilePayment")
    public ResponseEntity<MobilePayment> transactMobile(@Valid @RequestBody MobilePayment mobilePayment){
        return new ResponseEntity<>(transactionService.transactMobile(mobilePayment), HttpStatus.OK);
    }

    /*@PutMapping("/admin/payrollRepayment")
    public ResponseEntity<List<TransactionDto>> payrollRepayment(@RequestBody DeductionScheduleDTO deductionSchedule){

        return null;
    }*/
}
