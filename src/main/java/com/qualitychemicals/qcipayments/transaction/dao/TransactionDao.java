package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.Transaction;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByUserNameOrderByDateDesc(String userName);

    List<Transaction> findByWalletOrderByCreationDateTimeDesc(String wallet);

    List<Transaction> findFirst5ByWalletOrderByCreationDateTimeDesc(String wallet);



    List<Transaction> findByTransactionType(String transactionType);

    List<Transaction> findByAmountLessThanAndWalletAndStatus(double amount, String wallet, TransactionStatus status);
}
