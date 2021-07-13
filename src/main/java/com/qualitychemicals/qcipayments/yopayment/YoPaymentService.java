package com.qualitychemicals.qcipayments.yopayment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.qualitychemicals.qcipayments.exceptions.ResourceNotFoundException;
import com.qualitychemicals.qcipayments.security.appConfig.AppConfigReader;
import com.qualitychemicals.qcipayments.transaction.dto.TransactionDto;
import com.qualitychemicals.qcipayments.transaction.model.Transaction;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import com.qualitychemicals.qcipayments.transaction.service.TransactionService;
import com.qualitychemicals.qcipayments.yopayment.externalTransaction.ExternalTransactionService;
import com.qualitychemicals.qcipayments.yopayment.externalTransaction.model.ExternalTransaction;
import com.qualitychemicals.qcipayments.yopayment.externalTransaction.model.Request;
import com.qualitychemicals.qcipayments.yopayment.externalTransaction.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class YoPaymentService {
    @Autowired RestTemplate restTemplate;
    @Autowired
    RequestConverter requestConverter;
    @Autowired
    ExternalTransactionService externalTransactionService;
    @Autowired TransactionService transactionService;
    @Autowired
    AppConfigReader appConfigReader;

    private final Logger logger = LoggerFactory.getLogger(YoPaymentService.class);

    @Transactional
    public String deposit(TransactionDto transactionDto) throws IOException {
        Request request =requestConverter.transactionToRequest(transactionDto);
        request.setMethod("acdepositfunds");
        request.setNonBlocking("TRUE");
        logger.info("transacting with yo payments...");
        Response response =yoRequest(request);

        logger.info("saving external transaction...");
        ExternalTransaction externalTransaction =new ExternalTransaction();
        externalTransaction.setWallet(transactionDto.getWallet());
        externalTransaction.setDate(transactionDto.getCreationDateTime());
        request.setApiPassword("pass");
        request.setApiUserName("admin");
        externalTransaction.setUserName(transactionDto.getUserName());
        externalTransaction.setWallet(transactionDto.getWallet());
        externalTransaction.setTransactionRequest(request);
        externalTransaction.setTransactionResponse(response);
        externalTransaction.setStatus(response.getTransactionStatus());
        logger.info("saving externalTransaction...");
        externalTransactionService.save(externalTransaction);
        if(response.getStatusMessage() == null){
            logger.info("no message");
            return ": ";
        }
        return response.getStatusMessage();

    }

    @Transactional
    public List<Transaction> refreshDeposit(String wallet) throws IOException {
        List<ExternalTransaction> externalTransactions
                =externalTransactionService.getByWalletAndStatus(wallet, "PENDING");
        List<Transaction> successfulTransactions =new ArrayList<>();
       for(ExternalTransaction externalTransaction: externalTransactions){
           Request request=externalTransaction.getTransactionRequest();
           request.setApiUserName(appConfigReader.getUserName());
           request.setApiPassword(appConfigReader.getPassword());
           request.setMethod("actransactioncheckstatus");
           request.setTransactionReference(externalTransaction.getTransactionResponse().getTransactionReference());
           logger.info("checking transaction with yo payment...");
           Response newResponse =yoRequest(request);
           externalTransaction.setTransactionResponse(newResponse);
           Transaction transaction =generateTransaction(externalTransaction);
           assert newResponse != null;
           if(newResponse.getTransactionStatus().equals("SUCCEEDED")){
               logger.info("transaction successful...");
               transaction.setStatus(TransactionStatus.SUCCESS);
               transaction.setTransactionType("deposit");
               transaction.setNarrative("deposit from "+transaction.getNarrative());
               transaction.setAmount(transaction.getAmount()*-1);
               Transaction savedTransaction =transactionService.addTransaction(transaction);
               successfulTransactions.add(savedTransaction);
           }
           externalTransactionService.save(externalTransaction);

       }
       return successfulTransactions;
    }
    private Transaction generateTransaction(ExternalTransaction externalTransaction){
        logger.info("generating transaction ...");
        Transaction transaction =new Transaction();
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setNarrative(externalTransaction.getTransactionRequest().getNarrative());
        transaction.setAccount(externalTransaction.getTransactionRequest().getAccount());
        transaction.setAmount(externalTransaction.getTransactionRequest().getAmount());
        transaction.setDate(externalTransaction.getDate());
        transaction.setUserName(externalTransaction.getUserName());
        transaction.setCreationDateTime(externalTransaction.getDate());
        transaction.setWallet(externalTransaction.getWallet());

        return transaction;

    }


    private Response yoRequest(Request yoTransaction) throws IOException {
        logger.info("connecting to yo api...");
        final String uri = "https://paymentsapi1.yo.co.ug/ybs/task.php";
        //https://paymentsapi2.yo.co.ug/ybs/task.php
        //http://localhost:8080/transaction/transact/SUCCESS
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml; charset=utf-8");
        XmlMapper xmlMapper = new XmlMapper();
        String file = xmlMapper.writeValueAsString(yoTransaction);

        String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><AutoCreate>"+file+"</AutoCreate>";
        HttpEntity<String> request = new HttpEntity<>(xml, headers);

        logger.info("connecting...");
        try {
            ResponseEntity<String> response =restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
            HttpStatus httpStatus = response.getStatusCode();
            logger.info("yo service status..." + httpStatus);
            logger.error(response.getBody());
            XmlMapper mapper = new XmlMapper();
            //return response.getBody();
            return mapper.readValue(response.getBody().substring(50), Response.class);

        }
        catch (RestClientException | JsonProcessingException e) {
            logger.info("yo service error...");
            throw new ResourceNotFoundException("external Service Error YO!");
        }

    }

    public Request getTransaction() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<Request> httpRequest = new HttpEntity<>(headers);

        logger.info("connecting to yo api...");
        final String uri = "http://localhost:8080/transaction/get";
        ResponseEntity<String> response=
               restTemplate.exchange(uri, HttpMethod.GET, httpRequest, String.class);

        XmlMapper mapper = new XmlMapper();
        //assertTrue(request.getX() == 1 && request.getY() == 2);
        return mapper.readValue(response.getBody(), Request.class);


    }


}
