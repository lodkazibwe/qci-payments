package com.qualitychemicals.qcipayments.transaction.rest.v1;

import com.qualitychemicals.qcipayments.transaction.converter.ShareTConverter;
import com.qualitychemicals.qcipayments.transaction.dto.SavingsTransactionsDto;
import com.qualitychemicals.qcipayments.transaction.dto.ShareTDto;
import com.qualitychemicals.qcipayments.transaction.dto.SharesTransactionsDto;
import com.qualitychemicals.qcipayments.transaction.service.ShareTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("transaction/shares")
public class ShareTController {
    @Autowired
    ShareTService shareTService;
    @Autowired ShareTConverter shareTConverter;

    @PostMapping("/save")
    public ResponseEntity<ShareTDto> BuyMobile(@Valid @RequestBody ShareTDto shareTDto){
        return new ResponseEntity<>(shareTConverter.entityToDto(shareTService.mobileShares(shareTDto)), HttpStatus.OK);
    }

    @GetMapping("/shareTransactions/{userName}")
    public ResponseEntity<SharesTransactionsDto> loanTransactions(@PathVariable String userName){

        return  new ResponseEntity<>(new SharesTransactionsDto(shareTConverter.entityToDto
                (shareTService.loanTransactions(userName))), HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<SharesTransactionsDto> getAll(){

        return  new ResponseEntity<>(new SharesTransactionsDto(shareTConverter.entityToDto
                (shareTService.getAll())), HttpStatus.OK);

    }
}
