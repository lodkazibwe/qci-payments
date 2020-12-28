package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.LoanT;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanTDao extends JpaRepository<LoanT, Integer> {
    List<LoanT> findByUserNameOrderByDateDesc(String userName);

    List<LoanT> findByStatusAndAmountGreaterThanAndDate(TransactionStatus success, double v, Date date);

    List<LoanT> findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual(TransactionStatus success, double v, Date dateTo, Date dateFrom);
}
