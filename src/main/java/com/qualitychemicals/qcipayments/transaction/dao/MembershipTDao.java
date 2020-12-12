package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.MembershipT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipTDao extends JpaRepository<MembershipT, Integer> {
    @Query("from MembershipT" )
    List<MembershipT> findByUserNameOrderByDateDesc(String userName);
}
