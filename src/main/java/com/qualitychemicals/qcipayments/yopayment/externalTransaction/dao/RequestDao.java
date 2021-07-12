package com.qualitychemicals.qcipayments.yopayment.externalTransaction.dao;

import com.qualitychemicals.qcipayments.yopayment.externalTransaction.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDao extends JpaRepository<Request, Integer> {

}
