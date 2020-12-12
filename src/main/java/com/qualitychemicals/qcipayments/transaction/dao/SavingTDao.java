package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.SavingT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingTDao extends JpaRepository<SavingT, Integer> {
    List<SavingT> findByUserNameOrderByDateDesc(String userName);
}
