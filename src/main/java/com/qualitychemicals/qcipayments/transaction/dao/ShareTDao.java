package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.ShareT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareTDao extends JpaRepository<ShareT, Integer> {

    List<ShareT> findByUserNameOrderByDateDesc(String userName);
}
