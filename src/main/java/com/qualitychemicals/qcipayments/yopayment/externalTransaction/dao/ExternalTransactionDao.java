package com.qualitychemicals.qcipayments.yopayment.externalTransaction.dao;

import com.qualitychemicals.qcipayments.yopayment.externalTransaction.model.ExternalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalTransactionDao extends JpaRepository<ExternalTransaction, Integer> {

    List<ExternalTransaction> findByWalletAndStatus(String wallet, String status);

}
