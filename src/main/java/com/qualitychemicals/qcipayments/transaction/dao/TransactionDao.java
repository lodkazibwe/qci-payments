package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.Transaction;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import com.qualitychemicals.qcipayments.transaction.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByUserNameOrderByDateDesc(String userName);

    List<Transaction> findByWalletOrderByIdDesc(String wallet);

    List<Transaction> findFirst5ByWalletOrderByIdDesc(String wallet);

    List<Transaction>findByDate(Date date);

    List<Transaction>findByDateGreaterThanEqualAndDateLessThanEqual(Date date1, Date date2);


    List<Transaction> findByTransactionType(TransactionType transactionType);

    List<Transaction> findByAmountLessThanAndWalletAndStatus(double amount, String wallet, TransactionStatus status);

}
