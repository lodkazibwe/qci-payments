package com.qualitychemicals.qcipayments.yopayment.externalTransaction.dao;

import com.qualitychemicals.qcipayments.yopayment.externalTransaction.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseDao extends JpaRepository<Response, Integer> {
}
