package com.qualitychemicals.qcipayments.transaction.rest.v1;

import com.qualitychemicals.qcipayments.transaction.converter.ShareTConverter;
import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.DateTransactions;
import com.qualitychemicals.qcipayments.transaction.dto.ShareTDto;
import com.qualitychemicals.qcipayments.transaction.dto.SharesTransactionsDto;
import com.qualitychemicals.qcipayments.transaction.service.ShareTService;
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
    public ResponseEntity<SharesTransactionsDto> shareTransactions(@PathVariable String userName){

        return  new ResponseEntity<>(new SharesTransactionsDto(shareTConverter.entityToDto
                (shareTService.shareTransactions(userName))), HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<SharesTransactionsDto> getAll(){

        return  new ResponseEntity<>(new SharesTransactionsDto(shareTConverter.entityToDto
                (shareTService.getAll())), HttpStatus.OK);

    }

    @GetMapping("/totalShares/{date}")
    public ResponseEntity<Double> totalShares(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return new ResponseEntity<>(shareTService.totalShares(date), HttpStatus.OK);
    }

    @GetMapping("/dateShares/{dateFrom}/{dateTo}")
    public ResponseEntity<DateTransactions> dateShares
            (@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
             @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo){

        List<DateSavingDto> dateShares=shareTService.dateShares(dateFrom, dateTo);
        return new ResponseEntity<>(new DateTransactions(dateShares), HttpStatus.OK);

    }

    @GetMapping("/totalShares/{dateFrom}/{dateTo}")
    public ResponseEntity<Double> totalShares(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
                                              @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo){
        return new ResponseEntity<>(shareTService.totalShares(dateFrom, dateTo), HttpStatus.OK);
    }

}
