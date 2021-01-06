package com.qualitychemicals.qcipayments.transaction.rest.v1;

import com.qualitychemicals.qcipayments.transaction.converter.MembershipTConverter;
import com.qualitychemicals.qcipayments.transaction.dto.DateSavingDto;
import com.qualitychemicals.qcipayments.transaction.dto.DateTransactions;
import com.qualitychemicals.qcipayments.transaction.dto.MembershipTDto;
import com.qualitychemicals.qcipayments.transaction.dto.MembershipTransactionsDto;
import com.qualitychemicals.qcipayments.transaction.service.MembershipTService;
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
@RequestMapping("/transaction/membership")
public class MembershipTController {
    @Autowired
    MembershipTService membershipTService;
    @Autowired MembershipTConverter membershipTConverter;

    @PostMapping("/save")
    public ResponseEntity<MembershipTDto> saveMembership(@Valid @RequestBody MembershipTDto membershipTDto){
        return new ResponseEntity<>(membershipTConverter.entityToDto(membershipTService.saveMembership(membershipTDto)), HttpStatus.OK);
    }

    @GetMapping("/membershipTrans/{userName}")
    public ResponseEntity<MembershipTransactionsDto> membershipTrans(@PathVariable String userName){

        return  new ResponseEntity<>(new MembershipTransactionsDto(membershipTConverter.entityToDto
                (membershipTService.membershipTrans(userName))), HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<MembershipTransactionsDto> getAll(){

        return  new ResponseEntity<>(new MembershipTransactionsDto(membershipTConverter.entityToDto
                (membershipTService.getAll())), HttpStatus.OK);

    }

    @GetMapping("/totalMembership/{date}")
    public ResponseEntity<Double> totalMembership(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return new ResponseEntity<>(membershipTService.totalMembership(date), HttpStatus.OK);
    }

    @GetMapping("/dateMembership/{dateFrom}/{dateTo}")
    public ResponseEntity<DateTransactions> dateMembership
            (@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
             @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo){

        List<DateSavingDto> dateMemberships=membershipTService.dateMembership(dateFrom, dateTo);
        return new ResponseEntity<>(new DateTransactions(dateMemberships), HttpStatus.OK);

    }

    @GetMapping("/totalMembership/{dateFrom}/{dateTo}")
    public ResponseEntity<Double> totalMembership(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
                                              @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo){
        return new ResponseEntity<>(membershipTService.totalMembership(dateFrom, dateTo), HttpStatus.OK);
    }

}
