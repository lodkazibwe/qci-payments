package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanTDao extends JpaRepository<LoanT, Integer> {
    List<LoanT> findByUserNameOrderByDateDesc(String userName);
}
