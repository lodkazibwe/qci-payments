package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.MembershipT;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MembershipTDao extends JpaRepository<MembershipT, Integer> {
    @Query("from MembershipT" )
    List<MembershipT> findByUserNameOrderByDateDesc(String userName);

    List<MembershipT> findByStatusAndAmountGreaterThanAndDate(TransactionStatus success, double v, Date date);

    List<MembershipT> findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual(TransactionStatus success, double v, Date dateTo, Date dateFrom);
}
