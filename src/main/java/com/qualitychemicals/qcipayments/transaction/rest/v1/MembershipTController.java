package com.qualitychemicals.qcipayments.transaction.rest.v1;

import com.qualitychemicals.qcipayments.transaction.converter.MembershipTConverter;
import com.qualitychemicals.qcipayments.transaction.dto.MembershipTDto;
import com.qualitychemicals.qcipayments.transaction.service.MembershipTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
